package ir.setarehsepehr.sepehr3.manager;

public class List_JournalsRem {
    String Debit;
    String Credit;
    String Rem;

    List_JournalsRem(){}
    List_JournalsRem(String sDebit,String sCredit,String sRem){
        Debit = sDebit;
        Credit = sCredit;
        Rem = sRem;
    }

    public String Get_Debit(){return Debit;}
    public String Get_Credit(){return Credit;}
    public String Get_Rem(){return Rem;}

    public void Set_Debit(String sDebit){Debit = sDebit;}
    public void Set_Credit(String sCredit){Credit = sCredit;}
    public void Set_Rem(String sRem){Rem = sRem;}
}
