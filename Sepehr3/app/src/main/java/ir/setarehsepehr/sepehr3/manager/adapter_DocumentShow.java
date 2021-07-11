package ir.setarehsepehr.sepehr3.manager;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class adapter_DocumentShow extends BaseAdapter {
    Context contex;
    ArrayList<List_DocumnetJournal> lj;

    public adapter_DocumentShow(Context c,ArrayList alj){
        contex = c;
        lj = alj;
    }
    @Override
    public int getCount() {
        return lj.size();
    }

    @Override
    public Object getItem(int position) {
        return lj.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.drawer_documentshow,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) convertView.getBackground().mutate();
        gradientDrawable.setColor(convertView.getResources().getColor(R.color.colorNormal));

        if(position%2==0)
            gradientDrawable.setColor(convertView.getResources().getColor(R.color.colorDoIt));

        TextView tvRow = (TextView) convertView.findViewById(R.id.ds_lblRow);
        tvRow.setText("ردیف : " + lj.get(position).Get_Row());

        TextView tvAcc = (TextView) convertView.findViewById(R.id.ds_lblAcc);
        tvAcc.setText("حساب : " + lj.get(position).Get_Acc_No()  + " " + lj.get(position).Get_Acc_Name());

        TextView tvTafsil1 = (TextView) convertView.findViewById(R.id.ds_lblTaf1);
        if(lj.get(position).Get_Tafsil1().equals("null"))
            tvTafsil1.setText("تفصیل1 : ");
        else
            tvTafsil1.setText("تفصیل1 : " + lj.get(position).Get_Tafsil1()  + " " + lj.get(position).Get_Tafsil_Name1());

        TextView tvTafsil2 = (TextView) convertView.findViewById(R.id.ds_lblTaf2);
        if(lj.get(position).Get_Tafsil2().equals("null"))
            tvTafsil2.setText("تفصیل1 : ");
        else
            tvTafsil2.setText("تفصیل2 : " + lj.get(position).Get_Tafsil2()  + " " + lj.get(position).Get_Tafsil_Name2());

        TextView tvTafsil3 = (TextView) convertView.findViewById(R.id.ds_lblTaf3);
        if(lj.get(position).Get_Tafsil3().equals("null"))
            tvTafsil3.setText("تفصیل3: ");
        else
            tvTafsil3.setText("تفصیل3 : " + lj.get(position).Get_Tafsil3()  + " " + lj.get(position).Get_Tafsil_Name3());

        TextView tvDebit = (TextView) convertView.findViewById(R.id.ds_lblDebit);
        tvDebit.setText("بدهکار : " + lj.get(position).Get_Debit());

        TextView tvCredit = (TextView) convertView.findViewById(R.id.ds_lblCredit);
        tvCredit.setText("بستانکار : " + lj.get(position).Get_Credit());

        TextView tvDescr = (TextView) convertView.findViewById(R.id.ds_lblDoc_Desc);
        if(lj.get(position).Get_Descr().equals("null"))
            tvDescr.setText("");
        else
            tvDescr.setText("شرح : " + lj.get(position).Get_Descr());

        return convertView;
    }
}
