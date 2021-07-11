package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Get_Company extends Thread {
    private callSoap_Get_Company c;
    public Context con;

    public void run(){
        try{
            c=new callSoap_Get_Company();
            String resp=c.Call(con);
            ActivitySelectCoYear.rslt=resp;
        }catch(Exception ex) {
            ActivitySelectCoYear.rslt=ex.toString();
        }
    }
}
