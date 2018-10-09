package com.davis.tyler.rakuendessertstorm.ObjectMove;

import android.graphics.Point;

import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tadto on 3/9/2018.
 */
public class ObjectMovePlayerMoveLeftTest {
    @Test
    public void move() throws Exception {
        int elapsedTime = 10;
        double delta = .1;
        Coordinates point= new Coordinates(1000, 10);
        ObjectMovePlayerMoveLeft objectMovePlayerMoveLeft = new ObjectMovePlayerMoveLeft();
        objectMovePlayerMoveLeft.setSpeed(10);
        objectMovePlayerMoveLeft.move(point, elapsedTime);

        float expectedX = 900;
        float expectedY = 10;

        /*assertEquals(expectedX, point.x, delta);
        assertEquals(expectedY, point.y, delta);*/
    }

}