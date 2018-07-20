package com.example.h.rollem;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Communicator{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void respond(int diceId){
        FragmentManager manager = getFragmentManager();
        RollingArea rollingArea = (RollingArea)manager.findFragmentById(R.id.rollingAreaFragment);
        rollingArea.setDiceImage(diceId);
    }
}
