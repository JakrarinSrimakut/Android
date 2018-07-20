package com.example.h.rollem;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Dice {
    private int diceNumber;
    private int max;
    private int min;

    public int D4Side(){
        max = 4;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (R.drawable.d4_side_1);
            case 2:
                return (R.drawable.d4_side_2);
            case 3:
                return (R.drawable.d4_side_3);
            case 4:
                return (R.drawable.d4_side_4);
            default:
                Log.v("D4Error", String.valueOf(diceNumber));
                return (0);//return error
        }

    }

    public int D6Side(){
        max = 6;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (R.drawable.d6_side_1);
            case 2:
                return (R.drawable.d6_side_2);
            case 3:
                return (R.drawable.d6_side_3);
            case 4:
                return (R.drawable.d6_side_4);
            case 5:
                return (R.drawable.d6_side_5);
            case 6:
                return (R.drawable.d6_side_6);
            default:
                Log.v("D6Error", String.valueOf(diceNumber));
                return (0);//return error
        }

    }

    public int D8Side(){
        max = 8;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (R.drawable.d8_side_1);
            case 2:
                return (R.drawable.d8_side_2);
            case 3:
                return (R.drawable.d8_side_3);
            case 4:
                return (R.drawable.d8_side_4);
            case 5:
                return (R.drawable.d8_side_5);
            case 6:
                return (R.drawable.d8_side_6);
            case 7:
                return (R.drawable.d8_side_7);
            case 8:
                return (R.drawable.d8_side_8);
            default:
                Log.v("D6Error", String.valueOf(diceNumber));
                return (0);//return error
        }

    }

    public int D10Side(){
        max = 10;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (R.drawable.d10_side_1);
            case 2:
                return (R.drawable.d10_side_2);
            case 3:
                return (R.drawable.d10_side_3);
            case 4:
                return (R.drawable.d10_side_4);
            case 5:
                return (R.drawable.d10_side_5);
            case 6:
                return (R.drawable.d10_side_6);
            case 7:
                return (R.drawable.d10_side_7);
            case 8:
                return (R.drawable.d10_side_8);
            case 9:
                return (R.drawable.d10_side_9);
            case 10:
                return (R.drawable.d10_side_10);
            default:
                Log.v("D6Error", String.valueOf(diceNumber));
                return (0);//return error
        }

    }
}
