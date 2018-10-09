package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.GamePieces.CoreGameObjectsLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.Helpers.HelperPdog;
import com.davis.tyler.rakuendessertstorm.GamePieces.Helpers.HelperShigiLeeble;
import com.davis.tyler.rakuendessertstorm.GamePieces.Helpers.IHelper;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.ObjectManagerLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.GameSummary.EndGameScreenLevels;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.Mediator;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.ScoreDisplaySystem;
import com.davis.tyler.rakuendessertstorm.Sound.SoundManager;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.TreatDropSystem;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 3/22/2018.
 */

public class GameModeLevels implements IGameMode, ISubscriber, Parcelable{
    //REINIT RELATIVELAYOUT
    private StatsLevels stats;
    private TreatSmall[] listSmall;
    private TreatGiant[] listGiant;
    private TreatBad[] listBad;
    private MessageBoardLeebles messageBoardLeebles;
    private Player player;
    private RelativeLayout relativeLayout;
    private Bitmap background;
    private Rect rect;
    private Random rand;
    private TreatFactory treatFactory;
    private Coordinates playerLocation;
    private ObjectManagerLevels objectManagerLevels;
    private AnimationFactory animationFactory;
    private IHelper helper;
    private CoreGameObjectsLevels coreGameObjectsLevels;
    //private TreatPatternManager treatPatternManager;
    private TreatDropSystem treatDropSystem;
    private ObjectMoveFactory objectMoveFactory;
    private int curLevelValue;
    private EndGameScreenLevels endGameScreenLevels;
    private SoundManager soundManager;
    private Bitmap bitmap;
    private ScoreDisplaySystem scoreDisplaySystem;
    private Mediator mediator;


    public GameModeLevels(Context context, RelativeLayout relativeLayout, Mediator mediator){

        this.mediator = mediator;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultbackground);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        background = Bitmap.createScaledBitmap(bitmap, width, height, true);
        //background = new BitmapDrawable(context.getResources(), bitmap);

        this.relativeLayout = relativeLayout;
        rect = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        rand = new Random();
        listSmall = new TreatSmall[120];
        listGiant = new TreatGiant[3];
        listBad = new TreatBad[30];
        animationFactory = new AnimationFactory(context);
        messageBoardLeebles= new MessageBoardLeebles(animationFactory);
        int i;

        soundManager = new SoundManager(context);
        playerLocation = new Coordinates(Constants.SCREEN_WIDTH/2, Constants.SCREEN_HEIGHT/100 * 88);
        objectMoveFactory = new ObjectMoveFactory(rand, playerLocation);
        for(i = 0; i < listSmall.length; i++)
            listSmall[i] = new TreatSmall(objectMoveFactory, new Coordinates(0,0));
        for(i = 0; i < listGiant.length; i++)
            listGiant[i] = new TreatGiant(objectMoveFactory, new Coordinates(0,0));
        for(i = 0; i < listBad.length; i++)
            listBad[i] = new TreatBad(objectMoveFactory, new Coordinates(0,0));
        player = new Player(context, objectMoveFactory, playerLocation, animationFactory);
        treatFactory = new TreatFactory(context, rand, objectMoveFactory);
        stats = new StatsLevels(messageBoardLeebles);
        stats.addToCombo(treatFactory.getTreatSmall(28));
        stats.resetCombo();
        endGameScreenLevels = new EndGameScreenLevels(stats);
        coreGameObjectsLevels = new CoreGameObjectsLevels(listSmall,listGiant, listBad, rand, player, treatFactory, messageBoardLeebles, objectMoveFactory);

        treatDropSystem = new TreatDropSystem(stats, coreGameObjectsLevels);

