package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
public class caller_Tafsil3_Tafsils extends Thread {
    private callSoap_Tafsil3_Tafsils c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sTafsilNo;

    public void run(){
        try{
            c=new callSoap_Tafsil3_Tafsils();
            String resp=c.Call(con,sCoName,sYear,sTafsilNo);
            FragmentShowJournals.rslt=resp;
        }catch(Exception ex) {
            FragmentShowJournals.rslt=ex.toString();
        }
    }
}
