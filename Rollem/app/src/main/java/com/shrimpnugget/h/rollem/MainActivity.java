package com.shrimpnugget.h.rollem;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements Communicator{
    AdView mAdview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-1868303921416559~5227064073");
        mAdview = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdview.loadAd(adRequest);
    }

    public void respond(int diceId){
        FragmentManager manager = getFragmentManager();
        RollingArea rollingArea = (RollingArea)manager.findFragmentById(R.id.rollingAreaFragment);
        rollingArea.setDiceImage(diceId);
    }
}
