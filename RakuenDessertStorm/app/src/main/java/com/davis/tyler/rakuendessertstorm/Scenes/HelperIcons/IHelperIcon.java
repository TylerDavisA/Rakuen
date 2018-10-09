package com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface IHelperIcon {

    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public void select();
    public void deselect();
    public int getType();
    public Rect getRect();
}
