package com.example.h.webviewex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private Button b;
    private WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set the onclick listener
        b = findViewById(R.id.button);
        b.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                et = findViewById(R.id.editText);
                String url = et.getText().toString();

                //Navigate to that URL
                wb = findViewById(R.id.webView);
                wb.loadUrl(url);

                //if we want to to allow javascript
                WebSettings ws = wb.getSettings();
                ws.setJavaScriptEnabled(true);
            }
        });
    }
}
