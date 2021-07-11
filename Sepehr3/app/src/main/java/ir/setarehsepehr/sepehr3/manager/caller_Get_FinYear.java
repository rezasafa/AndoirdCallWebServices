package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Get_FinYear extends Thread {
    private callSoap_Get_FinYear c;
    public Context con;
    public String sCoName;

    public void run(){
        try{
            c=new callSoap_Get_FinYear();
            String resp=c.Call(con,sCoName);
            ActivitySelectCoYear.rslt=resp;
        }catch(Exception ex) {
            ActivitySelectCoYear.rslt=ex.toString();
        }
    }
}
