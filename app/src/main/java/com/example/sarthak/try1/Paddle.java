package com.example.sarthak.try1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by sarthak on 3/11/15.
 */
public class Paddle extends GameObject {

    private int score;
    private boolean playing;
    private long startTime;
    Bitmap bitmap;
    private int speed;

    public Paddle(Bitmap bitmap,int width,int height)
    {
        speed=0;
        score=0;
        dx=0;
        x=295;
        y=400;
        this.bitmap=bitmap;
        this.width=width;
        this.height=height;
        startTime=System.nanoTime();
    }

    public void update()
    {
        x+=dx;
        if(x>800-230)
            x=800-230;
        if(x<0)
            x=0;
        long elapsed=(System.nanoTime()-startTime)/1000000;
        if(elapsed>300)
        {
            score++;
            startTime=System.nanoTime();
        }

    }

    public void resetDx()
    {
        this.dx=0;
    }

    public void setSpeed(int speed)
    {
        this.speed=speed;
    }
    public void moveLeft()
    {
        this.dx=-speed;
    }

    public void moveRight()
    {
        this.dx=speed;
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap,x,y,null);
    }

    public int getScore()
    {
        return score;
    }

    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetScore(){score = 0;}
}
