package com.davis.tyler.rakuendessertstorm.GamePieces;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.CollisionHandler.CollisionHandlerBasic;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.Mediator;
import com.davis.tyler.rakuendessertstorm.MoveSystem.IMoveSystem;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.MoveSystem.MoveSystemBasic;
import com.davis.tyler.rakuendessertstorm.MoveSystem.MoveSystemMagnet;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.TreatDropSystem;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 3/17/2018.
 */

public class ObjectManagerLevels implements IObjectManager, ISubscriber{
    private int RECT_HEIGHT, RECT_WIDTH;
    private TreatSmall[] listSmallTreats;
    private TreatGiant[] listGiantTreats;
    private TreatBad[] listBadTreats;
    private Player player;
    private Random rand;
    private int level;
    private TreatFactory treatFactory;
    private TreatDropSystem treatDropSystem;
    private StatsLevels stats;
    private AnimationManager enemyAnimation;
    private AnimationFactory animationFactory;
    private boolean clear;
    private Rect rectEnemy;
    private boolean gameDone;
    private int doublePointsTimer;
    private MessageBoardLeebles messageBoardLeebles;
    private SoundManager soundManager;
    private IMoveSystem curMoveSystem;
    private IMoveSystem moveSystemBasic;
    private IMoveSystem moveSystemMagnet;
    private ICollisionHandler collisionHandlerBasic;
    //rename to buff later
    private TreatSmall curBuff;
    private int timer;
    private final int BUFF_TIMER_MAX = 7000;



    public ObjectManagerLevels(CoreGameObjectsLevels coreGameObjectsLevels, StatsLevels stats, TreatDropSystem treatDropSystem,
                               AnimationFactory animationFactory, SoundManager soundManager,
                               Mediator mediator){
        gameDone = false;
        RECT_HEIGHT = 8 * Constants.SCREEN_WIDTH / 100;
        RECT_WIDTH = 7 * Constants.SCREEN_WIDTH / 100;
        this.animationFactory = animationFactory;
        this.listSmallTreats = coreGameObjectsLevels.getSmallTreats();
        this.listGiantTreats = coreGameObjectsLevels.getGiantTreats();
        this.listBadTreats = coreGameObjectsLevels.getBadTreats();
        this.treatDropSystem = treatDropSystem;
        this.player = coreGameObjectsLevels.getPlayer();
        this.rand = coreGameObjectsLevels.getRandom();
        this.treatFactory = coreGameObjectsLevels.getTreatFactory();
        this.stats = stats;
        clear = false;
        enemyAnimation = null;
        rectEnemy = new Rect();
        this.messageBoardLeebles = coreGameObjectsLevels.getMessageBoard();
        this.soundManager = soundManager;
        moveSystemBasic = new MoveSystemBasic(coreGameObjectsLevels, stats, treatDropSystem, mediator);
        moveSystemMagnet = new MoveSystemMagnet(coreGameObjectsLevels, stats);
        curMoveSystem = moveSystemBasic;
        curBuff = new TreatSmall(coreGameObjectsLevels.getObjectMoveFactory(), new Coordinates(-100,-100));
        curBuff.setMove(coreGameObjectsLevels.getObjectMoveFactory().getObjectMove(ObjectMoveFactory.TREAT_FALL));
        loadNextBuff();
        collisionHandlerBasic = new CollisionHandlerBasic(soundManager);
        timer = 0;
        // you can set buff drop after lvl 2 later
        treatDropSystem.setBuffDrop(curBuff);

        level = 0;
    }
    @Override
    public void update(int elapsedTime) {
            moveTreats(elapsedTime);
            updatePlayer(elapsedTime);
            updateTreatPattern();
            updateEnemyAnimation(elapsedTime);
            updateDoublePoints(elapsedTime);



        // possibly make separate update methods for move treats and spawn treats;
    }



    @Override
    public void draw(Canvas canvas) {
        drawEnemyAnimation(canvas);
        if(!clear) {

            player.draw(canvas);
            for(Treat t:listSmallTreats)
                if(!t.isDeleted() && t.getRect().bottom > 0)
                    t.draw(canvas);
            for(Treat t:listGiantTreats)
                if(!t.isDeleted()&& t.getRect().bottom > 0)
                    t.draw(canvas);
            for(TreatBad t:listBadTreats)
                if(!t.isDeleted()&& t.getRect().bottom > 0)
                    t.draw(canvas);
            if(!curBuff.isDeleted())
                curBuff.draw(canvas);
        }
    }

    @Override
    public void moveTreats(int elapsedTime) {

        moveSmallTreats(elapsedTime);
        moveGiantTreats(elapsedTime);
        moveBadTreats(elapsedTime);
        clear();
    }


    @Override
    public void spawnTreats() {
    }

