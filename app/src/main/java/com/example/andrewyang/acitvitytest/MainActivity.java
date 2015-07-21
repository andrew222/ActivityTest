package com.example.andrewyang.acitvitytest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class MainActivity extends ActionBarActivity {
    @ViewById
    TextView main_activity_title;
    @ViewById(R.id.tool_bar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        ObjectAnimator oa = ObjectAnimator.ofFloat(main_activity_title, "rotationY", 0.0f, 360f);
        oa.setDuration(3600);
        oa.setRepeatCount(ObjectAnimator.INFINITE);
        oa.start();
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

        switch (id) {
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutActivity_.class);
                startActivity(aboutIntent);
                break;
            case R.id.project:
                Intent projectIntent = new Intent(this, ProjectActivity.class);
                startActivity(projectIntent);
                break;
            case R.id.action_settings:
                Intent settingIntent = new Intent(this, SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.profile:
                Intent profileIntent = new Intent(this, ProfileActivity_.class);
                startActivity(profileIntent);
                break;
            case R.id.swipe_refresh:
                Intent swipeIntent = new Intent(this, SwipeRefresh.class);
                startActivity(swipeIntent);
                break;
            case R.id.view_page:
                Intent viewPageIntent = new Intent(this, ViewPageAcitvity.class);
                startActivity(viewPageIntent);
                break;
            case R.id.sliding_tab:
                Intent slidingTab = new Intent(this, SlidingTab_.class);
                startActivity(slidingTab );
                break;
            case R.id.sqlite_helper:
                Intent intent = new Intent(this, CommentDatabaseActivity.class);
                startActivity(intent);
                break;
            case R.id.material_tab_host:
                Intent material_intent = new Intent(this, MaterialTabsActivity_.class);
                startActivity(material_intent);
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
