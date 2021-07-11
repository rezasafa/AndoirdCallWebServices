package ir.setarehsepehr.sepehr3.manager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.*;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class FragmentDocumentShow extends Fragment {

    public static String rslt;
    String Doc_No;

    boolean FirstLoad = false;

    TextView tvDoc_No;
    TextView tvDoc_Type;
    TextView tvDoc_Date;
    TextView tvDoc_Notes;
    TextView tvDoc_Attach;
    TextView tvDoc_ID;

    TextView tvDebit;
    TextView tvCredit;
    TextView tvRem;

    ArrayList<List_Document> ld;
    ArrayList<List_DocumnetJournal> lj;

    public FragmentDocumentShow() {
        // Required empty public constructor
    }

    public static FragmentDocumentShow newInstance(String param1, String param2) {
        FragmentDocumentShow fragment = new FragmentDocumentShow();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Doc_No = getArguments().getString("Doc_No");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_document_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.mDocumentShow));

        tvDoc_No = (TextView) getView().findViewById(R.id.fds_lblDoc_no);
        tvDoc_Type = (TextView) getView().findViewById(R.id.fds_lblDoc_type);
        tvDoc_Date = (TextView) getView().findViewById(R.id.fds_lblDoc_date);
        tvDoc_Notes = (TextView) getView().findViewById(R.id.fds_lblDoc_notes);
        tvDoc_Attach = (TextView) getView().findViewById(R.id.fds_lblDoc_attach);
        tvDoc_ID = (TextView) getView().findViewById(R.id.fds_lblid);

        tvDebit = (TextView) getView().findViewById(R.id.fds_Debit);
        tvCredit = (TextView) getView().findViewById(R.id.fds_Credit);
        tvRem = (TextView) getView().findViewById(R.id.fds_rem);

        tvDebit.setText("جمع بدهکار : " + "\n" + "0");
        tvCredit.setText("جمع بستانکار : " + "\n" + "0");
        tvRem.setText("مانده : " + "\n" + "0");

        fill_DataDocInfo(" AND D.Doc_No = '" + Doc_No + "'");
        fill_DataDocJournal(" AND J.Doc_No = '" + Doc_No + "'");
    }

    private void fill_DataDocInfo(final String Filter){
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
                    caller_Document c = new caller_Document();
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
                fill_RecordsDocument(ld,Filter);
                //*/
                super.onPostExecute(result);
            }
        }.execute();
    }
    private void fill_DataDocJournal(final String Filter){
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
                    caller_DocumentShow c = new caller_DocumentShow();
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

                    final ArrayList<List_DocumnetJournal> l = new ArrayList<List_DocumnetJournal>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        l.add(new List_DocumnetJournal(
                                jo.getString("Row"),
                                jo.getString("Acc_No"),
                                jo.getString("Acc_Name"),
                                jo.getString("Tafsil1"),
                                jo.getString("Tafsil_Name1"),
                                jo.getString("Tafsil2"),
                                jo.getString("Tafsil_Name2"),
                                jo.getString("Tafsil3"),
                                jo.getString("Tafsil_Name3"),
                                jo.getString("Descr"),
                                jo.getString("Debit"),
                                jo.getString("Credit"),
                                jo.getString("ID")
                        ));
                    };

                    lj = l;

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
                fill_Records(lj,Filter);
                //*/
                super.onPostExecute(result);
            }
        }.execute();
    }

    private void fill_Records(ArrayList<List_DocumnetJournal> list,String filter){
        String d = "";
        String c = "";
        int i = 0;
        int Count = 0;
        try{
            final ListView lv = (ListView) getView().findViewById(R.id.fds_List);
            final adapter_DocumentShow adapter;

            adapter = new adapter_DocumentShow(getContext(), list);
            lv.setAdapter(adapter);

            long deb = 0;
            long cre = 0;

            Count = list.size();

            for(i = 0; i < list.size(); i++){
                d = list.get(i).Get_Debit().replace(",","");
                c = list.get(i).Get_Credit().replace(",","");
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
            tvRem.setText("مانده : " + "\n" +  new sVar().CurrencyFormat(deb - cre));

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
    private void fill_RecordsDocument(ArrayList<List_Document> list,String filter) {
        try{
            tvDoc_No.setText("شماره سند : " + list.get(0).Doc_No);
            tvDoc_Type.setText("نوع سند : " + list.get(0).Doc_Type_Name);
            tvDoc_Date.setText("تاریخ سند : " + list.get(0).Doc_Date);
            if(list.get(0).Notes.equals("null"))
                tvDoc_Notes.setText("توضیحات : ");
            else
                tvDoc_Notes.setText("توضیحات : " + list.get(0).Notes);
            if(list.get(0).Attach.equals("null"))
                tvDoc_Attach.setText("پیوست : ");
            else
                tvDoc_Attach.setText("پیوست : " + list.get(0).Attach);
            tvDoc_ID.setText("عطف : " + list.get(0).ID);

        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage() + "\n"+
                            getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}