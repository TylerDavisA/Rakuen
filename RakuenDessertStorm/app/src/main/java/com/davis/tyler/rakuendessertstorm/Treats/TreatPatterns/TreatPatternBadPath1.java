package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 3/28/2018.
 */

public class TreatPatternBadPath1 implements TreatPattern {
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int type1, type2;
    private int screenWidthUnit, screenHeightUnit;
    private int type;


    public TreatPatternBadPath1(TreatFactory treatFactory, int type){
        this.treatFactory = treatFactory;
        this.type = type;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 6;
        screenWidthUnit = Constants.SCREEN_WIDTH/12;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern PATH1");
        isDone = false;
        treatFactory.spawnBadTreat(screenWidthUnit * 1, screenHeightUnit * -1, treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 1, screenHeightUnit * -3, treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 1, screenHeightUnit * -5, treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 1, screenHeightUnit * -7, treatBads);

        treatFactory.spawnBadTreat(screenWidthUnit * 3, screenHeightUnit * -1, treatBads);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 3, screenHeightUnit * -3, type1, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 3, screenHeightUnit * -5, type2, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 3, screenHeightUnit * -7, type1, treatSmalls);


        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 4, screenHeightUnit * -3, type2, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 4, screenHeightUnit * -5, type1, treatSmalls);

        treatFactory.spawnBadTreat(screenWidthUnit * 5, screenHeightUnit * -1, treatBads);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 5, screenHeightUnit * -3, type1, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 5, screenHeightUnit * -5, type2, treatSmalls);
        treatFactory.spawnBadTreat(screenWidthUnit * 5, screenHeightUnit * -7, treatBads);

        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 6, screenHeightUnit * -3, type2, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 6, screenHeightUnit * -5, type1, treatSmalls);

        treatFactory.spawnBadTreat(screenWidthUnit * 7, screenHeightUnit * -1, treatBads);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 7, screenHeightUnit * -3, type1, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 7, screenHeightUnit * -5, type2, treatSmalls);
        treatFactory.spawnBadTreat(screenWidthUnit * 7, screenHeightUnit * -7, treatBads);

        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 8, screenHeightUnit * -3, type2, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 8, screenHeightUnit * -5, type1, treatSmalls);



        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 9, screenHeightUnit * -1, type1, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 9, screenHeightUnit * -3, type1, treatSmalls);
        treatFactory.spawnSmallTreatByNumber(screenWidthUnit * 9, screenHeightUnit * -5, type2, treatSmalls);
        treatFactory.spawnBadTreat(screenWidthUnit * 9, screenHeightUnit * -7, treatBads);

        treatFactory.spawnBadTreat(screenWidthUnit * 11, screenHeightUnit * -1, treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 11, screenHeightUnit * -3, treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 11, screenHeightUnit * -5, treatBads);
        highestFood = treatFactory.findFirstAvailableBad(treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 11, screenHeightUnit * -7, treatBads);


    }

    @Override
    public void reset() {
        isDone = false;
    }

    @Override
    public void setFoodTypes(int[] treats, int target, int chanceTargetTreat) {

    }

    @Override
    public void update(){
        if(highestFood.getRectTop() > 0){
            isDone = true;
        }
    }
    public void setFoodTypes(int type1, int type2){
        this.type1 = type1;
        this.type2 = type2;
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
        return type;
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
