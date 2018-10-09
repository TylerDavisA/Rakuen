package com.davis.tyler.rakuendessertstorm.GamePieces;

import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

/**
 * Created by Tadto on 3/17/2018.
 */

public interface IStats {

    public void incrementScore(int toAdd);
    public void incrementHelperScore(int toAdd);
    public boolean isGameOver();
    public void incrementTargetTreat();
    public int getScore();
    public void addToCombo(ITreatTypeGood type);
    public int getCombo();
    public void resetCombo();
    public void registerTreatCollected(ITreatTypeGood type);
    public int findMostCollectedType();
    public int findNumberCollected(int type);
    public void setDoublePoints(boolean b);
    public int getHelperScore();
    public boolean isMultiplier();
    public int getPointMultiplier();
    public void setGameOver();
    public int getLevel();
    public int getTargetTreat();
    public int getTargetCount();
    public int getTargetGoal();
    public int[] getTreatsToDrop();
}
