package com.davis.tyler.rakuendessertstorm.MoveSystem;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;

/**
 * Created by Tadto on 6/9/2018.
 */

public interface IMoveSystem {

    public void setup();
    public void moveSmallTreats(int elapsedTime, int targetTreat, ICollisionHandler collisionHandler);
    public void moveBadTreats(int elapsedTime);
    public void moveGiantTreats(int elapsedTime);
    public void updatePlayer(int elapsedTime);

}
