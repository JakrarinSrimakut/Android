package com.example.h.main;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class WebViewActivity extends AppCompatActivity {

    static final public String MYPREFS = "myprefs";
    static final public String PREF_URL = "restore_url";
    static final public String WEBPAGE_NOTHING = "about:blank";
    static final public String MY_WEBPAGE = "https://users.soe.ucsc.edu/~dustinadams/CMPS121/assignment3/www/index.html";
    static final public String LOG_TAG = "webview_example";

    WebView myWebView;
    MediaRecorder mediaRecorder ;
    String AudioSavePathInDevice;

    //static allows the whole program to access it
    static int fileNum;
    MediaPlayer mediaPlayer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);



    }
    protected void onResume() {
        super.onResume();
        myWebView = (WebView) findViewById(R.id.webView1);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Binds the Javascript interface
        myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
        myWebView.loadUrl(MY_WEBPAGE);

    }

    public class JavaScriptInterface {
        Context mContext; // Having the context is useful for lots of things,
        // like accessing preferences.

        /** Instantiate the interface and set the context */
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void record() {
            Log.i(LOG_TAG, "I am in the javascript call.");
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Method code here*/
                    File f = new File(getFilesDir(), "file.ser");
                    Log.d("fileNum", String.valueOf(fileNum));
                    //Initialize file if none exists
                    if(fileNum == 0){
                        try {
                            FileOutputStream fos = new FileOutputStream(f);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            fileNum = 1;
                            oos.writeInt(fileNum);

                            oos.close();
                            fos.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // Reading a file that already exists
                    else{
                        try {
                            FileInputStream fis = new FileInputStream(f);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            fileNum = ois.readInt();
                            fileNum++;

                            ois.close();
                            fis.close();


                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    //create string for path
                    AudioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+fileNum+".3gp";

                    MediaRecorderReady();

                    try {
                        // recording starts
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    Toast.makeText(WebViewActivity.this, "Recording",
                            Toast.LENGTH_LONG).show();

                }
            });

        }
        @JavascriptInterface
        public void stop() {
            Log.i(LOG_TAG, "I am in the javascript call.");
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Method code here*/
                    mediaRecorder.stop(); // recording stops
                    File f = new File(getFilesDir(), "file.ser");

                    //Initialize file if none exists
                    if(fileNum == 0){
                        try {
                            FileOutputStream fos = new FileOutputStream(f);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            fileNum = 1;
                            oos.writeInt(fileNum);

                            oos.close();
                            fos.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    //update file with new audio memo. increment list
                    else{
                        try {
                            FileOutputStream fos = new FileOutputStream(f);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            oos.writeInt(fileNum);

                            oos.close();
                            fos.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                    Toast.makeText(WebViewActivity.this, "Stopping",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        @JavascriptInterface
        public void play() {
            Log.i(LOG_TAG, "I am in the javascript call.");
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Method code here*/
                    // object to play the audio
                    mediaPlayer = new MediaPlayer();
                    try {
                        mediaPlayer.setDataSource(AudioSavePathInDevice);
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    mediaPlayer.start();// play the audio
                    Toast.makeText(WebViewActivity.this, "Playing",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        @JavascriptInterface
        public void stoprec() {
            Log.i(LOG_TAG, "I am in the javascript call.");
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Method code here*/
                    if(mediaPlayer != null){
                        mediaPlayer.stop(); // stop audio
                        mediaPlayer.release(); // free up memory
                        MediaRecorderReady();
                    }
                    Toast.makeText(WebViewActivity.this, "Stopping recording",
                            Toast.LENGTH_LONG).show();
                }
            });

        }
        @JavascriptInterface
        public void exit() {
            Log.i(LOG_TAG, "I am in the javascript call.");
            runOnUiThread(new Runnable() {
                public void run() {
                    /*Method code here*/
                    Intent i = new Intent(WebViewActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    Toast.makeText(WebViewActivity.this, "Exit",
                            Toast.LENGTH_LONG).show();

                    //pop it off the stack to keep it from running out of memory
//                    finish();

                    startActivity(i);


                }
            });

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
    // initialize recorder object
    public void MediaRecorderReady() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(AudioSavePathInDevice);
    }
}