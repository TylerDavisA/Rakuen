package com.davis.tyler.rakuendessertstorm.Treats.TreatTypes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class TreatTypeGoodDonutSprinkled implements ITreatTypeGood, Serializable {
    private Bitmap bitmap;
    private final int RECT_HEIGHT ;
    private final int RECT_WIDTH;
    private int pointValue;
    private int type = Treat.DONUT_PINK_SPRINKLES;

    public TreatTypeGoodDonutSprinkled(Context context){
        RECT_HEIGHT = (int)(Constants.SCREEN_HEIGHT * .08);
        RECT_WIDTH = (int)(RECT_HEIGHT * 1.105f);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.donutpinksprinkles);
        pointValue = 50;
        bitmap = Bitmap.createScaledBitmap(bitmap, RECT_WIDTH, RECT_HEIGHT, true);
    }
    @Override
    public int getMidWidth() {
        return RECT_WIDTH/2;
    }

    @Override
    public int getMidHeight() {
        return RECT_HEIGHT/2;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getPoints() {
        return pointValue;
    }
    @Override
    public int getHeight() {
        return RECT_HEIGHT;
    }

    @Override
    public int getWidth() {
        return RECT_WIDTH;
    }
    @Override
    public int getType() {
        return type;
    }
}
