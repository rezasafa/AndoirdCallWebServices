package ir.setarehsepehr.sepehr3.manager;

public class List_Document {
    String ID;
    boolean Control;
    boolean Confirm;
    boolean Register;
    boolean Flag;
    String Doc_No;
    String Doc_Date;
    String Doc_Descr;
    String SumDebit;
    String SumCredit;
    String Attach;
    String Notes;
    String Doc_Type_Name;
    
    public List_Document(){}
    public List_Document(String sID,boolean bControl,boolean bConfirm,boolean bRegister,boolean bFlag,
                         String sDoc_No,String sDoc_Date,String sDoc_Descr,String sSumDebit,String sSumCredit,
                         String sAttach,String sNotes,String sDoc_Type_Name){
        ID = sID;
        Control = bControl;
        Confirm = bConfirm;
        Register = bRegister;
        Flag = bFlag;
        Doc_No = sDoc_No;
        Doc_Date = sDoc_Date;
        Doc_Descr = sDoc_Descr;
        SumDebit = sSumDebit;
        SumCredit = sSumCredit;
        Attach = sAttach;
        Notes = sNotes;
        Doc_Type_Name = sDoc_Type_Name;
    }
    public String Get_ID(){return ID;}
    public boolean Get_Control(){return Control;}
    public boolean Get_Confirm(){return Confirm;}
    public boolean Get_Register(){return Register;}
    public boolean Get_Flag(){return Flag;}
    public String Get_Doc_No(){return Doc_No;}
    public String Get_Doc_Date(){return Doc_Date;}
    public String Get_Doc_Descr(){return Doc_Descr;}
    public String Get_SumDebit(){return SumDebit;}
    public String Get_SumCredit(){return SumCredit;}
    public String Get_Attach(){return Attach;}
    public String Get_Notes(){return Notes;}
    public String Get_Doc_Type_Name(){return Doc_Type_Name;}

    public void Set_ID(String sID){ID = sID;}
    public void Set_Control(boolean bControl){Control = bControl;}
    public void Set_Confirm(boolean bConfirm){Confirm = bConfirm;}
    public void Set_Register(boolean bRegister){Register = bRegister;}
    public void Set_Flag(boolean bFlag){Flag = bFlag;}
    public void Set_Doc_No(String sDoc_No){Doc_No = sDoc_No;}
    public void Set_Doc_Date(String sDoc_Date){Doc_Date = sDoc_Date;}
    public void Set_Doc_Descr(String sDoc_Descr){Doc_Descr = sDoc_Descr;}
    public void Set_SumDebit(String sSumDebit){SumDebit = sSumDebit;}
    public void Set_SumCredit(String sSumCredit){SumCredit = sSumCredit;}
    public void Set_Attach(String sAttach){Attach = sAttach;}
    public void Set_Notes(String sNotes){Notes = sNotes;}
    public void Set_Doc_Type_Name(String sDoc_Type_Name){Doc_Type_Name = sDoc_Type_Name;}
}
/*
public class Documents
    {
        public string ID { get; set; }
        public bool Control { get; set; }
        public bool Confirm { get; set; }
        public bool Register { get; set; }
        public bool Flag { get; set; }
        public string Doc_No { get; set; }
        public string Doc_Date { get; set; }
        public string Doc_Descr { get; set; }
        public string SumDebit { get; set; }
        public string SumCredit { get; set; }
        public string Attach { get; set; }
        public string Notes { get; set; }
        public string Doc_Type_Name { get; set; }

    }

    public class DocumentJournal
    {
        public string Row { get; set; }
        public string Acc_No { get; set; }
        public string Acc_Name { get; set; }
        public string Tafsil1 { get; set; }
        public string Tafsil_Name1 { get; set; }
        public string Tafsil2 { get; set; }
        public string Tafsil_Name2 { get; set; }
        public string Tafsil3 { get; set; }
        public string Tafsil_Name3 { get; set; }
        public string Descr { get; set; }
        public string Debit { get; set; }
        public string Credit { get; set; }
        public string ID { get; set; }
    }
* */