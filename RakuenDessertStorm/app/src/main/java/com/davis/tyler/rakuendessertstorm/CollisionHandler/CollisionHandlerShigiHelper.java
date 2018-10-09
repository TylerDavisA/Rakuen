package com.davis.tyler.rakuendessertstorm.CollisionHandler;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 6/12/2018.
 */

public class CollisionHandlerShigiHelper implements ICollisionHandler {
    private TreatFactory treatFactory;
    private TreatSmall[] listTreats;
    private SoundManager soundManager;
    public CollisionHandlerShigiHelper(TreatFactory treatFactory, TreatSmall[] list, SoundManager soundManager){
        this.treatFactory = treatFactory;
        listTreats = list;
        this.soundManager = soundManager;
    }

    @Override
    public void executeCollision(Treat t, IStats stats ) {
        soundManager.smallTreatCollideSound();
        if(t.getType().getType() == stats.getTargetTreat())
            stats.incrementTargetTreat();
        if(t.getType().getType() == Treat.RED_BEAN_CAKE){
            treatFactory.spawnExplodedTreatsMini(t, listTreats);
            stats.incrementHelperScore(t.getPoints());
        }
        else if(t.getType().getType() == Treat.RED_BEAN_HEART){
            stats.incrementHelperScore(t.getPoints());
        }
        else{
            stats.addToCombo(t.getType());
            stats.registerTreatCollected(t.getType());

        }
        stats.incrementScore(t.getPoints());

        t.delete();
    }
}
