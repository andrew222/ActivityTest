package com.example.andrewyang.acitvitytest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by andrewyang on 2015/6/23.
 */
public class AboutFragment extends Fragment {
    public View container;
    public AboutActivity aboutActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aboutActivity = (AboutActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        aboutActivity.findViewById(R.id.displayAboutFragment).setVisibility(View.INVISIBLE);
        aboutActivity.findViewById(R.id.hideAboutFragment).setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        aboutActivity.findViewById(R.id.displayAboutFragment).setVisibility(View.VISIBLE);
        aboutActivity.findViewById(R.id.hideAboutFragment).setVisibility(View.INVISIBLE);
    }
}
