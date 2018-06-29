package com.example.h.main;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.*;

import org.json.JSONObject;
import org.json.JSONArray;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

//    public JSONObject jos = null;
//    public JSONArray ja = null;
//    private static final String TAG = "JSON_LIST";
    MediaPlayer mediaPlayer ;
    public static final int RequestPermissionCode = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(checkPermission()) {

        } else {
            requestPermission();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onResume(){
        super.onResume();
        ListView list = findViewById(R.id.data_list_view);
        TextView text = findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);

//        Log.d(TAG, ""+getFilesDir());

        //read in String object
        try{
            // Reading a file that already exists
            File f = new File(getFilesDir(), "file.ser");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            int numMemos = ois.readInt();

            ois.close();
            fis.close();

            Log.d("numMemos", String.valueOf(numMemos));

            //Initialize a String array with numMemos elements
            String[] listItems = new String[numMemos];

            //iterate Through for loop inititalizing each element in string array to audio recording
            for(int i = 0; i < numMemos; i++){
                listItems[i]="Audio Recording " + (i+1);
            }


            // Show the list view with the each list item an element from listItems
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            list.setAdapter(adapter);

            // Set an OnItemClickListener for each of the list items
            final Context context = this;
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    // object to play the audio
                    mediaPlayer = new MediaPlayer();

                    String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+(1+position)+".3gp";
                    try {
                        mediaPlayer.setDataSource(path);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();// play the audio
                    Toast.makeText(MainActivity.this, "Recording Playing",
                            Toast.LENGTH_LONG).show();


                }

            });
        }
        catch(Exception e){

            //Here, disable the list view
            list.setEnabled(false);
            list.setVisibility(View.INVISIBLE);

            //show the text view
            text.setVisibility(View.VISIBLE);
        }
    }

    // This method will just show the menu item (which is our button "ADD")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        // the menu being referenced here is the menu.xml from res/menu/menu.xml
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    /* Here is the event handler for the menu button that I forgot in class.
    The value returned by item.getItemID() is
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        Log.d(TAG, String.format("" + item.getItemId()));
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                /*the R.id.action_favorite is the ID of our button (defined in strings.xml).
                Change Activity here (if that's what you're intending to do, which is probably is).
                 */
                Intent i = new Intent(this, WebViewActivity.class);
                startActivity(i);
            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }
    // permissions from user
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
    // callback method
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }
}
