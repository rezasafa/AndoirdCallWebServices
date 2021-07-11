package ir.setarehsepehr.sepehr3.manager;

import android.app.ProgressDialog;
import android.os.AsyncTask;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTafsilJournals extends Fragment {
    public static String rslt;
    dbApp db;
    int Levels;
    int GLevel;
    int LevCount;
    int CLev;
    int LevLen;
    int Tafsil_Len;
    int GTafsil_Len;
    int CurrentLevel;

    ArrayList<List_Tafsils> lt;

    RelativeLayout rlTafsils;
    EditText etTafsil_No;
    EditText etTafsil_Name;
    //EditText etLTafsil_Name;
    Button btnSearch;

    public FragmentTafsilJournals() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            LevLen = Integer.parseInt(getArguments().getString("LevLen"));
            CurrentLevel = Integer.parseInt(getArguments().getString("LevelSelected"));
        }
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

        return inflater.inflate(R.layout.fragment_tafsil_journals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.mTafsilJournals));

        rlTafsils = (RelativeLayout) view.findViewById(R.id.ftj_rlTafsils);
        btnSearch = (Button) view.findViewById(R.id.ftj_btnSearch);

        etTafsil_No = null;
        etTafsil_Name = null;
        //etLTafsil_Name = null;

        etTafsil_No = (EditText) getView().findViewById(R.id.ftj_txtTafsil_No);
        etTafsil_Name = (EditText) getView().findViewById(R.id.ftj_txtTafsil_Name);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadInfo();
            }
        });

        LoadInfo();
    }

    private void LoadInfo(){
        //boolean findRecord = false;
        new AsyncTask<Void,Void,Void>(){
            ProgressDialog asyncDialog = new ProgressDialog(getContext());

            @Override
            final protected void onPreExecute() {
                //set message of the dialog
                asyncDialog.setTitle(getString(R.string.sWaitePLZ));
                asyncDialog.setMessage(getString(R.string.sProccess));
                //show dialog
                asyncDialog.show();
                super.onPreExecute();
            }

            @Override
            final protected Void doInBackground(Void... arg0) {

                try {
                    String wheres = " WHERE (1=1) ";
                    if (!etTafsil_No.getText().toString().equals("")){
                        wheres += " AND Tafsil_No LIKE '%" + etTafsil_No.getText().toString() + "%'";
                    }
                    if (!etTafsil_Name.getText().toString().equals("")){
                        wheres += " AND Tafsil_Name LIKE '%" + etTafsil_Name.getText().toString() + "%'";
                    }

                    rslt = "START";
                    caller_Tafsils c = new caller_Tafsils();
                    c.sWheres = wheres;
                    c.con = getContext();
                    c.sCoName = new dbApp(getContext()).Read_Setting_Field("Co_Name");
                    c.sYear = new dbApp(getContext()).Read_Setting_Field("Year");

                    c.join();
                    c.start();

                    while (rslt == "START") {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {
                        }
                    }

                    String sjson = "{ Records : " +  rslt + "}";

                    JSONObject json = new JSONObject(sjson);

                    JSONArray jArray = json.getJSONArray("Records");

                    final ArrayList<List_Tafsils> list = new ArrayList<List_Tafsils>();

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jo  = jArray.getJSONObject(i);

                        list.add(new List_Tafsils(
                                jo.getString("G_No"),
                                jo.getString("Tafsil_No"),
                                jo.getString("Tafsil_Name"),
                                jo.getString("LTafsil_Name")
                        ));
                    };

                    lt = list;
                }catch (Exception ex){
                    Toast.makeText(
                            getContext(),
                            ex.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }


                return null;
            }

            @Override
            final protected void onPostExecute(Void result) {
                //hide the dialog
                asyncDialog.dismiss();
                //if(lt.size() > 0)
                fill_Records(lt);
                super.onPostExecute(result);
            }
        }.execute();
    }

    private void fill_Records(final ArrayList<List_Tafsils> list){

        try{

            final ListView lv = (ListView) getView().findViewById(R.id.ftj_lvTafsils);
            final adapter_Tafsil_Journals adapter;

            adapter = new adapter_Tafsil_Journals(getContext(), list);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Bundle bundle = new Bundle();
                    bundle.putString("AccNo","");
                    bundle.putString("AccQuery"," Acc_No LIKE '%%'");
                    bundle.putString("StartDate",db.Read_Init_Field("Start_Date"));
                    bundle.putString("EndDate",db.Read_Init_Field("End_Date"));
                    bundle.putString("TafsilNo",list.get(i).Get_Tafsil_No());
                    bundle.putString("Title",list.get(i).Get_Tafsil_Name());
                    bundle.putString("myParam","Tafsils");
                    bundle.putString("LevLen", String.valueOf(LevLen));
                    bundle.putString("LevelSelected",String.valueOf(CurrentLevel));
                    Fragment fragment = null;
                    fragment = new FragmentShowJournals();
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
        }catch (Exception ex){
            Toast.makeText(
                    getContext(),
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }
    }
}