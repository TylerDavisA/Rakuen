package com.davis.tyler.rakuendessertstorm.Treats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

public class TreatTypeBadShroom implements ITreatTypeBad {
    private final int RECT_HEIGHT;
    private final int RECT_WIDTH;
    private int pointValue;
    private int type = Treat.BAD_SHROOM;
    private Bitmap[] anim;
    private AnimationManager animationManager;

    public TreatTypeBadShroom(Context context){

        RECT_HEIGHT = (int)(Constants.SCREEN_HEIGHT * .119);
        RECT_WIDTH = (int)(RECT_HEIGHT );
        pointValue = 50;
        /*anim = new Bitmap[8];
        anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom1);
        anim[0] = Bitmap.createScaledBitmap(anim[0], RECT_WIDTH, RECT_HEIGHT, true);
        anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom2);
        anim[1] = Bitmap.createScaledBitmap(anim[1], RECT_WIDTH, RECT_HEIGHT, true);
        anim[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom3);
        anim[2] = Bitmap.createScaledBitmap(anim[2], RECT_WIDTH, RECT_HEIGHT, true);
        anim[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom4);
        anim[3] = Bitmap.createScaledBitmap(anim[3], RECT_WIDTH, RECT_HEIGHT, true);
        anim[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom5);
        anim[4] = Bitmap.createScaledBitmap(anim[3], RECT_WIDTH, RECT_HEIGHT, true);
        anim[5] = anim[3];
        anim[6] = anim[2];
        anim[7] = anim[1];*/

        anim = new Bitmap[2];
        anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom1);
        anim[0] = Bitmap.createScaledBitmap(anim[0], RECT_WIDTH, RECT_HEIGHT, true);
        anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.badshroom5);
        anim[1] = Bitmap.createScaledBitmap(anim[1], RECT_WIDTH, RECT_HEIGHT, true);
        animationManager = new AnimationManager(new Animation[]{new Animation(anim, 1f, type)});
        animationManager.playAnim(0);
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
        return null;
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

    @Override
    public void update() {
        animationManager.update();
    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        animationManager.draw(canvas, rect);
    }
}
