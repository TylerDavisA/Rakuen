package com.davis.tyler.rakuendessertstorm.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class Animation implements Parcelable{
    private Bitmap[] frames;
    private int frameIndex;
    private boolean stopOnLastFrame = false;
    private int type;

    private boolean isPlaying = false;
    private float frameTime;
    private long lastFrame;
    public Animation(Bitmap[] frames, float animTime, int type){
        this.frames = frames;
        frameIndex = 0;
        frameTime = animTime/frames.length;
        lastFrame = System.currentTimeMillis();
        this.type = type;
    }
    public void setFrameIndex(int frameIndex){this.frameIndex = frameIndex;}
    public void setBitmaps(AnimationFactory animationFactory){
        frames = animationFactory.getFrames(type);
    }
    public Animation(Parcel in){

        frameIndex = in.readInt();
        frameTime = in.readFloat();
        lastFrame = System.currentTimeMillis();
        int temp = in.readInt();
        isPlaying = false;
        if(temp == 1){
            isPlaying = true;
        }
        type = in.readInt();
    }

    public void draw(Canvas canvas, Rect destination){
        if(!isPlaying)
            return;
        scaleRect(destination);
        canvas.drawBitmap(frames[frameIndex], null, destination,null);

    }

    private void scaleRect(Rect rect){
        float whRatio = (float) frames[frameIndex].getWidth()/frames[frameIndex].getHeight();
        if(rect.width() > rect.height())
            rect.left = rect.right - (int)(rect.height() * whRatio);
        else
            rect.top = rect.bottom - (int)(rect.width() * 1/whRatio);

    }
    public void update(){
        if(!isPlaying)
            return;
        if(System.currentTimeMillis() - lastFrame > frameTime*1000){
            frameIndex++;
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
        }
        /*if(stopOnLastFrame && frameIndex >= 0)
        {
            stop();
        }*/
    }

    public void play(){
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }
    public void stop(){isPlaying = false;}
    public boolean isPlaying(){return isPlaying;}
    //public void stopOnLastFrame(){stopOnLastFrame = true;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(frameIndex);
        parcel.writeFloat(frameTime);
        if(isPlaying){
            parcel.writeInt(1);
        }
        else
            parcel.writeInt(0);
        parcel.writeInt(type);



    }

    public static final Parcelable.Creator<Animation> CREATOR = new Parcelable.Creator<Animation>(){
        public Animation createFromParcel(Parcel in){
            return new Animation(in);
        }

        public Animation[] newArray(int size){
            return new Animation[size];
        }

    };
}
