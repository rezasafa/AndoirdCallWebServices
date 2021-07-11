package ir.setarehsepehr.sepehr3.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSetting extends Fragment {
    public static String rslt;
    EditText et_txtIpAddress;
    Button btn_Save;
    Button btn_CheckConnection;

    public FragmentSetting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.mSetting));

        dbApp db = new dbApp(getContext());
        et_txtIpAddress = (EditText) getView().findViewById(R.id.fs_txtIP);
        btn_Save = (Button) getView().findViewById(R.id.fs_btnSave);
        btn_CheckConnection = (Button) getView().findViewById(R.id.fs_btnCheck);

        et_txtIpAddress.setText(db.Read_Setting_Field("IP_Address"));

        btn_CheckConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Check_Connecton()){
                    Toast.makeText(
                            getContext(),
                            getResources().getString(R.string.msgCheckConnections),
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Check_Connecton() == true){
                    Save();
                }
            }
        });
    }

    private boolean Check_Connecton(){
        try{
            rslt = "START";
            caller_CheckConnetion c = new caller_CheckConnetion();
            c.source = "";
            c.IpConnections = et_txtIpAddress.getText().toString();

            c.join();
            c.start();

            while (rslt == "START") {
                try {
                    Thread.sleep(10);
                } catch (Exception ex) {
                }
            }

            if(rslt.equals("1")){return true;}
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage() + "\n" +
                            getResources().getString(R.string.msgCheckIntenet),
                    Toast.LENGTH_LONG
            ).show();
            return false;
        }
        return true;
    }

    private void Save(){
        try{
            et_txtIpAddress = (EditText) getView().findViewById(R.id.fs_txtIP);
            dbApp db = new dbApp(getContext());
            if(db.Check_Setting() == true)
                db.Update_Setting_Ip(et_txtIpAddress.getText().toString());//.replace("/","=="));
            else
                db.Insert_Setting(et_txtIpAddress.getText().toString(),"","","");//.replace("/","=="));
            Toast.makeText(
                    getContext(),
                    getString(R.string.sSaveChange),
                    Toast.LENGTH_LONG
            ).show();
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}
