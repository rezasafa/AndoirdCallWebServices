package ir.setarehsepehr.sepehr3.manager;

import android.content.Context;

public class caller_BalaceSheet extends Thread {
    private callSoap_BalanceSheet c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sHiarchical;
    public String sLevels;
    public String sCurrentLevel;
    public String sLevelLen;

    public void run(){
        try{
            c=new callSoap_BalanceSheet();
            String resp=c.Call(con,sCoName,sYear,sHiarchical,sLevels,sCurrentLevel,sLevelLen);
            FragmentShowBalanceSheet.rslt=resp;
        }catch(Exception ex) {
            FragmentShowBalanceSheet.rslt=ex.toString();
        }
    }
}
