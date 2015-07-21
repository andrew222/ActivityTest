package com.example.andrewyang.acitvitytest;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.w3c.dom.Text;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by andrewyang on 2015/6/25.
 */
@EActivity
public class ProfileActivity extends Activity {
    public EditText user_name;
    public EditText age;
    public TextView user_name_tv;
    public TextView age_tv;
    public DownloadManager downloadManager;
    public long myDownloadReference;
    public String downloadFileUrl = "http://www.101apps.co.za/images/android/articles/DownloadManager/screenshotDouble.png";
    private BroadcastReceiver receiverDownloadCompltete;
    private BroadcastReceiver receiverNotificationClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ColorStateList colors = getResources().getColorStateList(R.color.button);
        Button btn1 = (Button) findViewById(R.id.show_more);
        btn1.setTextColor(colors);
        Toast.makeText(this, "On create", Toast.LENGTH_LONG).show();
        user_name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);

        user_name_tv = (TextView) findViewById(R.id.user_name_tv);
        age_tv = (TextView) findViewById(R.id.age_tv);

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "on start", Toast.LENGTH_LONG).show();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.show_more:
                Toast.makeText(getApplicationContext(), "On clicked", Toast.LENGTH_LONG).show();
                break;
            case R.id.save_user:
                try {
                    JsonWriter jsonWriter = new JsonWriter(
                            new OutputStreamWriter(openFileOutput("data.json", MODE_PRIVATE))
                    );
                    try {
                        jsonWriter.setIndent("  ");
                        jsonWriter.beginObject();
                        jsonWriter.name("User Name");
                        jsonWriter.value("" + user_name.getText());

                        jsonWriter.name("Age");
                        jsonWriter.value("" + age.getText());
                        jsonWriter.endObject();
                    } finally {
                        jsonWriter.close();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.search_user:
                try {
                    JsonReader jsonReader = new JsonReader(
                            new InputStreamReader(openFileInput("data.json"))
                    );
                    try {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String name = jsonReader.nextName();
                            if ("User Name".equals(name)) {
                                user_name_tv.setText(jsonReader.nextString());
                            }else if ("Age".equals(name)) {
                                age_tv.setText(jsonReader.nextString());
                            }else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                    }finally {
                        jsonReader.close();
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    @Click
    void download_image() {
        Uri uri = Uri.parse(downloadFileUrl);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalFilesDir(ProfileActivity.this, Environment.DIRECTORY_DOWNLOADS, "logo.png");
        request.setTitle("Notification Title").setDescription("My Download");
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        myDownloadReference = downloadManager.enqueue(request);
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        receiverNotificationClicked = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String extraId = DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS;
                long[] references = intent.getLongArrayExtra(extraId);
                for(long reference : references) {
                    if(reference == myDownloadReference) {
                        Toast.makeText(getApplicationContext(), "Download Completed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        registerReceiver(receiverNotificationClicked, filter);
    }
}
