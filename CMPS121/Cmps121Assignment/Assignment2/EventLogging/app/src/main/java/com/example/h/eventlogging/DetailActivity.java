package com.example.h.eventlogging;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public JSONObject jo = null;
    public JSONArray ja = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read the file


        try{
            File f = new File(getFilesDir(), "file.ser");
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream o = new ObjectInputStream(fi);
            // Notice here that we are de-serializing a String object (instead of
            // a JSONObject object) and passing the String to the JSONObject’s
            // constructor. That’s because String is serializable and
            // JSONObject is not. To convert a JSONObject back to a String, simply
            // call the JSONObject’s toString method.
            String j = null;
            try{
                j = (String) o.readObject();
            }
            catch(ClassNotFoundException c){
                c.printStackTrace();
            }
            try {
                jo = new JSONObject(j);
                ja = jo.getJSONArray("data");
            }
            catch(JSONException e){
                e.printStackTrace();
            }
        }
        catch(IOException e){
            // Here, initialize a new JSONObject
            jo = new JSONObject();
            ja = new JSONArray();
            try{
                jo.put("data", ja);
            }
            catch(JSONException j){
                j.printStackTrace();
            }
        }

        // Show the list
        final ArrayList<ListData> aList = new ArrayList<ListData>();
        for(int i = 0; i < ja.length(); i++){

            ListData ld = new ListData();
            try {
                ld.titleText = ja.getJSONObject(i).getString("title");
                ld.timeText = ja.getJSONObject(i).getString("time");
                ld.dateText = ja.getJSONObject(i).getString("date");
                ld.gpsText = ja.getJSONObject(i).getString("gps");
                ld.eventText = ja.getJSONObject(i).getString("event");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }

            aList.add(ld);
        }

        setContentView(R.layout.activity_detail);
        Button deleteButton = findViewById(R.id.delete_button);
        final Intent i = getIntent();
        String title = i.getStringExtra("title");
        String time = i.getStringExtra("time");
        String date = i.getStringExtra("date");
        String gps = i.getStringExtra("gps");
        String description = i.getStringExtra("event");

        final int pos = i.getIntExtra("pos",-1);

//        final ArrayList<ListData> aList = (ArrayList<ListData>) i.getSerializableExtra("aList");
//
//        Log.d("aList",aList.toString());
//        aList.remove(pos);
//        Log.d("aList2",aList.toString());
//        JSONArray ja = new JSONArray(aList);
//        Log.d("ja",ja.toString());



        final TextView t = (TextView)findViewById(R.id.titleTextView);
        TextView ti = (TextView)findViewById(R.id.timeTextView);
        TextView da = (TextView)findViewById(R.id.dateTextView);
        TextView g = (TextView)findViewById(R.id.gpsTextView);
        TextView d = (TextView)findViewById(R.id.eventTextView);

        t.setText(title);
        ti.setText(time);
        da.setText(date);
        g.setText(gps);
        d.setText(description);

        //remove listData object from aList and update jsonArray->jsonObject
        final Context context = this;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eventListIntent = new Intent(context, EventListActivity.class);
//                Log.d("aList",aList.toString());
//
//                aList.remove(pos);
//                Log.d("aList",aList.toString());
//                JSONArray ja = new JSONArray(aList);
//                Log.d("ja!",ja.toString());

                JSONArray update_ja = new JSONArray();
                int len = ja.length();
                if (ja != null) {
                    for (int i=0;i<len;i++)
                    {
                        //Excluding the item at position
                        if (i != pos)
                        {
                            try {
                                update_ja.put(ja.get(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                    try {
                        jo.put("data", update_ja);
                        // write the file
                        try{
                            File f = new File(getFilesDir(), "file.ser");
                            FileOutputStream fo = new FileOutputStream(f);
                            ObjectOutputStream o = new ObjectOutputStream(fo);
                            String j = jo.toString();
                            o.writeObject(j);
                            o.close();
                            fo.close();
                        }
                        catch(IOException e){
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                eventListIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(eventListIntent);
            }
        });
    }
}
