package com.example.sarthak.try1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

    }

    public void diff1()
    {
        Intent i=new Intent(this,GameActivity.class);
        startActivity(i);
    }

    public void diff2()
    {
        Intent i=new Intent(this,GameActivity.class);
        startActivity(i);
    }

    public void diff3()
    {
        Intent i=new Intent(this,GameActivity.class);
        startActivity(i);
    }

    public void help()
    {
    }
}
