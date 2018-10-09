package com.davis.tyler.rakuendessertstorm.Animation;

import android.graphics.Bitmap;

/**
 * Created by Tadto on 3/22/2018.
 */

public interface IAnimation {
    public static final int SHIGI_BOUNCE = 0;
    public static final int SHIGI_WAVE = 1;
    public static final int SHIGI_TALK = 2;
    public static final int GUY_TALK = 3;
    public static final int LEEBLE_DANCE = 4;
    public static final int SHIMEJI_MARCH = 5;
    public static final int PDOG_CHEW = 6;
    public Animation getAnimation();
}
