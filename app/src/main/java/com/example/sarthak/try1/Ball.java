package com.example.sarthak.try1;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by sarthak on 3/11/15.
 */
public class Ball extends GameObject{
    private int dx;
    private int dy;
    Bitmap bitmap;
    private long startTime;

    public Ball(Bitmap bitmap,int width,int height,int paddleHeight)
    {
        x=(GamePanel.WIDTH-54)/2;
        y=(GamePanel.HEIGHT-paddleHeight-55);
        dx=-8;
        dy=-8;
        this.bitmap=bitmap;
        this.width=width;
        this.height=height;
        startTime=System.nanoTime();
    }

    public void update()
    {
        x+=dx;
        y+=dy;
        long elapsed=(System.nanoTime()-startTime)/1000000;
        if(elapsed>20000)
        {
            if(dx>0)
            {
                dx+=2;
                dy+=2;
            }
            else
            {
                dx-=2;
                dy-=2;
            }
            if(dx>16)
            {
                dx=16;
            }
            if(dx<-16)
            {
                dx=-16;
            }
            if(dy>16)
            {
                dy=16;
            }
            if(dy<-16)
            {
                dy=-16;
            }
            startTime=System.nanoTime();
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap,x,y,null);
    }

    public void setDx(int dx)
    {
        this.dx=dx;
    }
    public void setDy(int dy)
    {
        this.dy=dy;
    }
    public void setX(int x)
    {
        this.x=x;
    }
    public void setY(int y)
    {
        this.y=y;
    }
    public int getDx(){return dx;}
    public int getDy(){return dy;}
}
