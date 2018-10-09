package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
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

public class TreatPatternStars implements TreatPattern, Serializable {
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


    public TreatPatternStars(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = false;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 9;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
        spawnPositionsX = new int[5];
        spawnPositionsY = new int[5];
        spawnPositionsX[0] = (screenWidthUnit * 2);
        spawnPositionsY[0] = (screenHeightUnit * -5);
        spawnPositionsX[1] = (screenWidthUnit * 6);
        spawnPositionsY[1] = (screenHeightUnit * -5);
        spawnPositionsX[2] = (screenWidthUnit * 2);
        spawnPositionsY[2] = (screenHeightUnit * -7);
        spawnPositionsX[3] = (screenWidthUnit * 5);
        spawnPositionsY[3] = (screenHeightUnit * -7);
        spawnPositionsX[4] = (screenWidthUnit * 8);
        spawnPositionsY[4] = (screenHeightUnit * -7);
        this.rand = rand;
        spawnBuff = false;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern Stars");
        isDone = false;
        treatFactory.spawnTreatStar(types, spawnPositionsX[0], spawnPositionsY[0], treatSmalls);
        treatFactory.spawnTreatStar(types, spawnPositionsX[1], spawnPositionsY[1], treatSmalls);
        if(rand.nextInt(100)< targetTreatChance){
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[2], spawnPositionsY[2], targetTreat, treatSmalls);
        }
        else {
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[2], spawnPositionsY[2], types[rand.nextInt(types.length)], treatSmalls);
        }

        if(spawnBuff){
            spawnBuff = false;
            buff.reinit(buff.getType(), spawnPositionsX[3], spawnPositionsY[3]);
        }
        else if(rand.nextInt(100)< targetTreatChance){
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[3], spawnPositionsY[3], targetTreat, treatSmalls);
        }
        else {
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[3], spawnPositionsY[3], types[rand.nextInt(types.length)], treatSmalls);
        }
        highestFood = treatFactory.findFirstAvailableSmall(treatSmalls);
        if(rand.nextInt(100)< targetTreatChance){
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[4], spawnPositionsY[4], targetTreat, treatSmalls);
        }
        else {
            treatFactory.spawnSmallTreatByNumber(spawnPositionsX[4], spawnPositionsY[4], types[rand.nextInt(types.length)], treatSmalls);
        }

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
        return TreatPattern.STARS;
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
