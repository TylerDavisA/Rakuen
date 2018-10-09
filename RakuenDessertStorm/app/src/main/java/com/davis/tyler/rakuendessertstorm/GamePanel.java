package com.davis.tyler.rakuendessertstorm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Scenes.SceneManager;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private SceneManager manager;
    private long startTime;
    private int elapsedTime;

    public GamePanel(Context context, SceneManager sceneManager){
        super(context);
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        //System.out.println("MAKE GAME PANEL");
        manager = sceneManager;
        setFocusable(true);
        setLayerType(LAYER_TYPE_NONE, null);
        startTime = System.currentTimeMillis();
        //dst = new Rect()

    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){
        startTime = System.currentTimeMillis();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        /*thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();*/
        startTime = System.currentTimeMillis();
        if(thread.getState()== Thread.State.TERMINATED){
            thread = new MainThread(getHolder(),this);
            thread.setRunning(true);
            thread.start();
            // <-- added fix
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        //System.out.println("SURFACE DESTROYED");
        while(true){
            try{
                thread.setRunning(false);
                thread.join();
                //System.out.println("THREAD JOINED");
            }
            catch(Exception e){e.printStackTrace();}
            break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        manager.recieveTouch(event);
        return true;
    }

    public void update(){
        elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        //System.out.println("Update");
        manager.update(elapsedTime);

    }

    public void drawMyStuff(Canvas canvas){
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            //oSystem.out.println("Thread: "+thread);
            manager.draw(canvas);
            //System.out.println("Enter draw");
    }

    public void onResume(){
        //System.out.println("RESUME");

        //thread.setRunning(true);
        startTime = System.currentTimeMillis();

        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
        setFocusable(true);
        /*if( thread == null ) {
            System.out.println("RESUME THREAD NULL");
            thread = new MainThread(getHolder(), this);
            thread.setRunning(true);
            thread.start();
        }
        else {
            System.out.println("RESUME THREAD NOT NULL");
            thread.setRunning(true);
        }*/

    }
    public void onPause(){
        thread.setRunning(false);
        startTime = System.currentTimeMillis();
        System.out.println("PAUSE: "+thread);
        try{
            //thread.interrupt();
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
            System.out.println("Failed to interrupt");
        }
    }

    public void onDestroy(){
        thread.setRunning(false);
        System.out.println("CALLING DESTROY : "+thread);
        try{
            thread.interrupt();
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
            System.out.println("Failed to interrupt");
        }
    }


}
