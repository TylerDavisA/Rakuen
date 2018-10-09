package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 6/14/2018.
 */

public class TreatPatternBadHeart implements TreatPattern {
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
    private TreatSmall buff = null;

    public TreatPatternBadHeart(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 9;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
        this.rand = rand;
        spawnPositionsX = new int[12];
        spawnPositionsY = new int[12];
        spawnPositionsX[0] = (screenWidthUnit * 5);
        spawnPositionsY[0] = (screenHeightUnit * -4);
        spawnPositionsX[1] = (screenWidthUnit * 5);
        spawnPositionsY[1] = (screenHeightUnit * -4);
        spawnPositionsX[2] = (screenWidthUnit * 1);
        spawnPositionsY[2] = (screenHeightUnit * -6);
        spawnPositionsX[3] = (screenWidthUnit * 1);
        spawnPositionsY[3] = (screenHeightUnit * -2);
        spawnPositionsX[4] = (screenWidthUnit * 9);
        spawnPositionsY[4] = (screenHeightUnit * -6);
        spawnPositionsX[5] = (screenWidthUnit * 9);
        spawnPositionsY[5] = (screenHeightUnit * -2);
        spawnPositionsX[6] = (screenWidthUnit * 2);
        spawnPositionsY[6] = (screenHeightUnit * -4);
        spawnPositionsX[7] = (screenWidthUnit * 8);
        spawnPositionsY[7] = (screenHeightUnit * -4);
        spawnPositionsX[8] = (screenWidthUnit * 3);
        spawnPositionsY[8] = (screenHeightUnit * -2);
        spawnPositionsX[9] = (screenWidthUnit * 3);
        spawnPositionsY[9] = (screenHeightUnit * -6);
        spawnPositionsX[10] = (screenWidthUnit * 7);
        spawnPositionsY[10] = (screenHeightUnit * -2);
        spawnPositionsX[11] = (screenWidthUnit * 7);
        spawnPositionsY[11] = (screenHeightUnit * -6);
        spawnBuff = false;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        isDone = false;

        treatFactory.spawnTreatHeart(types, spawnPositionsX[0], spawnPositionsY[0], treatSmalls);
        if(spawnBuff){
            spawnBuff = false;
            buff.reinit(buff.getType(), spawnPositionsX[1], spawnPositionsY[1]);
        }
        else{
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[1], spawnPositionsY[1], types[1], treatSmalls);
        }
        for(int i = 2; i < 8; i++){
            treatFactory.spawnBadTreat(spawnPositionsX[i], spawnPositionsY[i],treatBads );

        }

        for(int i = 8; i < spawnPositionsX.length - 1; i++){
            if(rand.nextInt(100) < targetTreatChance)
                treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], targetTreat, treatSmalls);
            else{
                treatFactory.spawnSmallTreatByNumber(spawnPositionsX[i], spawnPositionsY[i], types[rand.nextInt(types.length)], treatSmalls);
            }

        }


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
        return TreatPattern.BAD_HEART;
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
