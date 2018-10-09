package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.TreatDropSystem;
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

public class TreatPatternCrissCross implements TreatPattern, Serializable {

    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int screenWidthUnit, screenHeightUnit;
    private int[] types;
    private int targetTreat;
    private int targetTreatChance;
    private int[] spawnPositionsX, spawnPositionsY;
    private Random rand;
    private boolean spawnBuff;
    private TreatSmall buff;
    private int buffPlacement;

    public TreatPatternCrissCross(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 14;
        screenWidthUnit = Constants.SCREEN_WIDTH/11;
        this.rand = rand;
        spawnBuff = false;
        spawnPositionsX = new int[14];
        spawnPositionsY = new int[14];
        spawnPositionsX[0] = (screenWidthUnit * 5);
        spawnPositionsY[0] = (screenHeightUnit * -1);
        spawnPositionsX[1] = (screenWidthUnit * 3);
        spawnPositionsY[1] = (screenHeightUnit * -3);
        spawnPositionsX[2] = (screenWidthUnit * 1);
        spawnPositionsY[2] = (screenHeightUnit * -5);
        spawnPositionsX[3] = (screenWidthUnit * 3);
        spawnPositionsY[3] = (screenHeightUnit * -7);
        spawnPositionsX[4] = (screenWidthUnit * 5);
        spawnPositionsY[4] = (screenHeightUnit * -9);
        spawnPositionsX[5] = (screenWidthUnit * 3);
        spawnPositionsY[5] = (screenHeightUnit * -11);
        spawnPositionsX[6] = (screenWidthUnit * 1);
        spawnPositionsY[6] = (screenHeightUnit * -13);
        spawnPositionsX[7] = (screenWidthUnit * 6);
        spawnPositionsY[7] = (screenHeightUnit * -1);
        spawnPositionsX[8] = (screenWidthUnit * 8);
        spawnPositionsY[8] = (screenHeightUnit * -3);
        spawnPositionsX[9] = (screenWidthUnit * 10);
        spawnPositionsY[9] = (screenHeightUnit * -5);
        spawnPositionsX[10] = (screenWidthUnit * 8);
        spawnPositionsY[10] = (screenHeightUnit * -7);
        spawnPositionsX[11] = (screenWidthUnit * 6);
        spawnPositionsY[11] = (screenHeightUnit * -9);
        spawnPositionsX[12] = (screenWidthUnit * 8);
        spawnPositionsY[12] = (screenHeightUnit * -11);
        spawnPositionsX[13] = (screenWidthUnit * 10);
        spawnPositionsY[13] = (screenHeightUnit * -13);
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern CrissCross");
        isDone = false;
        if(spawnBuff){
            buffPlacement = rand.nextInt(spawnPositionsX.length);
        }
        for(int i = 0; i < spawnPositionsX.length - 1; i++){
            if(spawnBuff && i == buffPlacement){
                spawnBuff = false;
                buff.reinit(buff.getType(), spawnPositionsX[i], spawnPositionsY[i]);
            }
            else if(rand.nextInt(100) < targetTreatChance)
                treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], targetTreat, treatSmalls);
            else{
                treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], types[rand.nextInt(types.length)], treatSmalls);
            }

        }

        highestFood = treatFactory.findFirstAvailableSmall(treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[spawnPositionsX.length -1],
                spawnPositionsY[spawnPositionsY.length -1], types[0], treatSmalls);
        spawnBuff = false;
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
    public void setBuff(TreatSmall buff) {
        spawnBuff = true;
        this.buff = buff;
    }

    @Override
    public boolean isBad() {
        return false;
    }

    @Override
    public void setHighest(Treat treat) {
        highestFood = treat;
    }

    @Override
    public Treat getHighest() {
        return highestFood;
    }

    @Override
    public int getType() {
        return TreatPattern.CRISS_CROSS;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
