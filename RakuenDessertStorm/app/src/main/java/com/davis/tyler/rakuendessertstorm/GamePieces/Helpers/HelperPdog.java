package com.davis.tyler.rakuendessertstorm.GamePieces.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.Animation.IAnimation;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.CoreGameObjectsLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.ICoreGameObjects;
import com.davis.tyler.rakuendessertstorm.GamePieces.IObjectManager;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.ObjectManagerLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 4/23/2018.
 */

public class HelperPdog implements IHelper{
    //TODO ON DEACTIVATION RESET FOOD PATTERN, ON ACTIVATION CHANGE EVERY TYPE TO CABBAGE AND ERASE LARGE FOOD
    //CHANGE FOOD TO SPAWN TO ONLY CABBAGES AND THEYLL GIVE LOTS OF POINTS
    //TEMPORARY BUFF, JUST SPAWN CABBAGES RANDOMLY TO SEE IF IT WORKS.
    // MAKE SHINY CABBAGE
    public int HELPERX ;
    public int HELPERY ;
    public int HELPER_WIDTH ;
    public  int HELPER_HEIGHT ;
    public int PRESENT_WIDTH ;
    public  int PRESENT_HEIGHT ;
    private int SPAWN_RATE = 300;
    private final int DURATION = 15000;
    private Context context;
    private ICoreGameObjects coreGameObjects;
    private IObjectManager objectManager;
    private IStats stats;
    private AnimationFactory animationFactory;
    private SoundManager soundManager;
    private Bitmap icon;

    private Player player;
    private Random rand;
    private TreatSmall[] listTreats;
    private TreatGiant[] listTreatsGiant;
    private TreatFactory treatFactory;
    private AnimationManager animationManager;
    private Rect rect;

    private int timer;
    private boolean isActive;
    private boolean isActivatable;
    private MessageBoardLeebles messageBoardLeebles;

    public HelperPdog(Context context, ICoreGameObjects coreGameObjects, IObjectManager objectManager,
                      IStats stats, AnimationFactory animationFactory, SoundManager soundManager){
        messageBoardLeebles = coreGameObjects.getMessageBoard();
        HELPERX =5 * Constants.SCREEN_WIDTH/6;
        HELPERY = Constants.SCREEN_HEIGHT /10;
        PRESENT_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;
        PRESENT_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;
        HELPER_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;
        HELPER_WIDTH = (int)(HELPER_HEIGHT * .6);
        this.context = context;
        this.coreGameObjects = coreGameObjects;
        this.objectManager = objectManager;
        this.stats = stats;
        this.animationFactory = animationFactory;
        this.soundManager = soundManager;
        this.timer = 0;

        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.present);
        icon = Bitmap.createScaledBitmap(icon,PRESENT_WIDTH, PRESENT_HEIGHT,  true);
        isActive = false;
        isActivatable = true;

        this.listTreats = coreGameObjects.getSmallTreats();
        this.treatFactory = coreGameObjects.getTreatFactory();
        this.player = coreGameObjects.getPlayer();
        this.rand = coreGameObjects.getRandom();
        this.listTreatsGiant = coreGameObjects.getGiantTreats();

        animationManager = new AnimationManager(new Animation[]{animationFactory.getAnimation(IAnimation.PDOG_CHEW)});
        this.rect = new Rect();
    }

    private void moveFood(int elapsedTime){
        objectManager.moveSmallTreats(elapsedTime);
        objectManager.moveGiantTreats(elapsedTime);
        objectManager.moveBadTreats(elapsedTime);
        objectManager.clear();
    }

    private void spawnFood(){
        int roll;
        objectManager.spawnTreats();


        roll = rand.nextInt(10000);
        if(roll < SPAWN_RATE){
            if(roll < SPAWN_RATE);
                treatFactory.spawnSmallTreatRandom(Treat.CABBAGE, listTreats);
        }
            //treatFactory.spawnSmallTreat(Treat.RED_BEAN_CAKE, listTreats);
    }

    @Override
    public void update(int elapsedTime) {
        timer -= elapsedTime;
        objectManager.updateDoublePoints(elapsedTime);


        if(!isActive){
            objectManager.update(elapsedTime);
            rect.set(HELPERX,HELPERY, HELPERX+PRESENT_WIDTH
                    , HELPERY+PRESENT_HEIGHT);
        }
        else{
            rect.set(HELPERX,HELPERY, HELPERX+HELPER_WIDTH
                    , HELPERY+HELPER_HEIGHT);
            moveFood(elapsedTime);// make own move method that moves all the cabbages
            spawnFood();// spawns all the cabbages. Dont touch the food patterns.

            objectManager.updatePlayer(elapsedTime);
            objectManager.updateEnemyAnimation(elapsedTime); //DONT WORRY ABOUT ENEMIES

            /*if(timer <= 1500) THIS IS WHEN YOU ADD TALKING
                animationManager.playAnim(1);
            else
                animationManager.playAnim(0);*/

            animationManager.playAnim(0);
            animationManager.update();
            objectManager.updateTreatPattern(); //by doing this, new patterns aren't spawned

            if(timer <= 0)
                deactivate();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(isActivatable){
            canvas.drawBitmap(icon, null, rect, null);
        }
        if(isActive){
            animationManager.draw(canvas, rect);
        }

        objectManager.draw(canvas);
    }

    @Override
    public void activate() {
        for(TreatGiant t:listTreatsGiant) {
            if(!t.isDeleted()) {
                t.delete();
            }
        }
        isActivatable = false;
        isActive = true;
        timer = DURATION;
        rect.set(HELPERX,HELPERY, HELPERX+HELPER_WIDTH
                , HELPERY+HELPER_HEIGHT);
        messageBoardLeebles.forceMessage("Hey. Bud... You got any cabbages for me?", MessageBoardLeebles.PDOG_TALK,
                HELPER_WIDTH, HELPER_HEIGHT);
        for(TreatSmall t:listTreats){
            if(!t.isDeleted() && t.getRect().bottom > 0){
                t.setType(treatFactory.getTreatSmall(Treat.CABBAGE));
            }
        }
        //MAKE METHOD IN FACTORY SETTYPE(int type);
        //SETS ALL TYPES TO THAT TYPE IN THE FOOD LIST
    }

    @Override
    public void deactivate() {
        isActive = false;
        isActivatable = true;
        rect.set(HELPERX,HELPERY, HELPERX+PRESENT_WIDTH
                , HELPERY+PRESENT_HEIGHT);
    }

    @Override
    public boolean isActivatable() {
        return isActivatable;
    }

    @Override
    public boolean isGameDone() {
        return objectManager.isGameDone();
    }

    @Override
    public void parcelableInit(Context context, CoreGameObjectsLevels coreGameObjectsLevels, StatsLevels stats, ObjectManagerLevels objectManagerLevels, AnimationFactory animationFactory) {

    }
}
