package com.example.andrewyang.acitvitytest;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by andrewyang on 2015/6/29.
 */
public class ViewPageAcitvity extends FragmentActivity {
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("On Create", "On Create");
        setContentView(R.layout.view_page);
        _mViewPager = (ViewPager) findViewById(R.id.view_page);
        _adapter = new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Toast.makeText(getApplicationContext(), "Scrolled", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("On Page Selected", "" + position);
                switch (position) {
                    case 0:
                        findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setTab() {
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("setTab", "page scrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("Set Tab", "set tab");
                switch (position) {
                    case 0:
                        findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
