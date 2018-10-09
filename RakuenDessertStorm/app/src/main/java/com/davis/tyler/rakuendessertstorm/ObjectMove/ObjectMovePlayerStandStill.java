package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMovePlayerStandStill implements IObjectMove, Serializable {

    private final float START_SPEED = Constants.SCREEN_WIDTH / 1700.0f;
    private float speed;
    private final float ACCELERATION = START_SPEED /8;

    public ObjectMovePlayerStandStill(){
        speed = START_SPEED;
    }
    @Override
    public void move(Coordinates coordinates, int elapsedTime) {
        if(coordinates.dx != 0){
            if(coordinates.dx > 0)
                slowDownRight(coordinates, elapsedTime);
            else
                slowDownLeft(coordinates, elapsedTime);
        }
    }
    private void slowDownRight(Coordinates coordinates, int elapsedTime){
        if(coordinates.dx < 0.05)
            coordinates.dx = 0;
        else
            coordinates.dx -= ACCELERATION;
    }
    private void slowDownLeft(Coordinates coordinates, int elapsedTime){
        if(coordinates.dx > -0.05)
            coordinates.dx = 0;
        else
            coordinates.dx += ACCELERATION;
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
