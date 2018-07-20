package com.example.h.rollem;

import android.app.Fragment;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class RollingArea extends Fragment {
    int diceType;// holds dice ID side 1 to call the correct Dice method
    ImageView rollingDiceImageView;
    int rolledDiceDrawableId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rolling_area, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rollingDiceImageView = (ImageView) getActivity().findViewById(R.id.rollingDice);
        rollingDiceImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch(diceType){
                    case R.drawable.d4_side_1:
                        rolledDiceDrawableId = new Dice().D4Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d4_side_blank_rolled);
                        break;
                    case R.drawable.d6_side_1:
                        rolledDiceDrawableId = new Dice().D6Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d6_side_blank_rolled);
                        break;
                    case R.drawable.d8_side_1:
                        rolledDiceDrawableId = new Dice().D8Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d8_side_blank_rolled);
                        break;
                    case R.drawable.d10_side_1:
                        rolledDiceDrawableId = new Dice().D10Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d10_side_blank_rolled);
                        break;
                    case R.drawable.d12_side_1:
                        rolledDiceDrawableId = new Dice().D12Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d12_side_blank_rolled);
                        break;
                    case R.drawable.d20_side_1:
                        rolledDiceDrawableId = new Dice().D20Side();//Get random d4 side drawable id
                        //show blank rolling dice for 1 second then to random die side
                        rollingDiceImageView.setImageResource(R.drawable.d20_side_blank_rolled);
                        break;
                }
                new CountDownTimer(500,500){
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        rollingDiceImageView.setImageResource(rolledDiceDrawableId);
                    }
                }.start();
            }
        });
    }

    //for start of app when no dice selected. make it appear.
    public void setDiceImage(int diceID){
        diceType = diceID;
        rollingDiceImageView.setImageResource(diceID);
        if(rollingDiceImageView.getVisibility() == View.INVISIBLE){
            rollingDiceImageView.setVisibility(View.VISIBLE);
        }
    }
}
