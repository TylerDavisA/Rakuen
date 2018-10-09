package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

/**
 * Created by Tadto on 3/9/2018.
 */

public interface IObjectMove {

    public void move(Coordinates coordinates, int elapsedTime);
    public void setSpeed(float speed);
    public void resetSpeed();
}
