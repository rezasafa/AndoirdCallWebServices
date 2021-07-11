package ir.setarehsepehr.sepehr3.manager;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.midi.MidiOutputPort;

public class dbApp extends SQLiteOpenHelper {
    public static final int DATABASE_Version 		= 1;
    public static final String DATABASE_NAME 	    = "Sepehr3.db";

    public static final String TABLE_NAME_Setting = "Setting";
    public static final String S_IP               = "IP_Address";
    public static final String S_Just_IP          = "IP";
    public static final String S_Just_Port        = "Port";
    public static final String S_CoName           = "Co_Name";
    public static final String S_Year             = "Year";
    public static final String S_Mobile           = "Mobile";

    public static final String TABLE_NAME_BalanceSheet  = "BalanceSheet";
    public static final String BS_Acc_No                = "Acc_No";
    public static final String BS_Acc_Name              = "Acc_Name";
    public static final String BS_Tafsil                = "Tafsil";
    public static final String BS_TafsilNo              = "TafsilNo";
    public static final String BS_FirstRemDebit         = "FirstRemDebit";
    public static final String BS_FirstRemCredi         = "FirstRemCredi";
    public static final String BS_SumDebit              = "SumDebit";
    public static final String BS_SumCredit             = "SumCredit";
    public static final String BS_RemDebit              = "RemDebit";
    public static final String BS_RemCredit             = "RemCredit";
    public static final String BS_FirstRemDebitSumDebit   = "FirstRemDebitSumDebit";
    public static final String BS_FirstRemCreditSumCredit = "FirstRemCreditSumCredit";
    public static final String BS_RemDebitFinal         = "RemDebitFinal";
    public static final String BS_RemCreditFina         = "RemCreditFina";
    public static final String BS_SumQty                = "SumQty";

    public static final String TABLE_NAME_Init	= "Init";
    public static final String I_Co_Name        = "Co_Name";
    public static final String I_Co_Address     = "Co_Address";
    public static final String I_Start_Date     = "Start_Date";
    public static final String I_End_Date       = "End_Date";
    public static final String I_ATafsil1_Visible = "ATafsil1_Visible";
    public static final String I_ATafsil2_Visible = "ATafsil2_Visible";
    public static final String I_ATafsil3_Visible = "ATafsil3_Visible";

    public static final String TABLE_NAME_InitAcc = "InitAcc";
    public static final String IA_Acc_Levels      = "Acc_Levels";
    public static final String IA_GL_Level        = "GL_Level";
    public static final String IA_Acc_Level1_Name = "Acc_Level1_Name";
    public static final String IA_Acc_Level1_Len  = "Acc_Level1_Len";
    public static final String IA_Acc_Level2_Name = "Acc_Level2_Name";
    public static final String IA_Acc_Level2_Len  = "Acc_Level2_Len";
    public static final String IA_Acc_Level3_Name = "Acc_Level3_Name";
    public static final String IA_Acc_Level3_Len  = "Acc_Level3_Len";
    public static final String IA_Acc_Level4_Name = "Acc_Level4_Name";
    public static final String IA_Acc_Level4_Len  = "Acc_Level4_Len";
    public static final String IA_Acc_Level5_Name = "Acc_Level5_Name";
    public static final String IA_Acc_Level5_Len  = "Acc_Level5_Len";
    public static final String IA_Acc_Level6_Name = "Acc_Level6_Name";
    public static final String IA_Acc_Level6_Len  = "Acc_Level6_Len";
    public static final String IA_Acc_Level7_Name = "Acc_Level7_Name";
    public static final String IA_Acc_Level7_Len  = "Acc_Level7_Len";
    public static final String IA_Acc_Level8_Name = "Acc_Level8_Name";
    public static final String IA_Acc_Level8_Len  = "Acc_Level8_Len";
    public static final String IA_Acc_Level9_Name = "Acc_Level9_Name";
    public static final String IA_Acc_Level9_Len  = "Acc_Level9_Len";
    public static final String IA_GAcc_Level1_Len = "GAcc_Level1_Len";
    public static final String IA_TAcc_Level1_Len = "TAcc_Level1_Len";
    public static final String IA_GAcc_Level2_Len = "GAcc_Level2_Len";
    public static final String IA_TAcc_Level2_Len = "TAcc_Level2_Len";
    public static final String IA_GAcc_Level3_Len = "GAcc_Level3_Len";
    public static final String IA_TAcc_Level3_Len = "TAcc_Level3_Len";
    public static final String IA_GAcc_Level4_Len = "GAcc_Level4_Len";
    public static final String IA_TAcc_Level4_Len = "TAcc_Level4_Len";
    public static final String IA_GAcc_Level5_Len = "GAcc_Level5_Len";
    public static final String IA_TAcc_Level5_Len = "TAcc_Level5_Len";
    public static final String IA_GAcc_Level6_Len = "GAcc_Level6_Len";
    public static final String IA_TAcc_Level6_Len = "TAcc_Level6_Len";
    public static final String IA_GAcc_Level7_Len = "GAcc_Level7_Len";
    public static final String IA_TAcc_Level7_Len = "TAcc_Level7_Len";
    public static final String IA_GAcc_Level8_Len = "GAcc_Level8_Len";
    public static final String IA_TAcc_Level8_Len = "TAcc_Level8_Len";
    public static final String IA_GAcc_Level9_Len = "GAcc_Level9_Len";
    public static final String IA_TAcc_Level9_Len = "TAcc_Level9_Len";
    public static final String IA_Tafsil_Len      = "Tafsil_Len";
    public static final String IA_GTafsil_Len     = "GTafsil_Len";

