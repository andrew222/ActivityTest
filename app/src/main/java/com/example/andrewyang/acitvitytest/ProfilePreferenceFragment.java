package com.example.andrewyang.acitvitytest;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by andrewyang on 2015/7/4.
 */
public class ProfilePreferenceFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.profile_preference);
    }
}
