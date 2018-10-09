package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;

/**
 * Created by Tadto on 3/22/2018.
 */

public interface IGameMode {

    public void update(int elapsedTime);
    public void draw(Canvas canvas);
    public Player getPlayer();
    public boolean helperActivatable();
    public void activateHelper();
    public void parcelableInit(Context context, RelativeLayout relativeLayout);
    public boolean isGameDone();
    public IStats getStats();

}
