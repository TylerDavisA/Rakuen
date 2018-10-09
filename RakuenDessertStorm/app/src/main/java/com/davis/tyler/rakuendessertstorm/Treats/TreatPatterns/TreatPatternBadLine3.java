package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

public class TreatPatternBadLine3 implements TreatPattern{
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int screenWidthUnit, screenHeightUnit;
    private int[] types;
    private int targetTreat;
    private int targetTreatChance;
    private int[] spawnPositionsX, spawnPositionsY;
    private Random rand;

    public TreatPatternBadLine3(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 9;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
        this.rand = rand;
        spawnPositionsX = new int[9];
        spawnPositionsY = new int[9];
        spawnPositionsX[0] = (screenWidthUnit * 5);
        spawnPositionsY[0] = (screenHeightUnit * -1);
        spawnPositionsX[1] = (screenWidthUnit * 2);
        spawnPositionsY[1] = (screenHeightUnit * -1);
        spawnPositionsX[2] = (screenWidthUnit * 8);
        spawnPositionsY[2] = (screenHeightUnit * -1);
        spawnPositionsX[3] = (screenWidthUnit * 1);
        spawnPositionsY[3] = (screenHeightUnit * -1);
        spawnPositionsX[4] = (screenWidthUnit * 3);
        spawnPositionsY[4] = (screenHeightUnit * -1);
        spawnPositionsX[5] = (screenWidthUnit * 4);
        spawnPositionsY[5] = (screenHeightUnit * -1);
        spawnPositionsX[6] = (screenWidthUnit * 6);
        spawnPositionsY[6] = (screenHeightUnit * -1);
        spawnPositionsX[7] = (screenWidthUnit * 7);
        spawnPositionsY[7] = (screenHeightUnit * -1);
        spawnPositionsX[8] = (screenWidthUnit * 9);
        spawnPositionsY[8] = (screenHeightUnit * -1);
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {

        isDone = false;
        for(int i = 0; i < 3; i++){
            treatFactory.spawnBadTreat(spawnPositionsX[i], spawnPositionsY[i], treatBads);


        }
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[3], spawnPositionsY[3], types[rand.nextInt(types.length)], treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[4], spawnPositionsY[4], types[rand.nextInt(types.length)], treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[5], spawnPositionsY[5], types[rand.nextInt(types.length)], treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[6], spawnPositionsY[6], types[rand.nextInt(types.length)], treatSmalls);
        treatFactory.spawnSmallTreatByNumber(spawnPositionsX[7], spawnPositionsY[7], types[rand.nextInt(types.length)], treatSmalls);
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
        return TreatPattern.BAD_LINE2;
    }
    @Override
    public Treat getHighest() {
        return highestFood;
    }

    @Override
    public void setBuff(TreatSmall buff) {

    }

    @Override
    public boolean isBad() {
        return true;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
