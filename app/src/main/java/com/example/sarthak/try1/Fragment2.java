package com.example.sarthak.try1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sarthak on 9/11/15.
 */
public class Fragment2 extends Fragment {

    TextView text1;
    TextView text2;
    TextView text3;
    TextView levelno;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_2,container,false);
    }

    public void setScore(int level)
    {

        text1=(TextView)getView().findViewById(R.id.score1);
        text2=(TextView)getView().findViewById(R.id.score2);
        text3=(TextView)getView().findViewById(R.id.score3);
        levelno=(TextView)getView().findViewById(R.id.levelno);

        int score1,score2,score3;
        SharedPreferences preferences=getActivity().getSharedPreferences("MyHighScore", Context.MODE_PRIVATE);
        score1=preferences.getInt("level"+level+"a",0);
        score2=preferences.getInt("level"+level+"b",0);
        score3=preferences.getInt("level"+level+"c",0);
        text1.setText("1- "+score1);
        text2.setText("2- "+score2);
        text3.setText("3- "+score3);

        level++;
        levelno.setText("LEVEL-"+level);
    }
}
