package com.davis.tyler.rakuendessertstorm.CollisionHandler;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;

/**
 * Created by Tadto on 6/13/2018.
 */

public class CollisionHandlerBasic implements ICollisionHandler {
    private SoundManager soundManager;

    public CollisionHandlerBasic(SoundManager soundManager){
        this.soundManager = soundManager;
    }

    @Override
    public void executeCollision(Treat treat, IStats stats) {
        soundManager.smallTreatCollideSound();
        if(treat.getType().getType() == stats.getTargetTreat())
            stats.incrementTargetTreat();
        stats.incrementScore(treat.getPoints());
        stats.addToCombo(treat.getType());
        stats.registerTreatCollected(treat.getType());

        treat.delete();
    }
}
