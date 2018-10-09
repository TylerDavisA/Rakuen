package com.davis.tyler.rakuendessertstorm.Animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

import java.io.Serializable;

/**
 * Created by Tadto on 3/22/2018.
 */

public class AnimationShigiBounce implements IAnimation, Serializable{
    public final int SHIGI_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;;
    public final int SHIGI_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;;
    private Bitmap[] shigiBounce;
    private Animation animShigiBounce;

    public AnimationShigiBounce(Context context){
        shigiBounce = new Bitmap[2];
        shigiBounce[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultsmile1);
        shigiBounce[0] = Bitmap.createScaledBitmap(shigiBounce[0], SHIGI_WIDTH, SHIGI_HEIGHT, true);
        shigiBounce[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultsmile2);
        shigiBounce[1] = Bitmap.createScaledBitmap(shigiBounce[1], SHIGI_WIDTH, SHIGI_HEIGHT, true);

        animShigiBounce = new Animation(shigiBounce, 1.2f, IAnimation.SHIGI_BOUNCE);
    }
    @Override
    public Animation getAnimation() {
        return animShigiBounce;
    }
}
