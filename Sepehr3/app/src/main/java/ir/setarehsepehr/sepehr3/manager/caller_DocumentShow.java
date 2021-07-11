package ir.setarehsepehr.sepehr3.manager;
import android.content.Context;
public class caller_DocumentShow extends Thread{
    private callSoap_DocumentShow c;
    public Context con;
    public String sCoName;
    public String sYear;
    public String sWheres;

    public void run(){
        try{
            c=new callSoap_DocumentShow();
            String resp=c.Call(con,sCoName,sYear,sWheres);
            FragmentDocumentShow.rslt=resp;
        }catch(Exception ex) {
            FragmentDocumentShow.rslt=ex.toString();
        }
    }
}
