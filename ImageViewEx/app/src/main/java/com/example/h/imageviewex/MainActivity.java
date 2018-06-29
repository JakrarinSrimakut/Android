package com.example.h.imageviewex;

import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.button);
        b.setOnClickListener(new Button.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view){
                ImageView im = (ImageView)findViewById(R.id.imageView);
                im.setImageDrawable(getResources().getDrawable(R.drawable.donavan, getApplicationContext().getTheme()));
            }
        });
    }
}
