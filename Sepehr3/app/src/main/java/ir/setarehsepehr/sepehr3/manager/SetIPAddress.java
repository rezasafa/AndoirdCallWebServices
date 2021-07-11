package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.setarehsepehr.sepehr3.manager.R;

public class SetIPAddress extends AppCompatActivity {
    public static String rslt;
    EditText etAddress;
    EditText etPort;
    Button btnCheckConnection;
    String IPS;
    String PORTS;
    Boolean Comback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_ipaddress);

        Comback = getIntent().getBooleanExtra("ComeBack",false);
        String ipdb="";
        final dbApp db = new dbApp(this);

        if(Comback == false){
            if(db.Check_Setting()){
                ipdb = db.Read_Setting_Field("IP_Address");
                if (!ipdb.equals("")){
                    if(Check_Connecton(ipdb)){
                        Intent i = new Intent(this,ActivitySelectCoYear.class);
                        startActivity(i);
                        finish();
                        return;
                    }
                }
            }
        }

        etAddress = (EditText) findViewById(R.id.sip_Address);
        etPort = (EditText) findViewById(R.id.sip_Port);
        btnCheckConnection = (Button) findViewById(R.id.sip_BtnCheckAddress);
        final Context context = this;

        //Toast.makeText(this,Comback.toString(),Toast.LENGTH_LONG).show();

        if(Comback == true){
            etAddress.setText(db.Read_Setting_Field("IP"));
            etPort.setText(db.Read_Setting_Field("Port"));
        }

        btnCheckConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String MyAddress="";

                if (!etAddress.getText().toString().equals("")) {
                    if (!etPort.getText().toString().equals(""))
                        MyAddress = "http://" + etAddress.getText().toString() + ":" + etPort.getText().toString();
                    else
                        MyAddress = "http://" + etAddress.getText().toString();

                    //Toast.makeText(context,MyAddress,Toast.LENGTH_LONG).show();

                    if(Check_Connecton(MyAddress)){
                        Toast.makeText(
                                context,
                                getString(R.string.sSaveChange),
                                Toast.LENGTH_LONG
                        ).show();
                        db.Delete_Setting();
                        db.Insert_Setting(MyAddress,"","","");
                        db.Update_Setting_Ip_Port(etAddress.getText().toString(),etPort.getText().toString());
                        Intent i = new Intent(context,ActivitySelectCoYear.class);
                        startActivity(i);
                        finish();
                    }
                    else
                        Toast.makeText(
                                context,
                                getString(R.string.msgNotIpAddress),
                                Toast.LENGTH_LONG
                        ).show();
                }
                else {
                    Toast.makeText(
                            context,
                            getString(R.string.msgNotIpAddress),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }

    private boolean Check_Connecton(String Address){
        try{
            rslt = "START";
            caller_CheckConnetion c = new caller_CheckConnetion();
            c.source = "SetIp";
            c.IpConnections = Address;

            c.join();
            c.start();

            while (rslt == "START") {
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                }
            }

            if(rslt.equals("1"))
            {return true;}
            else
            {return false;}
        }catch (Exception ex){
            Toast.makeText(
                    this,
                    ex.getMessage() + "\n" +
                            getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }
    }

}

