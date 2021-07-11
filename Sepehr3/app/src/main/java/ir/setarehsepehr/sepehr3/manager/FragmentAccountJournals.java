package ir.setarehsepehr.sepehr3.manager;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccountJournals extends Fragment {
    public static String rslt;
    dbApp db;
    int Levels;
    int GLevel;
    int LevCount;
    int CLev;
    int LevLen;
    int Tafsil_Len;
    int GTafsil_Len;
    int CurrentLevel;

    ArrayList<List_Accounts> la;

    RelativeLayout rlAccounts;
    EditText etAcc_No;
    EditText etAcc_Name;
    //EditText etLAcc_Name;
    Button btnSearch;

    public FragmentAccountJournals() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            LevLen = Integer.parseInt(getArguments().getString("LevLen"));
            CurrentLevel = Integer.parseInt(getArguments().getString("LevelSelected"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new dbApp(getContext());
        Levels = Integer.parseInt(db.Read_InitAcc_Field("Acc_Levels"));
        GLevel = Integer.parseInt(db.Read_InitAcc_Field("GL_Level"));
        GTafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("GTafsil_Len"));
        Tafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("Tafsil_Len"));

        return inflater.inflate(R.layout.fragment_account_journals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.mAccountJournals));

        rlAccounts = (RelativeLayout) view.findViewById(R.id.faj_rlAccounts);
        btnSearch = (Button) view.findViewById(R.id.faj_btnSearch);
        etAcc_No = null;
        etAcc_Name = null;
        //etLAcc_Name = null;

        etAcc_No = (EditText) getView().findViewById(R.id.faj_txtAcc_No);
        etAcc_Name = (EditText) getView().findViewById(R.id.faj_txtAcc_Name);
        //etLAcc_Name = (EditText) getView().findViewById(R.id.faj_txtLAcc_Name);
        /*
        etAcc_No.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fill_Records();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etAcc_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fill_Records();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etLAcc_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                fill_Records();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        */

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadInfo();
            }
        });

        LoadInfo();
    }

    private void LoadInfo(){
        //boolean findRecord = false;
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
                    String wheres = " WHERE (Len(Acc_No) =" + LevLen + " ) ";
                    if (!etAcc_No.getText().toString().equals("")){
                        wheres += " AND Acc_No LIKE '%" + etAcc_No.getText().toString() + "%'";
                    }
                    if (!etAcc_Name.getText().toString().equals("")){
                        wheres += " AND Acc_Name LIKE '%" + etAcc_Name.getText().toString() + "%'";
                    }
                    /*if (!etLAcc_Name.getText().toString().equals("")){
                        wheres += " AND LAcc_Name LIKE '%" + etLAcc_Name.getText().toString() + "%'";
                    }*/
                    rslt = "START";
                    caller_Accounts c = new caller_Accounts();
                    c.sWheres = wheres;
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

                    final ArrayList<List_Accounts> list = new ArrayList<List_Accounts>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        list.add(new List_Accounts(
                                jo.getString("Acc_No"),
                                jo.getString("Acc_Name"),
                                jo.getString("LAcc_Name")
                        ));
                    };

                    la = list;
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
                //if(la.size() > 0)
                fill_Records(la);
                super.onPostExecute(result);
            }
        }.execute();
    }

    private void fill_Records(final ArrayList<List_Accounts> list){
        try{
            final ListView lv = (ListView) getView().findViewById(R.id.faj_lvAccounts);
            final adapter_Account_Journals adapter;

            adapter = new adapter_Account_Journals(getContext(), list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //dbApp db = new dbApp(getContext());
                    //Toast.makeText(
                    //        getActivity(),
                    //        "AccQuery : " + " Acc_No = '" + list.get(i).Get_Acc_No() + "'" + "\n"+
                    //                "TafsilNo : " + "" + "\n"+
                    //                "StartDate : " + db.Read_Init_Field("Start_Date")+ "\n"+
                    //                "EndDate : " +db.Read_Init_Field("End_Date")+ "\n"+
                    //                "myParam : " + "Accounts"+ "\n"+
                    //                "LevLen : " +  String.valueOf(LevLen)+ "\n"+
                    //                "LevelSelected : " + String.valueOf(CurrentLevel),
                    //        Toast.LENGTH_LONG
                    //).show();
                    ///*
                    Bundle bundle = new Bundle();
                    bundle.putString("AccNo",list.get(i).Get_Acc_No());
                    bundle.putString("AccQuery"," Acc_No LIKE '" + list.get(i).Get_Acc_No() + "%'");
                    bundle.putString("StartDate",db.Read_Init_Field("Start_Date"));
                    bundle.putString("EndDate",db.Read_Init_Field("End_Date"));
                    bundle.putString("TafsilNo","");
                    bundle.putString("Title",list.get(i).Get_Acc_Name());
                    bundle.putString("myParam","Accounts");
                    bundle.putString("LevLen", String.valueOf(LevLen));
                    bundle.putString("LevelSelected",String.valueOf(CurrentLevel));
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
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