    @Override
    public void clear() {
        if(clear) {
            for(TreatSmall t:listSmallTreats) {
                t.delete();
            }
            for(TreatGiant t:listGiantTreats) {
                t.delete();
            }
            for(TreatBad t:listBadTreats) {
                t.delete();
            }
            clear = false;
        }
    }

    @Override
    public void updateTreatPattern() {
        treatDropSystem.update(listSmallTreats, listGiantTreats, listBadTreats);
    }

    @Override
    public void updatePlayer(int elapsedTime) {
        curMoveSystem.updatePlayer(elapsedTime);
    }

    @Override
    public void moveGiantTreats(int elapsedTime) {
        curMoveSystem.moveGiantTreats(elapsedTime);
    }

    @Override
    public void moveSmallTreats(int elapsedTime) {
        moveBuffTreat(elapsedTime);
        curMoveSystem.moveSmallTreats(elapsedTime, stats.getTargetTreat(), collisionHandlerBasic);

    }

    public void moveBuffTreat(int elapsedTime){
        timer -= elapsedTime;
        if(curMoveSystem instanceof MoveSystemMagnet && timer < 0){
            curMoveSystem = moveSystemBasic;
            loadNextBuff();
        }
        if(!curBuff.isDeleted()) {
            curBuff.update(elapsedTime);
            if(curBuff.getRect().intersect(player.getRect())){
                if(curBuff.getType().getType() == Treat.BUFF_MAGNET) {
                    curMoveSystem = moveSystemMagnet;
                    treatDropSystem.setBuffDropOff();
                }
                else{
                    //THIS IS WHERE YOU SET NEXT PATTERN TO BE PARTY BALL
                    treatDropSystem.setNextPatternParty();
                    loadNextBuff();
                    treatDropSystem.setBuffDrop(curBuff);
                }

                timer = BUFF_TIMER_MAX;
                curBuff.delete();
            }
            if(curBuff.getRect().top > Constants.SCREEN_HEIGHT ) {

                loadNextBuff();
            }
        }
    }
    @Override
    public void moveSmallTreats(int elapsedTime, ICollisionHandler collisionHandler) {
        moveBuffTreat(elapsedTime);
        curMoveSystem.moveSmallTreats(elapsedTime, stats.getTargetTreat(), collisionHandler);

    }

    @Override
    public void updateEnemyAnimation(int elapsedTime) {
        rectEnemy.set(50, 200, 50+ RECT_WIDTH, 200 + RECT_HEIGHT);
        enemyAnimation.playAnim(0);
        enemyAnimation.update();
    }

    private void loadNextBuff(){
        //POSSIBLY MAKE AN OBJECT TO DECIDE WHAT BUFF TO SPAWN AND WHEN.
        if(level > 1 && !treatDropSystem.getPartyBallSpawned()){
            if(rand.nextInt(10)<4) {
                curBuff.reinit(treatFactory.getTreatSmall(Treat.BUFF_PARTY_BALL), -100, -100);
            }
            else
                curBuff.reinit(treatFactory.getTreatSmall(Treat.BUFF_MAGNET), -100, -100);
        }
        else
            curBuff.reinit(treatFactory.getTreatSmall(Treat.BUFF_MAGNET), -100, -100);

        treatDropSystem.setBuffDrop(curBuff);
        curBuff.delete();
    }
    @Override
    public void drawEnemyAnimation(Canvas canvas) {
        enemyAnimation.draw(canvas, rectEnemy);
    }

    @Override
    public void moveBadTreats(int elapsedTime) {
        curMoveSystem.moveBadTreats(elapsedTime);
    }

    @Override
    public boolean isGameDone() {
        return stats.isGameOver();
    }

    @Override
    public void updateDoublePoints(int elapsedTime) {
        doublePointsTimer -= elapsedTime;
        if(stats.getCombo() >= 5){
            stats.resetCombo();
            doublePointsTimer = 10000;
            stats.setDoublePoints(true);
            if(!stats.isMultiplier())
                messageBoardLeebles.queueMessage("Multiplier activated. Woo!", MessageBoardLeebles.SHIGI_BOUNCE);

        }
        if(doublePointsTimer < 0) {
            stats.setDoublePoints(false);
        }
    }


    @Override
    public void notifyLevels(LevelSystem level) {
        this.level = level.getLevel();
        clear = true;
        enemyAnimation = new AnimationManager(new Animation[]{animationFactory.getAnimation(level.getEnemyType())});
        stats.resetCombo();
        stats.setDoublePoints(false);
        curMoveSystem = moveSystemBasic;
        treatDropSystem.setCanMakePatterns(true);
        treatDropSystem.setBuffDrop(curBuff);
        timer = -1;
        curBuff.delete();
    }

    @Override
    public void notifyAreaChange() {

    }
}
