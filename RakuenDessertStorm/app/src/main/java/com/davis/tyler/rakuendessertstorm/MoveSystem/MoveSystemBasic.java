package com.davis.tyler.rakuendessertstorm.MoveSystem;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.ICoreGameObjects;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;
import com.davis.tyler.rakuendessertstorm.Mediator;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveTreatExplode;
import com.davis.tyler.rakuendessertstorm.TreatDropSystem;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 6/9/2018.
 */

public class MoveSystemBasic implements IMoveSystem {
    private Player player;
    private TreatSmall[] treatSmalls;
    private TreatBad[] treatBads;
    private TreatGiant[] treatGiants;
    private IStats stats;
    private TreatFactory treatFactory;
    private ObjectMoveFactory objectMoveFactory;
    private TreatDropSystem treatDropSystem;
    private boolean isTapTime;
    private int taptimer;
    private TreatSmall partyball;
    private final int TIMER_MAX = 3000;
    private MessageBoardLeebles messageBoardLeebles;
    private Mediator mediator;
    public int tapCounter;
    public MoveSystemBasic(ICoreGameObjects coreGameObjects, IStats stats, TreatDropSystem treatDropSystem, Mediator mediator){
        this.player = coreGameObjects.getPlayer();
        this.treatSmalls = coreGameObjects.getSmallTreats();
        this.treatBads = coreGameObjects.getBadTreats();
        this.treatGiants = coreGameObjects.getGiantTreats();
        this.stats = stats;
        this.treatFactory = coreGameObjects.getTreatFactory();
        this.objectMoveFactory = coreGameObjects.getObjectMoveFactory();
        this.treatDropSystem = treatDropSystem;
        this.messageBoardLeebles = coreGameObjects.getMessageBoard();
        isTapTime = false;
        taptimer = -1;
        this.mediator = mediator;
        this.mediator.setMoveSystemBasic(this);
    }
    @Override
    public void setup() {
        for(TreatSmall t: treatSmalls){
            if(!(t.getMove() instanceof ObjectMoveTreatExplode) )
                t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL));
        }
        for(TreatBad t: treatBads){
            if(!(t.getMove() instanceof ObjectMoveTreatExplode))
                t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL));
        }
        for(TreatGiant t: treatGiants){
            t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_GIANT_FALL));
        }
    }


    @Override
    public void moveSmallTreats(int elapsedTime, int targetTreat, ICollisionHandler collisionHandler) {
        if(!isTapTime) {
            for (TreatSmall t : treatSmalls) {
                if (!t.isDeleted()) {
                    t.update(elapsedTime);
                    if (t.getRect().intersect(player.getCollisionRect())) {
                        if(t.getType().getType() == Treat.PARTY_BALL){
                            isTapTime = true;
                            taptimer = TIMER_MAX;
                            //stats.setTapTime(true);
                            System.out.println("SETTAPTIME TRUE");
                            messageBoardLeebles.forceMessage("TAP!", MessageBoardLeebles.SHIGI_BOUNCE);
                            partyball = t;
                            mediator.eventHandler(Mediator.EVENT_TAP_TIME_START);
                            partyball.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.STOP));
                            //stats.setPartyBall(t);
                        }
                        else
                            collisionHandler.executeCollision(t, stats);
                    }
                    if (t.getRectTop() > Constants.SCREEN_HEIGHT * 1.2) {
                        if(t.getType().getType() == Treat.PARTY_BALL)
                            treatDropSystem.setCanMakePatterns(true);
                        t.delete();
                    }
                }
            }
        }
        else{
            taptimer -= elapsedTime;
            partyball.update(elapsedTime);
            if(taptimer < 0) {
                isTapTime = false;
                treatFactory.spawnExplodedTreatsPartyBall(treatSmalls, stats.getTreatsToDrop(),tapCounter);
                tapCounter = 0;
                System.out.println("SETTAPTIME FALSE");
                //stats.setTapTime(false);
                mediator.eventHandler(Mediator.EVENT_TAP_TIME_END);
                partyball.setMove(objectMoveFactory.objectMoveTreatExplodeLarge(partyball));
                partyball.delete();
                treatDropSystem.setCanMakePatterns(true);


            }
            //set spawn patterns enabled

        }
    }

    @Override
    public void moveBadTreats(int elapsedTime) {
        if(!isTapTime) {
            for (TreatBad t : treatBads) {
                if (!t.isDeleted()) {
                    t.update(elapsedTime);
                    if (t.getRect().intersect(player.getCollisionRect())) {
                        stats.setGameOver();
                        t.delete();
                    }
                    if (t.getRectTop() > Constants.SCREEN_HEIGHT)
                        t.delete();

                }
            }
        }
    }

    @Override
    public void moveGiantTreats(int elapsedTime) {
        if(!isTapTime) {
            for (TreatGiant t : treatGiants) {
                if (!t.isDeleted()) {
                    t.update(elapsedTime);
                    if (t.getRect().intersect(player.getCollisionRect())) {
                        stats.incrementScore(t.getPoints());
                        treatFactory.spawnExplodedTreats(t, treatSmalls);
                        t.delete();
                    }
                    if (t.getRectTop() > Constants.SCREEN_HEIGHT)
                        t.delete();
                }
            }
        }
    }

    @Override
    public void updatePlayer(int elapsedTime) {
        if(!isTapTime)
            player.update(elapsedTime);
    }

    public TreatSmall getPartyBall(){return partyball;}
    public int getTapCounter(){return tapCounter;}
    public void incrementTapCounter(){tapCounter++;}
}
