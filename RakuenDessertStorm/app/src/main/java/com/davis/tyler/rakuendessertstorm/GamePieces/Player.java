package com.davis.tyler.rakuendessertstorm.GamePieces;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.Animation.IAnimation;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.ObjectMove.IObjectMove;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMovePlayerMoveLeft;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMovePlayerMoveRight;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMovePlayerStandStill;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Scenes.GameModeLevels;
import com.davis.tyler.rakuendessertstorm.Scenes.SceneGame;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class Player implements Parcelable {
    //STUFF TO REINIT: FACTORY
    public static final int PLAYER_HEIGHT = 13 * Constants.SCREEN_WIDTH / 100;
    public static final int PLAYER_WIDTH = PLAYER_HEIGHT;
    public final int PLAYER_MID_WIDTH = PLAYER_WIDTH / 2;
    public final int PLAYER_MID_HEIGHT = PLAYER_HEIGHT / 2;

    private Coordinates location;
    private IObjectMove move;
    private ObjectMoveFactory moveFactory;
    private Rect rect, collisionRect;
    private AnimationManager animManager;

    private Animation dance;
    private Bitmap[] danceAnimation;

    public Player(Context context, ObjectMoveFactory moveFactory, Coordinates location,
                  AnimationFactory animationFactory){
        this.moveFactory = moveFactory;
        this.location = location;
        rect = new Rect();
        move = moveFactory.getObjectMove(ObjectMoveFactory.PLAYER_STAND);
        initAnimation(context, animationFactory);

        collisionRect = new Rect();

    }
    public void parcelableInit(ObjectMoveFactory objectMoveFactory, AnimationFactory animationFactory,
                               Coordinates location){
        this.moveFactory = objectMoveFactory;
        animManager.setBitmaps(animationFactory);
        move = objectMoveFactory.getObjectMove(ObjectMoveFactory.PLAYER_STAND);
        this.location = location;
    }
    public Player(Parcel in){
        System.out.println("ENTERING WRITE TO PARCEL: PLAYER");
        rect = new Rect();
        collisionRect = new Rect();
        animManager = in.readParcelable(AnimationManager.class.getClassLoader());
    }

    public void setMove(IObjectMove objMove){
            move = objMove;
    }

    public void draw(Canvas canvas){
        animManager.draw(canvas, rect);
    }

    public void update(int elapsedTime){
        move.move(location, elapsedTime);
        rect.set(location.x - PLAYER_MID_WIDTH, location.y -PLAYER_MID_HEIGHT ,
                PLAYER_MID_WIDTH + location.x, PLAYER_MID_HEIGHT + location.y);
        if(rect.left < 0){
            rect.left = 0;
            rect.right = PLAYER_WIDTH;
            location.x = PLAYER_MID_WIDTH;
        }
        if(rect.right > Constants.SCREEN_WIDTH){
            rect.right = Constants.SCREEN_WIDTH;
            rect.left = Constants.SCREEN_WIDTH - PLAYER_WIDTH;
            location.x = Constants.SCREEN_WIDTH - PLAYER_MID_WIDTH;
        }

        collisionRect.set(location.x - PLAYER_WIDTH/200 * 100, location.y - PLAYER_WIDTH/200 * 75,
                location.x + PLAYER_WIDTH/200 * 100, location.y +PLAYER_MID_HEIGHT/2 );
        int state = 0;

        animManager.playAnim(state);
        animManager.update();

    }

    public void moveRight(){move = moveFactory.getObjectMove(ObjectMoveFactory.PLAYER_MOVE_RIGHT);}
    public void moveLeft(){move = moveFactory.getObjectMove(ObjectMoveFactory.PLAYER_MOVE_LEFT);}
    public void moveStop(){move = moveFactory.getObjectMove(ObjectMoveFactory.PLAYER_STAND);}
    public Coordinates getCoordinates(){return location;}
    public void setRect(Rect r){rect = r;}
    public void setCoordinates(Coordinates c){location = c;}
    public Rect getRect(){return rect;}
    public Rect getCollisionRect(){return collisionRect;}
    public int getRectTop(){return location.y - PLAYER_MID_HEIGHT;}

    private void initAnimation(Context context, AnimationFactory animationFactory){
        dance = animationFactory.getAnimation(IAnimation.LEEBLE_DANCE);
        animManager = new AnimationManager(new Animation[]{dance});
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeParcelable(animManager, i);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>(){
        public Player createFromParcel(Parcel in){
            return new Player(in);
        }

        public Player[] newArray(int size){
            return new Player[size];
        }

    };
}
