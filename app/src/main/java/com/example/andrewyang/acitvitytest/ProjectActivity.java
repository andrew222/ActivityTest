package com.example.andrewyang.acitvitytest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;


public class ProjectActivity extends ActionBarActivity {
    public ListView projectList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ne) {
            ne.printStackTrace();
        }
        String[] project_arr = getResources().getStringArray(R.array.projects_name);
        ArrayAdapter<String> projectsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, project_arr);
        projectList = (ListView) findViewById(R.id.projects);
        projectList.setAdapter(projectsAdapter);
        Intent contactIntent = new Intent();
        contactIntent.setAction(Intent.ACTION_VIEW);
        contactIntent.setData(Uri.parse("http://www.baidu.com"));
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, contactIntent, 0);
        projectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent ubertagsIntent = new Intent(getApplicationContext(), UbertagsActivity.class);
                        startActivity(ubertagsIntent);
                        break;
                    case 1:
                        try {
                            getSupportActionBar().hide();
                        }catch (NullPointerException ne) {
                            ne.printStackTrace();
                        }
                        break;
                    case 2:
                        try {
                            getSupportActionBar().show();
                            try {
                                pendingIntent.send();
                            }catch (PendingIntent.CanceledException ce) {
                                ce.printStackTrace();
                            }
                        }catch (NullPointerException ne) {
                            ne.printStackTrace();
                        }
                        break;
                    default:
                        break;

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "activity is on started", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project, menu);
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
    public void onBackPressed() {
        moveTaskToBack(true);
        ProjectActivity.this.finish();
    }
}
