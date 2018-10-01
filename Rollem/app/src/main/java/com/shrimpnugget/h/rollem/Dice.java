package com.shrimpnugget.h.rollem;

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
                return (com.shrimpnugget.h.rollem.R.drawable.d4_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d4_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d4_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d4_side_4);
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
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_4);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_5);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d6_side_6);
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
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_4);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_5);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_6);
            case 7:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_7);
            case 8:
                return (com.shrimpnugget.h.rollem.R.drawable.d8_side_8);
            default:
                Log.v("D8Error", String.valueOf(diceNumber));
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
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_4);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_5);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_6);
            case 7:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_7);
            case 8:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_8);
            case 9:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_9);
            case 10:
                return (com.shrimpnugget.h.rollem.R.drawable.d10_side_10);
            default:
                Log.v("D10Error", String.valueOf(diceNumber));
                return (0);//return error
        }
    }

    public int D12Side(){
        max = 12;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_4);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_5);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_6);
            case 7:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_7);
            case 8:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_8);
            case 9:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_9);
            case 10:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_10);
            case 11:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_11);
            case 12:
                return (com.shrimpnugget.h.rollem.R.drawable.d12_side_12);
            default:
                Log.v("D12Error", String.valueOf(diceNumber));
                return (0);//return error
        }
    }

    public int D20Side(){
        max = 20;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_1);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_2);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_3);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_4);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_5);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_6);
            case 7:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_7);
            case 8:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_8);
            case 9:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_9);
            case 10:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_10);
            case 11:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_11);
            case 12:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_12);
            case 13:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_13);
            case 14:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_14);
            case 15:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_15);
            case 16:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_16);
            case 17:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_17);
            case 18:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_18);
            case 19:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_19);
            case 20:
                return (com.shrimpnugget.h.rollem.R.drawable.d20_side_20);
            default:
                Log.v("D20Error", String.valueOf(diceNumber));
                return (0);//return error
        }
    }

    public int D100Side(){
        max = 10;
        min = 1;

        int range = (max-min)+1;
        diceNumber =(int) (Math.random()*range)+min;//(int)(Math.random() * ((upperbound - lowerbound) + 1) + lowerbound);

        switch (diceNumber){
            case 1:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_00);
            case 2:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_10);
            case 3:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_20);
            case 4:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_30);
            case 5:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_40);
            case 6:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_50);
            case 7:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_60);
            case 8:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_70);
            case 9:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_80);
            case 10:
                return (com.shrimpnugget.h.rollem.R.drawable.d100_side_90);
            default:
                Log.v("D100Error", String.valueOf(diceNumber));
                return (0);//return error
        }
    }

    public int getDiceNumber(){
        return diceNumber;
    }
}
