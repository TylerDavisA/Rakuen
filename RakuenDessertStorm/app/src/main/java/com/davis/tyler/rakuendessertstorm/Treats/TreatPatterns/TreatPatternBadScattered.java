package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.Random;

/**
 * Created by Tadto on 3/28/2018.
 */

public class TreatPatternBadScattered implements TreatPattern {
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int screenWidthUnit, screenHeightUnit;
    private int type;
    private int[] types;
    private int targetTreat;
    private int targetTreatChance;
    private int[] spawnPositionsX, spawnPositionsY;
    private Random rand;
    private boolean spawnBuff;
    private TreatSmall buff;
    private int buffPlacement;
    private int bad1, bad2, bad3, bad4, bad5;

    public TreatPatternBadScattered(TreatFactory treatFactory, Random rand){
        this.treatFactory = treatFactory;
        this.type = type;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 6;
        screenWidthUnit = Constants.SCREEN_WIDTH/12;
        this.rand = rand;
        spawnBuff = false;
        spawnPositionsX = new int[24];
        spawnPositionsY = new int[24];
        spawnPositionsX[0] = (screenWidthUnit * 1);
        spawnPositionsY[0] = (screenHeightUnit * -1);
        spawnPositionsX[1] = (screenWidthUnit * 1);
        spawnPositionsY[1] = (screenHeightUnit * -3);
        spawnPositionsX[2] = (screenWidthUnit * 1);
        spawnPositionsY[2] = (screenHeightUnit * -5);
        spawnPositionsX[3] = (screenWidthUnit * 1);
        spawnPositionsY[3] = (screenHeightUnit * -7);
        spawnPositionsX[4] = (screenWidthUnit * 3);
        spawnPositionsY[4] = (screenHeightUnit * -1);
        spawnPositionsX[5] = (screenWidthUnit * 3);
        spawnPositionsY[5] = (screenHeightUnit * -3);
        spawnPositionsX[6] = (screenWidthUnit * 3);
        spawnPositionsY[6] = (screenHeightUnit * -5);
        spawnPositionsX[7] = (screenWidthUnit * 3);
        spawnPositionsY[7] = (screenHeightUnit * -7);
        spawnPositionsX[8] = (screenWidthUnit * 5);
        spawnPositionsY[8] = (screenHeightUnit * -1);
        spawnPositionsX[9] = (screenWidthUnit * 5);
        spawnPositionsY[9] = (screenHeightUnit * -3);
        spawnPositionsX[10] = (screenWidthUnit * 5);
        spawnPositionsY[10] = (screenHeightUnit * -5);
        spawnPositionsX[11] = (screenWidthUnit * 5);
        spawnPositionsY[11] = (screenHeightUnit * -7);
        spawnPositionsX[12] = (screenWidthUnit * 7);
        spawnPositionsY[12] = (screenHeightUnit * -1);
        spawnPositionsX[13] = (screenWidthUnit * 7);
        spawnPositionsY[13] = (screenHeightUnit * -3);
        spawnPositionsX[14] = (screenWidthUnit * 7);
        spawnPositionsY[14] = (screenHeightUnit * -5);
        spawnPositionsX[15] = (screenWidthUnit * 7);
        spawnPositionsY[15] = (screenHeightUnit * -7);
        spawnPositionsX[16] = (screenWidthUnit * 9);
        spawnPositionsY[16] = (screenHeightUnit * -1);
        spawnPositionsX[17] = (screenWidthUnit * 9);
        spawnPositionsY[17] = (screenHeightUnit * -3);
        spawnPositionsX[18] = (screenWidthUnit * 9);
        spawnPositionsY[18] = (screenHeightUnit * -5);
        spawnPositionsX[19] = (screenWidthUnit * 9);
        spawnPositionsY[19] = (screenHeightUnit * -7);
        spawnPositionsX[20] = (screenWidthUnit * 11);
        spawnPositionsY[20] = (screenHeightUnit * -1);
        spawnPositionsX[21] = (screenWidthUnit * 11);
        spawnPositionsY[21] = (screenHeightUnit * -3);
        spawnPositionsX[22] = (screenWidthUnit * 11);
        spawnPositionsY[22] = (screenHeightUnit * -5);
        spawnPositionsX[23] = (screenWidthUnit * 11);
        spawnPositionsY[23] = (screenHeightUnit * -7);
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        //System.out.println("Pattern SCATTERED");
        bad1 = rand.nextInt(spawnPositionsX.length);
        bad2 = rand.nextInt(spawnPositionsX.length);
        bad3 = rand.nextInt(spawnPositionsX.length);
        bad4 = rand.nextInt(spawnPositionsX.length);
        bad5 = rand.nextInt(spawnPositionsX.length);
        isDone = false;
        if(spawnBuff){
            buffPlacement = rand.nextInt(spawnPositionsX.length);
        }
        /*for(int i = 0; i < spawnPositionsX.length - 1; i++){
            if( i == bad1 || i == bad2 || i == bad3 || i == bad4 || i == bad5){
                treatFactory.spawnBadTreat(spawnPositionsX[i], spawnPositionsY[i], treatBads);
            }
            else if(rand.nextInt(100) < targetTreatChance)
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
            else if( i == bad1 || i == bad2 || i == bad3 || i == bad4 || i == bad5){
                treatFactory.spawnBadTreat(spawnPositionsX[i], spawnPositionsY[i], treatBads);
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
    public void setFoodTypes(int[] treats, int target, int chanceTargetTreat){
        this.types = treats;
        this.targetTreat = target;
        this.targetTreatChance = chanceTargetTreat;
    }
    @Override
    public void update(){
        if(highestFood.getRectTop() > 0){
            isDone = true;
        }
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
        return TreatPattern.BAD_SCATTERED;
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
        return true;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}
