package com.davis.tyler.rakuendessertstorm.GamePieces;

import android.graphics.Canvas;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;

/**
 * Created by Tadto on 3/17/2018.
 */

public interface IObjectManager {

    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public void moveTreats(int elapsedTime);
    public void spawnTreats();
    public void clear();
    public void updateTreatPattern();
    public void updatePlayer(int elapsedTime);
    public void moveGiantTreats(int elapsedTime);
    public void moveSmallTreats(int elapsedTime);
    public void moveSmallTreats(int elapsedTime, ICollisionHandler collisionHandler);
    public void updateEnemyAnimation(int elapsedTime);
    public void drawEnemyAnimation(Canvas canvas);
    public void moveBadTreats(int elapsedTime);
    public boolean isGameDone();
    public void updateDoublePoints(int elapsedTime);

}
