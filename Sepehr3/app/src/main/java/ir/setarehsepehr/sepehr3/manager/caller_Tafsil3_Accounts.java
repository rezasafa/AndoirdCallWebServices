package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
public class caller_Tafsil3_Accounts extends Thread {
    private callSoap_Tafsil3_Accounts c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sAcc_No;

    public void run(){
        try{
            c=new callSoap_Tafsil3_Accounts();
            String resp=c.Call(con,sCoName,sYear,sAcc_No);
            FragmentShowJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentShowJournals.rslt=ex.toString();
        }
    }
}

