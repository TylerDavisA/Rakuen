package com.davis.tyler.rakuendessertstorm.ObjectMove;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMoveTreatExplode implements IObjectMove {
    private final float START_SPEED = Constants.SCREEN_HEIGHT/1000.0f;
    private float speed;
    private int range;
    private TreatSmall treat;
    private Coordinates target;
    private double theta;
    private Coordinates focus;
    private double dist;

    public ObjectMoveTreatExplode(TreatSmall treat, Random rand, int range){
        this.range = range;

        this.treat = treat;
        speed = START_SPEED * 2;
        focus = new Coordinates(treat.getCoordinates().x, treat.getCoordinates().y);
        target = new Coordinates(rand.nextInt(range) - range/2 +focus.x,
                                                rand.nextInt(range) - range/2 + focus.y);
        if(target.x < 0)
            target.x = 0;
        if(target.x > Constants.SCREEN_WIDTH)
            target.x = Constants.SCREEN_WIDTH;
        if(target.y < 0)
            target.y = 0;
        if(target.y > Constants.SCREEN_HEIGHT)
            target.y = Constants.SCREEN_HEIGHT;
        double distX = target.x - focus.x;
        double distY = target.y - focus.y;
        dist = Math.abs(distX);
        theta = Math.atan( (distY )/ distX );


        if(distY < 0)
            theta += Math.PI;
        treat.getCoordinates().dx = (float)(Math.cos(theta) * speed);
        treat.getCoordinates().dy = (float)(Math.sin(theta) * speed);

    }
    public double getDist(){return dist;}
    public ObjectMoveTreatExplode(TreatSmall treat, double dist){
        this.treat = treat;
        this.dist = dist;
        speed = START_SPEED;
    }
    @Override
    public void move(Coordinates coordinates, int elapsedTime) {

        coordinates.y += (int)(elapsedTime * coordinates.dy);
        coordinates.x += (int)(elapsedTime * coordinates.dx);
        dist -= Math.abs(coordinates.dx * elapsedTime);
        if(dist <=0)
            treat.doneExploding();
    }

    public void setSpeed(float speed){this.speed = speed;}
    public void resetSpeed(){this.speed = START_SPEED;}
    public float getSpeed(){return speed;}


}
