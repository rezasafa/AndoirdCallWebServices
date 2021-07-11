package ir.setarehsepehr.sepehr3.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import ir.setarehsepehr.sepehr3.manager.R;

public class ActivitySelectCoYear extends Activity {
    public static String rslt;
    EditText etAddress;
    Spinner cmbCompany;
    Spinner cmbYear;
    Button btnGo;
    Button btnCheckConnection;
    String sCompany;
    String sFinYear;
    String sTafsil_No;
    String sTafsil_Name;
    String sFirstName;
    String sLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_co_year);

        cmbCompany = (Spinner) findViewById(R.id.coy_Company);
        cmbYear = (Spinner) findViewById(R.id.coy_Year);
        btnGo = (Button) findViewById(R.id.coy_Go);
        btnCheckConnection = (Button) findViewById(R.id.coy_Address);

        Fill_Company();

        btnCheckConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivitySelectCoYear.this ,SetIPAddress.class);
                i.putExtra("ComeBack",true);
                startActivity(i);
                finish();
            }
        });

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IP = new dbApp(view.getContext()).Read_Setting_Field("IP_Address");
                new dbApp(view.getContext()).Delete_Setting();

                new dbApp(view.getContext()).Insert_Setting(IP,sCompany,sFinYear,"");

                Fill_Init();
                Fill_InitAcc();

                Intent intent = new Intent(ActivitySelectCoYear.this,ActivityMainApp.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void Fill_Init(){
        dbApp db = new dbApp(this);
        try{
            rslt="START";
            caller_Init c=new caller_Init();
            c.con = this;
            c.sCoName = sCompany;
            c.sYear = sFinYear;

            c.join();
            c.start();

            while(rslt=="START") {
                try {
                    Thread.sleep(10);
                }catch(Exception ex) {

                }
            }

            String sjson = "{ Record : " + rslt + "}";

            JSONObject json = new JSONObject(sjson);
            JSONArray jArray = json.getJSONArray("Record");

            db.Delete_Init();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                db.Insert_Init(
                        jo.getString("Co_Name"),
                        jo.getString("Co_Address"),
                        jo.getString("Start_Date"),
                        jo.getString("End_Date"),
                        jo.getString("ATafsil1_Visible"),
                        jo.getString("ATafsil2_Visible"),
                        jo.getString("ATafsil3_Visible")
                );
            };


        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    private void Fill_InitAcc(){
        dbApp db = new dbApp(this);
        try{
            rslt="START";
            caller_InitAcc c=new caller_InitAcc();
            c.con = this;
            c.sCoName = sCompany;
            c.sYear = sFinYear;

            c.join();
            c.start();

            while(rslt=="START") {
                try {
                    Thread.sleep(10);
                }catch(Exception ex) {

                }
            }

            String sjson = "{ Record : " + rslt + "}";

            JSONObject json = new JSONObject(sjson);
            JSONArray jArray = json.getJSONArray("Record");

            db.Delete_InitAcc();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                db.Insert_InitAcc(
                        jo.getString("Acc_Levels"),
                        jo.getString("GL_Level"),
                        jo.getString("Acc_Level1_Name"),
                        jo.getString("Acc_Level1_Len"),
                        jo.getString("Acc_Level2_Name"),
                        jo.getString("Acc_Level2_Len"),
                        jo.getString("Acc_Level3_Name"),
                        jo.getString("Acc_Level3_Len"),
                        jo.getString("Acc_Level4_Name"),
                        jo.getString("Acc_Level4_Len"),
                        jo.getString("Acc_Level5_Name"),
                        jo.getString("Acc_Level5_Len"),
                        jo.getString("Acc_Level6_Name"),
                        jo.getString("Acc_Level6_Len"),
                        jo.getString("Acc_Level7_Name"),
                        jo.getString("Acc_Level7_Len"),
                        jo.getString("Acc_Level8_Name"),
                        jo.getString("Acc_Level8_Len"),
                        jo.getString("Acc_Level9_Name"),
                        jo.getString("Acc_Level9_Len"),
                        jo.getString("GAcc_Level1_Len"),
                        jo.getString("TAcc_Level1_Len"),
                        jo.getString("GAcc_Level2_Len"),
                        jo.getString("TAcc_Level2_Len"),
                        jo.getString("GAcc_Level3_Len"),
                        jo.getString("TAcc_Level3_Len"),
                        jo.getString("GAcc_Level4_Len"),
                        jo.getString("TAcc_Level4_Len"),
                        jo.getString("GAcc_Level5_Len"),
                        jo.getString("TAcc_Level5_Len"),
                        jo.getString("GAcc_Level6_Len"),
                        jo.getString("TAcc_Level6_Len"),
                        jo.getString("GAcc_Level7_Len"),
                        jo.getString("TAcc_Level7_Len"),
                        jo.getString("GAcc_Level8_Len"),
                        jo.getString("TAcc_Level8_Len"),
                        jo.getString("GAcc_Level9_Len"),
                        jo.getString("TAcc_Level9_Len"),
                        jo.getString("Tafsil_Len"),
                        jo.getString("GTafsil_Len")
                );
            };


        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void Fill_Company(){
        try{
            rslt = "START";
            caller_Get_Company c = new caller_Get_Company();
            c.con = this;

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

            final ArrayList<List_Company> list = new ArrayList<List_Company>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Company(
                        jo.getString("Name"),
                        jo.getString("Path")
                ));
            };

            final adapter_Company adapter;
            adapter= new adapter_Company(this, list);
            cmbCompany.setAdapter(adapter);

            cmbCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Fill_Year(list.get(i).Get_Name());
                    sCompany = list.get(i).Get_Name();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception ex){
            Toast.makeText(
                    this,
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
    private void Fill_Year(String CoName){
        try{
            rslt = "START";
            caller_Get_FinYear c = new caller_Get_FinYear();
            c.con = this;
            c.sCoName = CoName;

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

            final ArrayList<List_Year> list = new ArrayList<List_Year>();

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jo  = jArray.getJSONObject(i);

                list.add(new List_Year(
                        jo.getString("Name"),
                        jo.getString("FinYear"),
                        jo.getString("Description"),
                        jo.getString("LastYear")
                ));
            };

            final adapter_Year adapter;
            adapter= new adapter_Year(this, list);
            cmbYear.setAdapter(adapter);

            cmbYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    sFinYear = list.get(i).Get_FinYear();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }catch (Exception ex){
            Toast.makeText(
                    this,
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

}
