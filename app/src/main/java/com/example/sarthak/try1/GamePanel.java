package com.example.sarthak.try1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by sarthak on 3/11/15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH=800;
    public static final int HEIGHT=450;
    private MainThread thread;
    private Paddle paddle;
    private ArrayList<Ball> ball;
    private boolean newGameCreated;
    private int previousScore;
    private long startTime;
    private int difflevel;
    private ArrayList<Paddle> paddles;

    public GamePanel(Context context,int difflevel)
    {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        this.difflevel=difflevel;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder)
    {
        paddles=new ArrayList<Paddle>();
        paddles.add(new Paddle(BitmapFactory.decodeResource(getResources(),R.drawable.paddle3),340,15));
        paddles.add(new Paddle(BitmapFactory.decodeResource(getResources(),R.drawable.paddle2),270,48));
        paddles.add(new Paddle(BitmapFactory.decodeResource(getResources(),R.drawable.paddle1),230,47));
        paddle=paddles.get(difflevel);
        paddle.setSpeed(45*340/paddle.getWidth());
        ball=new ArrayList<Ball>();
        ball.add(new Ball(BitmapFactory.decodeResource(getResources(),R.drawable.rsz_red_ball),54,55,paddle.getHeight()));
        newGameCreated=true;
        previousScore=0;

        thread =new MainThread(getHolder(),this);
        thread.setRunning(true);
        thread.start();
        startTime=System.nanoTime();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder)
    {
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try{thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;

            }catch(InterruptedException e){e.printStackTrace();}

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder,int format,int width,int height)
    {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            final float scaleFactorX=getWidth()/(WIDTH*1.f);
            float touchX=event.getX();
            if(!paddle.getPlaying()&&newGameCreated)
            {
                paddle.setPlaying(true);
                startTime=System.nanoTime();
            }
            else if(paddle.getX()*scaleFactorX>touchX)
            {
                paddle.moveLeft();
            }
            else  if((paddle.getX()+paddle.getWidth())*scaleFactorX<touchX)
            {
                paddle.moveRight();
            }

            return true;
        }
        else if(event.getAction()==MotionEvent.ACTION_UP)
        {
            paddle.resetDx();
        }
        return super.onTouchEvent(event);
    }

    public void update()
    {
        if(paddle.getPlaying())
        {
            paddle.update();
            for(Ball b:ball) {
                b.update();
            }
            previousScore=paddle.getScore();
            bounce();
            if((System.nanoTime()-startTime)/1000000>20000&&ball.size()<3)
            {
                ball.add(new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.rsz_red_ball), 54, 55, paddle.getHeight()));
                startTime=System.nanoTime();
            }
        }
        else
        {
            newGameCreated=false;
            if(!newGameCreated)
            {
                newGame();
            }
        }
    }

    public void draw(Canvas canvas)
    {
        final float scaleFactorX=getWidth()/(WIDTH*1.f);
        final float scaleFactorY=getHeight()/(HEIGHT*1.f);

        if(canvas!=null)
        {
            final int savedState=canvas.save();
            canvas.scale(scaleFactorX,scaleFactorY);
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background), 0, 0, null);
            paddle.draw(canvas);
            for(Ball b:ball)
            {
                b.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }

    }

    public void bounce()
    {
        for(Ball b:ball)
        {
            if(b.x<=0||b.x>=(WIDTH-54))
            {
                b.setDx(-1 * b.getDx());
            }
            if(b.y<=0) {
                b.setDy(-1 * b.getDy());
            }
            if(collision(b,paddle)) {
                if(b.getX()+55>=paddle.getX()&&b.getX()<=paddle.getX()+paddle.getWidth())
                {
                    b.setDy(-1 * b.getDy());
                    int paddlex;
                    int ballx;
                    paddlex=paddle.getX()+paddle.getWidth()/2;
                    ballx=b.x+54/2;
                    b.setDx(b.getDx()+(ballx-paddlex)/40);
                }
                else
                {
                    paddle.setPlaying(false);
                    updateHighScore();
                    break;
                }
            }
            else if(b.y+55>=(HEIGHT-paddle.getHeight()+5)) {
                paddle.setPlaying(false);
                updateHighScore();
                break;
            }
        }
    }

    public boolean collision(GameObject a,GameObject b)
    {
        if(a.getRectangle().intersect(b.getRectangle()))
        {
            return true;
        }
        return  false;
    }

    public void newGame()
    {
        paddle.resetScore();
        paddle.setX((GamePanel.WIDTH-paddle.getWidth())/2);
        ball.clear();
        ball.add(new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.rsz_red_ball), 54, 55,paddle.getHeight()));
        newGameCreated=true;
    }

    public void drawText(Canvas canvas)
    {
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        if(paddle.getPlaying())
        canvas.drawText("SCORE: " + paddle.getScore(), 30, 30, paint);
        if(!paddle.getPlaying()&&newGameCreated)
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(40);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            if(previousScore!=0)
            {
                canvas.drawText("PREVIOUS SCORE: "+previousScore,30,30,paint);
            }
            canvas.drawText("PRESS TO START", WIDTH/2-150+4, HEIGHT/2, paint1);
        }
    }

    public void updateHighScore()
    {
        int currentScore=paddle.getScore();
        SharedPreferences preferences=getContext().getSharedPreferences("MyHighScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int[] score=new int[3];
        score[0]=preferences.getInt("level"+difflevel+"a",0);
        score[1]=preferences.getInt("level"+difflevel+"b",0);
        score[2]=preferences.getInt("level"+difflevel+"c",0);

        for(int i=0;i<3;i++)
        {
            if(currentScore>score[i])
            {
                int temp=currentScore;
                currentScore=score[i];
                score[i]=temp;
            }
        }

        editor.putInt("level"+difflevel+"a",score[0]);
        editor.putInt("level"+difflevel+"b",score[1]);
        editor.putInt("level"+difflevel+"c",score[2]);
        editor.commit();
    }
}
