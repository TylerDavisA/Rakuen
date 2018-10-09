package com.davis.tyler.rakuendessertstorm.GameSummary;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;

import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;

/**
 * Created by Tadto on 3/29/2018.
 */

public class EndGameScreenLevels implements EndGameScreen {
    private StatsLevels stats;
    private String mostFoodCollected;
    private String score;
    private String pointsFromHelper;


    public EndGameScreenLevels(StatsLevels stats){
        this.stats = stats;
        score = "";
        mostFoodCollected = "";
        pointsFromHelper = "";
    }
    @Override
    public void update(int elapsedTime) {
        score = "Score: "+stats.getScore();
        mostFoodCollected = "Most food collected: "+ stats.findNumberCollected(stats.findMostCollectedType());
        pointsFromHelper = "Points from Helper: "+ stats.getHelperScore();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

        canvas.drawText(score, 50, 50 + paint.descent() - paint.ascent(), paint);
        canvas.drawText(mostFoodCollected, 50, 250 + paint.descent() - paint.ascent(), paint);
        canvas.drawText(pointsFromHelper, 50, 550 + paint.descent() - paint.ascent(), paint);
    }
}
