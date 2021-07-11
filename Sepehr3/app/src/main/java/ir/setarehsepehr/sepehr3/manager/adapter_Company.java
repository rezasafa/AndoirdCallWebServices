package ir.setarehsepehr.sepehr3.manager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class adapter_Company extends BaseAdapter {
    Context contex;
    ArrayList<List_Company> lc;

    public adapter_Company(Context c,ArrayList alc){
        contex = c;
        lc = alc;
    }

    @Override
    public int getCount() {
        return lc.size();
    }

    @Override
    public Object getItem(int i) {
        return lc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_company,null);
        }

        TextView tvCompanyName = (TextView) view.findViewById(R.id.dc_lblCompanyName);
        tvCompanyName.setText(lc.get(i).Get_Name());

        return view;
    }

}

