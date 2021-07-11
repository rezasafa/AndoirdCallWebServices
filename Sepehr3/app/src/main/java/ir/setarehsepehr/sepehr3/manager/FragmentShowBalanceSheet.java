package ir.setarehsepehr.sepehr3.manager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShowBalanceSheet extends Fragment {
    public static String rslt;
    int Levels;
    int CurrentLevel;
    int LevelLen;
    ListView lvList;
    ArrayList<List_BalanceSheet> li;

    public FragmentShowBalanceSheet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getArguments() != null){
            Levels = getArguments().getInt("LevCount");
            CurrentLevel = getArguments().getInt("CLev");
            LevelLen = getArguments().getInt("LevLen");
        }

        return inflater.inflate(R.layout.fragment_show_balance_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getResources().getString(R.string.sBalanceSheetName));

        new AsyncTask<Void,Void,Void>(){
            ProgressDialog asyncDialog = new ProgressDialog(getContext());

            @Override
            final protected void onPreExecute() {
                //set message of the dialog
                asyncDialog.setTitle(getString(R.string.sWaitePLZ));
                asyncDialog.setMessage(getString(R.string.sProccess));
                //show dialog
                asyncDialog.show();
                super.onPreExecute();
            }

            @Override
            final protected Void doInBackground(Void... arg0) {

                try {
                    rslt = "START";
                    final caller_BalaceSheet c = new caller_BalaceSheet();
                    c.sCurrentLevel = String.valueOf(CurrentLevel);
                    c.sHiarchical = "false";
                    c.sLevelLen = String.valueOf(LevelLen);
                    c.sLevels = String.valueOf(Levels);
                    c.con = getContext();
                    c.sCoName = new dbApp(getContext()).Read_Setting_Field("Co_Name");
                    c.sYear = new dbApp(getContext()).Read_Setting_Field("Year");

                    c.join();
                    c.start();

                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                        }
                    }

                    String sjson = "{ Records : " +  rslt + "}";
                    JSONObject json = new JSONObject(sjson);

                    JSONArray jArray = json.getJSONArray("Records");

                    final ArrayList<List_BalanceSheet> l = new ArrayList<List_BalanceSheet>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        l.add(new List_BalanceSheet(
                                jo.getString("Acc_No"),
                                jo.getString("AccNo"),
                                jo.getString("Acc_Name"),
                                jo.getString("Tafsil"),
                                jo.getString("TafsilNo"),
                                jo.getString("FirstRemDebit"),
                                jo.getString("FirstRemCredit"),
                                jo.getString("SumDebit"),
                                jo.getString("SumCredit"),
                                jo.getString("RemDebit"),
                                jo.getString("RemCredit"),
                                jo.getString("FirstRemDebitSumDebit"),
                                jo.getString("FirstRemCreditSumCredit"),
                                jo.getString("RemDebitFinal"),
                                jo.getString("RemCreditFinal"),
                                jo.getString("SumQty")
                        ));
                    };
                    li = l;
                }catch (Exception ex){
                    Toast.makeText(
                            getContext(),
                            ex.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }


                return null;
            }

            @Override
            final protected void onPostExecute(Void result) {
                //hide the dialog
                asyncDialog.dismiss();
                if(li.size() > 0)
                    fill_Records(li);
                super.onPostExecute(result);
            }
        }.execute();

    }

    void fill_Records(final ArrayList<List_BalanceSheet> list){
        try{
            final ListView lv = (ListView) getView().findViewById(R.id.fsbs_List);
            //lv.setTextFilterEnabled(true);
            final adapter_Balance_Sheet adapter;

            adapter = new adapter_Balance_Sheet(getContext(), list);
            lv.setAdapter(adapter);

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                public boolean onItemLongClick(final AdapterView<?> arg0, View arg1, int pos, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(getString(R.string.sAcc_Name));

                    // Set up the input
                    final EditText input = new EditText(getContext());
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    // Set up the buttons
                    builder.setPositiveButton(getString(R.string.msgOKButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int count = list.size();
                            for(int i = 0; i < count; i++){
                                if(list.get(i).Get_Acc_Name().toLowerCase().contains(input.getText().toString())){
                                    /*
                                    try{
                                        lv.getChildAt(1).setBackgroundColor(Color.CYAN);
                                    }catch (Exception ex){
                                        Toast.makeText(
                                                getContext(),
                                                ex.getMessage(),
                                                Toast.LENGTH_LONG
                                        ).show();
                                    }
                                    */
                                    lv.setSelection(i);
                                    break;
                                }
                            }
                        }
                    });
                    builder.setNegativeButton(getString(R.string.msgCancelButton), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                    return true;
                }
            });

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int Lens = 0;
                    int cLevel = 0;
                    String accquerys = "";
                    String myParam = "";
                    if(CurrentLevel > Levels){
                        myParam = "Tafsils";
                        dbApp db = new dbApp(getContext());
                        cLevel = Integer.parseInt(db.Read_InitAcc_Field("Acc_Levels"));
                        for (int j = 1; j <= Levels; j++){
                            Lens += Integer.parseInt(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(j) + "_Len"));
                        }
                        accquerys = "Acc_No LIKE '%%'";
                    }
                    else{
                        myParam = "Accounts";
                        Lens = LevelLen;
                        cLevel = CurrentLevel;
                        accquerys=" Acc_No LIKE '" + list.get(i).Get_Acc_No() + "%'";
                    }
                    //dbApp db = new dbApp(getContext());
                    //Toast.makeText(
                    //        getActivity(),
                    //        "AccQuery : " + " Acc_No = '" + list.get(i).Get_AccNo() + "'" + "\n"+
                    //        "TafsilNo : " + list.get(i).Get_Tafsil() + "\n"+
                    //        "StartDate : " + db.Read_Init_Field("Start_Date")+ "\n"+
                    //        "EndDate : " +db.Read_Init_Field("End_Date")+ "\n"+
                    //        "myParam : " + myParam+ "\n"+
                    //        "LevLen : " +  String.valueOf(Lens)+ "\n"+
                    //        "LevelSelected : " + String.valueOf(cLevel),
                    //        Toast.LENGTH_LONG
                    //).show();
                    ///*
                    dbApp db = new dbApp(getContext());
                    Bundle bundle = new Bundle();
                    bundle.putString("AccNo",list.get(i).Get_Acc_No());
                    bundle.putString("AccQuery",accquerys);
                    bundle.putString("StartDate",db.Read_Init_Field("Start_Date"));
                    bundle.putString("EndDate",db.Read_Init_Field("End_Date"));
                    bundle.putString("TafsilNo",list.get(i).Get_Tafsil());
                    bundle.putString("Title",list.get(i).Get_Acc_Name());
                    bundle.putString("myParam",myParam);
                    bundle.putString("LevLen", String.valueOf(Lens));
                    bundle.putString("LevelSelected",String.valueOf(cLevel));
                    Fragment fragment = null;
                    fragment = new FragmentShowJournals();
                    fragment.setArguments(bundle);
                    if (fragment != null){
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame,fragment);
                        ft.addToBackStack(null);
                        ft.isAddToBackStackAllowed();
                        ft.commit();
                    }
                    //*/
                }
            });
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage() + "\n" +
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }


}
