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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShowJournals extends Fragment {
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
    TextView tvRem;
    ArrayList<List_Journals> li;
    //sarfasl
    ArrayList<List_Accounts> lsa;
    //accounts
    ArrayList<List_Tafsils> la1;
    ArrayList<List_Tafsils> la2;
    ArrayList<List_Tafsils> la3;
    //tafsils
    ArrayList<List_Tafsils> lt1;
    ArrayList<List_Tafsils> lt2;
    ArrayList<List_Tafsils> lt3;

    Button btnFilter;

    public FragmentShowJournals() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(getArguments() != null){
            sAcc_No = getArguments().getString("AccNo");
            sAccQuery = getArguments().getString("AccQuery");
            sStartDate = getArguments().getString("StartDate");
            sEndDate = getArguments().getString("EndDate");
            sTafsilNo = getArguments().getString("TafsilNo");
            sTitle = getArguments().getString("Title");
            sParam = getArguments().getString("myParam");
            LevLen = Integer.parseInt(getArguments().getString("LevLen"));
            CurrentLevel = Integer.parseInt(getArguments().getString("LevelSelected"));
        }

        return inflater.inflate(R.layout.fragment_show_journals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(sTitle);

        btnFilter = (Button) getView().findViewById(R.id.fsjs_btnFilter);
        final String FilterText = btnFilter.getText().toString();
        final int btnHeight = btnFilter.getHeight();
        final float btnFontSize = btnFilter.getTextSize();
        tvDebit = (TextView) getView().findViewById(R.id.fsjs_Debit);
        tvCredit = (TextView) getView().findViewById(R.id.fsjs_Credit);
        tvRem = (TextView) getView().findViewById(R.id.fsjs_Rem);

        tvDebit.setText("جمع بدهکار : " + "\n" + "0");
        tvCredit.setText("جمع بستانکار : " + "\n" + "0");
        tvRem.setText("مانده : " + "0");

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Filter_Dialog fd=new Filter_Dialog(getActivity());
                //fd.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(getString(R.string.sFilter));

                // Set up the input
                if(sParam == "Accounts"){
                    if(!FirstLoad){
                        fill_Tafsil1_Accounts();
                        fill_Tafsil2_Accounts();
                        fill_Tafsil3_Accounts();
                        FirstLoad = true;
                    }
                    //reset checked to false
                    for(int i =0;i<la1.size();i++){
                        la1.get(i).LTafsil_Name = "false";
                    }
                    for(int i =0;i<la2.size();i++){
                        la2.get(i).LTafsil_Name = "false";
                    }
                    for(int i =0;i<la3.size();i++){
                        la3.get(i).LTafsil_Name = "false";
                    }

                    if(la1.size()!= 0){
                        final LinearLayout featureLayout = (LinearLayout) View.inflate(
                                getActivity(), R.layout.filter_dialog, null);
                        LinearLayout ll = new LinearLayout(getActivity());
                        ll.setOrientation(LinearLayout.VERTICAL);
                        ll.setGravity(Gravity.RIGHT);
                        //ll.setHorizontalScrollBarEnabled(true);
                        ScrollView sv1 = new ScrollView(getActivity());
                        //sv.setId();
                        sv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

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

                        if(la2.size()!=0){
                            TextView tvT2 = new TextView(getActivity());
                            tvT2.setText(getString(R.string.sLevelTafsil2));
                            ll.addView(tvT2);

                            for(int i = 0; i < la2.size() ; i++) {
                                final CheckBox cb = new CheckBox(getActivity());
                                cb.setText(la2.get(i).Tafsil_No  + " " + la2.get(i).Tafsil_Name);
                                final int finalI = i;
                                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                    @Override
                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                        la2.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                                    }
                                });
                                ll.addView(cb);
                            }

                            if(la3.size()!=0){
                                TextView tvT3 = new TextView(getActivity());
                                tvT3.setText(getString(R.string.sLevelTafsil3));
                                ll.addView(tvT3);

                                for(int i = 0; i < la3.size() ; i++) {
                                    final CheckBox cb = new CheckBox(getActivity());
                                    cb.setText(la3.get(i).Tafsil_No  + " " + la3.get(i).Tafsil_Name);
                                    final int finalI = i;
                                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                            la3.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                                        }
                                    });
                                    ll.addView(cb);
                                }
                            }
                        }
                        sv1.addView(ll);
                        featureLayout.addView(sv1);
                        builder.setView(featureLayout);
                    }

                }

                if (sParam == "Tafsils"){
                    if(!FirstLoad){
                        fill_Sarfasl();
                        fill_Tafsil1_Tafsils();
                        fill_Tafsil2_Tafsils();
                        fill_Tafsil3_Tafsils();
                        FirstLoad = true;
                    }
                    //reset checked to false
                    for(int i =0;i<lsa.size();i++){
                        lsa.get(i).LAcc_Name = "false";
                    }
                    for(int i =0;i<lt1.size();i++){
                        lt1.get(i).LTafsil_Name = "false";
                    }
                    for(int i =0;i<lt2.size();i++){
                        lt2.get(i).LTafsil_Name = "false";
                    }
                    for(int i =0;i<lt3.size();i++){
                        lt3.get(i).LTafsil_Name = "false";
                    }

                    final LinearLayout featureLayout = (LinearLayout) View.inflate(
                            getActivity(), R.layout.filter_dialog, null);

                    LinearLayout ll = new LinearLayout(getActivity());
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.setGravity(Gravity.RIGHT);

                    ScrollView sv1 = new ScrollView(getActivity());
                    sv1.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));


                    TextView tvSarfasl = new TextView(getActivity());
                    tvSarfasl.setText(getString(R.string.sSarfasl));
                    ll.addView(tvSarfasl);

                    for(int i = 0; i < lsa.size() ; i++) {
                        final CheckBox cb = new CheckBox(getActivity());
                        cb.setText(lsa.get(i).Acc_No  + " " + lsa.get(i).Acc_Name);
                        final int finalI = i;
                        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                lsa.get(finalI).Set_LAcc_Name(String.valueOf(isChecked));
                            }
                        });
                        ll.addView(cb);
                    }

                    TextView tv1 = new TextView(getActivity());
                    tv1.setText(getString(R.string.sLevelTafsil1));
                    ll.addView(tv1);

                    for(int i = 0; i < lt1.size() ; i++) {
                        final CheckBox cb = new CheckBox(getActivity());
                        cb.setText(lt1.get(i).Tafsil_No  + " " + lt1.get(i).Tafsil_Name);
                        final int finalI = i;
                        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                lt1.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                            }
                        });
                        ll.addView(cb);
                    }

                    TextView tv2 = new TextView(getActivity());
                    tv2.setText(getString(R.string.sLevelTafsil2));
                    ll.addView(tv2);

                    for(int i = 0; i < lt2.size() ; i++) {
                        final CheckBox cb = new CheckBox(getActivity());
                        cb.setText(lt2.get(i).Tafsil_No  + " " + lt2.get(i).Tafsil_Name);
                        final int finalI = i;
                        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                lt2.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                            }
                        });
                        ll.addView(cb);
                    }

                    TextView tv3 = new TextView(getActivity());
                    tv3.setText(getString(R.string.sLevelTafsil3));
                    ll.addView(tv3);

                    for(int i = 0; i < lt3.size() ; i++) {
                        final CheckBox cb = new CheckBox(getActivity());
                        cb.setText(lt3.get(i).Tafsil_No  + " " + lt3.get(i).Tafsil_Name);
                        final int finalI = i;
                        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                lt3.get(finalI).Set_LTafsil_Name(String.valueOf(isChecked));
                            }
                        });
                        ll.addView(cb);
                    }

                    sv1.addView(ll);
                    featureLayout.addView(sv1);
                    builder.setView(featureLayout);
                }
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                //input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                // Set up the
               builder.setPositiveButton(getString(R.string.msgOKButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //m_Text = input.getText().toString();
                        String filter = "";
                        String showFilter = "";
                        if(sParam == "Accounts"){
                            if(la1.size()!=0){
                                for(int i =0;i<la1.size();i++){
                                    if(Boolean.parseBoolean(la1.get(i).LTafsil_Name) == true){
                                        //filter yadet nare
                                        showFilter += la1.get(i).Tafsil_Name + "-";
                                        if(filter != "")
                                            filter += " OR Tafsil1 = '" + la1.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil1 = '" + la1.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }
                            if(la2.size()!=0){
                                for(int i =0;i<la2.size();i++){
                                    if(Boolean.parseBoolean(la2.get(i).LTafsil_Name) == true){
                                        showFilter += la2.get(i).Tafsil_Name + "-";
                                        if(filter != "")
                                            filter += " OR Tafsil2 = '" + la2.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil2 = '" + la2.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }
                            if(la3.size()!=0){
                                for(int i =0;i<la3.size();i++){
                                    if(Boolean.parseBoolean(la3.get(i).LTafsil_Name) == true){
                                        showFilter += la3.get(i).Tafsil_Name + "-";
                                        if(filter !="")
                                            filter += " OR Tafsil3 = '" + la3.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil3 = '" + la3.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }

                            if (filter != "")
                                filter = " AND (" + filter + ")";

                            fill_Data(filter);
                        }
                        if(sParam == "Tafsils"){
                            String AccFilter = "";
                            if(lsa.size()!=0){
                                for(int i =0;i<lsa.size();i++){
                                    if(Boolean.parseBoolean(lsa.get(i).LAcc_Name) == true){
                                        //filter yadet nare
                                        showFilter += lsa.get(i).Acc_Name + "-";
                                        if(AccFilter != "")
                                            AccFilter += " OR Acc_No LIKE '" + lsa.get(i).Acc_No + "%' ";
                                        else
                                            AccFilter = " Acc_No LIKE '" + lsa.get(i).Acc_No + "%' ";
                                    }
                                }
                            }
                            if(lt1.size()!=0){
                                for(int i =0;i<lt1.size();i++){
                                    if(Boolean.parseBoolean(lt1.get(i).LTafsil_Name) == true){
                                        showFilter += lt1.get(i).Tafsil_Name + "-";
                                        //filter yadet nare
                                        if(filter != "")
                                            filter += " OR Tafsil1 = '" + lt1.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil1 = '" + lt1.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }
                            if(lt2.size()!=0){
                                for(int i =0;i<lt2.size();i++){
                                    if(Boolean.parseBoolean(lt2.get(i).LTafsil_Name) == true){
                                        showFilter += lt2.get(i).Tafsil_Name + "-";
                                        if(filter != "")
                                            filter += " OR Tafsil2 = '" + lt2.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil2 = '" + lt2.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }
                            if(lt3.size()!=0){
                                for(int i =0;i<lt3.size();i++){
                                    if(Boolean.parseBoolean(lt3.get(i).LTafsil_Name) == true){
                                        showFilter += lt3.get(i).Tafsil_Name + "-";
                                        if(filter !="")
                                            filter += " OR Tafsil3 = '" + lt3.get(i).Tafsil_No + "' ";
                                        else
                                            filter = " Tafsil3 = '" + lt3.get(i).Tafsil_No + "' ";
                                    }
                                }
                            }

                            if (AccFilter !=""){
                                //sAccQuery = new sVar().removeChar(sAccQuery,'(');
                                sAccQuery = sAccQuery.replace("(","");
                                sAccQuery = sAccQuery.replace(")","");
                                AccFilter = AccFilter.replace("(","");
                                AccFilter = AccFilter.replace(")","");
                                sAccQuery = "(" + sAccQuery;
                                AccFilter = " AND "+  AccFilter +")";
                            }else{
                                sAccQuery = sAccQuery.replace("(","");
                                sAccQuery = sAccQuery.replace(")","");
                            }

                            if (filter != "" && AccFilter != "")
                                filter = AccFilter + " AND (" + filter + ")";
                            else{
                                if(filter != "" && AccFilter == ""){
                                    filter = " AND (" + filter + ")";
                                }
                                if(filter == "" && AccFilter != ""){
                                    filter = AccFilter;
                                }
                            }
                            //Toast.makeText(
                            //        getActivity(),
                            //        sAccQuery+filter,
                            //        Toast.LENGTH_LONG
                            //).show();
                            fill_Data(filter);
                        }
                        if (showFilter != ""){
                            btnFilter.setTextSize(btnFontSize/2);
                            int countline = showFilter.split("-").length;
                            showFilter = FilterText + "\n"+
                                    showFilter.replace("-","\n");
                            btnFilter.setHeight(btnHeight + (countline*70));
                            btnFilter.setText(showFilter);
                        }
                        else{
                            btnFilter.setHeight(btnHeight);
                            btnFilter.setTextSize(btnFontSize/2);
                            btnFilter.setText(FilterText);
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
            }
        });

        fill_Data("");
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
                    caller_Journals c = new caller_Journals();
                    c.sAccQuery = sAccQuery + Filter;
                    c.sStartDate = sStartDate;
                    c.sEndDate = sEndDate;
                    c.sTafsilNo = sTafsilNo;
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

                    final ArrayList<List_Journals> l = new ArrayList<List_Journals>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        l.add(new List_Journals(
                                jo.getString("Acc_No"),
                                jo.getString("Rows"),
                                jo.getString("Register"),
                                jo.getString("Doc_No"),
                                jo.getString("Doc_Date"),
                                jo.getString("Descr"),
                                jo.getString("Debit"),
                                jo.getString("Credit"),
                                jo.getString("SDoc_No"),
                                jo.getString("PDoc_No"),
                                jo.getString("RDoc_No"),
                                jo.getString("Row"),
                                jo.getString("Flag"),
                                jo.getString("Hidden"),
                                jo.getString("Settle"),
                                jo.getString("Counts"),
                                jo.getString("Price"),
                                jo.getString("ID"),
                                jo.getString("Follow_Date")
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
                ///*
                if(li.size() > 0)
                    fill_Records(li,Filter);
                //*/
                super.onPostExecute(result);
            }
        }.execute();
    }

    private void fill_Records(ArrayList<List_Journals> list,String filter){
        try{

            final ListView lv = (ListView) getView().findViewById(R.id.fsjs_List);
            final adapter_Journals adapter;

            adapter = new adapter_Journals(getContext(), list);
            lv.setAdapter(adapter);

        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }

        fill_RemRecords(filter);
    }
    private void fill_RemRecords(String Filter){
        try{
            rslt = "START";
            caller_JournalsRem c = new caller_JournalsRem();
            c.sAccQuery = sAccQuery + Filter;
            c.sStartDate = sStartDate;
            c.sEndDate = sEndDate;
            c.sTafsilNo = sTafsilNo;
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

            final ArrayList<List_JournalsRem> list = new ArrayList<List_JournalsRem>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_JournalsRem(
                        jo.getString("Debit"),
                        jo.getString("Credit"),
                        jo.getString("Rem")
                ));
            };

            tvDebit.setText("جمع بدهکار : " + "\n" + list.get(0).Get_Debit());
            tvCredit.setText("جمع بستانکار : " + "\n" + list.get(0).Get_Credit());
            tvRem.setText("مانده : " + list.get(0).Get_Rem());

        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private void fill_Tafsil1_Accounts(){
        try{
            rslt = "START";
            caller_Tafsil1_Accounts c = new caller_Tafsil1_Accounts();
            c.sAcc_No = sAcc_No;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        "")
                );
            };
            la1 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Tafsil2_Accounts(){
        try{
            rslt = "START";
            caller_Tafsil2_Accounts c = new caller_Tafsil2_Accounts();
            c.sAcc_No = sAcc_No;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        ""
                ));
            };
            la2 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Tafsil3_Accounts(){
        try{
            rslt = "START";
            caller_Tafsil3_Accounts c = new caller_Tafsil3_Accounts();
            c.sAcc_No = sAcc_No;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        ""
                ));
            };
            la3 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Sarfasl(){
        try{
            rslt = "START";
            caller_AccountSarfasls c = new caller_AccountSarfasls();
            c.sLevelLen = String.valueOf(LevLen);
            c.sTafsilNo = sTafsilNo;
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
                        ""
                ));
            };

            lsa = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Tafsil1_Tafsils(){
        try{
            rslt = "START";
            caller_Tafsil1_Tafsils c = new caller_Tafsil1_Tafsils();
            c.sTafsilNo = sTafsilNo;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        ""
                ));
            };
            lt1 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Tafsil2_Tafsils(){
        try{
            rslt = "START";
            caller_Tafsil2_Tafsils c = new caller_Tafsil2_Tafsils();
            c.sTafsilNo = sTafsilNo;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        ""
                ));
            };
            lt2 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void fill_Tafsil3_Tafsils(){
        try{
            rslt = "START";
            caller_Tafsil3_Tafsils c = new caller_Tafsil3_Tafsils();
            c.sTafsilNo = sTafsilNo;
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

            final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Tafsils(
                        "",
                        jo.getString("Tafsil_No"),
                        jo.getString("Tafsil_Name"),
                        ""
                ));
            };
            lt3 = list;
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
