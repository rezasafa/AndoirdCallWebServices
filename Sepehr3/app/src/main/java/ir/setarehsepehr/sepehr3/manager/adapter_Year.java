package ir.setarehsepehr.sepehr3.manager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class adapter_Year extends BaseAdapter {
    Context contex;
    ArrayList<List_Year> ly;

    public adapter_Year(Context c,ArrayList aly){
        contex = c;
        ly = aly;
    }

    @Override
    public int getCount() {
        return ly.size();
    }

    @Override
    public Object getItem(int i) {
        return ly.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater lif = (LayoutInflater) contex.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = lif.inflate(R.layout.drawer_year,null);
        }
        
        TextView tvYear = (TextView) view.findViewById(R.id.dy_lblYear);
        tvYear.setText(ly.get(i).Get_FinYear());

        return view;
    }

}
