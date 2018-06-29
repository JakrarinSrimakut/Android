package com.example.jakrarinsrimakut.cmps121class4clicking;

import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.;
public class MainActivity extends AppCompatActivity {

    private String TAG = "Button";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Button b = findViewById(R.id.button);

        b.setOnClickListener(new Button.OnClickListener){
            public void onClick(View view){
                Log.d(TAG, "click event generated");
            }
        });

        b.setOnLongClickListener(new Button.OnLongClickListener()){
            public boolean onLongClick(View view){
                Log.d(TAG, "Long clic event generated");
                return false;
            }
        });
        */
    }
    //swap activities
    public void onCLickButton(View view){
        Intent i = new Intent(this, Activity2.class);
        i.putExtra("text_box","");
        startActivity(i);
    }

}
