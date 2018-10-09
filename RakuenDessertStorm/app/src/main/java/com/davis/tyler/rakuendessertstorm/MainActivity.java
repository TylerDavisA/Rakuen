package com.davis.tyler.rakuendessertstorm;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Parcel;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Scenes.SceneManager;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rootPanel;
    private GamePanel surfaceView;
    private SceneManager sceneManager;
    public  int SCREEN_WIDTH; //2560
    public  int SCREEN_HEIGHT; // 1440
    private Constants constants;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindow().setFormat(PixelFormat.RGBA_8888);
        getWindowManager().getDefaultDisplay().getMetrics(dm);


        rootPanel = findViewById(R.id.layout);


        if(savedInstanceState != null){
            constants = savedInstanceState.getParcelable("constants");
            sceneManager = savedInstanceState.getParcelable("scenemanager");
            //sceneManager.parcelableInit(this, rootPanel);
            System.out.println("CALLING REINIT");
        }
        else{
            /*Constants.SCREEN_WIDTH = dm.widthPixels;
            Constants.SCREEN_HEIGHT = dm.heightPixels;
            Constants.MAX_PLAYER_SPEED = Constants.SCREEN_WIDTH / 1700.0f;
            Constants.PLAYER_WIDTH = 13 * Constants.SCREEN_WIDTH / 100;*/
            constants = new Constants(dm.widthPixels, dm.heightPixels,
                    13 * dm.widthPixels / 100);
            sceneManager = new SceneManager(this, rootPanel);
        }
        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        boolean isScreenOn;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        if(isScreenOn) {
            //sceneManager = new SceneManager(this, rootPanel);
            surfaceView = new GamePanel(this, sceneManager);
            surfaceView.setZOrderOnTop(true);
            surfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
            rootPanel.setBackgroundResource(R.drawable.defaultbackground);
            rootPanel.addView(surfaceView, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        }
        //surfaceView.onResume();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(surfaceView != null)
            surfaceView.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if(surfaceView != null)
        surfaceView.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(surfaceView != null)
        surfaceView.onDestroy();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        constants = savedInstanceState.getParcelable("constants");
        sceneManager = savedInstanceState.getParcelable("scenemanager");

    }
    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("constants", constants);
        bundle.putParcelable("scenemanager", sceneManager);
        //surfaceView.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if(sceneManager.onBackPressed())
            super.onBackPressed();
    }
}
