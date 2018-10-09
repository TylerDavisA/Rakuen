package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.Mediator;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;

/**
 * Created by Tadto on 3/9/2018.
 */

public class SceneGame implements IScene, Parcelable {
    //REINIT: GAME CREATOR, PAINT
    private Player player;
    private Paint paint;
    private GameCreator gameCreator;
    private IGameMode gameMode;
    private int action, xPos, yPos, index;
    private boolean left;
    private SceneManager sceneManager;// need this for when game is over to goto menu
    private IStats stats;
    private boolean isTapTime;
    private Mediator mediator;


    public SceneGame(Context context, RelativeLayout relativeLayout, SceneManager sceneManager){
        mediator = new Mediator();
        mediator.setSceneGame(this);
        gameCreator = new GameCreator(context, relativeLayout, mediator);
        gameMode = gameCreator.makeGame(GameCreator.GAME_LEVELS);
        stats = gameMode.getStats();
        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        this.player = gameMode.getPlayer();
        this.sceneManager = sceneManager;
        isTapTime = false;

        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("helper", "shigi");
        //editor.commit();

        //result = sharedPreferences.getString("helper", "bad");
        //System.out.println(""+result);
    }
    public SceneGame(Parcel in){
        String type = in.readString();
        if(type.equals("levels")){
            gameMode = gameCreator.makeGame(GameCreator.GAME_LEVELS);
        }

    }

    @Override
    public void parcelableInit(Context context, RelativeLayout relativeLayout, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        gameMode.parcelableInit(context, relativeLayout);
        player = gameMode.getPlayer();

    }

    @Override
    public void update(int elapsedTime) {
        if(gameMode.isGameDone()){
            //gameMode = gameCreator.makeGameLevels();
            //player = gameMode.getPlayer();
            sceneManager.changeScene(SceneManager.SCENE_TITLE);
        }
            gameMode.update(elapsedTime);

    }

    @Override
    public void draw(Canvas canvas) {
        gameMode.draw(canvas);
    }

    @Override
    public void terminate() {
        //SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        action = event.getActionMasked();
        index = event.getActionIndex();
        xPos = (int) event.getX( index);
        if(gameMode.helperActivatable()){
            yPos =(int) event.getY( index);
            if(xPos > 2 *Constants.SCREEN_WIDTH/3 && yPos < Constants.SCREEN_HEIGHT/3){
                gameMode.activateHelper();
            }
        }


        if(xPos < Constants.SCREEN_WIDTH/2)
            left = true;
        else
            left = false;
        if(isTapTime) {
            switch (action) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    mediator.eventHandler(Mediator.EVENT_TAP_TIME_TAP);
                    break;
                case MotionEvent.ACTION_DOWN:
                    mediator.eventHandler(Mediator.EVENT_TAP_TIME_TAP);
                    break;

                case MotionEvent.ACTION_UP:
                    mediator.eventHandler(Mediator.EVENT_TAP_TIME_UNTAP);
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    mediator.eventHandler(Mediator.EVENT_TAP_TIME_UNTAP);

                    break;
            }

        }
        else {
            switch (action) {
                case MotionEvent.ACTION_POINTER_DOWN:

                    if (left)
                        player.moveLeft();
                    else
                        player.moveRight();
                    break;
                case MotionEvent.ACTION_DOWN:
                    if (left)
                        player.moveLeft();
                    else
                        player.moveRight();
                    break;

                case MotionEvent.ACTION_UP:
                    if (event.getPointerCount() > 1) {
                        if (left) {
                            player.moveRight();
                        } else
                            player.moveLeft();
                    } else
                        player.moveStop();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    if (event.getPointerCount() > 1) {
                        if (left) {
                            player.moveRight();
                        } else
                            player.moveLeft();
                    } else
                        player.moveStop();

                    break;
            }
        }
    }

    public void setTapTime(boolean b){
        isTapTime = b;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        if(gameMode instanceof GameModeLevels){
            parcel.writeString("levels");
            parcel.writeParcelable((GameModeLevels)gameMode, i);
            //MAKE A NEW GAME MODE OBJECT USING GAMECREATOR
        }


    }

    public static final Parcelable.Creator<SceneGame> CREATOR = new Parcelable.Creator<SceneGame>(){
        public SceneGame createFromParcel(Parcel in){
            return new SceneGame(in);
        }

        public SceneGame[] newArray(int size){
            return new SceneGame[size];
        }

    };

}
