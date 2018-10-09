package com.davis.tyler.rakuendessertstorm.GamePieces.Helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.CoreGameObjectsLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.IObjectManager;
import com.davis.tyler.rakuendessertstorm.GamePieces.ObjectManagerLevels;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;

/**
 * Created by Tadto on 3/22/2018.
 */

public interface IHelper {

    public static final int HELPER_SHIGI = 0;
    public static final int HELPER_PDOG = 1;
    public static final int NUM_HELPERS = 2;
    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public void activate();
    public void deactivate();
    public boolean isActivatable();
    public boolean isGameDone();
    public void parcelableInit(Context context, CoreGameObjectsLevels coreGameObjectsLevels,
                               StatsLevels stats, ObjectManagerLevels objectManagerLevels,
                               AnimationFactory animationFactory);

}
