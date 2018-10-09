package com.davis.tyler.rakuendessertstorm.CollisionHandler;

import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;

/**
 * Created by Tadto on 6/12/2018.
 */

public interface ICollisionHandler {

    public void executeCollision(Treat treat, IStats stats);
}
