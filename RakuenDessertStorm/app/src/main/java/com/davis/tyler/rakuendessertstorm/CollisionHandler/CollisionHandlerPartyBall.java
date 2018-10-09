package com.davis.tyler.rakuendessertstorm.CollisionHandler;

import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 6/13/2018.
 */

public class CollisionHandlerPartyBall implements ICollisionHandler {
    private TreatFactory treatFactory;
    private TreatSmall[] listTreats;
    private SoundManager soundManager;
    private boolean taptime;
    public CollisionHandlerPartyBall(TreatFactory treatFactory, TreatSmall[] list, SoundManager soundManager, boolean taptime){
        this.treatFactory = treatFactory;
        listTreats = list;
        this.soundManager = soundManager;
        this.taptime = taptime;
    }

    @Override
    public void executeCollision(Treat t, IStats stats ) {
        soundManager.smallTreatCollideSound();
        if(t.getType().getType() == stats.getTargetTreat())
            stats.incrementTargetTreat();
        if(t.getType().getType() == Treat.PARTY_BALL){
            //treatFactory.spawnExplodedTreatsPartyBall(t, listTreats);
            taptime = true;
        }
        else{
            stats.addToCombo(t.getType());
            stats.registerTreatCollected(t.getType());

        }
        stats.incrementScore(t.getPoints());

        t.delete();
    }
}
