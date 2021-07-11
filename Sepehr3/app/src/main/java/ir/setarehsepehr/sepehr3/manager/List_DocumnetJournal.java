package ir.setarehsepehr.sepehr3.manager;

public class List_DocumnetJournal {
    String Row;
    String Acc_No;
    String Acc_Name;
    String Tafsil1;
    String Tafsil_Name1;
    String Tafsil2;
    String Tafsil_Name2;
    String Tafsil3;
    String Tafsil_Name3;
    String Descr;
    String Debit;
    String Credit;
    String ID;
    
    public List_DocumnetJournal(){}
    public List_DocumnetJournal(
            String sRow, String sAcc_No, String sAcc_Name, String sTafsil1, String sTafsil_Name1, 
            String sTafsil2, String sTafsil_Name2, String sTafsil3, String sTafsil_Name3, String sDescr, 
            String sDebit, String sCredit, String sID
    ){
        Row = sRow;
        Acc_No = sAcc_No;
        Acc_Name = sAcc_Name;
        Tafsil1 = sTafsil1;
        Tafsil_Name1 = sTafsil_Name1;
        Tafsil2 = sTafsil2;
        Tafsil_Name2 = sTafsil_Name2;
        Tafsil3 = sTafsil3;
        Tafsil_Name3 = sTafsil_Name3;
        Descr = sDescr;
        Debit = sDebit;
        Credit = sCredit;
        ID = sID;
    }

    public String Get_Row(){return Row;}
    public String Get_Acc_No(){return Acc_No;}
    public String Get_Acc_Name(){return Acc_Name;}
    public String Get_Tafsil1(){return Tafsil1;}
    public String Get_Tafsil_Name1(){return Tafsil_Name1;}
    public String Get_Tafsil2(){return Tafsil2;}
    public String Get_Tafsil_Name2(){return Tafsil_Name2;}
    public String Get_Tafsil3(){return Tafsil3;}
    public String Get_Tafsil_Name3(){return Tafsil_Name3;}
    public String Get_Descr(){return Descr;}
    public String Get_Debit(){return Debit;}
    public String Get_Credit(){return Credit;}
    public String Get_ID(){return ID;}

    public void Set_Row(String sRow){Row = sRow;}
    public void Set_Acc_No(String sAcc_No){Acc_No = sAcc_No;}
    public void Set_Acc_Name(String sAcc_Name){Acc_Name = sAcc_Name;}
    public void Set_Tafsil1(String sTafsil1){Tafsil1 = sTafsil1;}
    public void Set_Tafsil_Name1(String sTafsil_Name1){Tafsil_Name1 = sTafsil_Name1;}
    public void Set_Tafsil2(String sTafsil2){Tafsil2 = sTafsil2;}
    public void Set_Tafsil_Name2(String sTafsil_Name2){Tafsil_Name2 = sTafsil_Name2;}
    public void Set_Tafsil3(String sTafsil3){Tafsil3 = sTafsil3;}
    public void Set_Tafsil_Name3(String sTafsil_Name3){Tafsil_Name3 = sTafsil_Name3;}
    public void Set_Descr(String sDescr){Descr = sDescr;}
    public void Set_Debit(String sDebit){Debit = sDebit;}
    public void Set_Credit(String sCredit){Credit = sCredit;}
    public void Set_ID(String sID){ID = sID;}
}
