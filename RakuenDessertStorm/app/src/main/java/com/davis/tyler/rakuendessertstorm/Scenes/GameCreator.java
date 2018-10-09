package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Mediator;

/**
 * Created by Tadto on 3/22/2018.
 */

public class GameCreator {
    public static final int GAME_LEVELS = 0;
    private RelativeLayout relativeLayout;
    private Context context;
    private Mediator mediator;


    //TODO THIS CLASS WILL HAVE A METHOD TO TAKE ENUM, MAKE RESULTING GAME OBJECT
    //SCENE GAME CALLS GETGAME AND THIS WILL RETURN OBJECT
    //MAKES ALL THE NECESSARY GAME FACTORIES AND SUCH
    public GameCreator(Context context, RelativeLayout relativeLayout, Mediator mediator){
        this.relativeLayout = relativeLayout;
        this.context = context;
        this.mediator = mediator;
    }

    public IGameMode makeGame(int type){

        if(type == GAME_LEVELS)
            return makeGameLevels();
        return null;
    }
    public IGameMode makeGameLevels(){

        return new GameModeLevels(context, relativeLayout, mediator);
    }




}
