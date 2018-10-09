package com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.GamePieces.Helpers.IHelper;
import com.davis.tyler.rakuendessertstorm.R;

public class HelperIconShigiLeeble implements IHelperIcon {
    private Rect rect;
    private int width, height;
    private double sizeMultiplier;
    private Coordinates coords;
    private Bitmap icon;

    public HelperIconShigiLeeble(Context context, Coordinates coords){
        height = 12 * Constants.SCREEN_WIDTH / 100;
        width = 11 * Constants.SCREEN_WIDTH / 100;
        this.coords = coords;
        sizeMultiplier = 1;
        rect = new Rect();
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultsmile2);
        icon = Bitmap.createScaledBitmap(icon, width, height, true);
    }
    @Override
    public void update(int elapsedTime) {
        rect.set( coords.x ,  coords.y
                , (int)(width*sizeMultiplier + coords.x)
                , (int)((height*sizeMultiplier + coords.y)));

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(icon, null, rect, null);
    }

    @Override
    public void select() {
        sizeMultiplier = 1.1;
    }

    @Override
    public void deselect() {
        sizeMultiplier = 1;
    }

    @Override
    public int getType() {
        return IHelper.HELPER_SHIGI;
    }
    @Override
    public Rect getRect() {
        return rect;
    }
}
