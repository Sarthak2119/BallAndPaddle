package com.example.sarthak.try1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    static int difflevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras=getIntent().getExtras();
        difflevel=0;
        if(extras!=null)
        {
            difflevel=extras.getInt("difflevel");
        }

        SharedPreferences preferences=getSharedPreferences("MyHighScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();

        if(!preferences.contains("level0a"))
        {
            editor.putInt("level0a",0);
            editor.putInt("level0b",0);
            editor.putInt("level0c",0);
        }

        if(!preferences.contains("level1a"))
        {
            editor.putInt("level1a",0);
            editor.putInt("level1b",0);
            editor.putInt("level1c",0);
        }

        if(!preferences.contains("level2a"))
        {
            editor.putInt("level2a",0);
            editor.putInt("level2b",0);
            editor.putInt("level2c",0);
        }

        editor.commit();

        setContentView(R.layout.activity_main);

    }

    public void play(View view)
    {
        Intent i=new Intent(this,GameActivity.class);
        i.putExtra("difflevel", difflevel);
        startActivity(i);
    }

    public void diff(View view)
    {
        Intent i=new Intent(this,DiffActivity.class);
        startActivity(i);
    }

    public void highscore(View view)
    {
        Intent i=new Intent(this,HighscoreActivity.class);
        startActivity(i);
    }

    public void help(View view)
    {
        Intent i=new Intent(this,HelpActivity.class);
        startActivity(i);
    }
}
