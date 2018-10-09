package com.davis.tyler.rakuendessertstorm.GamePieces;

import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.TreatDropSystem;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternManager;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Created by Tadto on 3/17/2018.
 */

public class CoreGameObjectsLevels implements ICoreGameObjects, Serializable {
    //TODO ERASE TREATPATTERNMANAGER
    private TreatFactory treatFactory;
    private TreatSmall[] listTreatSmall;
    private TreatGiant[] listTreatGiant;
    private TreatBad[] listTreatBad;
    private Random rand;
    private Player player;
    private TreatDropSystem treatDropSystem;
    private MessageBoardLeebles messageBoardLeebles;
    private ObjectMoveFactory objectMoveFactory;

    public CoreGameObjectsLevels(TreatSmall[] listTreatSmall, TreatGiant[] listTreatGiant, TreatBad[] listBad,
                                 Random rand, Player player, TreatFactory treatFactory, MessageBoardLeebles messageBoardLeebles,
                                 ObjectMoveFactory objectMoveFactory){
        this.listTreatSmall = listTreatSmall;
        this.listTreatGiant = listTreatGiant;
        this.listTreatBad = listBad;
        this.rand = rand;
        this.player = player;
        this.treatFactory = treatFactory;
        this.messageBoardLeebles = messageBoardLeebles;
        this.objectMoveFactory = objectMoveFactory;
    }
    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public TreatSmall[] getSmallTreats() {
        return listTreatSmall;
    }

    @Override
    public TreatGiant[] getGiantTreats(){
        return listTreatGiant;
    }
    @Override
    public Random getRandom() {
        return rand;
    }

    @Override
    public TreatFactory getTreatFactory() {
        return treatFactory;
    }

    @Override
    public TreatBad[] getBadTreats() {
        return listTreatBad;
    }

    @Override
    public MessageBoardLeebles getMessageBoard() {
        return messageBoardLeebles;
    }

    @Override
    public ObjectMoveFactory getObjectMoveFactory() {
        return objectMoveFactory;
    }


}
