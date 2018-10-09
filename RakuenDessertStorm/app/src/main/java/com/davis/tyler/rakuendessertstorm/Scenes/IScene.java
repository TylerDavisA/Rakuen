package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Tadto on 3/9/2018.
 */

public interface IScene {
    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent e);
    public void parcelableInit(Context context, RelativeLayout relativeLayout, SceneManager sceneManager);
}
