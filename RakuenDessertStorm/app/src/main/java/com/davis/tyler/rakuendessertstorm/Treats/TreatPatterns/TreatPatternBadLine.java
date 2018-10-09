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

public class TreatPatternBadLine implements TreatPattern {
    private boolean isDone;
    private TreatFactory treatFactory;
    private Treat highestFood;
    private int screenWidthUnit, screenHeightUnit;
    private int type;


    public TreatPatternBadLine(TreatFactory treatFactory){
        this.treatFactory = treatFactory;
        isDone = true;
        screenHeightUnit = Constants.SCREEN_HEIGHT / 6;
        screenWidthUnit = Constants.SCREEN_WIDTH/10;
    }

    @Override
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBads) {
        System.out.println("Pattern BAD LINE");
        isDone = false;
        treatFactory.spawnBadTreat(screenWidthUnit * 1, screenHeightUnit * -1, treatBads);

        treatFactory.spawnBadTreat(screenWidthUnit * 5, screenHeightUnit * -1, treatBads);

        highestFood = treatFactory.findFirstAvailableBad(treatBads);
        treatFactory.spawnBadTreat(screenWidthUnit * 9, screenHeightUnit * -1, treatBads);


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
        return TreatPattern.BAD_LINE;
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
