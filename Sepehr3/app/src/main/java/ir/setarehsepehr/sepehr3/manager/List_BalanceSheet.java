package ir.setarehsepehr.sepehr3.manager;

public class List_BalanceSheet {
    String Acc_No;
    String AccNo;
    String Acc_Name;
    String Tafsil;
    String TafsilNo;
    String FirstRemDebit;
    String FirstRemCredit;
    String SumDebit;
    String SumCredit;
    String RemDebit;
    String RemCredit;
    String FirstRemDebitSumDebit;
    String FirstRemCreditSumCredit;
    String RemDebitFinal;
    String RemCreditFinal;
    String SumQty;

    public List_BalanceSheet(){}
    public List_BalanceSheet(
            String sAcc_No, String sAccNo, String sAcc_Name, String sTafsil, String sTafsilNo,
            String lFirstRemDebit, String lFirstRemCredit, 
            String lSumDebit, String lSumCredit, 
            String lRemDebit, String lRemCredit,
            String lFirstRemDebitSumDebit, String lFirstRemCreditSumCredit, 
            String lRemDebitFinal, String lRemCreditFinal,
            String lSumQty
    ){
        Acc_No = sAcc_No;
        AccNo = sAccNo;
        Acc_Name = sAcc_Name;
        Tafsil = sTafsil;
        TafsilNo = sTafsilNo;
        FirstRemDebit = lFirstRemDebit;
        FirstRemCredit = lFirstRemCredit;
        SumDebit = lSumDebit;
        SumCredit = lSumCredit;
        RemDebit = lRemDebit;
        RemCredit = lRemCredit;
        FirstRemDebitSumDebit = lFirstRemDebitSumDebit;
        FirstRemCreditSumCredit = lFirstRemCreditSumCredit;
        RemDebitFinal = lRemDebitFinal;
        RemCreditFinal = lRemCreditFinal;
        SumQty = lSumQty;
    }

    public String Get_Acc_No(){return Acc_No;}
    public String Get_AccNo(){return AccNo;}
    public String Get_Acc_Name(){return Acc_Name;}
    public String Get_Tafsil(){return Tafsil;}
    public String Get_TafsilNo(){return TafsilNo;}
    public String Get_FirstRemDebit(){return FirstRemDebit;}
    public String Get_FirstRemCredit(){return FirstRemCredit;}
    public String Get_SumDebit(){return SumDebit;}
    public String Get_SumCredit(){return SumCredit;}
    public String Get_RemDebit(){return RemDebit;}
    public String Get_RemCredit(){return RemCredit;}
    public String Get_FirstRemDebitSumDebit(){return FirstRemDebitSumDebit;}
    public String Get_FirstRemCreditSumCredit(){return FirstRemCreditSumCredit;}
    public String Get_RemDebitFinal(){return RemDebitFinal;}
    public String Get_RemCreditFinal(){return RemCreditFinal;}
    public String Get_SumQty(){return SumQty;}

    public void Set_Acc_No(String sAcc_No){Acc_No=sAcc_No;}
    public void Set_AccNo(String sAccNo){AccNo=sAccNo;}
    public void Set_Acc_Name(String sAcc_Name){Acc_Name=sAcc_Name;}
    public void Set_Tafsil(String sTafsil){Tafsil=sTafsil;}
    public void Set_TafsilNo(String sTafsilNo){TafsilNo=sTafsilNo;}
    public void Set_FirstRemDebit(String lFirstRemDebit){FirstRemDebit= lFirstRemDebit;}
    public void Set_FirstRemCredit(String lFirstRemCredit){FirstRemCredit= lFirstRemCredit;}
    public void Set_SumDebit(String lSumDebit){SumDebit= lSumDebit;}
    public void Set_SumCredit(String lSumCredit){SumCredit= lSumCredit;}
    public void Set_RemDebit(String lRemDebit){RemDebit= lRemDebit;}
    public void Set_RemCredit(String lRemCredit){RemCredit= lRemCredit;}
    public void Set_FirstRemDebitSumDebit(String lFirstRemDebitSumDebit){FirstRemDebitSumDebit= lFirstRemDebitSumDebit;}
    public void Set_FirstRemCreditSumCredit(String lFirstRemCreditSumCredit){FirstRemCreditSumCredit= lFirstRemCreditSumCredit;}
    public void Set_RemDebitFinal(String lRemDebitFinal){RemDebitFinal= lRemDebitFinal;}
    public void Set_RemCreditFinal(String lRemCreditFinal){RemCreditFinal= lRemCreditFinal;}
    public void Set_SumQty(String lSumQty){SumQty= lSumQty;}
}
