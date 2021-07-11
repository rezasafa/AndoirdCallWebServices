package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Accounts extends Thread {
    private callSoap_Accounts c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sWheres;

    public void run(){
        try{
            c=new callSoap_Accounts();
            String resp=c.Call(con,sCoName,sYear,sWheres);
            FragmentAccountJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentAccountJournals.rslt=ex.toString();
        }
    }
}
