package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tadto on 3/21/2018.
 */

public class TreatPatternX implements TreatPattern, Serializable {
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

    public TreatPatternX(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 9;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
        this.rand = rand;
        spawnPositionsX = new int[9];
        spawnPositionsY = new int[9];
        spawnPositionsX[0] = (screenWidthUnit * 1);
        spawnPositionsY[0] = (screenHeightUnit * -1);
        spawnPositionsX[1] = (screenWidthUnit * 3);
        spawnPositionsY[1] = (screenHeightUnit * -3);
        spawnPositionsX[2] = (screenWidthUnit * 5);
        spawnPositionsY[2] = (screenHeightUnit * -5);
        spawnPositionsX[3] = (screenWidthUnit * 7);
        spawnPositionsY[3] = (screenHeightUnit * -7);
        spawnPositionsX[4] = (screenWidthUnit * 9);
        spawnPositionsY[4] = (screenHeightUnit * -9);
        spawnPositionsX[5] = (screenWidthUnit * 9);
        spawnPositionsY[5] = (screenHeightUnit * -1);
        spawnPositionsX[6] = (screenWidthUnit * 7);
        spawnPositionsY[6] = (screenHeightUnit * -3);
        spawnPositionsX[7] = (screenWidthUnit * 3);
        spawnPositionsY[7] = (screenHeightUnit * -7);
        spawnPositionsX[8] = (screenWidthUnit * 1);
        spawnPositionsY[8] = (screenHeightUnit * -9);
        spawnBuff = false;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern X");
        isDone = false;
        if(spawnBuff){
            buffPlacement = rand.nextInt(spawnPositionsX.length);
        }
        /*for(int i = 0; i < spawnPositionsX.length - 1; i++){
            if(rand.nextInt(100) < targetTreatChance)
                treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], targetTreat, treatSmalls);
            else{
                if(spawnBuff && i == buffPlacement){
                    spawnBuff = false;
                    buff.reinit(buff.getType(), spawnPositionsX[i], spawnPositionsY[i]);
                }
                else
                    treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], types[rand.nextInt(types.length)], treatSmalls);
            }

        }*/

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

        spawnBuff = false;
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
        return TreatPattern.X;
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
