package com.davis.tyler.rakuendessertstorm.Subscriber;

import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;

/**
 * Created by Tadto on 3/17/2018.
 */

public interface ISubscriber {

    public void notifyLevels(LevelSystem level);
    public void notifyAreaChange();
}
