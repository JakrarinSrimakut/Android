package com.example.jakrarinsrimakut.cmps121class4clicking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Bundle extras = getIntent().getExtras();

        if(extras!= null){
            TextView t = findViewById(R.id.act2TextView);
            String str = extras.getString("text_box");

            t.setText(str);
        }
        //FileOutputStream os = openFileOutput(:file.txt", MODE_PRIVATE);

        //Or
        try{

        }

        Button b = findViewById(R.id.button3);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                Intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//pop activity off stack
                startActivity(intent);
            }
        });
    }
}
