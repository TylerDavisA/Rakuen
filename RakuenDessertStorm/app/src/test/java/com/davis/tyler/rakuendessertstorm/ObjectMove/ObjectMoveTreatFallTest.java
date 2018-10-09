package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tadto on 3/9/2018.
 */
public class ObjectMoveTreatFallTest {
    @Test
    public void move() throws Exception {
        int elapsedTime = 10;
        double delta = .1;
        Coordinates point= new Coordinates(10, 10);
        ObjectMoveTreatFall objectMoveTreatFall = new ObjectMoveTreatFall();

        objectMoveTreatFall.move(point, elapsedTime);

        float expectedX = 10;
        float expectedY = 110;

        assertEquals(expectedX, point.x, delta);
        assertEquals(expectedY, point.y, delta);
    }

    @Test
    public void setSpeed() throws Exception {
        float input = 10;
        float output;
        float expected = 10;
        double delta = .1;

        ObjectMoveTreatFall objectMoveTreatFall = new ObjectMoveTreatFall();
        objectMoveTreatFall.setSpeed(input);
        output = objectMoveTreatFall.getSpeed();
        assertEquals(expected, output, delta);
    }

    @Test
    public void resetSpeed() throws Exception {
        float input = 10;
        float output;
        Constants.SCREEN_HEIGHT = 12;
        float expected = Constants.SCREEN_HEIGHT /5000f;
        double delta = .1;

        ObjectMoveTreatFall objectMoveTreatFall = new ObjectMoveTreatFall(input);
        objectMoveTreatFall.resetSpeed();
        output = objectMoveTreatFall.getSpeed();
        assertEquals(expected, output, delta);

    }


}