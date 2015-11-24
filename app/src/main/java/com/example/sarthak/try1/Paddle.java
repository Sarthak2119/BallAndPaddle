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
        this.bitmap=bitmap;
        this.width=width;
        this.height=height;

        x=(GamePanel.WIDTH-width)/2;
        y=(GamePanel.HEIGHT-height);
        startTime=System.nanoTime();
    }

    public void update()
    {
        x+=dx;
        if(x>GamePanel.WIDTH-width)
            x=GamePanel.WIDTH-width;
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

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetScore(){score = 0;}
}
