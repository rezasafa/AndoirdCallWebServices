package ir.setarehsepehr.sepehr3.manager;

public class List_Company {
    String Name;
    String Path;

    public List_Company(){}
    public List_Company(String sName,String sPath){
        Name = sName;
        Path = sPath;
    }

    public String Get_Name(){return Name;}
    public String Get_Path(){return Path;}

    public void Set_Name(String sName){Name = sName;}
    public void Set_Path(String sPath){Path = sPath;}
}

