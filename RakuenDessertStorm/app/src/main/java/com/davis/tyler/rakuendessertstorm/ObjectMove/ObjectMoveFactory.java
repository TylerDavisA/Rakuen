package com.davis.tyler.rakuendessertstorm.ObjectMove;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Tadto on 3/9/2018.
 */

public class ObjectMoveFactory implements ISubscriber{
    public static final int PLAYER_MOVE_LEFT = 0;
    public static final int PLAYER_MOVE_RIGHT = 1;
    public static final int PLAYER_STAND = 2;
    public static final int TREAT_FALL = 3;
    public static final int TREAT_MAGNET = 4;
    public static final int TREAT_EXPLODE = 5;
    public static final int TREAT_GIANT_FALL = 6;
    public static final int STOP = 7;

    private HashMap<Integer,IObjectMove> moves;
    private Random rand;
    private Coordinates player;

    public ObjectMoveFactory(Random rand, Coordinates player){
        this.rand = rand;
        moves = new HashMap<>();
        this.player = player;
    }
    public IObjectMove getObjectMove(int type){
        IObjectMove move = moves.get(type);
        if(move == null){
            if(type == PLAYER_MOVE_LEFT)
                move = new ObjectMovePlayerMoveLeft();
            else if(type == PLAYER_MOVE_RIGHT)
                move = new ObjectMovePlayerMoveRight();
            else if(type == PLAYER_STAND)
                move = new ObjectMovePlayerStandStill();
            else if(type == TREAT_FALL)
                move = new ObjectMoveTreatFall();
            else if(type == TREAT_GIANT_FALL) {
                move = new ObjectMoveTreatGiantFall();
            }
            else if(type == STOP) {
                move = new ObjectMoveStop();
            }

            moves.put(type, move);
        }

        return move;
    }

    public IObjectMove getObjectMoveMagnet(){
        IObjectMove move = moves.get(TREAT_MAGNET);
        if(move == null){
            move = new ObjectMoveTreatMagnet(player);
            moves.put(TREAT_MAGNET, move);
        }
        return move;
    }
    public IObjectMove objectMoveTreatExplode(TreatSmall treat){return new ObjectMoveTreatExplode(treat, rand, Constants.SCREEN_WIDTH/3);}

    public IObjectMove objectMoveTreatExplodeMini(TreatSmall treat){return new ObjectMoveTreatExplode(treat, rand, Constants.SCREEN_WIDTH/6);}
    public IObjectMove objectMoveTreatExplodeLarge(TreatSmall treat){return new ObjectMoveTreatExplode(treat, rand, (int)(Constants.SCREEN_WIDTH * .56));}

    @Override
    public void notifyLevels(LevelSystem level) {
        if(moves.get(TREAT_FALL) != null)
            moves.get(TREAT_FALL).setSpeed(level.getTreatDropSpeed());
        if(moves.get(TREAT_GIANT_FALL) != null)
            moves.get(TREAT_GIANT_FALL).setSpeed(level.getTreatDropSpeed());
    }

    @Override
    public void notifyAreaChange() {

    }
}
