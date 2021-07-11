package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
public class caller_DocumentManager extends Thread {
    private callSoap_Documents c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sWheres;

    public void run(){
        try{
            c=new callSoap_Documents();
            String resp=c.Call(con,sCoName,sYear,sWheres);
            FragmentDocumentManager.rslt=resp;
        }catch(Exception ex) {
            FragmentDocumentManager.rslt=ex.toString();
        }
    }
}