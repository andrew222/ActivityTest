package com.example.andrewyang.acitvitytest;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by andrewyang on 2015/6/27.
 */
public class UbertagsFragment extends Fragment {
    View rootView;
    Context rootContext;

    public UbertagsFragment(Context context) {
        rootContext = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_ubertags, container, false);

        Button startContextBtn = (Button) rootView.findViewById(R.id.startContextMenu);
        registerForContextMenu(rootView);
        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Profile Setting");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Profile Setting") {
            Log.d("Setting Activity: ", "Profile Setting Selected");
            Intent intent = new Intent(rootContext, ProfilePreferenceActivity.class);
            startActivity(intent);
        }else {
            return false;
        }
        return true;
    }
}
