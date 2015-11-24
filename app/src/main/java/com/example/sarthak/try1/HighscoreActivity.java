package com.example.sarthak.try1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by sarthak on 9/11/15.
 */
public class HighscoreActivity extends FragmentActivity implements Fragment1.FragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.highscorelayout);
        showscore(0);
    }

    @Override
    public void showscore(int level)
    {
        Fragment2 obj=(Fragment2)getSupportFragmentManager().findFragmentById(R.id.fragment_2);
        obj.setScore(level);
    }

    public void back(View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
