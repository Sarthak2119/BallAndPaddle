package com.example.sarthak.try1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by sarthak on 9/11/15.
 */
public class DiffActivity extends Activity {
    private int difflevel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.difflayout);
    }

    public void level1(View view)
    {
        difflevel=0;
    }

    public void level2(View view)
    {
        difflevel=1;
    }

    public void level3(View view)
    {
        difflevel=2;
    }

    public void back(View view)
    {
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        if(radioGroup.getCheckedRadioButtonId()==-1)
        {
            Toast t=Toast.makeText(this,"Please Select a Level!!!",Toast.LENGTH_SHORT);
            t.show();
        }
        else
        {
            Intent i=new Intent(this,MainActivity.class);
            i.putExtra("difflevel",difflevel);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        if(radioGroup.getCheckedRadioButtonId()==-1)
        {
            Toast t=Toast.makeText(this,"Please Select a Level!!!",Toast.LENGTH_SHORT);
            t.show();
        }
        else
        {
            Intent i=new Intent(this,MainActivity.class);
            i.putExtra("difflevel",difflevel);
            startActivity(i);
        }
    }
}
