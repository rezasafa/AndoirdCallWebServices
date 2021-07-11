package ir.setarehsepehr.sepehr3.manager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class FragmentDocumentManager extends Fragment {

    public static String rslt;
    String sAcc_No;
    String sAccQuery;
    String sStartDate;
    String sEndDate;
    String sTafsilNo;
    String sTitle;
    String sParam;
    int LevLen;
    int CurrentLevel;
    boolean FirstLoad = false;

    TextView tvDebit;
    TextView tvCredit;
    ArrayList<List_Document> ld;

    Button btnFilter;

    public FragmentDocumentManager() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentDocumentManager newInstance(String param1, String param2) {
        FragmentDocumentManager fragment = new FragmentDocumentManager();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_document_manager, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.mDocumentManager));

        btnFilter = (Button) getView().findViewById(R.id.fdm_btnFilter);

        final String FilterText = btnFilter.getText().toString();
        final int btnHeight = btnFilter.getHeight();
        final float btnFontSize = btnFilter.getTextSize();

        tvDebit = (TextView) getView().findViewById(R.id.fdm_Debit);
        tvCredit = (TextView) getView().findViewById(R.id.fdm_Credit);

        tvDebit.setText("جمع بدهکار : " + "\n" + "0");
        tvCredit.setText("جمع بستانکار : " + "\n" + "0");

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Filter_Dialog fd=new Filter_Dialog(getActivity());
                //fd.show();

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.sFilter));

                // Set up the input
                final LinearLayout featureLayout = (LinearLayout) View.inflate(
                        getActivity(), R.layout.filter_dialog, null);
                LinearLayout ll = new LinearLayout(getActivity());
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setGravity(Gravity.RIGHT);
                //ll.setHorizontalScrollBarEnabled(true);
                ScrollView sv1 = new ScrollView(getActivity());
                //sv.setId();
                sv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                final TextView tvDocno = new TextView(getActivity());
                tvDocno.setText("شماره سند:");
                ll.addView(tvDocno);

                final EditText etDocno = new EditText(getActivity());
                ll.addView(etDocno);

                final TextView tvID = new TextView(getActivity());
                tvID.setText("عطف:");
                ll.addView(tvID);

                final EditText etID = new EditText(getActivity());
                ll.addView(etID);

                final TextView tvDocdateAZ = new TextView(getActivity());
                tvDocdateAZ.setText("از تاریخ:");
                ll.addView(tvDocdateAZ);

                dbApp db = new dbApp(getActivity());

                final EditText etDocdateAZ = new EditText(getActivity());
                etDocdateAZ.setText(db.Read_Init_Field("Start_Date"));
                ll.addView(etDocdateAZ);

                final TextView tvDocdateTA = new TextView(getActivity());
                tvDocdateTA.setText("تا تاریخ:");
                ll.addView(tvDocdateTA);

                final EditText etDocdateTA = new EditText(getActivity());
                etDocdateTA.setText(db.Read_Init_Field("End_Date"));
                ll.addView(etDocdateTA);

                final TextView tvDocdesc = new TextView(getActivity());
                tvDocdesc.setText("شرح سند:");
                ll.addView(tvDocdesc);

                final EditText etDocdesc = new EditText(getActivity());
                ll.addView(etDocdesc);

                final TextView tvAcc = new TextView(getActivity());
                tvAcc.setText("حساب:");
                ll.addView(tvAcc);

                final EditText etAcc = new EditText(getActivity());
                ll.addView(etAcc);

                final TextView tvTafsil1 = new TextView(getActivity());
                tvTafsil1.setText("تفصیل1:");
                ll.addView(tvTafsil1);

                final EditText etTafsil1 = new EditText(getActivity());
                ll.addView(etTafsil1);

                final TextView tvTafsil2 = new TextView(getActivity());
                tvTafsil2.setText("تفصیل2:");
                ll.addView(tvTafsil2);

                final EditText etTafsil2 = new EditText(getActivity());
                ll.addView(etTafsil2);

                final TextView tvTafsil3 = new TextView(getActivity());
                tvTafsil3.setText("تفصیل3:");
                ll.addView(tvTafsil3);

                final EditText etTafsil3 = new EditText(getActivity());
                ll.addView(etTafsil3);

                final TextView tvNotes = new TextView(getActivity());
                tvNotes.setText("توضیحات:");
                ll.addView(tvNotes);

                final EditText etNotes = new EditText(getActivity());
                ll.addView(etNotes);

                final TextView tvAttach = new TextView(getActivity());
                tvAttach.setText("پیوست:");
                ll.addView(tvAttach);

                final EditText etAttach = new EditText(getActivity());
                ll.addView(etAttach);


                final Button rbWCTRL = new Button(getActivity());
                rbWCTRL.setText(getString(R.string.sWCTRL));
                ll.addView(rbWCTRL);

                final Button rbCTRL = new Button(getActivity());
                rbCTRL.setText(getString(R.string.sCTRL));
                ll.addView(rbCTRL);

                final Button rbWCNF = new Button(getActivity());
                rbWCNF.setText(getString(R.string.sWCNF));
                ll.addView(rbWCNF);

                final Button rbCNF = new Button(getActivity());
                rbCNF.setText(getString(R.string.sCNF));
                ll.addView(rbCNF);

                final Button rbWRGST = new Button(getActivity());
                rbWRGST.setText(getString(R.string.sWRGST));
                ll.addView(rbWRGST);

                final Button rbRGST = new Button(getActivity());
                rbRGST.setText(getString(R.string.sRGST));
                ll.addView(rbRGST);

                final Button rbALLDOC = new Button(getActivity());
                rbALLDOC.setText(getString(R.string.sALLDOC));
                ll.addView(rbALLDOC);

                /*
                TextView tvT1 = new TextView(getActivity());
                tvT1.setText(getString(R.string.sLevelTafsil1));
                ll.addView(tvT1);
                for(int i = 0; i < la1.size() ; i++) {
                    final CheckBox cb = new CheckBox(getActivity());
                    cb.setText(la1.get(i).Tafsil_No  + " " + la1.get(i).Tafsil_Name);
                    final int finalI = i;
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            la1.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                        }
                    });
                    ll.addView(cb);
                }
                */
                sv1.addView(ll);
                featureLayout.addView(sv1);
                builder.setView(featureLayout);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                // Set up the
                builder.setPositiveButton(getString(R.string.msgOKButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                        String filter = "";
                        if(!etDocno.getText().toString().equals(""))
                            filter += " AND D.Doc_No LIKE '%" + etDocno.getText().toString() + "%' ";
                        if(!etID.getText().toString().equals(""))
                            filter += " AND D.ID LIKE '%" + etID.getText() + "%' ";
                        if(!etDocdesc.getText().toString().equals(""))
                            filter += " AND D.Doc_Desc LIKE '%" + etDocdesc.getText() + "%' ";
                        if(!etDocdateAZ.getText().toString().equals("") && !etDocdateTA.getText().toString().equals(""))
                            filter += " AND D.Doc_Date BETWEEN '" + etDocdateAZ.getText().toString() + "' " +
                                    " AND '" + etDocdateTA.getText().toString() + "' ";
                        if(!etDocdateAZ.getText().toString().equals("") && etDocdateTA.getText().toString().equals(""))
                            filter += " AND D.Doc_Date LIKE '%" + etDocdateAZ.getText().toString() + "%' ";
                        if( etDocdateAZ.getText().toString().equals("") && !etDocdateTA.getText().toString().equals(""))
                            filter += " AND D.Doc_Date LIKE '%" + etDocdateTA.getText().toString() + "%' ";
                        if(!etAttach.getText().toString().equals(""))
                            filter += " AND D.Attach LIKE '%" + etAttach.getText().toString() + "%' ";
                        if(!etNotes.getText().toString().equals(""))
                            filter += " AND D.Notes LIKE '%" + etNotes.getText().toString() + "%' ";
                        if(!etAcc.getText().toString().equals(""))
                            filter += " AND A.Acc_Name LIKE '%" + etAcc.getText().toString() + "%' ";
                        if(!etTafsil1.getText().toString().equals(""))
                            filter += " AND T1.Tafsil_Name LIKE '%" + etTafsil1.getText().toString() + "%' ";
                        if(!etTafsil2.getText().toString().equals(""))
                            filter += " AND T2.Tafsil_Name LIKE '%" + etTafsil2.getText().toString() + "%' ";
                        if(!etTafsil3.getText().toString().equals(""))
                            filter += " AND T3.Tafsil_Name LIKE '%" + etTafsil3.getText().toString() + "%' ";

                        //Toast.makeText(
                        //        getContext(),
                        //        filter,
                        //        Toast.LENGTH_LONG
                        //).show();
                        fill_Data(filter);
                    }
                });
                builder.setNegativeButton(getString(R.string.msgCancelButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                final AlertDialog alertDialog = builder.show();

                rbWCTRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Controler = False ");
                        alertDialog.dismiss();
                    }
                });
                rbCTRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Controler  ");
                        alertDialog.dismiss();
                    }
                });
                rbWCNF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Controler AND D.Confirm = False ");
                        alertDialog.dismiss();
                    }
                });
                rbCNF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Confirm ");
                        alertDialog.dismiss();
                    }
                });
                rbWRGST.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Confirm AND D.Register = False ");
                        alertDialog.dismiss();
                    }
                });
                rbRGST.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data(" AND D.Controler AND D.Confirm AND D.Register ");
                        alertDialog.dismiss();
                    }
                });
                rbALLDOC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fill_Data("");
                        alertDialog.dismiss();
                    }
                });
            }
        });

        fill_Data(" AND D.Controler = False ");
    }

    private void fill_Data(final String Filter){
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
                    caller_DocumentManager c = new caller_DocumentManager();
                    c.sWheres = Filter;
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

                    //if (rslt.equals("[]")){
                    //    Toast.makeText(
                    //            getContext(),
                    //            getResources().getString(R.string.sGardesh),
                    //            Toast.LENGTH_LONG
                    //    ).show();
                    //    return null;
                    //}

                    String sjson = "{ Records : " +  rslt + "}";

                    JSONObject json = new JSONObject(sjson);

                    JSONArray jArray = json.getJSONArray("Records");

                    final ArrayList<List_Document> l = new ArrayList<List_Document>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        l.add(new List_Document(
                                jo.getString("ID"),
                                jo.getBoolean("Controler"),
                                jo.getBoolean("Confirm"),
                                jo.getBoolean("Register"),
                                jo.getBoolean("Flag"),
                                jo.getString("Doc_No"),
                                jo.getString("Doc_Date"),
                                jo.getString("Doc_Desc"),
                                jo.getString("SumDebit"),
                                jo.getString("SumCredit"),
                                jo.getString("Attach"),
                                jo.getString("Notes"),
                                jo.getString("Doc_Type_Name")
                        ));
                    };

                    ld = l;

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
                ///*
                //if(ld.size() > 0)
                fill_Records(ld,Filter);
                //*/
                super.onPostExecute(result);
            }
        }.execute();
    }

    private void fill_Records(final ArrayList<List_Document> list,String filter){
        String d = "";
        String c = "";
        int i = 0;
        int Count = 0;
        try{
            final ListView lv = (ListView) getView().findViewById(R.id.fdm_List);
            final adapter_Documents adapter;

            adapter = new adapter_Documents(getContext(), list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Doc_No",list.get(position).Get_Doc_No());
                    Fragment fragment = null;
                    fragment = new FragmentDocumentShow();
                    fragment.setArguments(bundle);
                    if (fragment != null){
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame,fragment);
                        ft.addToBackStack(null);
                        ft.isAddToBackStackAllowed();
                        ft.commit();
                    }
                }
            });

            long deb = 0;
            long cre = 0;

            Count = list.size();

            for(i = 0; i < list.size(); i++){
                d = list.get(i).Get_SumDebit().replace(",","");
                c = list.get(i).Get_SumCredit().replace(",","");
                if (!d.equals(""))
                    deb += Long.parseLong(d);
                else
                    deb += 0;
                if (!c.equals(""))
                    cre += Long.parseLong(c);
                else
                    cre += 0;
            }

            tvDebit.setText("جمع بدهکار : " + "\n" + new sVar().CurrencyFormat(deb));
            tvCredit.setText("جمع بستانکار : " + "\n" +  new sVar().CurrencyFormat(cre));

        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage() + "\n"+
                            " d = " + d + "\n " + "c = " + c + "\n"+
                            " i = " + String.valueOf(i) + "\n"+
                            " count = " + String.valueOf(Count) + "\n"+
                            getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

}