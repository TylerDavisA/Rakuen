package com.davis.tyler.rakuendessertstorm.Animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

/**
 * Created by Tadto on 3/24/2018.
 */

public class AnimationShigiTalk implements IAnimation {
    public final int SHIGI_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;;
    public final int SHIGI_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;;
    private Bitmap[] frames;
    private Animation anim;

    public AnimationShigiTalk(Context context){
        frames = new Bitmap[2];
        frames[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaulttalk1);
        frames[0] = Bitmap.createScaledBitmap(frames[0], SHIGI_WIDTH, SHIGI_HEIGHT, true);
        frames[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaulttalk2);
        frames[1] = Bitmap.createScaledBitmap(frames[1], SHIGI_WIDTH, SHIGI_HEIGHT, true);

        anim = new Animation(frames, 1.2f, IAnimation.SHIGI_TALK);
    }
    @Override
    public Animation getAnimation() {
        return anim;
    }
}
