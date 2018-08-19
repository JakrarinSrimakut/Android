package com.example.h.rollem;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.h.rollem.R;

public class DiceModifier extends Fragment {
    Button amountOfDiceMinusButton;
    Button amountOfDicePlusButton;
    Button modifierMinusButton;
    Button modifierPlusButton;
    TextView amountOfDiceTextView;
    TextView modifierTextView;
    static int diceAmount = 1;
    static int modifier = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dice_modifier, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*amountOfDiceMinusButton = (Button) getActivity().findViewById(R.id.amountOfDiceMinusButton);
        amountOfDicePlusButton = (Button) getActivity().findViewById(R.id.amountOfDicePlusButton);
        modifierMinusButton = (Button) getActivity().findViewById(R.id.modifierMinusButton);
        modifierPlusButton = (Button) getActivity().findViewById(R.id.modifierPlusButton);
        amountOfDiceTextView = (TextView) getActivity().findViewById(R.id.amountOfDiceTextView);
        modifierTextView = (TextView) getActivity().findViewById(R.id.modifierTextView);

        amountOfDiceMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check so it won't go less than 1 dice
                if(diceAmount > 1){
                    diceAmount--;
                    String diceAmountString = String.valueOf(diceAmount);
                    amountOfDiceTextView.setText(diceAmountString + "d");
                }
            }
        });

        amountOfDicePlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(diceAmount < 100){
                    diceAmount++;
                    String diceAmountString = String.valueOf(diceAmount);
                    amountOfDiceTextView.setText(diceAmountString + "d");
                }
            }
        });
        modifierMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check so it won't go less than 1 dice
                if(modifier > 0){
                    modifier--;
                    String diceAmountString = String.valueOf(diceAmount);
                    modifierTextView.setText("+" + modifier);
                }
            }
        });

        modifierPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(modifier < 100){
                    modifier++;
                    String diceAmountString = String.valueOf(diceAmount);
                    modifierTextView.setText("+" + modifier);
                }
            }
        });*/
    }

    public int getDiceAmount(){
        return diceAmount;
    }

    public int getModifier(){
        return modifier;
    }
}
