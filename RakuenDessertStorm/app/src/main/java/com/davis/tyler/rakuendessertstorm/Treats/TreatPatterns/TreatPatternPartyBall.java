package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 6/13/2018.
 */

public class TreatPatternPartyBall implements TreatPattern{
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int screenWidthUnit, screenHeightUnit;
    private int[] types;
    private int targetTreat;
    private int targetTreatChance;
    private int[] spawnPositionsX, spawnPositionsY;
    private boolean spawnBuff;
    private TreatSmall buff = null;
    private int buffPlacement;
    private Random rand;

    public TreatPatternPartyBall(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 9;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
        spawnPositionsX = new int[19];
        spawnPositionsY = new int[19];
        spawnPositionsX[0] = (screenWidthUnit * 1);
        spawnPositionsY[0] = (screenHeightUnit * -1);
        spawnPositionsX[1] = (screenWidthUnit * 2);
        spawnPositionsY[1] = (screenHeightUnit * -1);
        spawnPositionsX[2] = (screenWidthUnit * 3);
        spawnPositionsY[2] = (screenHeightUnit * -1);
        spawnPositionsX[3] = (screenWidthUnit * 4);
        spawnPositionsY[3] = (screenHeightUnit * -1);
        spawnPositionsX[4] = (screenWidthUnit * 5);
        spawnPositionsY[4] = (screenHeightUnit * -1);
        spawnPositionsX[5] = (screenWidthUnit * 6);
        spawnPositionsY[5] = (screenHeightUnit * -1);
        spawnPositionsX[6] = (screenWidthUnit * 7);
        spawnPositionsY[6] = (screenHeightUnit * -1);
        spawnPositionsX[7] = (screenWidthUnit * 8);
        spawnPositionsY[7] = (screenHeightUnit * -1);
        spawnPositionsX[8] = (screenWidthUnit * 9);
        spawnPositionsY[8] = (screenHeightUnit * -1);
        spawnPositionsX[9] = (screenWidthUnit * 1);
        spawnPositionsY[9] = (screenHeightUnit * -9);
        spawnPositionsX[10] = (screenWidthUnit * 2);
        spawnPositionsY[10] = (screenHeightUnit * -9);
        spawnPositionsX[11] = (screenWidthUnit * 3);
        spawnPositionsY[11] = (screenHeightUnit * -9);
        spawnPositionsX[12] = (screenWidthUnit * 4);
        spawnPositionsY[12] = (screenHeightUnit * -9);
        spawnPositionsX[13] = (screenWidthUnit * 5);
        spawnPositionsY[13] = (screenHeightUnit * -9);
        spawnPositionsX[14] = (screenWidthUnit * 6);
        spawnPositionsY[14] = (screenHeightUnit * -9);
        spawnPositionsX[15] = (screenWidthUnit * 7);
        spawnPositionsY[15] = (screenHeightUnit * -9);
        spawnPositionsX[16] = (screenWidthUnit * 8);
        spawnPositionsY[16] = (screenHeightUnit * -9);
        spawnPositionsX[17] = (screenWidthUnit * 5);
        spawnPositionsY[17] = (screenHeightUnit * -5);
        spawnPositionsX[18] = (screenWidthUnit * 9);
        spawnPositionsY[18] = (screenHeightUnit * -9);
        spawnBuff = false;
        this.rand = rand;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern party ball spawning");
        isDone = false;
        for(int i = 0; i < spawnPositionsX.length - 2; i++){
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], types[rand.nextInt(types.length)], treatSmalls);

        }

        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[17], spawnPositionsY[17], Treat.PARTY_BALL, treatSmalls);
        highestFood = treatFactory.findFirstAvailableSmall(treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[spawnPositionsX.length -1],
                spawnPositionsY[spawnPositionsY.length -1], types[0], treatSmalls);
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
    public int getType() {
        return TreatPattern.PARTY_BALL;
    }
    @Override
    public Treat getHighest() {
        return highestFood;
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
    public boolean isDone() {
        return isDone;
    }
}
