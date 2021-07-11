package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_JournalsRem extends Thread {
    private callSoap_JournalsRem c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sAccQuery;
    public String sStartDate;
    public String sEndDate;
    public String sTafsilNo;

    public void run(){
        try{
            c=new callSoap_JournalsRem();
            String resp=c.Call(con,sCoName,sYear,sAccQuery,sStartDate,sEndDate,sTafsilNo);
            FragmentShowJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentShowJournals.rslt=ex.toString();
        }
    }
}
