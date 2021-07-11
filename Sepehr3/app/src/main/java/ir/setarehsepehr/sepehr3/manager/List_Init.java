package ir.setarehsepehr.sepehr3.manager;

import android.content.AbstractThreadedSyncAdapter;

public class List_Init {
    //{"":"صنايع توليدي عروس","":"تهران - خيابان بهشتي - خيابان سرافراز\r\nكوچه پنجم - پلاك17 - واحد3","":"97/01/01","":"97/12/29"}]
    String Co_Name;
    String Co_Address;
    String Start_Date;
    String End_Date;
    boolean ATafsil1_Visible;
    boolean ATafsil2_Visible;
    boolean ATafsil3_Visible;

    public List_Init(){}
    public List_Init(String sCo_Name, String sCo_Address, String sStart_Date, String sEnd_Date,
            boolean bATafsil1_Visible,boolean bATafsil2_Visible,boolean bATafsil3_Visible
    ){
        Co_Name = sCo_Name;
        Co_Address = sCo_Address;
        Start_Date = sStart_Date;
        End_Date = sEnd_Date;
        ATafsil1_Visible = bATafsil1_Visible;
        ATafsil2_Visible = bATafsil2_Visible;
        ATafsil3_Visible = bATafsil3_Visible;
    }

    public String Get_Co_Name(){return Co_Name;}
    public String Get_Co_Address(){return Co_Address;}
    public String Get_Start_Date(){return Start_Date;}
    public String Get_End_Date(){return End_Date;}
    public boolean Get_ATafsil1_Visible(){return ATafsil1_Visible;}
    public boolean Get_ATafsil2_Visible(){return ATafsil2_Visible;}
    public boolean Get_ATafsil3_Visible(){return ATafsil3_Visible;}

    public void Set_Co_Name(String sCo_Name){Co_Name = sCo_Name;}
    public void Set_Co_Address(String sCo_Address){Co_Address = sCo_Address;}
    public void Set_Start_Date(String sStart_Date){Start_Date = sStart_Date;}
    public void Set_End_Date(String sEnd_Date){End_Date = sEnd_Date;}
    public void Set_ATafsil1_Visible(boolean bATafsil1_Visible){ATafsil1_Visible = bATafsil1_Visible;}
    public void Set_ATafsil2_Visible(boolean bATafsil2_Visible){ATafsil2_Visible = bATafsil2_Visible;}
    public void Set_ATafsil3_Visible(boolean bATafsil3_Visible){ATafsil3_Visible = bATafsil3_Visible;}

}
