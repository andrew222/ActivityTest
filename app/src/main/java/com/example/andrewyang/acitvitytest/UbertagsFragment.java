package com.example.andrewyang.acitvitytest;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by andrewyang on 2015/6/27.
 */
public class UbertagsFragment extends Fragment {
    View rootView;
    Context rootContext;
    public TextView user_setting;

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
        user_setting = (TextView) rootView.findViewById(R.id.user_setting);

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
            startActivityForResult(intent, 1);
        }else {
            return false;
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                showUserSetting();
                break;

        }
    }

    private void showUserSetting() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(rootContext);

        StringBuilder builder = new StringBuilder();
        builder.append("\n Username: " + sharedPreferences.getString("name", "NULL"));
        builder.append("\n Enable wifi: " + sharedPreferences.getBoolean("wifi", false));
        Toast.makeText(rootContext, builder.toString(), Toast.LENGTH_LONG).show();
    }
}
