package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_Journals extends Thread {
    private callSoap_Journals c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sAccQuery;
    public String sStartDate;
    public String sEndDate;
    public String sTafsilNo;

    public void run(){
        try{
            c=new callSoap_Journals();
            String resp=c.Call(con,sCoName,sYear,sAccQuery,sStartDate,sEndDate,sTafsilNo);
            FragmentShowJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentShowJournals.rslt=ex.toString();
        }
    }
}
