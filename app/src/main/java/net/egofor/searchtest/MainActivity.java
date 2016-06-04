package net.egofor.searchtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements MaterialFragment.MaterialInterface {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    public Toolbar toolbar;
    private final String DETAILFRAGMENT_TAG = "DFTAG";
    private boolean mTwoPane;
    String[] searchArray = { "Mfg", "Mfg Part#", "Trade Size" };
    String[] categoriesArray =
                            { "Boxes", "Brackets", "Breakers", "Bushings", "Cable Entry Devices",
                            "Clamps", "Coaxial", "Connectors", "Couplings", "Dimmers",
                            "Entrance Caps", "Fittings", "Fuses", "Fuse Holders", "Glue", "Hangers",
                            "Lamps" };


    private DrawerLayout mDrawerLayout;
    private ListView mLeftNav;
    private ListView mRightNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLeftNav = (ListView) findViewById(R.id.left_drawer);
        mRightNav = (ListView) findViewById(R.id.right_drawer);

        mLeftNav.setAdapter(new ArrayAdapter<String>(this,
                R.layout.layout_file, categoriesArray));

        mRightNav.setAdapter(new ArrayAdapter<String>(this,
                R.layout.layout_file, searchArray));






        mTwoPane = findViewById(R.id.detail_container) != null;

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        DecideLayout();


        //  TODO  Move this code to gradle.properties to hide from java code let Android handle it secretly
//        String APP_ID = "5FDD3FE0-9CDD-C6C4-FFD8-87B570EA4500";
//        String SECRET_KEY = "10C95C8A-BED6-3238-FF26-7C3CF5AA2F00";
//        String appVersion = "v1";
//
//        //  Before the Java/Android client uses any of the APIs, the code must initialize the
//        //  Backendless Application using the following call
//        Backendless.initApp(this, APP_ID, SECRET_KEY, appVersion);



    }

    //  TODO  This will be used for the drawer OnItemClickListener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }
    }
    //  TODO  This will be used for the drawer OnItemClickListener
    private void selectItem(int position) {

    }







    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        DecideLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DecideLayout();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        DecideLayout();
    }


    public void DecideLayout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new MaterialFragment(getApplicationContext())).commitAllowingStateLoss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void showMaterialDetail(Intent intent) {

        if (mTwoPane) {

            //  If we're in two pane mode add new instance of MovieDetailFragment to right side
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,
                    MaterialDetailFragment.newInstance(intent), DETAILFRAGMENT_TAG).commit();


        } else {
            //  If we're in single pane mode start the new screen with passed in Intent
            startActivity(intent);
        }
    }
}
