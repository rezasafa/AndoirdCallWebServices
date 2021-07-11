package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Init extends Thread {
    private callSoap_Init c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String source;

    public void run(){
        try{
            c=new callSoap_Init();
            String resp=c.Call(con,sCoName,sYear);
            ActivitySelectCoYear.rslt=resp;
        }catch(Exception ex) {
            ActivitySelectCoYear.rslt=ex.toString();
        }
    }
}
