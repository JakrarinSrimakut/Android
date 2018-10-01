package com.shrimpnugget.h.rollem;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class RollingArea extends Fragment implements GestureDetector.OnGestureListener{
    int diceType;// holds dice ID side 1 to call the correct Dice method
    ImageView rollingDiceImageView;
    int rolledDiceDrawableId;
    private GestureDetectorCompat gestureDetector;
    int diceNumber;
    int diceAmount;
    int modifier;
    int calculatedResult;
    RelativeLayout popUpWindowRelativeLayout;
    private LayoutInflater layoutInflater;
    PopupWindow popupWindow;
    TextView popUpWindowResultTextView;
    Dice dice = new Dice();
    //DiceModifier diceModifier = new DiceModifier();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rolling_area, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rollingDiceImageView = (ImageView) getActivity().findViewById(R.id.rollingDice);
        gestureDetector = new GestureDetectorCompat(getActivity(),this);

        //detects the different gestures on dice image
        rollingDiceImageView.setOnTouchListener((new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        }));
    }

    //for start of app when no dice selected. make it appear.
    public void setDiceImage(int diceID){
        diceType = diceID;
        rollingDiceImageView.setImageResource(diceID);
        if(rollingDiceImageView.getVisibility() == View.INVISIBLE){
            rollingDiceImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.i("onDown", "ONDOWNED");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.i("onSingleTapUP", "SINGLETAPPED");
        rollDice();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i("fling", "FLINGED");
        rollDice();
        return true;
    }

    //Dice is rolled and see result.
    public void rollDice(){
        //play sound everytime dice is click because it's recreated
        MediaPlayer diceRollSound = MediaPlayer.create(getActivity(), R.raw.roll_dice);
        diceRollSound.start();

        switch(diceType){
            case R.drawable.d4_side_1:
                rolledDiceDrawableId = dice.D4Side();//Get random d4 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d4_side_blank_rolled);
                break;
            case R.drawable.d6_side_1:
                rolledDiceDrawableId = dice.D6Side();//Get random d6 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d6_side_blank_rolled);
                break;
            case R.drawable.d8_side_1:
                rolledDiceDrawableId = dice.D8Side();//Get random d8 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d8_side_blank_rolled);
                break;
            case R.drawable.d10_side_1:
                rolledDiceDrawableId = dice.D10Side();//Get random d10 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d10_side_blank_rolled);
                break;
            case R.drawable.d12_side_1:
                rolledDiceDrawableId = dice.D12Side();//Get random d12 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d12_side_blank_rolled);
                break;
            case R.drawable.d20_side_1:
                rolledDiceDrawableId = dice.D20Side();//Get random d20 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d20_side_blank_rolled);
                break;
            case R.drawable.d100_side_00:
                rolledDiceDrawableId = dice.D100Side();//Get random d100 side drawable id
                //show blank rolling dice for 1 second then to random die side
                rollingDiceImageView.setImageResource(R.drawable.d100_side_blank_rolled);
                break;
        }
        new CountDownTimer(500,500){
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                rollingDiceImageView.setImageResource(rolledDiceDrawableId);
                //get dice result and dice modifer combine and set it in  pop up layout
                //1.DiceJava-Set variable to save number rolled DONE
                //2.DiceJava-Get method to grab result of rolled dice DONE
                //3.DiceModifier-get method to grab number of dice and modifier DONE
                //4.Calculate result here and set it to pop_up_window textview
                //5.Dislplay pop up when dice is clicked
                /*diceNumber = dice.getDiceNumber();
                diceAmount = diceModifier.getDiceAmount();
                modifier = diceModifier.getModifier();
                calculatedResult = diceAmount*diceNumber+modifier;

                Log.d("diceAmount", String.valueOf(diceAmount));
                Log.d("modifier", String.valueOf(modifier));

                layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.pop_up_window,null);
                popUpWindowRelativeLayout = (RelativeLayout) container.findViewById(R.id.popUpWindowRelativeLayout);
                popUpWindowResultTextView = (TextView) container.findViewById(R.id.popUpWindowResultTextView);

                popUpWindowResultTextView.setText(String.valueOf(calculatedResult));

                popupWindow = new PopupWindow(container, 400,400,true);
                popupWindow.showAtLocation(popUpWindowRelativeLayout, Gravity.CENTER, 0, 500 );


                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        return true;
                    }
                });*/
            }
        }.start();
    }
}
