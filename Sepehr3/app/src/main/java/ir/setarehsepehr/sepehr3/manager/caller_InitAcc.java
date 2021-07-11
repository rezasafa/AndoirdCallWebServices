package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_InitAcc extends Thread {
    private callSoap_InitAcc c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String source;

    public void run(){
        try{
            c=new callSoap_InitAcc();
            String resp=c.Call(con,sCoName,sYear);
            ActivitySelectCoYear.rslt=resp;
        }catch(Exception ex) {
            ActivitySelectCoYear.rslt=ex.toString();
        }
    }
}
