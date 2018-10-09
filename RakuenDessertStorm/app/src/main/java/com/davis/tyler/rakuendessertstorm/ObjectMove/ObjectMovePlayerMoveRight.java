package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMovePlayerMoveRight implements IObjectMove, Serializable{
    private final float START_SPEED = Constants.SCREEN_WIDTH / 1900.0f;
    private float speed;
    private final float ACCELERATION = START_SPEED /3;

    public ObjectMovePlayerMoveRight(){
        speed = START_SPEED;
    }
    @Override
    public void move(Coordinates coordinates, int elapsedTime) {
        if(coordinates.dx == speed){

        }
        else{
            if (coordinates.dx < 0)
                coordinates.dx = speed;
            coordinates.dx += ACCELERATION;
            if(coordinates.dx > speed)
                coordinates.dx = speed;
        }
        coordinates.x += (int)(coordinates.dx * elapsedTime);
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void resetSpeed() {
        speed = START_SPEED;
    }
}
