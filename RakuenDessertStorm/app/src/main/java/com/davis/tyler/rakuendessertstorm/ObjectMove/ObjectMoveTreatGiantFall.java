package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

import java.io.Serializable;

/**
 * Created by Tadto on 3/10/2018.
 */

public class ObjectMoveTreatGiantFall implements IObjectMove, Serializable{
    private final float START_SPEED = Constants.SCREEN_HEIGHT/6000.0f;
    private float speed;
    public ObjectMoveTreatGiantFall(float speed){
        this.speed = speed;
    }
    public ObjectMoveTreatGiantFall(){

        speed = START_SPEED;
    }
    @Override
    public void move(Coordinates coordinates, int elapsedTime) {
        coordinates.dy = speed;
        coordinates.y += (int)(elapsedTime * speed);

    }

    public void setSpeed(float speed){this.speed = speed;}
    public void resetSpeed(){this.speed = START_SPEED;}
    public float getSpeed(){return speed;}
}
