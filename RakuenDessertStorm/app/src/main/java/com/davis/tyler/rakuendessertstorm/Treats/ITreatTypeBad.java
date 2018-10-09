package com.davis.tyler.rakuendessertstorm.Treats;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

public interface ITreatTypeBad extends ITreatTypeGood {
    public int getMidWidth();
    public int getMidHeight();
    public Bitmap getBitmap();
    public int getPoints();
    public int getHeight();
    public int getWidth();
    public int getType();
    public void update();
    public void draw(Canvas canvas, Rect rect);
}
