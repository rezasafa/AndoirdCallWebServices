package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Tafsils extends Thread {
    private callSoap_Tafsils c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sWheres;

    public void run(){
        try{
            c=new callSoap_Tafsils();
            String resp=c.Call(con,sCoName,sYear,sWheres);
            FragmentTafsilJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentTafsilJournals.rslt=ex.toString();
        }
    }
}
