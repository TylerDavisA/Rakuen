package com.davis.tyler.rakuendessertstorm.ObjectMove;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.Scenes.SceneManager;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMoveTreatFall implements IObjectMove{
    private final float START_SPEED = Constants.SCREEN_HEIGHT/5000.0f;
    private float speed;
    public ObjectMoveTreatFall(float speed){
        this.speed = speed;
    }
    public ObjectMoveTreatFall(){

        speed = START_SPEED;
    }

    @Override
    public void move(Coordinates coordinates, int elapsedTime) {
        coordinates.dy = speed;
        coordinates.dx = 0;
        coordinates.y += (int)(elapsedTime * coordinates.dy);

    }

    public void setSpeed(float speed){this.speed = speed;}
    public void resetSpeed(){this.speed = START_SPEED;}
    public float getSpeed(){return speed;}


}
