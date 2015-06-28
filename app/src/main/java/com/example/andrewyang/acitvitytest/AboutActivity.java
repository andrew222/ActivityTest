package com.example.andrewyang.acitvitytest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class AboutActivity extends ActionBarActivity {
    TextView title;
    public FragmentManager fragmentManager;
    public AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        title = (TextView) findViewById(R.id.title_of_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        AboutActivity.this.finish();
    }

    public void onClick(View view) {
        int targetId = view.getId();
        switch (targetId) {
            case R.id.openContacts:
                 alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Warning")
                        .setMessage("Are you sure to open your contacts?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                alertDialog.show();
                break;
            case R.id.startAnimate:
                Animation anim = AnimationUtils.loadAnimation(this, R.anim.twin);
                final String[] projects = getResources().getStringArray(R.array.projects_name);

                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("Please one project")
                        .setItems(projects, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "" + projects[which], Toast.LENGTH_LONG).show();
                            }
                        })
                        .create();
                alertDialog.show();
                title.startAnimation(anim);
                break;
            case R.id.displayAboutFragment:
                AboutFragment aboutFragment = new AboutFragment();
                fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.aboutFragment, aboutFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            case R.id.hideAboutFragment:
                Toast.makeText(getApplicationContext(), "Pop fragment", Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
                Intent intent = new Intent(this, ProjectActivity.class);
                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
                taskStackBuilder.addParentStack(MainActivity.class);
                taskStackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification notification = new Notification.Builder(this)
                        .setContentTitle("New message")
                        .setContentText("This is a notify message")
                        .setSmallIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.abc_ic_clear_mtrl_alpha, "Clear", pendingIntent)
                        .addAction(R.drawable.abc_ab_share_pack_holo_light, "Shared", pendingIntent)
                        .build();
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
                break;
            default:
                break;
        }
    }
}
