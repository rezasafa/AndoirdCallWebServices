package ir.setarehsepehr.sepehr3.manager;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class Filter_Dialog extends Dialog  {

    public Activity c;
    public Dialog d;
    public Button yes, no;

    public Filter_Dialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filter_dialog);
        //yes = (Button) findViewById(R.id.btn_yes);
        //no = (Button) findViewById(R.id.btn_no);
        //yes.setOnClickListener(this);
        //no.setOnClickListener(this);

    }

    //@Override
    //public void onClick(View v) {
    //    switch (v.getId()) {
    //        case R.id.btn_yes:
    //            c.finish();
    //            break;
    //        case R.id.btn_no:
    //            dismiss();
    //            break;
    //        default:
    //            break;
    //    }
    //    dismiss();
    //}
}
