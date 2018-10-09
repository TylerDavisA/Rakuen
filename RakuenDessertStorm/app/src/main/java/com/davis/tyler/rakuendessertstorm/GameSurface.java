package com.davis.tyler.rakuendessertstorm;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Tadto on 3/23/2018.
 */

public class GameSurface extends SurfaceView implements Runnable {
    Thread thread = null;
    SurfaceHolder holder;
    boolean isItOk = false;
    public GameSurface(Context context) {
        super(context);
        holder = getHolder();
    }

    @Override
    public void run() {
        while(isItOk){
            if(!holder.getSurface().isValid())
                continue;
            Canvas c = holder.lockCanvas();

        }
    }

    public void pause(){
        isItOk = false;
        while(true){
            try{
                thread.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            break;
        }
        thread = null;
    }

    public void resume(){
        isItOk = true;
        thread = new Thread(this);
        thread.start();
    }
}
