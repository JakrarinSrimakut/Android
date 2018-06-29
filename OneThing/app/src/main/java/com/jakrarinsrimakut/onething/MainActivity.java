package com.jakrarinsrimakut.onething;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (EditText) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);

        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);//link to SharedPreferences
        String oldItem = sharedPref.getString("oldItem", "Nothing created yet...");//value to be put in SharedPreferences

        textView.setText(oldItem);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPref.edit();//allow to edit value in SharedPreferences
                editor.putString("oldItem", textView.getText().toString());
                editor.commit();
            }
        });
    }
}
