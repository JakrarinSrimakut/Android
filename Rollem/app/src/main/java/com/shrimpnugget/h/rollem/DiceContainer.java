package com.shrimpnugget.h.rollem;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DiceContainer extends Fragment {
    ImageView invisibleDice; // keep track of dice to be set Invisible
    ImageView d4;
    ImageView d6;
    ImageView d8;
    ImageView d10;
    ImageView d12;
    ImageView d20;
    ImageView d100;
    Communicator comm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dice_container, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        d4 = (ImageView) getActivity().findViewById(R.id.d4ImageView);
        d6 = (ImageView) getActivity().findViewById(R.id.d6ImageView);
        d8 = (ImageView) getActivity().findViewById(R.id.d8ImageView);
        d10 = (ImageView) getActivity().findViewById(R.id.d10ImageView);
        d12 = (ImageView) getActivity().findViewById(R.id.d12ImageView);
        d20 = (ImageView) getActivity().findViewById(R.id.d20ImageView);
        d100 = (ImageView) getActivity().findViewById(R.id.d100ImageView);

        //When clicked d4 disappear and appear in rolling area
        d4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d4_side_1);//send d4 drawable id to main respond method
                setDiceVisibility(d4);//set previous invisible dice to visible

            }
        });

        //When clicked d6 disappear and appear in rolling area
        d6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d6_side_1);//send d6 drawable id to main respond method
                setDiceVisibility(d6);//set previous invisible dice to visible
            }
        });

        //When clicked d8 disappear and appear in rolling area
        d8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d8_side_1);//send d8 drawable id to main respond method
                setDiceVisibility(d8);//set previous invisible dice to visible
            }
        });

        //When clicked d10 disappear and appear in rolling area
        d10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d10_side_1);//send d10 drawable id to main respond method
                setDiceVisibility(d10);//set previous invisible dice to visible
            }
        });

        //When clicked d12 disappear and appear in rolling area
        d12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d12_side_1);//send d12 drawable id to main respond method
                setDiceVisibility(d12);//set previous invisible dice to visible
            }
        });

        //When clicked d20 disappear and appear in rolling area
        d20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d20_side_1);//send d12 drawable id to main respond method
                setDiceVisibility(d20);//set previous invisible dice to visible
            }
        });

        //When clicked d100 disappear and appear in rolling area
        d100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comm = (Communicator)getActivity();
                comm.respond(R.drawable.d100_side_00);//send d12 drawable id to main respond method
                setDiceVisibility(d100);//set previous invisible dice to visible
            }
        });
    }

    //make previous selected dice visible and and current invisible. if no previous selected just make current visible
    public void setDiceVisibility(ImageView diceToBeSetInvisible)
    {
        if(invisibleDice == null){
            invisibleDice = diceToBeSetInvisible;
            diceToBeSetInvisible.setVisibility(View.INVISIBLE);
        }
        else{
            switch (invisibleDice.getId()){
                case R.id.d4ImageView:
                    d4.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d6ImageView:
                    d6.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d8ImageView:
                    d8.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d10ImageView:
                    d10.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d12ImageView:
                    d12.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d20ImageView:
                    d20.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
                case R.id.d100ImageView:
                    d100.setVisibility(View.VISIBLE);
                    invisibleDice = diceToBeSetInvisible;
                    diceToBeSetInvisible.setVisibility(View.INVISIBLE);
                    break;
            }
        }
    }
}
