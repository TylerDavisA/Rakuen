package com.davis.tyler.rakuendessertstorm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

/**
 * Created by Tadto on 6/12/2018.
 */

public class ScoreDisplaySystem implements ISubscriber {
    private StatsLevels stats;
    private TreatFactory treatFactory;
    private String score;
    private Paint paint, painttarget;
    private ITreatTypeGood targetTreat;
    private String targetCount;
    private String level;
    private Bitmap targetBitmap;
    private Rect targetRect;
    private int coordX, coordY;
    private int textCoord;
    private int RECT_HEIGHT ;
    private int RECT_WIDTH;
    private int targetTextX, targetTextY;



    public ScoreDisplaySystem(StatsLevels stats, TreatFactory treatFactory){
        this.stats = stats;
        this.treatFactory = treatFactory;
        stats.subscribe(this);

        RECT_HEIGHT = (int)(Constants.SCREEN_HEIGHT * .119)/2;
        RECT_WIDTH = (int)(RECT_HEIGHT * .8);
        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        painttarget = new Paint();
        painttarget.setTextSize(60);
        painttarget.setColor(Color.MAGENTA);
        coordX = Constants.SCREEN_WIDTH /2;
        coordY = Constants.SCREEN_HEIGHT / 10;
        targetRect = new Rect();
        targetRect.set(coordX -RECT_WIDTH, coordY - RECT_HEIGHT, coordX + RECT_WIDTH, coordY+ RECT_HEIGHT);
        textCoord = 50;
        targetTextX = coordX - RECT_WIDTH;
        targetTextY = coordY +RECT_HEIGHT;
        targetCount = ""+stats.getTargetCount()+"/"+stats.getTargetGoal();
    }

    public void update(){
        score = "Score: " +stats.getScore();
        targetCount = ""+stats.getTargetCount()+"/"+stats.getTargetGoal();
    }

    public void draw(Canvas canvas){
        canvas.drawText(score + level, textCoord, textCoord + paint.descent() - paint.ascent(), paint);
        canvas.drawBitmap(targetBitmap, null, targetRect, null);
        canvas.drawText(targetCount, targetTextX, targetTextY + painttarget.descent() - painttarget.ascent(), painttarget);

    }

    @Override
    public void notifyLevels(LevelSystem level) {
        targetTreat = treatFactory.getTreatSmall(stats.getTargetTreat());
        targetBitmap = targetTreat.getBitmap();
        this.level = " Level: "+level.getLevel();

    }

    @Override
    public void notifyAreaChange() {

    }
}
