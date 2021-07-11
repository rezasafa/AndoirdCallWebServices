package ir.setarehsepehr.sepehr3.manager;

public class List_Tafsils {
    String G_No;
    String Tafsil_No;
    String Tafsil_Name;
    String LTafsil_Name;

    public List_Tafsils(){}
    public List_Tafsils(String sG_No, String sTafsil_No, String sTafsil_Name, String sLTafsil_Name){
        G_No = sG_No;
        Tafsil_No = sTafsil_No;
        Tafsil_Name = sTafsil_Name;
        LTafsil_Name = sLTafsil_Name;
    }

    public String Get_G_No(){return G_No;}
    public String Get_Tafsil_No(){return Tafsil_No;}
    public String Get_Tafsil_Name(){return Tafsil_Name;}
    public String Get_LTafsil_Name(){return LTafsil_Name;}

    public void Set_G_No(String sG_No){G_No = sG_No;}
    public void Set_Tafsil_No(String sTafsil_No){Tafsil_No = sTafsil_No;}
    public void Set_Tafsil_Name(String sTafsil_Name){Tafsil_Name = sTafsil_Name;}
    public void Set_LTafsil_Name(String sLTafsil_Name){LTafsil_Name = sLTafsil_Name;}
}
