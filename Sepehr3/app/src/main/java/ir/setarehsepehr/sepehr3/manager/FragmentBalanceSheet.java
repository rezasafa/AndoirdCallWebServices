package ir.setarehsepehr.sepehr3.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBalanceSheet extends Fragment {
    public static String rslt;
    dbApp db;
    int Levels;
    int GLevel;
    int Tafsil_Len;
    int GTafsil_Len;
    RelativeLayout rlBtn;

    public FragmentBalanceSheet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = new dbApp(getContext());
        Levels = Integer.parseInt(db.Read_InitAcc_Field("Acc_Levels"));
        GLevel = Integer.parseInt(db.Read_InitAcc_Field("GL_Level"));
        GTafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("GTafsil_Len"));
        Tafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("Tafsil_Len"));

        return inflater.inflate(R.layout.fragment_balance_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getResources().getString(R.string.app_name));
        rlBtn = (RelativeLayout) view.findViewById(R.id.fbs_rl_Buttons);

        int TafsilCount = 0;
        if(Boolean.parseBoolean(db.Read_Init_Field("ATafsil1_Visible")) == true){
            TafsilCount += 1;
            if(Boolean.parseBoolean(db.Read_Init_Field("ATafsil2_Visible")) == true){
                TafsilCount += 1;
                if(Boolean.parseBoolean(db.Read_Init_Field("ATafsil1_Visible")) == true){
                    TafsilCount += 1;
                }
            }
        }
        try{
            for(int i = GLevel; i <= Levels + TafsilCount; i ++){
                final int index = i;
                final int tcount = TafsilCount;
                Button btn = (Button) view.findViewWithTag("fbs_btn" + String.valueOf(i));
                btn.setVisibility(View.VISIBLE);
                if(i > Levels){
                    int lens_Tafsil = 0;
                    int lens_Acc = 0;
                    int Lens = 0;
                    for (int j = 1; j <= Levels; j++){
                        lens_Acc += Integer.parseInt(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(j) + "_Len"));
                    }
                    for(int d = 1; d <=  Levels - i; d++){
                        lens_Tafsil += GTafsil_Len + Tafsil_Len;
                        Lens = lens_Acc + lens_Tafsil;
                    }
                    final int LevelLen = Lens;
                    btn.setText(getString(R.string.sTafsil) + String.valueOf(Levels - i));
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("LevCount",Levels);
                            bundle.putInt("CLev",index);
                            bundle.putInt("LevLen",LevelLen);
                            Fragment fragment = null;
                            fragment = new FragmentShowBalanceSheet();
                            fragment.setArguments(bundle);
                            if (fragment != null){
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame,fragment);
                                ft.addToBackStack(null);
                                ft.isAddToBackStackAllowed();
                                ft.commit();
                            }
                        }
                    });
                }
                else{
                    btn.setText(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(i) + "_Name"));
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int lens = 0;
                            for (int j = 1; j <= index; j++){
                                lens += Integer.parseInt(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(j) + "_Len"));
                            }

                            Bundle bundle = new Bundle();
                            bundle.putInt("LevCount",Levels);
                            bundle.putInt("CLev",index);
                            bundle.putInt("LevLen",lens);
                            Fragment fragment = null;
                            fragment = new FragmentShowBalanceSheet();
                            fragment.setArguments(bundle);
                            if (fragment != null){
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.content_frame,fragment);
                                ft.addToBackStack(null);
                                ft.isAddToBackStackAllowed();
                                ft.commit();
                            }
                        }
                    });
                }
            }
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }


    }
}
