package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.transition.Scene;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

/**
 * Created by Tadto on 3/24/2018.
 */

public class SceneTitle implements IScene, Parcelable{
    //STUFF TO REINIT: SCENEMANAGER, CONTEXT
    private Context context;
    private Bitmap bitmap;
    private Rect rect;
    private SceneManager sceneManager;
    private Paint paint;
    public SceneTitle(Context context, SceneManager sceneManager){
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.titlescreen);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        rect = new Rect(0,0,width, height);
        this.sceneManager = sceneManager;
        paint = new Paint();
    }

    public SceneTitle(Parcel in){

        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void parcelableInit(Context context, RelativeLayout relativeLayout, SceneManager sceneManager) {
        this.context = context;
        this.sceneManager = sceneManager;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        rect = new Rect(0,0,width, height);
    }
    @Override
    public void update(int elapsedTime) {

    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, null, rect, null);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent e) {
        int action = e.getAction();

        switch (action){
            case MotionEvent.ACTION_UP:
                sceneManager.changeScene(SceneManager.SCENE_GAME);
                break;
        }
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        //parcel.writeParcelable(bitmap, i);
        System.out.println("INSTANCE OF PARCEL");


    }

    public static final Parcelable.Creator<SceneTitle> CREATOR = new Parcelable.Creator<SceneTitle>(){
        public SceneTitle createFromParcel(Parcel in){
            return new SceneTitle(in);
        }

        public SceneTitle[] newArray(int size){
            return new SceneTitle[size];
        }

    };
}
