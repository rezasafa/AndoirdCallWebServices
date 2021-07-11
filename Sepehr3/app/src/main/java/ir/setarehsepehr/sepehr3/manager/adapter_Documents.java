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
public class adapter_Documents extends BaseAdapter {
    Context contex;
    ArrayList<List_Document> ld;

    public adapter_Documents(Context c,ArrayList ald){
        contex = c;
        ld = ald;
    }

    @Override
    public int getCount() {
        return ld.size();
    }

    @Override
    public Object getItem(int position) {
        return ld.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = lif.inflate(R.layout.drawer_documents,null);
        }

        GradientDrawable gradientDrawable = (GradientDrawable) convertView.getBackground().mutate();
        gradientDrawable.setColor(convertView.getResources().getColor(R.color.colorNormal));

        if(position%2==0)
            gradientDrawable.setColor(convertView.getResources().getColor(R.color.colorDoIt));

        TextView tvDoc_No = (TextView) convertView.findViewById(R.id.doc_lblDoc_No);
        tvDoc_No.setText("سند : " + ld.get(position).Get_Doc_No());

        TextView tvDoc_Date = (TextView) convertView.findViewById(R.id.doc_lblDoc_Date);
        tvDoc_Date.setText("تاریخ : " + ld.get(position).Get_Doc_Date());

        TextView tvDoc_Descr = (TextView) convertView.findViewById(R.id.doc_lblDoc_Desc);
        if (ld.get(position).Get_Doc_Descr().equals("null"))
            tvDoc_Descr.setText("شرح : ");
        else
            tvDoc_Descr.setText("شرح : " + ld.get(position).Get_Doc_Descr());

        TextView tvDebit = (TextView) convertView.findViewById(R.id.doc_lblDebit);
        if (ld.get(position).Get_SumDebit().equals("null"))
            tvDebit.setText("بذهکار : 0");
        else
            tvDebit.setText("بدهکار : " + ld.get(position).Get_SumDebit());

        TextView tvCredit = (TextView) convertView.findViewById(R.id.doc_lblCredit);
        if(ld.get(position).Get_SumCredit().equals("null"))
            tvCredit.setText("بستانکار : 0");
        else
            tvCredit.setText("بستانکار : " + ld.get(position).Get_SumCredit());

        TextView tvControl = (TextView) convertView.findViewById(R.id.doc_lblControl);
        if(ld.get(position).Get_Control())
            tvControl.setText("کنترل : *");
        else
            tvControl.setText("کنترل : ");

        TextView tvConfirm = (TextView) convertView.findViewById(R.id.doc_lblConfirm);
        if(ld.get(position).Get_Confirm())
            tvConfirm.setText("تایید : *");
        else
            tvConfirm.setText("تایید : ");

        TextView tvRegister = (TextView) convertView.findViewById(R.id.doc_lblRegister);
        if(ld.get(position).Get_Register())
            tvRegister.setText("ثبت : *");
        else
            tvRegister.setText("ثبت : ");

        TextView tvFlag = (TextView) convertView.findViewById(R.id.doc_lblFlag);
        if(ld.get(position).Get_Flag())
            tvFlag.setText("پرچم : *");
        else
            tvFlag.setText("پرچم : ");

        return convertView;
    }
}
