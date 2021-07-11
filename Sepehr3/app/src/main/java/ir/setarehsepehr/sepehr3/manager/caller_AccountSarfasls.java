package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
public class caller_AccountSarfasls extends Thread {
    private callSoap_AccountSarfasls c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sTafsilNo;
    public String sLevelLen;

    public void run(){
        try{
            c=new callSoap_AccountSarfasls();
            String resp=c.Call(con,sCoName,sYear,sTafsilNo,sLevelLen);
            FragmentShowJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentShowJournals.rslt=ex.toString();
        }
    }
}
