package com.davis.tyler.rakuendessertstorm.MoveSystem;

import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.CollisionHandler.ICollisionHandler;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.GamePieces.ICoreGameObjects;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.Player;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveTreatExplode;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 6/11/2018.
 */

public class MoveSystemMagnet implements IMoveSystem {
    private float rectW, rectH;
    private Player player;
    private Rect rectMagnet;
    private TreatSmall[] treatSmalls;
    private TreatBad[] treatBads;
    private TreatGiant[] treatGiants;
    private IStats stats;
    private TreatFactory treatFactory;
    private ObjectMoveFactory objectMoveFactory;
    private Coordinates location;
    public MoveSystemMagnet(ICoreGameObjects coreGameObjects, IStats stats){
        rectW = 1.7f * Player.PLAYER_WIDTH;
        rectH = 1.7f * Player.PLAYER_HEIGHT;
        this.player = coreGameObjects.getPlayer();
        location = player.getCoordinates();
        this.treatSmalls = coreGameObjects.getSmallTreats();
        this.treatBads = coreGameObjects.getBadTreats();
        this.treatGiants = coreGameObjects.getGiantTreats();
        this.stats = stats;
        this.treatFactory = coreGameObjects.getTreatFactory();
        this.objectMoveFactory = coreGameObjects.getObjectMoveFactory();
        rectMagnet = new Rect();
    }
    @Override
    public void setup() {
        for(TreatSmall t: treatSmalls){
            if(!(t.getMove() instanceof ObjectMoveTreatExplode))
                t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL));
        }
        for(TreatBad t: treatBads){
            if(!(t.getMove() instanceof ObjectMoveTreatExplode))
                t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL));
        }
        for(TreatGiant t: treatGiants){
            t.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_GIANT_FALL));
        }
    }


    @Override
    public void moveSmallTreats(int elapsedTime, int targetTreat, ICollisionHandler collisionHandler) {
        rectMagnet.set((int)(location.x-rectH), (int)(location.y-rectH),
                (int)(location.x+rectH),(int) (location.y+rectH));
        for(TreatSmall t:treatSmalls) {
            if(!t.isDeleted()) {
                if(t.getRect().intersect(rectMagnet)) {
                    t.setMove(objectMoveFactory.getObjectMoveMagnet());
                }
                t.update(elapsedTime);
                if (t.getRect().intersect(player.getCollisionRect())) {
                    collisionHandler.executeCollision(t, stats);
                }
                if (t.getRectTop() > Constants.SCREEN_HEIGHT * 1.5)
                    t.delete();

            }
        }
    }

    @Override
    public void moveBadTreats(int elapsedTime) {
        for(TreatBad t:treatBads) {
            if(!t.isDeleted()) {
                t.update(elapsedTime);
                if (t.getRect().intersect(player.getCollisionRect())) {
                    stats.setGameOver();
                    t.delete();
                }
                if (t.getRectTop() > Constants.SCREEN_HEIGHT )
                    t.delete();

            }
        }
    }

    @Override
    public void moveGiantTreats(int elapsedTime) {
        for(TreatGiant t:treatGiants) {
            if(!t.isDeleted()) {
                t.update(elapsedTime);
                if (t.getRect().intersect(player.getCollisionRect())) {
                    stats.incrementScore(t.getPoints());
                    treatFactory.spawnExplodedTreats(t, treatSmalls);
                    t.delete();
                }
                if (t.getRectTop() > Constants.SCREEN_HEIGHT )
                    t.delete();
            }
        }
    }

    @Override
    public void updatePlayer(int elapsedTime) {
        player.update(elapsedTime);
    }

}
