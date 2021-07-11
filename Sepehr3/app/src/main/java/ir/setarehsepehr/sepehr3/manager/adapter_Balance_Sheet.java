package ir.setarehsepehr.sepehr3.manager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class adapter_Balance_Sheet extends BaseAdapter {
    Context contex;
    ArrayList<List_BalanceSheet> lbs;
    //ArrayList<List_BalanceSheet> filteredData;
    //private ItemFilter mFilter = new ItemFilter();

    public adapter_Balance_Sheet(Context c,ArrayList albs){
        contex = c;
        lbs = albs;
        //filteredData = albs;
    }

    @Override
    public int getCount() {
        return lbs.size();
    }

    @Override
    public Object getItem(int i) {
        return lbs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void setColor(int i, View view,Color c){
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(view.getResources().getColor(R.color.colorRequText));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_balance_sheet,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground().mutate();
        gradientDrawable.setColor(view.getResources().getColor(R.color.colorNormal));

        if(i%2==0)
            gradientDrawable.setColor(view.getResources().getColor(R.color.colorDoIt));

        TextView tvAccNo = (TextView) view.findViewById(R.id.bs_txtAccNo);
        tvAccNo.setText(lbs.get(i).Get_Acc_No());

        TextView tvAccName = (TextView) view.findViewById(R.id.bs_txtAccName);
        tvAccName.setText(lbs.get(i).Get_Acc_Name());

        TextView tvFirstRemDebitSumDebit = (TextView) view.findViewById(R.id.bs_txtFirstRemDebitSumDebit);
        tvFirstRemDebitSumDebit.setText(lbs.get(i).Get_FirstRemDebitSumDebit());

        TextView tvFirstRemCreditSumCredit = (TextView) view.findViewById(R.id.bs_txtFirstRemCreditSumCredit);
        tvFirstRemCreditSumCredit.setText(lbs.get(i).Get_FirstRemCreditSumCredit());
        
        TextView tvRemDebitFinal = (TextView) view.findViewById(R.id.bs_txtFinalRemDebit);
        tvRemDebitFinal.setText(lbs.get(i).Get_RemDebitFinal());

        TextView tvRemCreditFinal = (TextView) view.findViewById(R.id.bs_txtFinalRemCredit);
        tvRemCreditFinal.setText(lbs.get(i).Get_RemCreditFinal());

        return view;
    }

}

