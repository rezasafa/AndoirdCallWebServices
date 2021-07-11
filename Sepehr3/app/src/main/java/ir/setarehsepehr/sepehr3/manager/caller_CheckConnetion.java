package ir.setarehsepehr.sepehr3.manager;

public class caller_CheckConnetion extends Thread {
    private callSoap_CheckConnetion c;
    public String source;
    public String IpConnections;
    public void run(){
        try{
            c=new callSoap_CheckConnetion();
            String resp=c.Call(IpConnections);
            if(source.equals("SelectCoYear")){
                ActivitySelectCoYear.rslt = resp;
            }
            else if (source.equals("SetIp")){
                SetIPAddress.rslt = resp;
            }else{
                FragmentSetting.rslt=resp;
            }

        }catch(Exception ex) {
            if (source.equals("SelectCoYear")){
                ActivitySelectCoYear.rslt = ex.toString();
            }
            else if (source.equals("SetIp") ){
                SetIPAddress.rslt = ex.toString();
            }else{
                FragmentSetting.rslt = ex.toString();
            }
        }
    }
}
