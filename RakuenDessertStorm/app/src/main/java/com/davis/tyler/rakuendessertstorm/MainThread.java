package com.davis.tyler.rakuendessertstorm;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Tadto on 3/9/2018.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 60;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private boolean cLocked = false;




    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {

            startTime = System.nanoTime();
            Canvas canvas = null;
            try {
                //System.out.println(""+this);
                if(!surfaceHolder.getSurface().isValid())
                    continue;

                if (!cLocked){
                    canvas = this.surfaceHolder.lockCanvas();
                    cLocked = true;
                }

//Unlocking

                //System.out.println("HELLO FROM BEFORE SYNCRHONIZED");
                if(canvas != null) {
                    synchronized (surfaceHolder) {
                        this.gamePanel.update();
                        this.gamePanel.drawMyStuff(canvas);
                    }
                    if (cLocked) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        cLocked = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime > 0)
                    this.sleep(waitTime);
            }catch(Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS){
                averageFPS = 1000/((totalTime / frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("AVERAGE FPS"+averageFPS);
            }
        }
    }

    public void setRunning(boolean running){
        this.running = running;
    }
}
