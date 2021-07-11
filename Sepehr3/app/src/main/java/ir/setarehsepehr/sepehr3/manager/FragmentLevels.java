package ir.setarehsepehr.sepehr3.manager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentLevels extends Fragment {
    public static String rslt;
    dbApp db;
    int Levels;
    int GLevel;
    int LevCount;
    int CLev;
    int LevLen;
    int Tafsil_Len;
    int GTafsil_Len;

    RelativeLayout rlBtn;

    String sParam;

    public FragmentLevels() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    public static FragmentLevels newInstance(String param1, String param2) {
        FragmentLevels fragment = new FragmentLevels();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            sParam = getArguments().getString("myParam");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new dbApp(getContext());
        Levels = Integer.parseInt(db.Read_InitAcc_Field("Acc_Levels"));
        GLevel = Integer.parseInt(db.Read_InitAcc_Field("GL_Level"));
        GTafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("GTafsil_Len"));
        Tafsil_Len = Integer.parseInt(db.Read_InitAcc_Field("Tafsil_Len"));
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_levels, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.sLevels));

        rlBtn = (RelativeLayout) view.findViewById(R.id.fl_rl_Buttons);

        try{
        for(int i = 1; i <= Levels; i ++){
                final int index = i;
                Button btn = (Button) view.findViewWithTag("fl_btn" + String.valueOf(i));
                btn.setVisibility(View.VISIBLE);

                btn.setText(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(i) + "_Name"));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int lens = 0;
                        int LevelSelected = 0;
                        for (int j = 1; j <= index; j++){
                            lens += Integer.parseInt(db.Read_InitAcc_Field("Acc_Level" + String.valueOf(j) + "_Len"));
                            LevelSelected = j;
                        }
                        LevLen = lens;


                        Bundle bundle = new Bundle();
                        bundle.putString("LevLen",String.valueOf(lens));// toString(.parseInt(lens));
                        bundle.putString("LevelSelected", String.valueOf(LevelSelected));

                        Fragment fragment = null;
                        if(sParam == "Accounts"){
                            fragment = new FragmentAccountJournals();
                        }
                        if(sParam == "Tafsils"){
                            fragment = new FragmentTafsilJournals();
                        }
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
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}