package ir.setarehsepehr.sepehr3.manager;

public class List_Journals {

    String Acc_No;
    String Rows;
    String Register;
    String Doc_No;
    String Doc_Date;
    String Descr;
    String Debit;
    String Credit;
    String SDoc_No;
    String PDoc_No;
    String RDoc_No;
    String Row;
    String Flag;
    String Hidden;
    String Settle;
    String Counts;
    String Price;
    String ID;
    String Follow_Date;

    public List_Journals(){}
    public List_Journals(String sAcc_No, String sRows,String sRegister, String sDoc_No,String sDoc_Date, String sDescr,
                         String sDebit, String sCredit,String sSDoc_No, String sPDoc_No,String sRDoc_No, String sRow,
                         String sFlag, String sHidden,String sSettle, String sCounts,String sPrice, String sID,String sFollow_Date){
        Acc_No = sAcc_No;
        Rows = sRows;
        Register = sRegister;
        Doc_No = sDoc_No;
        Doc_Date = sDoc_Date;
        Descr = sDescr;
        Debit = sDebit;
        Credit = sCredit;
        SDoc_No = sSDoc_No;
        PDoc_No = sPDoc_No;
        RDoc_No = sRDoc_No;
        Row = sRow;
        Flag = sFlag;
        Hidden = sHidden;
        Settle = sSettle;
        Counts = sCounts;
        Price = sPrice;
        ID = sID;
        Follow_Date = sFollow_Date;
    }

    public String Get_Acc_No()      {return Acc_No;}
    public String Get_Rows()        {return Rows;}
    public String Get_Register()    {return Register;}
    public String Get_Doc_No()      {return Doc_No;}
    public String Get_Doc_Date()    {return Doc_Date;}
    public String Get_Descr()       {return Descr;}
    public String Get_Debit()       {return Debit;}
    public String Get_Credit()      {return Credit;}
    public String Get_SDoc_No()     {return SDoc_No;}
    public String Get_PDoc_No()     {return PDoc_No;}
    public String Get_RDoc_No()     {return RDoc_No;}
    public String Get_Row()         {return Row;}
    public String Get_Flag()        {return Flag;}
    public String Get_Hidden()      {return Hidden;}
    public String Get_Settle()      {return Settle;}
    public String Get_Counts()      {return Counts;}
    public String Get_Price()       {return Price;}
    public String Get_ID()          {return ID;}
    public String Get_Follow_Date() {return Follow_Date;}

    public void Set_Acc_No(String sAcc_No){Acc_No = sAcc_No;}
    public void Set_Rows(String sRows){Rows = sRows;}
    public void Set_Register(String sRegister){Register = sRegister;}
    public void Set_Doc_No(String sDoc_No){Doc_No = sDoc_No;}
    public void Set_Doc_Date(String sDoc_Date){Doc_Date = sDoc_Date;}
    public void Set_Descr(String sDescr){Descr = sDescr;}
    public void Set_Debit(String sDebit){Debit = sDebit;}
    public void Set_Credit(String sCredit){Credit = sCredit;}
    public void Set_SDoc_No(String sSDoc_No){SDoc_No = sSDoc_No;}
    public void Set_PDoc_No(String sPDoc_No){PDoc_No = sPDoc_No;}
    public void Set_RDoc_No(String sRDoc_No){RDoc_No = sRDoc_No;}
    public void Set_Row(String sRow){Row = sRow;}
    public void Set_Flag(String sFlag){Flag = sFlag;}
    public void Set_Hidden(String sHidden){Hidden = sHidden;}
    public void Set_Settle(String sSettle){Settle = sSettle;}
    public void Set_Counts(String sCounts){Counts = sCounts;}
    public void Set_Price(String sPrice){Price = sPrice;}
    public void Set_ID(String sID){ID = sID;}
    public void Set_Follow_Date(String sFollow_Date){Follow_Date = sFollow_Date;}
}

