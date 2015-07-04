package com.example.andrewyang.acitvitytest;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by andrewyang on 2015/7/4.
 */
public class ProfilePreferenceActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile_preference);
        FragmentManager fm = getFragmentManager();

        fm.beginTransaction().replace(R.id.profile_preference, new ProfilePreferenceFragment()).commit();
    }
}
