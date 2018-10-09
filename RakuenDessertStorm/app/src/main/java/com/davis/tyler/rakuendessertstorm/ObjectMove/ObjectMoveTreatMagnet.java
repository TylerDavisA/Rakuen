package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMoveTreatMagnet implements IObjectMove, Serializable {
    private Coordinates player;
    private final float START_SPEED = Constants.SCREEN_WIDTH / 2000.0f;
    private final float FALL_SPEED = Constants.SCREEN_HEIGHT/4000.0f;
    private final float MAX_ACCELERATION = 1.5f;
    private float speed, baseSpeed;
    private double theta;
    private double distX, distY, tempX, tempY, dist;


    public ObjectMoveTreatMagnet(Coordinates player){
        baseSpeed = START_SPEED;
        //speed =  START_SPEED / 4 ;
        speed =  START_SPEED;
        this.player = player;
    }

    @Override
    public void move(Coordinates coordinates, int elapsedTime){
        tempX = distX = coordinates.x - player.x;
        tempY = distY = coordinates.y - player.y;
        if(tempX < 0)
            tempX *= -1;
        if(tempY < 0)
            tempY *= -1;
        //double distY = coordinates.y - player.y;

        theta = Math.atan( (tempY )/ tempX );


        //System.out.println("Theta is: "+theta);

        /*if(distY < -100) {
            if(coordinates.dy < - MAX_ACCELERATION)
                coordinates.dy += (float) (Math.cos(theta) * speed * 2);
            else
                coordinates.dy += (float) (Math.cos(theta) * speed * tempY / Player.PLAYER_HEIGHT);

        }
        else if(distY > 100){
            if(coordinates.dy > MAX_ACCELERATION)
                coordinates.dy += -(float) (Math.cos(theta) * speed * 2);
            else
                coordinates.dy += -(float) (Math.cos(theta) * speed * tempY / Player.PLAYER_HEIGHT);

        }
        if(distX < -100) {
            if(coordinates.dx < -MAX_ACCELERATION){
                coordinates.dx += (float) (Math.sin(theta) * speed * 2);
            }
            else
                coordinates.dx += (float) (Math.sin(theta) * speed * tempX / Player.PLAYER_HEIGHT );


        }
        else if (distX > 100){
            if (coordinates.dx > MAX_ACCELERATION)
                coordinates.dx += -(float)(Math.sin(theta) * speed * 2 );
            else
                coordinates.dx += -(float) (Math.sin(theta) * speed * tempX / Player.PLAYER_HEIGHT );

        }*/
        if(distY < 0) {
                coordinates.dy = (float) (Math.sin(theta) * speed * tempY / Player.PLAYER_HEIGHT);

        }
        else if(distY > 0){
                coordinates.dy = -(float) (Math.sin(theta) * speed * tempY / Player.PLAYER_HEIGHT);

        }
        if(distX < 0) {
                coordinates.dx = (float) (Math.cos(theta) * speed * tempX / Player.PLAYER_HEIGHT);


        }
        else if (distX > 0){
                coordinates.dx = -(float) (Math.cos(theta) * speed* tempX / Player.PLAYER_HEIGHT);

        }
        //System.out.println("dx: "+coordinates.dx +" dy: "+coordinates.dy);
        /*if(coordinates.dx < 0)
            coordinates.x += coordinates.dx * elapsedTime -baseSpeed;
        else*/
        coordinates.x += coordinates.dx * elapsedTime;
        coordinates.y += coordinates.dy * elapsedTime ;
    }
    @Override
    public void setSpeed(float speed) {

    }
    public void resetDY(Coordinates coordinates){coordinates.dy = 0;}
    public void setTarget(Coordinates player){this.player = player;}
    @Override
    public void resetSpeed() {

    }
}
