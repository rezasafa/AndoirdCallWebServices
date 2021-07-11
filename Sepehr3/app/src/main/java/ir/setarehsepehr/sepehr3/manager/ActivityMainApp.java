package ir.setarehsepehr.sepehr3.manager;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityMainApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //display_SelectedMneu(R.id.nav_BalanceSheet);
        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        dbApp db = new dbApp(this);
        TextView tvCompanyName = (TextView) headerView.findViewById(R.id.tvCoName);
        TextView tvFinsYears = (TextView) headerView.findViewById(R.id.tvFinYear);
        try{
            tvCompanyName.setText(db.Read_Init_Field("Co_Name"));
            tvFinsYears.setText(db.Read_Init_Field("Start_Date") + " - " + db.Read_Init_Field("End_Date"));
        }catch (Exception ex){
            Toast.makeText(
                    this,
                    ex.getMessage(),
                    Toast.LENGTH_LONG
            ).show();
        }

    }

    //////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {
        //FragmentManager fm = this.getFragmentManager();
        this.getSupportFragmentManager().popBackStack();
        //Toast.makeText(
        //        this,
        //        String.valueOf(fm.getBackStackEntryCount()),
        //        Toast.LENGTH_LONG
        //).show();
        //if (fm.getBackStackEntryCount() > 0)
        //{
        //    //Log.i("MainActivity", "popping backstack");
        //
        //}
        //else
        //{
        //    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //    if (drawer.isDrawerOpen(GravityCompat.START)) {
        //        drawer.closeDrawer(GravityCompat.START);
        //    } else {
        //        drawer.openDrawer(GravityCompat.START);
        //    }
        //}
    }
    //////////////////////////////////////////////////////////
    //@Override
    //public void onBackPressed() {
        //Fragment currentFragment = getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getBackStackEntryCount() - 1);
        //if (currentFragment instanceof OnBackPressed) {
        //    ((OnBackPressed) currentFragment).onBackPressed();
        //}
        //super.onBackPressed();
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //if (drawer.isDrawerOpen(GravityCompat.START)) {
        //    drawer.closeDrawer(GravityCompat.START);
        //} else {
        //    drawer.openDrawer(GravityCompat.START);
        //}
    //}
        /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity_main_app, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
        */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        display_SelectedMneu(id);
        return true;
    }

    public void display_SelectedMneu(int iItem) {
        Fragment fragment = null;

        if (iItem == R.id.nav_DocumentManager){
            fragment = new FragmentDocumentManager();
        }
        else if (iItem == R.id.nav_AccountJournal){
            Bundle bundle = new Bundle();
            bundle.putString("myParam","Accounts");
            fragment = new FragmentLevels();
            fragment.setArguments(bundle);
        }else if (iItem == R.id.nav_TafsilJournal){
            Bundle bundle = new Bundle();
            bundle.putString("myParam","Tafsils");
            fragment = new FragmentLevels();
            fragment.setArguments(bundle);
        }else if (iItem == R.id.nav_BalanceSheet) {
            fragment = new FragmentBalanceSheet();
        } else if (iItem == R.id.nav_ChangeYear) {
            Intent i = new Intent(ActivityMainApp.this,ActivitySelectCoYear.class);
            startActivity(i);
            finish();
        } else if (iItem == R.id.nav_Exit) {
            Exit_App();
        }

        if (fragment != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //ft.add(R.id.content_frame,fragment);
            ft.replace(R.id.content_frame,fragment);
            ft.addToBackStack(null);
            ft.isAddToBackStackAllowed();
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    private void Exit_App(){
        DialogInterface.OnClickListener di = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which)
                {
                    case DialogInterface.BUTTON_POSITIVE :
                        android.os.Process.killProcess(android.os.Process.myPid());
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE :
                        break;
                }
            }
        };

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(getResources().getString(R.string.mExit));
        String msg = "آیا می خواهید از برنامه خارج شوید";
        ad.setMessage(msg);
        ad.setPositiveButton(getResources().getString(R.string.msgOKButton), di);
        ad.setNegativeButton(getResources().getString(R.string.msgCancelButton), di);
        ad.show();
    }
}
