package com.davis.tyler.rakuendessertstorm.MessageBoard;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * Created by Tadto on 3/21/2018.
 */

public interface IMessageBoard {
    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public void queueMessage(String msg, int animation);
    public void forceMessage(String msg, int animation, int animWidth, int animHeight);
    public void forceMessage(String msg, int animation);

}
