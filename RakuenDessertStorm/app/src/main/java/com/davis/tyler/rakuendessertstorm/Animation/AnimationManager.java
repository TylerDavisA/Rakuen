package com.davis.tyler.rakuendessertstorm.Animation;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.GamePieces.Player;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class AnimationManager implements Parcelable {
    private Animation[] animations;
    private int animationIndex = 0;

    public AnimationManager(Animation[] animations){
        this.animations = animations;
    }

    public void playAnim(int index){
        for(int i = 0; i < animations.length; i++){
            if(i == index) {
                if(!animations[index].isPlaying())
                    animations[i].play();
            }
            else
                animations[i].stop();
        }
        animationIndex = index;
    }
    public AnimationManager(Parcel in){
        animationIndex = in.readInt();
        animations = in.createTypedArray(Animation.CREATOR);
    }

    public void setBitmaps(AnimationFactory animationFactory){
        for(Animation a: animations){
            a.setBitmaps(animationFactory);
        }
    }

    public void draw(Canvas canvas, Rect rect){
        if(animations[animationIndex].isPlaying()) {
            animations[animationIndex].draw(canvas, rect);
        }

    }

    public void update(){
        if(animations[animationIndex].isPlaying())
            animations[animationIndex].update();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(animationIndex);
        parcel.writeTypedArray(animations, i);



    }

    public static final Parcelable.Creator<AnimationManager> CREATOR = new Parcelable.Creator<AnimationManager>(){
        public AnimationManager createFromParcel(Parcel in){
            return new AnimationManager(in);
        }

        public AnimationManager[] newArray(int size){
            return new AnimationManager[size];
        }

    };
}
