package com.example.sarthak.try1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by sarthak on 9/11/15.
 */
public class HelpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.helplayout);
    }

    public void home(View view)
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
