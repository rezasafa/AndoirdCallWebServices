package ir.setarehsepehr.sepehr3.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class adapter_Journals extends BaseAdapter {
    Context contex;
    ArrayList<List_Journals> ljs;

    public adapter_Journals(Context c,ArrayList aljs){
        contex = c;
        ljs = aljs;
    }

    @Override
    public int getCount() {
        return ljs.size();
    }

    @Override
    public Object getItem(int i) {
        return ljs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_journals,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(view.getResources().getColor(R.color.colorNormal));

        if(i%2==0)
            gradientDrawable.setColor(view.getResources().getColor(R.color.colorDoIt));

        TextView tvDoc_No = (TextView) view.findViewById(R.id.dj_lblDoc_No);
        tvDoc_No.setText("سند : " + ljs.get(i).Get_Doc_No());

        TextView tvDoc_Date = (TextView) view.findViewById(R.id.dj_lblDoc_Date);
        tvDoc_Date.setText("تاریخ : " + ljs.get(i).Get_Doc_Date());

        TextView tvDoc_Descr = (TextView) view.findViewById(R.id.dj_lblDoc_Desc);
        tvDoc_Descr.setText("شرح : " + ljs.get(i).Get_Descr());

        TextView tvDebit = (TextView) view.findViewById(R.id.dj_lblDebit);
        tvDebit.setText("بدهکار : " + ljs.get(i).Get_Debit());

        TextView tvCredit = (TextView) view.findViewById(R.id.dj_lblCredit);
        tvCredit.setText("بستانکار : " + ljs.get(i).Get_Credit());

        return view;
    }

}