    //private HashMap hp;
    public dbApp(Context context) {
        super(context , DATABASE_NAME, null, DATABASE_Version);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String s = "" ;

        s= " CREATE TABLE " + TABLE_NAME_Setting + "( "+
                " " + S_CoName      + " TEXT ," +
                " " + S_Year        + " TEXT ," +
                " " + S_IP          + " TEXT ," +
                " " + S_Just_IP     + " TEXT ," +
                " " + S_Just_Port   + " TEXT ," +
                " " + S_Mobile      + " TEXT )";
        db.execSQL(s);

        s= " CREATE TABLE " + TABLE_NAME_BalanceSheet + "( "+
                " " + BS_Acc_No           + " TEXT ," +
                " " + BS_Acc_Name         + " TEXT ," +
                " " + BS_Tafsil           + " TEXT ," +
                " " + BS_TafsilNo         + " TEXT ," +
                " " + BS_FirstRemDebit    + " TEXT ," +
                " " + BS_FirstRemCredi    + " TEXT ," +
                " " + BS_SumDebit         + " TEXT ," +
                " " + BS_SumCredit        + " TEXT ," +
                " " + BS_RemDebit         + " TEXT ," +
                " " + BS_RemCredit        + " TEXT ," +
                " " + BS_FirstRemDebitSumDebit       + " TEXT ," +
                " " + BS_FirstRemCreditSumCredit      + " TEXT ," +
                " " + BS_RemDebitFinal    + " TEXT ," +
                " " + BS_RemCreditFina    + " TEXT ," +
                " " + BS_SumQty           + " TEXT )" ;
        db.execSQL(s);

        s = " CREATE TABLE " + TABLE_NAME_Init + "( " +
                " " + I_Co_Name        + " TEXT ," +
                " " + I_Co_Address     + " TEXT ," +
                " " + I_Start_Date     + " TEXT ," +
                " " + I_End_Date       + " TEXT ," +
                " " + I_ATafsil1_Visible + " TEXT ," +
                " " + I_ATafsil2_Visible + " TEXT ," +
                " " + I_ATafsil3_Visible + " TEXT )" ;
        db.execSQL(s);

        s = " CREATE TABLE " + TABLE_NAME_InitAcc + "( " +
                " " + IA_Acc_Levels      + " TEXT ," +
                " " + IA_GL_Level        + " TEXT ," +
                " " + IA_Acc_Level1_Name + " TEXT ," +
                " " + IA_Acc_Level1_Len  + " TEXT ," +
                " " + IA_Acc_Level2_Name + " TEXT ," +
                " " + IA_Acc_Level2_Len  + " TEXT ," +
                " " + IA_Acc_Level3_Name + " TEXT ," +
                " " + IA_Acc_Level3_Len  + " TEXT ," +
                " " + IA_Acc_Level4_Name + " TEXT ," +
                " " + IA_Acc_Level4_Len  + " TEXT ," +
                " " + IA_Acc_Level5_Name + " TEXT ," +
                " " + IA_Acc_Level5_Len  + " TEXT ," +
                " " + IA_Acc_Level6_Name + " TEXT ," +
                " " + IA_Acc_Level6_Len  + " TEXT ," +
                " " + IA_Acc_Level7_Name + " TEXT ," +
                " " + IA_Acc_Level7_Len  + " TEXT ," +
                " " + IA_Acc_Level8_Name + " TEXT ," +
                " " + IA_Acc_Level8_Len  + " TEXT ," +
                " " + IA_Acc_Level9_Name + " TEXT ," +
                " " + IA_Acc_Level9_Len  + " TEXT ," +
                " " + IA_GAcc_Level1_Len + " TEXT ," +
                " " + IA_TAcc_Level1_Len + " TEXT ," +
                " " + IA_GAcc_Level2_Len + " TEXT ," +
                " " + IA_TAcc_Level2_Len + " TEXT ," +
                " " + IA_GAcc_Level3_Len + " TEXT ," +
                " " + IA_TAcc_Level3_Len + " TEXT ," +
                " " + IA_GAcc_Level4_Len + " TEXT ," +
                " " + IA_TAcc_Level4_Len + " TEXT ," +
                " " + IA_GAcc_Level5_Len + " TEXT ," +
                " " + IA_TAcc_Level5_Len + " TEXT ," +
                " " + IA_GAcc_Level6_Len + " TEXT ," +
                " " + IA_TAcc_Level6_Len + " TEXT ," +
                " " + IA_GAcc_Level7_Len + " TEXT ," +
                " " + IA_TAcc_Level7_Len + " TEXT ," +
                " " + IA_GAcc_Level8_Len + " TEXT ," +
                " " + IA_TAcc_Level8_Len + " TEXT ," +
                " " + IA_GAcc_Level9_Len + " TEXT ," +
                " " + IA_TAcc_Level9_Len + " TEXT ," +
                " " + IA_Tafsil_Len      + " TEXT ," +
                " " + IA_GTafsil_Len     + " TEXT )" ;
        db.execSQL(s);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Setting);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BalanceSheet);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_InitAcc);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Init);

        onCreate(db);

    }

    //Setting Transation
    public Cursor Display_Seeting(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_Setting;
        return db.rawQuery(s, null) ;
    }
    public void Insert_Setting(String sIP,String sCoName,String sYear,String sMobile) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(S_IP     ,sIP);
        cv.put(S_CoName ,sCoName);
        cv.put(S_Year   ,sYear);
        cv.put(S_Mobile, sMobile);
        cv.put(S_Just_IP,"");
        cv.put(S_Just_Port,"");
        db.insert(TABLE_NAME_Setting , null, cv);
    }
    public void Update_Setting_Ip(String sIp){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_IP + " = '" + sIp + "'";
        db.execSQL(s);
    }
    public void Update_Setting_Ip_Port(String sIp,String sPort){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_Just_IP + " = '" + sIp + "', " + S_Just_Port + " = '" + sPort + "'";
        db.execSQL(s);
    }
    public void Update_Setting_CoName(String sCoName){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_CoName + " = '" + sCoName + "'";
        db.execSQL(s);
    }
    public void Update_Setting_Year(String sYear){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_Year + " = '" + sYear + "'";
        db.execSQL(s);
    }
    public boolean Check_Setting (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_Setting ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_Setting(){
        String s = "DELETE FROM " + TABLE_NAME_Setting;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_Setting_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_Setting,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //Init Transaction
    public Cursor Display_Init(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_Init ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_Init  (String sCo_Name,String sCo_Address,String sStart_Date,String sEnd_Date,
                              String sATafsil1_Visible,String sATafsil2_Visible,String sATafsil3_Visible)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(I_Co_Name     ,sCo_Name   );
        cv.put(I_Co_Address  ,sCo_Address);
        cv.put(I_Start_Date  ,sStart_Date);
        cv.put(I_End_Date    ,sEnd_Date  );
        cv.put(I_ATafsil1_Visible,sATafsil1_Visible);
        cv.put(I_ATafsil2_Visible,sATafsil2_Visible);
        cv.put(I_ATafsil3_Visible,sATafsil3_Visible);

        db.insert(TABLE_NAME_Init , null, cv);
    }
    public boolean Check_Init (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_Init ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_Init(){
        String s = "DELETE FROM " + TABLE_NAME_Init;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_Init_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_Init,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //InitAcc Transaction
    public Cursor Display_InitAcc(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_InitAcc ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_InitAcc  (
            String sAcc_Levels,String sGL_Level,
            String sAcc_Level1_Name,String sAcc_Level1_Len,
            String sAcc_Level2_Name,String sAcc_Level2_Len,
            String sAcc_Level3_Name,String sAcc_Level3_Len,
            String sAcc_Level4_Name,String sAcc_Level4_Len,
            String sAcc_Level5_Name,String sAcc_Level5_Len,
            String sAcc_Level6_Name,String sAcc_Level6_Len,
            String sAcc_Level7_Name,String sAcc_Level7_Len,
            String sAcc_Level8_Name,String sAcc_Level8_Len,
            String sAcc_Level9_Name,String sAcc_Level9_Len,
            String sGAcc_Level1_Len,String sTAcc_Level1_Len,
            String sGAcc_Level2_Len,String sTAcc_Level2_Len,
            String sGAcc_Level3_Len,String sTAcc_Level3_Len,
            String sGAcc_Level4_Len,String sTAcc_Level4_Len,
            String sGAcc_Level5_Len,String sTAcc_Level5_Len,
            String sGAcc_Level6_Len,String sTAcc_Level6_Len,
            String sGAcc_Level7_Len,String sTAcc_Level7_Len,
            String sGAcc_Level8_Len,String sTAcc_Level8_Len,
            String sGAcc_Level9_Len,String sTAcc_Level9_Len,
            String sTafsil_Len,String sGTafsil_Len
    )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(IA_Acc_Levels,sAcc_Levels      );
        cv.put(IA_GL_Level,sGL_Level        );
        cv.put(IA_Acc_Level1_Name,sAcc_Level1_Name );
        cv.put(IA_Acc_Level1_Len,sAcc_Level1_Len  );
        cv.put(IA_Acc_Level2_Name,sAcc_Level2_Name );
        cv.put(IA_Acc_Level2_Len,sAcc_Level2_Len  );
        cv.put(IA_Acc_Level3_Name,sAcc_Level3_Name );
        cv.put(IA_Acc_Level3_Len,sAcc_Level3_Len  );
        cv.put(IA_Acc_Level4_Name,sAcc_Level4_Name );
        cv.put(IA_Acc_Level4_Len,sAcc_Level4_Len  );
        cv.put(IA_Acc_Level5_Name,sAcc_Level5_Name );
        cv.put(IA_Acc_Level5_Len,sAcc_Level5_Len  );
        cv.put(IA_Acc_Level6_Name,sAcc_Level6_Name );
        cv.put(IA_Acc_Level6_Len,sAcc_Level6_Len  );
        cv.put(IA_Acc_Level7_Name,sAcc_Level7_Name );
        cv.put(IA_Acc_Level7_Len,sAcc_Level7_Len  );
        cv.put(IA_Acc_Level8_Name,sAcc_Level8_Name );
        cv.put(IA_Acc_Level8_Len,sAcc_Level8_Len  );
        cv.put(IA_Acc_Level9_Name,sAcc_Level9_Name );
        cv.put(IA_Acc_Level9_Len,sAcc_Level9_Len  );
        cv.put(IA_GAcc_Level1_Len,sGAcc_Level1_Len );
        cv.put(IA_TAcc_Level1_Len,sTAcc_Level1_Len );
        cv.put(IA_TAcc_Level2_Len,sTAcc_Level2_Len );
        cv.put(IA_GAcc_Level3_Len,sGAcc_Level3_Len );
        cv.put(IA_TAcc_Level3_Len,sTAcc_Level3_Len );
        cv.put(IA_GAcc_Level4_Len,sGAcc_Level4_Len );
        cv.put(IA_TAcc_Level4_Len,sTAcc_Level4_Len );
        cv.put(IA_GAcc_Level5_Len,sGAcc_Level5_Len );
        cv.put(IA_TAcc_Level5_Len,sTAcc_Level5_Len );
        cv.put(IA_GAcc_Level6_Len,sGAcc_Level6_Len );
        cv.put(IA_TAcc_Level6_Len,sTAcc_Level6_Len );
        cv.put(IA_GAcc_Level7_Len,sGAcc_Level7_Len );
        cv.put(IA_TAcc_Level7_Len,sTAcc_Level7_Len );
        cv.put(IA_GAcc_Level8_Len,sGAcc_Level8_Len );
        cv.put(IA_GAcc_Level2_Len,sGAcc_Level2_Len );
        cv.put(IA_TAcc_Level8_Len,sTAcc_Level8_Len );
        cv.put(IA_GAcc_Level9_Len,sGAcc_Level9_Len );
        cv.put(IA_TAcc_Level9_Len,sTAcc_Level9_Len );
        cv.put(IA_Tafsil_Len,sTafsil_Len      );
        cv.put(IA_GTafsil_Len,sGTafsil_Len     );
        //cv.put(C_Body,cBody);

        db.insert(TABLE_NAME_InitAcc , null, cv);
    }
    public boolean Check_InitAcc (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_InitAcc ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_InitAcc(){
        String s = "DELETE FROM " + TABLE_NAME_InitAcc;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_InitAcc_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_InitAcc,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //BalanceSheet Transaction
    public Cursor Display_BalanceSheet(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_BalanceSheet ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_BalanceSheet  (String sAcc_No, String sAcc_Name, String sTafsil, String sTafsilNo, String sFirstRemDebit, String sFirstRemCredi, String sSumDebit, String sSumCredit, String sRemDebit, String sRemCredit, String sFirstRemDebitSumDebit, String sFirstRemCreditSumCredit, String sRemDebitFinal, String sRemCreditFina, String sSumQty)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BS_Acc_No         ,sAcc_No         );
        cv.put(BS_Acc_Name       ,sAcc_Name       );
        cv.put(BS_Tafsil         ,sTafsil         );
        cv.put(BS_TafsilNo       ,sTafsilNo       );
        cv.put(BS_FirstRemDebit  ,sFirstRemDebit  );
        cv.put(BS_FirstRemCredi  ,sFirstRemCredi  );
        cv.put(BS_SumDebit       ,sSumDebit       );
        cv.put(BS_SumCredit      ,sSumCredit      );
        cv.put(BS_RemDebit       ,sRemDebit       );
        cv.put(BS_RemCredit      ,sRemCredit      );
        cv.put(BS_FirstRemDebitSumDebit     ,sFirstRemDebitSumDebit     );
        cv.put(BS_FirstRemCreditSumCredit    ,sFirstRemCreditSumCredit    );
        cv.put(BS_RemDebitFinal  ,sRemDebitFinal  );
        cv.put(BS_RemCreditFina  ,sRemCreditFina  );
        cv.put(BS_SumQty         ,sSumQty         );
        db.insert(TABLE_NAME_BalanceSheet , null, cv);
    }
    public boolean Check_BalanceSheet (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_BalanceSheet ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_BalanceSheet(){
        String s = "DELETE FROM " + TABLE_NAME_BalanceSheet;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_BalanceSheet_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_BalanceSheet,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

}
/*
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class dbApp extends SQLiteOpenHelper {
    public static final int DATABASE_Version 		= 1;
    public static final String DATABASE_NAME 	    = "Sepehr3.db";

    public static final String TABLE_NAME_Setting = "Setting";
    public static final String S_IP               = "IP_Address";
    public static final String S_CoName           = "Co_Name";
    public static final String S_Year             = "Year";

    public static final String TABLE_NAME_BalanceSheet  = "BalanceSheet";
    public static final String BS_Acc_No                = "Acc_No";
    public static final String BS_Acc_Name              = "Acc_Name";
    public static final String BS_Tafsil                = "Tafsil";
    public static final String BS_TafsilNo              = "TafsilNo";
    public static final String BS_FirstRemDebit         = "FirstRemDebit";
    public static final String BS_FirstRemCredi         = "FirstRemCredi";
    public static final String BS_SumDebit              = "SumDebit";
    public static final String BS_SumCredit             = "SumCredit";
    public static final String BS_RemDebit              = "RemDebit";
    public static final String BS_RemCredit             = "RemCredit";
    public static final String BS_FirstRemDebitSumDebit   = "FirstRemDebitSumDebit";
    public static final String BS_FirstRemCreditSumCredit = "FirstRemCreditSumCredit";
    public static final String BS_RemDebitFinal         = "RemDebitFinal";
    public static final String BS_RemCreditFina         = "RemCreditFina";
    public static final String BS_SumQty                = "SumQty";

    public static final String TABLE_NAME_Init	= "Init";
    public static final String I_Co_Name        = "Co_Name";
    public static final String I_Co_Address     = "Co_Address";
    public static final String I_Start_Date     = "Start_Date";
    public static final String I_End_Date       = "End_Date";
    public static final String I_ATafsil1_Visible = "ATafsil1_Visible";
    public static final String I_ATafsil2_Visible = "ATafsil2_Visible";
    public static final String I_ATafsil3_Visible = "ATafsil3_Visible";

    public static final String TABLE_NAME_InitAcc = "InitAcc";
    public static final String IA_Acc_Levels      = "Acc_Levels";
    public static final String IA_GL_Level        = "GL_Level";
    public static final String IA_Acc_Level1_Name = "Acc_Level1_Name";
    public static final String IA_Acc_Level1_Len  = "Acc_Level1_Len";
    public static final String IA_Acc_Level2_Name = "Acc_Level2_Name";
    public static final String IA_Acc_Level2_Len  = "Acc_Level2_Len";
    public static final String IA_Acc_Level3_Name = "Acc_Level3_Name";
    public static final String IA_Acc_Level3_Len  = "Acc_Level3_Len";
    public static final String IA_Acc_Level4_Name = "Acc_Level4_Name";
    public static final String IA_Acc_Level4_Len  = "Acc_Level4_Len";
    public static final String IA_Acc_Level5_Name = "Acc_Level5_Name";
    public static final String IA_Acc_Level5_Len  = "Acc_Level5_Len";
    public static final String IA_Acc_Level6_Name = "Acc_Level6_Name";
    public static final String IA_Acc_Level6_Len  = "Acc_Level6_Len";
    public static final String IA_Acc_Level7_Name = "Acc_Level7_Name";
    public static final String IA_Acc_Level7_Len  = "Acc_Level7_Len";
    public static final String IA_Acc_Level8_Name = "Acc_Level8_Name";
    public static final String IA_Acc_Level8_Len  = "Acc_Level8_Len";
    public static final String IA_Acc_Level9_Name = "Acc_Level9_Name";
    public static final String IA_Acc_Level9_Len  = "Acc_Level9_Len";
    public static final String IA_GAcc_Level1_Len = "GAcc_Level1_Len";
    public static final String IA_TAcc_Level1_Len = "TAcc_Level1_Len";
    public static final String IA_GAcc_Level2_Len = "GAcc_Level2_Len";
    public static final String IA_TAcc_Level2_Len = "TAcc_Level2_Len";
    public static final String IA_GAcc_Level3_Len = "GAcc_Level3_Len";
    public static final String IA_TAcc_Level3_Len = "TAcc_Level3_Len";
    public static final String IA_GAcc_Level4_Len = "GAcc_Level4_Len";
    public static final String IA_TAcc_Level4_Len = "TAcc_Level4_Len";
    public static final String IA_GAcc_Level5_Len = "GAcc_Level5_Len";
    public static final String IA_TAcc_Level5_Len = "TAcc_Level5_Len";
    public static final String IA_GAcc_Level6_Len = "GAcc_Level6_Len";
    public static final String IA_TAcc_Level6_Len = "TAcc_Level6_Len";
    public static final String IA_GAcc_Level7_Len = "GAcc_Level7_Len";
    public static final String IA_TAcc_Level7_Len = "TAcc_Level7_Len";
    public static final String IA_GAcc_Level8_Len = "GAcc_Level8_Len";
    public static final String IA_TAcc_Level8_Len = "TAcc_Level8_Len";
    public static final String IA_GAcc_Level9_Len = "GAcc_Level9_Len";
    public static final String IA_TAcc_Level9_Len = "TAcc_Level9_Len";
    public static final String IA_Tafsil_Len      = "Tafsil_Len";
    public static final String IA_GTafsil_Len     = "GTafsil_Len";

    //private HashMap hp;
    public dbApp(Context context) {
        super(context , DATABASE_NAME, null, DATABASE_Version);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        String s = "" ;

        s= " CREATE TABLE " + TABLE_NAME_Setting + "( "+
                " " + S_CoName      + " TEXT ," +
                " " + S_Year        + " TEXT ," +
                " " + S_IP          + " TEXT )";
        db.execSQL(s);

        s= " CREATE TABLE " + TABLE_NAME_BalanceSheet + "( "+
                " " + BS_Acc_No           + " TEXT ," +
                " " + BS_Acc_Name         + " TEXT ," +
                " " + BS_Tafsil           + " TEXT ," +
                " " + BS_TafsilNo         + " TEXT ," +
                " " + BS_FirstRemDebit    + " TEXT ," +
                " " + BS_FirstRemCredi    + " TEXT ," +
                " " + BS_SumDebit         + " TEXT ," +
                " " + BS_SumCredit        + " TEXT ," +
                " " + BS_RemDebit         + " TEXT ," +
                " " + BS_RemCredit        + " TEXT ," +
                " " + BS_FirstRemDebitSumDebit       + " TEXT ," +
                " " + BS_FirstRemCreditSumCredit      + " TEXT ," +
                " " + BS_RemDebitFinal    + " TEXT ," +
                " " + BS_RemCreditFina    + " TEXT ," +
                " " + BS_SumQty           + " TEXT )" ;
        db.execSQL(s);

        s = " CREATE TABLE " + TABLE_NAME_Init + "( " +
                " " + I_Co_Name        + " TEXT ," +
                " " + I_Co_Address     + " TEXT ," +
                " " + I_Start_Date     + " TEXT ," +
                " " + I_End_Date       + " TEXT ," +
                " " + I_ATafsil1_Visible + " TEXT ," +
                " " + I_ATafsil2_Visible + " TEXT ," +
                " " + I_ATafsil3_Visible + " TEXT )" ;
        db.execSQL(s);

        s = " CREATE TABLE " + TABLE_NAME_InitAcc + "( " +
                " " + IA_Acc_Levels      + " TEXT ," +
                " " + IA_GL_Level        + " TEXT ," +
                " " + IA_Acc_Level1_Name + " TEXT ," +
                " " + IA_Acc_Level1_Len  + " TEXT ," +
                " " + IA_Acc_Level2_Name + " TEXT ," +
                " " + IA_Acc_Level2_Len  + " TEXT ," +
                " " + IA_Acc_Level3_Name + " TEXT ," +
                " " + IA_Acc_Level3_Len  + " TEXT ," +
                " " + IA_Acc_Level4_Name + " TEXT ," +
                " " + IA_Acc_Level4_Len  + " TEXT ," +
                " " + IA_Acc_Level5_Name + " TEXT ," +
                " " + IA_Acc_Level5_Len  + " TEXT ," +
                " " + IA_Acc_Level6_Name + " TEXT ," +
                " " + IA_Acc_Level6_Len  + " TEXT ," +
                " " + IA_Acc_Level7_Name + " TEXT ," +
                " " + IA_Acc_Level7_Len  + " TEXT ," +
                " " + IA_Acc_Level8_Name + " TEXT ," +
                " " + IA_Acc_Level8_Len  + " TEXT ," +
                " " + IA_Acc_Level9_Name + " TEXT ," +
                " " + IA_Acc_Level9_Len  + " TEXT ," +
                " " + IA_GAcc_Level1_Len + " TEXT ," +
                " " + IA_TAcc_Level1_Len + " TEXT ," +
                " " + IA_GAcc_Level2_Len + " TEXT ," +
                " " + IA_TAcc_Level2_Len + " TEXT ," +
                " " + IA_GAcc_Level3_Len + " TEXT ," +
                " " + IA_TAcc_Level3_Len + " TEXT ," +
                " " + IA_GAcc_Level4_Len + " TEXT ," +
                " " + IA_TAcc_Level4_Len + " TEXT ," +
                " " + IA_GAcc_Level5_Len + " TEXT ," +
                " " + IA_TAcc_Level5_Len + " TEXT ," +
                " " + IA_GAcc_Level6_Len + " TEXT ," +
                " " + IA_TAcc_Level6_Len + " TEXT ," +
                " " + IA_GAcc_Level7_Len + " TEXT ," +
                " " + IA_TAcc_Level7_Len + " TEXT ," +
                " " + IA_GAcc_Level8_Len + " TEXT ," +
                " " + IA_TAcc_Level8_Len + " TEXT ," +
                " " + IA_GAcc_Level9_Len + " TEXT ," +
                " " + IA_TAcc_Level9_Len + " TEXT ," +
                " " + IA_Tafsil_Len      + " TEXT ," +
                " " + IA_GTafsil_Len     + " TEXT )" ;
        db.execSQL(s);

            }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Setting);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BalanceSheet);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_InitAcc);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_Init);

        onCreate(db);

    }

    //Setting Transation
    public Cursor Display_Seeting(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_Setting;
        return db.rawQuery(s, null) ;
    }
    public void Insert_Setting(String sIP,String sCoName,String sYear) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(S_IP     ,sIP);
        cv.put(S_CoName ,sCoName);
        cv.put(S_Year   ,sYear);

        db.insert(TABLE_NAME_Setting , null, cv);
    }
    public void Update_Setting_Ip(String sIp){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_IP + " = '" + sIp + "'";
        db.execSQL(s);
    }
    public void Update_Setting_CoName(String sCoName){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_CoName + " = '" + sCoName + "'";
        db.execSQL(s);
    }
    public void Update_Setting_Year(String sYear){
        SQLiteDatabase db = this.getWritableDatabase();

        String s= " UPDATE " + TABLE_NAME_Setting + " SET " + S_Year + " = '" + sYear + "'";
        db.execSQL(s);
    }
    public boolean Check_Setting (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_Setting ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_Setting(){
        String s = "DELETE FROM " + TABLE_NAME_Setting;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_Setting_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_Setting,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //Init Transaction
    public Cursor Display_Init(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_Init ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_Init  (String sCo_Name,String sCo_Address,String sStart_Date,String sEnd_Date,
                              String sATafsil1_Visible,String sATafsil2_Visible,String sATafsil3_Visible)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(I_Co_Name     ,sCo_Name   );
        cv.put(I_Co_Address  ,sCo_Address);
        cv.put(I_Start_Date  ,sStart_Date);
        cv.put(I_End_Date    ,sEnd_Date  );
        cv.put(I_ATafsil1_Visible,sATafsil1_Visible);
        cv.put(I_ATafsil2_Visible,sATafsil2_Visible);
        cv.put(I_ATafsil3_Visible,sATafsil3_Visible);

        db.insert(TABLE_NAME_Init , null, cv);
    }
    public boolean Check_Init (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_Init ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_Init(){
        String s = "DELETE FROM " + TABLE_NAME_Init;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_Init_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_Init,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //InitAcc Transaction
    public Cursor Display_InitAcc(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_InitAcc ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_InitAcc  (
            String sAcc_Levels,String sGL_Level,
            String sAcc_Level1_Name,String sAcc_Level1_Len,
            String sAcc_Level2_Name,String sAcc_Level2_Len,
            String sAcc_Level3_Name,String sAcc_Level3_Len,
            String sAcc_Level4_Name,String sAcc_Level4_Len,
            String sAcc_Level5_Name,String sAcc_Level5_Len,
            String sAcc_Level6_Name,String sAcc_Level6_Len,
            String sAcc_Level7_Name,String sAcc_Level7_Len,
            String sAcc_Level8_Name,String sAcc_Level8_Len,
            String sAcc_Level9_Name,String sAcc_Level9_Len,
            String sGAcc_Level1_Len,String sTAcc_Level1_Len,
            String sGAcc_Level2_Len,String sTAcc_Level2_Len,
            String sGAcc_Level3_Len,String sTAcc_Level3_Len,
            String sGAcc_Level4_Len,String sTAcc_Level4_Len,
            String sGAcc_Level5_Len,String sTAcc_Level5_Len,
            String sGAcc_Level6_Len,String sTAcc_Level6_Len,
            String sGAcc_Level7_Len,String sTAcc_Level7_Len,
            String sGAcc_Level8_Len,String sTAcc_Level8_Len,
            String sGAcc_Level9_Len,String sTAcc_Level9_Len,
            String sTafsil_Len,String sGTafsil_Len
    )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

            cv.put(IA_Acc_Levels,sAcc_Levels      );
            cv.put(IA_GL_Level,sGL_Level        );
            cv.put(IA_Acc_Level1_Name,sAcc_Level1_Name );
            cv.put(IA_Acc_Level1_Len,sAcc_Level1_Len  );
            cv.put(IA_Acc_Level2_Name,sAcc_Level2_Name );
            cv.put(IA_Acc_Level2_Len,sAcc_Level2_Len  );
            cv.put(IA_Acc_Level3_Name,sAcc_Level3_Name );
            cv.put(IA_Acc_Level3_Len,sAcc_Level3_Len  );
            cv.put(IA_Acc_Level4_Name,sAcc_Level4_Name );
            cv.put(IA_Acc_Level4_Len,sAcc_Level4_Len  );
            cv.put(IA_Acc_Level5_Name,sAcc_Level5_Name );
            cv.put(IA_Acc_Level5_Len,sAcc_Level5_Len  );
            cv.put(IA_Acc_Level6_Name,sAcc_Level6_Name );
            cv.put(IA_Acc_Level6_Len,sAcc_Level6_Len  );
            cv.put(IA_Acc_Level7_Name,sAcc_Level7_Name );
            cv.put(IA_Acc_Level7_Len,sAcc_Level7_Len  );
            cv.put(IA_Acc_Level8_Name,sAcc_Level8_Name );
            cv.put(IA_Acc_Level8_Len,sAcc_Level8_Len  );
            cv.put(IA_Acc_Level9_Name,sAcc_Level9_Name );
            cv.put(IA_Acc_Level9_Len,sAcc_Level9_Len  );
            cv.put(IA_GAcc_Level1_Len,sGAcc_Level1_Len );
            cv.put(IA_TAcc_Level1_Len,sTAcc_Level1_Len );
            cv.put(IA_TAcc_Level2_Len,sTAcc_Level2_Len );
            cv.put(IA_GAcc_Level3_Len,sGAcc_Level3_Len );
            cv.put(IA_TAcc_Level3_Len,sTAcc_Level3_Len );
            cv.put(IA_GAcc_Level4_Len,sGAcc_Level4_Len );
            cv.put(IA_TAcc_Level4_Len,sTAcc_Level4_Len );
            cv.put(IA_GAcc_Level5_Len,sGAcc_Level5_Len );
            cv.put(IA_TAcc_Level5_Len,sTAcc_Level5_Len );
            cv.put(IA_GAcc_Level6_Len,sGAcc_Level6_Len );
            cv.put(IA_TAcc_Level6_Len,sTAcc_Level6_Len );
            cv.put(IA_GAcc_Level7_Len,sGAcc_Level7_Len );
            cv.put(IA_TAcc_Level7_Len,sTAcc_Level7_Len );
            cv.put(IA_GAcc_Level8_Len,sGAcc_Level8_Len );
            cv.put(IA_GAcc_Level2_Len,sGAcc_Level2_Len );
            cv.put(IA_TAcc_Level8_Len,sTAcc_Level8_Len );
            cv.put(IA_GAcc_Level9_Len,sGAcc_Level9_Len );
            cv.put(IA_TAcc_Level9_Len,sTAcc_Level9_Len );
            cv.put(IA_Tafsil_Len,sTafsil_Len      );
            cv.put(IA_GTafsil_Len,sGTafsil_Len     );
        //cv.put(C_Body,cBody);

        db.insert(TABLE_NAME_InitAcc , null, cv);
    }
    public boolean Check_InitAcc (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_InitAcc ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_InitAcc(){
        String s = "DELETE FROM " + TABLE_NAME_InitAcc;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_InitAcc_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_InitAcc,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

    //BalanceSheet Transaction
    public Cursor Display_BalanceSheet(){
        SQLiteDatabase db  = this.getReadableDatabase();
        String s  = "SELECT * FROM " + TABLE_NAME_BalanceSheet ;
        return db.rawQuery(s, null) ;
    }
    public void Insert_BalanceSheet  (String sAcc_No, String sAcc_Name, String sTafsil, String sTafsilNo, String sFirstRemDebit, String sFirstRemCredi, String sSumDebit, String sSumCredit, String sRemDebit, String sRemCredit, String sFirstRemDebitSumDebit, String sFirstRemCreditSumCredit, String sRemDebitFinal, String sRemCreditFina, String sSumQty)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(BS_Acc_No         ,sAcc_No         );
        cv.put(BS_Acc_Name       ,sAcc_Name       );
        cv.put(BS_Tafsil         ,sTafsil         );
        cv.put(BS_TafsilNo       ,sTafsilNo       );
        cv.put(BS_FirstRemDebit  ,sFirstRemDebit  );
        cv.put(BS_FirstRemCredi  ,sFirstRemCredi  );
        cv.put(BS_SumDebit       ,sSumDebit       );
        cv.put(BS_SumCredit      ,sSumCredit      );
        cv.put(BS_RemDebit       ,sRemDebit       );
        cv.put(BS_RemCredit      ,sRemCredit      );
        cv.put(BS_FirstRemDebitSumDebit     ,sFirstRemDebitSumDebit     );
        cv.put(BS_FirstRemCreditSumCredit    ,sFirstRemCreditSumCredit    );
        cv.put(BS_RemDebitFinal  ,sRemDebitFinal  );
        cv.put(BS_RemCreditFina  ,sRemCreditFina  );
        cv.put(BS_SumQty         ,sSumQty         );
        db.insert(TABLE_NAME_BalanceSheet , null, cv);
    }
    public boolean Check_BalanceSheet (){
        SQLiteDatabase db = this.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_NAME_BalanceSheet ;
        Cursor c ;
        c = db.rawQuery(s, null);
        if (c.getCount() <= 0 ){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
    public void Delete_BalanceSheet(){
        String s = "DELETE FROM " + TABLE_NAME_BalanceSheet;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(s);
    }
    public String Read_BalanceSheet_Field(String FieldName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String fieldValue = "";
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_BalanceSheet,null);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                fieldValue = cursor.getString(cursor.getColumnIndex(FieldName));
            }
            cursor.close();
            return fieldValue;
        }finally {
            cursor.close();
        }
    }

}

*/