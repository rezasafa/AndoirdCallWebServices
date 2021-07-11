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

public class adapter_Tafsil_Journals extends BaseAdapter {
    Context contex;
    ArrayList<List_Tafsils> lt;

    public adapter_Tafsil_Journals(Context c,ArrayList ala){
        contex = c;
        lt = ala;
    }

    @Override
    public int getCount() {
        return lt.size();
    }

    @Override
    public Object getItem(int i) {
        return lt.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_tafsil_journals,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(view.getResources().getColor(R.color.colorNormal));

        if(i%2==0)
            gradientDrawable.setColor(view.getResources().getColor(R.color.colorDoIt));

        TextView tvAccNo = (TextView) view.findViewById(R.id.dtj_lblTafsil_No);
        tvAccNo.setText(view.getResources().getString(R.string.sAcc_No) + ": " + lt.get(i).Get_Tafsil_No());

        TextView tvAccName = (TextView) view.findViewById(R.id.dtj_lblTafsil_Name);
        tvAccName.setText(view.getResources().getString(R.string.sAcc_Name) + ": " + lt.get(i).Get_Tafsil_Name());

        /*
        TextView tvLAccName = (TextView) view.findViewById(R.id.dtj_lblLTafsil_Name);
        tvLAccName.setText(view.getResources().getString(R.string.sLTafsil_Name) + ": " + lt.get(i).Get_LTafsil_Name());
        */
        return view;
    }

}
