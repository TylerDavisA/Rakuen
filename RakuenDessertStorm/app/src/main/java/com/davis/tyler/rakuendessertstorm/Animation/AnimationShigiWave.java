package com.davis.tyler.rakuendessertstorm.Animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

/**
 * Created by Tadto on 3/24/2018.
 */

public class AnimationShigiWave implements IAnimation {
    public final int SHIGI_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;;
    public final int SHIGI_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;;
    private Bitmap[] frames;
    private Animation anim;

    public AnimationShigiWave(Context context){
        frames = new Bitmap[4];
        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave1);
        frames[0] = Bitmap.createScaledBitmap(frames[0], SHIGI_WIDTH, SHIGI_HEIGHT, true);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave2);
        frames[1] = Bitmap.createScaledBitmap(frames[1], SHIGI_WIDTH, SHIGI_HEIGHT, true);
        frames[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave3);
        frames[2] = Bitmap.createScaledBitmap(frames[2], SHIGI_WIDTH, SHIGI_HEIGHT, true);
        frames[3] = frames[1];

        anim = new Animation(frames, .5f, IAnimation.SHIGI_WAVE);
    }
    @Override
    public Animation getAnimation() {
        return anim;
    }
}
