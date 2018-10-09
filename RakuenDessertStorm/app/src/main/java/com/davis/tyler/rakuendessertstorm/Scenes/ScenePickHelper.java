package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;

import java.util.Set;

/**
 * Created by Tadto on 3/25/2018.
 */

public class ScenePickHelper {
    private String[] helpers;
    //private Set<String> helpersUnlocked; //can store and retrieve set strings from preferences

    public ScenePickHelper(Context context){
        helpers = new String[]{"shigi", "pdog"};
        //make array of bitmaps here
        //make list for 5 bitmaps to draw
        //each image to draw is in an object which will have a move object from objectMoveFactory
        //
        //display the bitmaps index 1-3
        //if move right, call right animation
        //right animation: setMoveIconRight on index 0-4
        // remove last, add new to index 0

        //if move left, call left animation
        //left animation: setMoveIconLeft on index 0-4
        //remove first, add new to index last

        //if end of helpers is reached, display nothing past the helper, and do nothing on movements
    }
}
