package com.davis.tyler.rakuendessertstorm;

import com.davis.tyler.rakuendessertstorm.MoveSystem.MoveSystemBasic;
import com.davis.tyler.rakuendessertstorm.Scenes.SceneGame;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 6/15/2018.
 */

public class Mediator {
    public static final int EVENT_TAP_TIME_START = 0;
    public static final int EVENT_TAP_TIME_END = 1;
    public static final int EVENT_TAP_TIME_TAP = 2;
    public static final int EVENT_TAP_TIME_UNTAP = 3;

    public TreatSmall partyBall;
    public MoveSystemBasic moveSystemBasic;
    public SceneGame sceneGame;

    public Mediator(){}

    public void eventHandler(int event){
        if(event == EVENT_TAP_TIME_START){
            sceneGame.setTapTime(true);
            partyBall = moveSystemBasic.getPartyBall();
        }
        else if(event == EVENT_TAP_TIME_END){
            sceneGame.setTapTime(false);
        }
        else if(event == EVENT_TAP_TIME_TAP){
            partyBall.setSize(1.1f);
            moveSystemBasic.incrementTapCounter();
        }
        else if(event == EVENT_TAP_TIME_UNTAP){
            partyBall.resetSize();
        }
    }

    public void setMoveSystemBasic(MoveSystemBasic m){moveSystemBasic = m;}
    public void setSceneGame(SceneGame s){sceneGame = s;}
}
