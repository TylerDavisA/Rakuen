package com.davis.tyler.rakuendessertstorm.GamePieces.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.Animation.IAnimation;
import com.davis.tyler.rakuendessertstorm.CollisionHandler.CollisionHandlerShigiHelper;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.CoreGameObjectsLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.ICoreGameObjects;
import com.davis.tyler.rakuendessertstorm.GamePieces.IObjectManager;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.ObjectManagerLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodReadBeanHeart;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodRedBeanCake;

import java.util.Random;

/**
 * Created by Tadto on 3/22/2018.
 */

public class HelperShigiLeeble implements IHelper, Parcelable{
    public int HELPERX ;
    public int HELPERY ;
    public int HELPER_WIDTH ;
    public  int HELPER_HEIGHT ;
    private final int SPAWN_RATE = 400;
    private final int DURATION = 15000;

    private boolean isActive;
    private boolean isActivatable;
    private IObjectManager objectManager;
    private Rect rect;
    private Random rand;
    private TreatSmall[] listTreats;
    private TreatFactory treatFactory;
    private Bitmap icon;
    private AnimationManager animationManager;
    private int timer;
    private ICollisionHandler collisionHandler;
    private int roll;


    //TODO: IN GAMESCENE, IF A TOUCH HAPPENS IN TOP-RIGHT, IF ACTIVATABLE, THEN ACTIVATE
    //MAKE A HELPER FACTORY
    //INSTEAD OF MAGNET, MAKE A FOOD SPECIAL TO SHIGI THAT EXPLODES INT 3 MORE

    public HelperShigiLeeble(Context context, ICoreGameObjects coreGameObjects, IObjectManager objectManager,
                             IStats stats, AnimationFactory animationFactory, SoundManager soundManager){
        HELPERX =5 * Constants.SCREEN_WIDTH/6;
        HELPERY = Constants.SCREEN_HEIGHT /10;

        HELPER_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;
        HELPER_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.present);
        icon = Bitmap.createScaledBitmap(icon,HELPER_WIDTH, HELPER_HEIGHT,  true);
        this.objectManager = objectManager;
        this.listTreats = coreGameObjects.getSmallTreats();
        this.treatFactory = coreGameObjects.getTreatFactory();
        this.rand = coreGameObjects.getRandom();
        isActive = false;
        isActivatable = true;
        animationManager = new AnimationManager(new Animation[]{animationFactory.getAnimation(IAnimation.SHIGI_BOUNCE)
                ,animationFactory.getAnimation(IAnimation.SHIGI_WAVE)});
        rect = new Rect();
        collisionHandler = new CollisionHandlerShigiHelper(treatFactory, listTreats, soundManager);


    }
    @Override
    public void parcelableInit(Context context, CoreGameObjectsLevels coreGameObjectsLevels,
                               StatsLevels stats, ObjectManagerLevels objectManagerLevels
                                ,AnimationFactory animationFactory ) {
        //objectmanager, stats,  treatfactory, player, rand,  coregame
        listTreats = coreGameObjectsLevels.getSmallTreats();
        treatFactory = coreGameObjectsLevels.getTreatFactory();
        rand = coreGameObjectsLevels.getRandom();
        this.objectManager = objectManagerLevels;

        animationManager.setBitmaps(animationFactory);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.present);
        icon = Bitmap.createScaledBitmap(icon,HELPER_WIDTH, HELPER_HEIGHT,  true);

    }
    public HelperShigiLeeble(Parcel in){


        isActive = false;
        if(in.readInt() == 1)
            isActive = true;
        isActivatable = false;
        if(in.readInt() == 1)
            isActivatable = true;

        rect = in.readParcelable(Rect.class.getClassLoader());
        icon = in.readParcelable(Bitmap.class.getClassLoader());
        animationManager = in.readParcelable(AnimationManager.class.getClassLoader());
        timer = in.readInt();
    }
    @Override
    public void update(int elapsedTime) {
        timer -= elapsedTime;
        objectManager.updateDoublePoints(elapsedTime);
        rect.set(HELPERX,HELPERY, HELPERX+HELPER_WIDTH
                , HELPERY+HELPER_HEIGHT);
        if(!isActive){
            objectManager.update(elapsedTime);
        }
        else{
            moveFood(elapsedTime);
            spawnFood();

            objectManager.updatePlayer(elapsedTime);
            objectManager.updateEnemyAnimation(elapsedTime);

            if(timer <= 1500)
                animationManager.playAnim(1);
            else
                animationManager.playAnim(0);
            animationManager.update();
            objectManager.updateTreatPattern();

            if(timer <= 0)
                deactivate();
        }
    }

    public void moveFood(int elapsedTime){
        objectManager.moveSmallTreats(elapsedTime, collisionHandler);
        objectManager.moveGiantTreats(elapsedTime);
        objectManager.moveBadTreats(elapsedTime);
        objectManager.clear();
    }

    public void spawnFood(){
        objectManager.spawnTreats();


        roll = rand.nextInt(10000);
        if(roll < SPAWN_RATE) {
            treatFactory.spawnSmallTreatRandom(Treat.RED_BEAN_CAKE, listTreats);
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
        isActivatable = false;
        isActive = true;
        timer = DURATION;
    }

    @Override
    public void deactivate() {
        isActive = false;
        isActivatable = true;
        for(TreatSmall t:listTreats) {
            if(!t.isDeleted()) {
                if (t.getType() instanceof TreatTypeGoodRedBeanCake ||
                        t.getType() instanceof TreatTypeGoodReadBeanHeart) {
                    t.delete();
                }
            }
        }
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if(isActive)
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
        if(isActivatable){
            parcel.writeInt(1);
        }
        else
            parcel.writeInt(0);

        parcel.writeParcelable(rect, i);
        parcel.writeParcelable(animationManager, i);
        parcel.writeInt(timer);


    }

    public static final Parcelable.Creator<HelperShigiLeeble> CREATOR = new Parcelable.Creator<HelperShigiLeeble>(){
        public HelperShigiLeeble createFromParcel(Parcel in){
            return new HelperShigiLeeble(in);
        }

        public HelperShigiLeeble[] newArray(int size){
            return new HelperShigiLeeble[size];
        }

    };

}
