package com.example.andrewyang.acitvitytest;

import android.app.Fragment;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

public class SettingActivity extends ActionBarActivity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] projects;
    private String mTitle;
    private String mActivityTitle;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        projects = getResources().getStringArray(R.array.projects_name);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.drawer_menu);
        ArrayAdapter<String> projectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projects);
        mDrawerList.setAdapter(projectsAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.mipmap.ic_drawer, R.string.app_name, R.string.app_name){
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
                getSupportActionBar().setTitle("Navigation!");
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Log.d("Setting activity", "" + getSupportActionBar().getTitle());
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        mTitle = mActivityTitle = getTitle().toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }

    public void displayView(int position) {
        Log.d("Setting activity", "" + position);
        Fragment fragment  = null;
        switch (position) {
            case 0:
                fragment = new UbertagsFragment();
                mTitle = getString(R.string.ubertags);
                break;
            case 1:
                fragment = new FigaroFragment();
                mTitle = "Figaro";

                break;
            default:
                fragment = new HomeFragment();
                mTitle = getString(R.string.home_fragment);
                break;
        }

        if (fragment != null) {
            getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            mDrawerLayout.closeDrawer(mDrawerList);
        }else {
            Log.e("Setting activity", "Error in creating fragment");
        };
    }

}
