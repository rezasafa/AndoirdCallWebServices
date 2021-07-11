package ir.setarehsepehr.sepehr3.manager;

public class List_Year {
    String Name;
    String FinYear;
    String Description;
    String LastYear;

    public List_Year(){}
    public List_Year(String sName,String sFinYear,String sDescription,String sLastYear){
        Name = sName;
        FinYear = sFinYear;
        Description = sDescription;
        LastYear = sLastYear;
    }

    public String Get_Name(){return Name;}
    public String Get_FinYear(){return FinYear;}
    public String Get_Description(){return Description;}
    public String Get_LastYear(){return LastYear;}

    public void Set_Name(String sName){Name = sName;}
    public void Set_FinYear(String sFinYear){FinYear = sFinYear;}
    public void Set_Description(String sDescription){Description = sDescription;}
    public void Set_LastYear(String sLastYear){LastYear = sLastYear;}
}
