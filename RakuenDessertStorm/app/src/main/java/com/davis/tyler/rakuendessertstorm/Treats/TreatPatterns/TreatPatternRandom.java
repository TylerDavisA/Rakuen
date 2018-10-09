package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.IStats;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Tadto on 3/21/2018.
 */

public class TreatPatternRandom implements TreatPattern, Serializable {
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int[] types;
    private int targetTreat;
    private int targetTreatChance;
    private int screenWidthUnit, screenHeightUnit;
    private Random rand;
    private boolean spawnBuff;
    private TreatSmall buff;


    public TreatPatternRandom(TreatFactory treatFactory,Random rand){
        this.treatFactory = treatFactory;
        this.rand = rand;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 34 ;
        screenWidthUnit = Constants.SCREEN_WIDTH/34;
        spawnBuff = false;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        //System.out.println("Pattern Random");
        int i, numSpawned = 15, startX, startY, type, highestY = 0;
        for(i = 0; i < numSpawned; i++){
            if(rand.nextInt(100) < targetTreatChance)
                type = targetTreat;
            else
                type = types[rand.nextInt(types.length)];

            startX = (rand.nextInt(32) + 1) * screenWidthUnit;
            startY = -(rand.nextInt(32) + 1) * screenHeightUnit;
            if (startY < highestY)
                highestY = startY;
            if(spawnBuff){
                spawnBuff = false;
                buff.reinit(buff.getType(), startX, startY);
            }
            else
                treatFactory.spawnSmallTreatByNumber(startX, startY, type, treatSmalls);
        }
        highestFood = treatFactory.findFirstAvailableSmall(treatSmalls);
        treatFactory.spawnSmallTreatByNumber((rand.nextInt(32) + 1) * screenWidthUnit,
                highestY, types[0], treatSmalls);

    }

    @Override
    public void reset() {
        isDone = false;
    }

    @Override
    public void update(){
        if(highestFood.getRectTop() > 0){
            isDone = true;
        }
    }
    @Override
    public void setFoodTypes(int[] treats, int target, int chanceTargetTreat){
        this.types = treats;
        this.targetTreat = target;
        this.targetTreatChance = chanceTargetTreat;
    }

    @Override
    public void setDone() {
        isDone = true;
    }

    @Override
    public void setHighest(Treat treat) {
        highestFood = treat;
    }
    @Override
    public void setBuff(TreatSmall buff) {
        spawnBuff = true;
        this.buff = buff;
    }

    @Override
    public boolean isBad() {
        return false;
    }

    @Override
    public int getType() {
        return TreatPattern.RANDOM;
    }
    @Override
    public Treat getHighest() {
        return highestFood;
    }
    @Override
    public boolean isDone() {
        return isDone;
    }
}