        objectManagerLevels = new ObjectManagerLevels(coreGameObjectsLevels, stats, treatDropSystem, animationFactory, soundManager, mediator);
        helper = new HelperPdog(context, coreGameObjectsLevels, objectManagerLevels, stats, animationFactory, soundManager );
        stats.subscribe(objectManagerLevels);
        stats.subscribe(treatFactory);
        stats.subscribe(objectMoveFactory);
        stats.subscribe(this);
        scoreDisplaySystem = new ScoreDisplaySystem(stats, treatFactory);
        //MAKE EVERYTHING
        //THEN CALL STATS TO NOTIFY LVL CHANGE
        stats.notifyLevelUp();

    }

    public GameModeLevels(Parcel in){
        //System.out.println("ENTERING PARCE CONSTRUCTOR: game mode levels");
        rect = in.readParcelable(Rect.class.getClassLoader());
        rand = new Random();
        in.readTypedArray(listSmall, TreatSmall.CREATOR);
        in.readTypedArray(listGiant, TreatGiant.CREATOR);
        messageBoardLeebles = in.readParcelable(MessageBoardLeebles.class.getClassLoader());
        playerLocation = in.readParcelable(Coordinates.class.getClassLoader());
        objectMoveFactory = new ObjectMoveFactory(rand, playerLocation);
        player = in.readParcelable(Player.class.getClassLoader());
        curLevelValue = in.readInt();
        stats = in.readParcelable(StatsLevels.class.getClassLoader());
        //treatPatternManager = in.readParcelable(TreatPatternManager.class.getClassLoader());
        String temp = in.readString();
        if(temp.equals("shigi")){
            helper = in.readParcelable(HelperShigiLeeble.class.getClassLoader());
        }



    }

    @Override
    public void parcelableInit(Context context, RelativeLayout relativeLayout) {

        animationFactory = new AnimationFactory(context);
        treatFactory = new TreatFactory(context, rand, objectMoveFactory);
        messageBoardLeebles.parcelableInit(animationFactory);
        coreGameObjectsLevels = new CoreGameObjectsLevels(listSmall, listGiant, listBad, rand, player, treatFactory, messageBoardLeebles, objectMoveFactory);
        for(TreatSmall t: listSmall)
            t.parcelableInit(treatFactory, objectMoveFactory);
        for(TreatGiant t: listGiant)
            t.parcelableInit(treatFactory, objectMoveFactory);

        player.parcelableInit(objectMoveFactory, animationFactory, playerLocation);
        helper.parcelableInit(context, coreGameObjectsLevels, stats, objectManagerLevels, animationFactory);
        objectManagerLevels = new ObjectManagerLevels(coreGameObjectsLevels, stats, treatDropSystem, animationFactory, soundManager, mediator);

        stats.subscribe(objectManagerLevels);
        stats.subscribe(treatFactory);
        stats.subscribe(objectMoveFactory);
        stats.subscribe(this);

        stats.notifyLevelUp();
    }

    @Override
    public boolean isGameDone() {
        return helper.isGameDone();
    }

    @Override
    public IStats getStats() {
        return stats;
    }

    public void update(int elapsedTime){
        if(!stats.isGameOver()) {
            helper.update(elapsedTime);
            messageBoardLeebles.update(elapsedTime);
            scoreDisplaySystem.update();
        }
        else{
            endGameScreenLevels.update(elapsedTime);
        }



    }

    public void draw(Canvas canvas){
        if(!stats.isGameOver()){
            //canvas.drawBitmap(background,null, rect, null);
            helper.draw(canvas);
            messageBoardLeebles.draw(canvas);
            scoreDisplaySystem.draw(canvas);
        }
        /*else{
            endGameScreenLevels.draw(canvas, paint);
        }*/

    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean helperActivatable() {
        return helper.isActivatable();
    }

    @Override
    public void activateHelper() {
        helper.activate();
    }



    @Override
    public void notifyLevels(LevelSystem level) {
        //background = level.getBackgroundImage();
        curLevelValue = level.getLevel();
        helper.deactivate();
    }

    @Override
    public void notifyAreaChange() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

       parcel.writeParcelable(rect, i);
       //make new random
        parcel.writeTypedArray(listSmall, i);
        parcel.writeTypedArray(listGiant, i);
        //make new animation factory
        parcel.writeParcelable(messageBoardLeebles,i);
        parcel.writeParcelable(playerLocation,i);
        //make new objectmovefactory
        parcel.writeParcelable(player, i);
        //make new treatfactory
        //make coregameobjectslevels
        parcel.writeInt(curLevelValue);
        //make new levelFactory
        parcel.writeParcelable(stats, i);
        //parcel.writeParcelable(treatPatternManager, i);
        //make new coregameobjectlevels
        //make new ObjectManagerLevels
        if(helper instanceof HelperShigiLeeble){
            parcel.writeString("shigi");
            parcel.writeParcelable((HelperShigiLeeble)helper, i);
        }
        //subscribe everything to stats




    }

    public static final Parcelable.Creator<GameModeLevels> CREATOR = new Parcelable.Creator<GameModeLevels>(){
        public GameModeLevels createFromParcel(Parcel in){
            return new GameModeLevels(in);
        }

        public GameModeLevels[] newArray(int size){
            return new GameModeLevels[size];
        }

    };
}
