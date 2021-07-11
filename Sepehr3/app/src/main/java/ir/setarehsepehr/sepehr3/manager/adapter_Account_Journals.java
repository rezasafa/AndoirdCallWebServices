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

public class adapter_Account_Journals extends BaseAdapter {
    Context contex;
    ArrayList<List_Accounts> la;

    public adapter_Account_Journals(Context c,ArrayList ala){
        contex = c;
        la = ala;
    }

    @Override
    public int getCount() {
        return la.size();
    }

    @Override
    public Object getItem(int i) {
        return la.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_account_journal,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(view.getResources().getColor(R.color.colorNormal));

        if(i%2==0)
            gradientDrawable.setColor(view.getResources().getColor(R.color.colorDoIt));

        TextView tvAccNo = (TextView) view.findViewById(R.id.daj_lblAcc_No);
        tvAccNo.setText(view.getResources().getString(R.string.sAcc_No) + ": " + la.get(i).Get_Acc_No());

        TextView tvAccName = (TextView) view.findViewById(R.id.daj_lblAcc_Name);
        tvAccName.setText(view.getResources().getString(R.string.sAcc_Name) + ": " + la.get(i).Get_Acc_Name());

        /*TextView tvLAccName = (TextView) view.findViewById(R.id.daj_lblLAcc_Name);
        tvLAccName.setText(view.getResources().getString(R.string.sLAcc_Name) + ": " + la.get(i).Get_LAcc_Name());*/

        return view;
    }


}
