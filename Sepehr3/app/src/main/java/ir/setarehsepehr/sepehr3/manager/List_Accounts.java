package ir.setarehsepehr.sepehr3.manager;

public class List_Accounts {
    String Acc_No;
    String Acc_Name;
    String LAcc_Name;

    public List_Accounts(){}
    public List_Accounts(String sAcc_No, String sAcc_Name, String sLAcc_Name){
        Acc_No = sAcc_No;
        Acc_Name = sAcc_Name;
        LAcc_Name = sLAcc_Name;
    }

    public String Get_Acc_No(){return Acc_No;}
    public String Get_Acc_Name(){return Acc_Name;}
    public String Get_LAcc_Name(){return LAcc_Name;}

    public void Set_Acc_No(String sAcc_No){Acc_No = sAcc_No;}
    public void Set_Acc_Name(String sAcc_Name){Acc_Name = sAcc_Name;}
    public void Set_LAcc_Name(String sLAcc_Name){LAcc_Name = sLAcc_Name;}
}
