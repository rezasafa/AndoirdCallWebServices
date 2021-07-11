package ir.setarehsepehr.sepehr3.manager;
import java.text.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sVar {
    private String WebAddress = "http://192.168.1.3:84"; //Company
    //private String WebAddress = "http://192.168.1.101:84"; //Home
    //private String WebAddress = "http://192.168.43.251:84"; //hotspot sony

    private String WAPI = "/ws/Sepehrsws.asmx";
    private String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    public sVar(){};

    public void Set_WebAddress(String WebAdd){WebAddress = WebAdd;}

    public String Get_WebAddress(){return WebAddress + Get_WAPI();}
    public String Get_WAPI(){return WAPI;}
    public String Get_WSDL_TARGET_NAMESPACE(){return WSDL_TARGET_NAMESPACE;}

    private String Operator_CheckConnection = "CheckConnection";
    private String Operator_BalanceSheet = "Get_Taraz";
    private String Operator_Init = "Get_Init_Option";
    private String Operator_InitAcc = "Get_InitAcc";
    private String Operator_Journals = "Get_Journal";
    private String Operator_Journals_Rem = "Get_Journal_Rem";
    private String Operator_Get_Accounts = "Get_Account";
    private String Operator_Get_Tafsils = "Get_Tafsil";
    private String Operator_Get_AccountSarfasls = "Get_AccountSarfasls";
    private String Operator_Get_Tafsil1_Accounts = "Get_Tafsil1_Accounts";
    private String Operator_Get_Tafsil2_Accounts = "Get_Tafsil2_Accounts";
    private String Operator_Get_Tafsil3_Accounts = "Get_Tafsil3_Accounts";
    private String Operator_Get_Tafsil1_Tafsils = "Get_Tafsil1_Tafsils";
    private String Operator_Get_Tafsil2_Tafsils = "Get_Tafsil2_Tafsils";
    private String Operator_Get_Tafsil3_Tafsils = "Get_Tafsil3_Tafsils";
    private String Operator_Get_Documents = "Get_Documents";
    private String Operator_Get_DocumentsShow = "Get_Journal_Doc";
    private String Operator_Get_FinYear = "Get_FinYear";
    private String Operator_Get_Company = "Get_Company";

    public String Get_Operator_CheckConnection(){return Operator_CheckConnection;}
    public String Get_Operator_BalanceSheet(){return Operator_BalanceSheet;}
    public String Get_Operator_Init(){return Operator_Init;}
    public String Get_Operator_InitAcc(){return Operator_InitAcc;}
    public String Get_Operator_Journals(){return Operator_Journals;}
    public String Get_Operator_Journals_Rem(){return Operator_Journals_Rem;}
    public String Get_Operator_Get_Accounts(){return Operator_Get_Accounts;}
    public String Get_Operator_Get_Tafsils(){return Operator_Get_Tafsils;}
    public String Get_Operator_Get_AccountSarfasls(){return Operator_Get_AccountSarfasls;}
    public String Get_Operator_Get_Tafsil1_Accounts(){return Operator_Get_Tafsil1_Accounts;}
    public String Get_Operator_Get_Tafsil2_Accounts(){return Operator_Get_Tafsil2_Accounts;}
    public String Get_Operator_Get_Tafsil3_Accounts(){return Operator_Get_Tafsil3_Accounts;}
    public String Get_Operator_Get_Tafsil1_Tafsils(){return Operator_Get_Tafsil1_Tafsils;}
    public String Get_Operator_Get_Tafsil2_Tafsils(){return Operator_Get_Tafsil2_Tafsils;}
    public String Get_Operator_Get_Tafsil3_Tafsils(){return Operator_Get_Tafsil3_Tafsils;}
    public String Get_Operator_Get_Documents(){return Operator_Get_Documents;}
    public String Get_Operator_Get_DocumentsShow(){return Operator_Get_DocumentsShow;}
    public String Get_Operator_Get_FinYear(){return Operator_Get_FinYear;}
    public String Get_Operator_Get_Company(){return Operator_Get_Company;}

    public String Get_CurrentDate(){
        Date today  = new Date();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String MiladiDt = df.format(today);

        int y = Integer.parseInt(MiladiDt.substring(0, 4));
        int m  = Integer.parseInt(MiladiDt.substring(5, 7));
        int d  = Integer.parseInt(MiladiDt.substring(8, 10));

        String PersianDt = PersianCalendar.getPersianDate(y,m,d);

        Date dt;
        try {
            dt = df.parse(PersianDt);
            PersianDt = df.format(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return PersianDt;
    }

    public String MakeHourMinute(int Val){
        if(String.valueOf(Val).length() == 1)
            return "0" + String.valueOf(Val);
        else
            return String.valueOf(Val);
    }

    public String Get_CurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        return currentTime;
    }

    public String Get_CurrentHour(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String currentTime = sdf.format(new Date());
        return currentTime;
    }

    public String Get_CurrentHour(int NextHour){
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String currentTime = sdf.format(new Date());
        return MakeHourMinute(Integer.parseInt(currentTime) + NextHour);
    }

    public String removeChar(String str, char c){
        StringBuilder strNew=new StringBuilder(str.length());
        char charRead;
        for(int i=0;i<str.length();i++){
            charRead=str.charAt(i);
            if(charRead!=c)
                strNew.append(charRead);
        }
        return strNew.toString();
    }

    public String CurrencyFormat(Long value){
        NumberFormat formatter = new DecimalFormat("#,###");
        //double myNumber = 1000000;
        return  formatter.format(value );
//formattedNumber is equal to 1,000,000
    }
}

