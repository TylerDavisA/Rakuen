package com.davis.tyler.rakuendessertstorm.Treats.TreatTypes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;

public class TreatTypeGoodMousseTripleRaspberry implements ITreatTypeGood {
    private Bitmap bitmap;
    private int RECT_HEIGHT;
    private int RECT_WIDTH;
    private int pointValue;
    private int type = Treat.MOUSSE_TRIPLE_RASPBERRY;

    public TreatTypeGoodMousseTripleRaspberry(Context context){
        RECT_HEIGHT = (int)(Constants.SCREEN_HEIGHT * .119);
        RECT_WIDTH = (int)(RECT_HEIGHT * .7);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.triplerasberrymousse);
        bitmap = Bitmap.createScaledBitmap(bitmap, RECT_WIDTH, RECT_HEIGHT, true);
        pointValue = 50;
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
