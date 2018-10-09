package com.davis.tyler.rakuendessertstorm.GamePieces;

import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternManager;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.List;
import java.util.Random;

/**
 * Created by Tadto on 3/17/2018.
 */

public interface ICoreGameObjects {
    public Player getPlayer();
    public TreatSmall[] getSmallTreats();
    public TreatGiant[] getGiantTreats();
    public Random getRandom();
    public TreatFactory getTreatFactory();
    public TreatBad[] getBadTreats();
    public MessageBoardLeebles getMessageBoard();
    public ObjectMoveFactory getObjectMoveFactory();
}
