package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tadto on 3/21/2018.
 */

public class TreatPatternManager implements ISubscriber, Parcelable {
    public final int RANDOM = 0;
    public final int STARS = 1;
    public final int X = 2;
    public final int CRISS_CROSS = 3;
    public final int BAD_SCATTERED = 4;
    public final int BAD_LINE = 5;
    public final int BAD_PATH1 = 6;
    public final int BAD_PATH1_REVERSE = 7;
    private ArrayList<TreatPattern> treatPatterns;
    private TreatPattern currentPattern;
    private TreatPatternRandom patternRandom;
    private Random rand;
    private int type1, type2;
    private int[] foodTypes;
    private boolean canMakePatterns = true;
    private int patternType;
    private int highFoodIndex;
    private TreatSmall[] treatSmalls;
    private TreatFactory treatFactory;


    public TreatPatternManager(StatsLevels stats, TreatFactory treatFactory, Random rand, TreatBad[] treatSmalls){

        this.rand = rand;
        this.treatFactory = treatFactory;
        //this.treatSmalls = treatSmalls;
        listInit(treatFactory);
        currentPattern = patternRandom;
        patternType = RANDOM;
        stats.subscribe(this);

    }
    public TreatPatternManager(Parcel in){
        patternType = in.readInt();
        highFoodIndex = in.readInt();
    }
    public void parcelableInit(TreatFactory treatFactory, TreatSmall[] treatSmalls){
        listInit(treatFactory);
        this.treatSmalls = treatSmalls;
        currentPattern = patternRandom;
        currentPattern.setHighest(treatSmalls[highFoodIndex]);
        //make new treatpattern list
        //setTreat list
        //sethighestfood and cur pattern

    }
    public void update(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatSmall[] treatBad){

        if(canMakePatterns){
            if(currentPattern.isDone()){
                makePattern(treatSmalls, treatGiants, treatBad);
            }
            currentPattern.update();
        }


    }
    private void listInit(TreatFactory treatFactory){
        treatPatterns = new ArrayList<TreatPattern>();

        patternRandom = new TreatPatternRandom(treatFactory, rand);
        //treatPatterns.add(new TreatPatternX(treatFactory, X));
        //treatPatterns.add(new TreatPatternCrissCross(treatFactory, CRISS_CROSS));
        //treatPatterns.add(new TreatPatternStars(treatFactory, STARS));

    }
    private void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatSmall[] treatBad){
        currentPattern.reset();
        if(currentPattern == patternRandom)
            currentPattern = treatPatterns.get(rand.nextInt(treatPatterns.size()));
        else
            currentPattern = patternRandom;

        type1 = rand.nextInt(foodTypes.length);
        type2 = rand.nextInt(foodTypes.length);
        if(type1 == type2)
            type2 = rand.nextInt(foodTypes.length);
        //currentPattern.setFoodTypes(foodTypes[rand.nextInt(foodTypes.length)], foodTypes[rand.nextInt(foodTypes.length)]);
       // currentPattern.makePattern(treatSmalls, treatGiants, treatBad);
        patternType = currentPattern.getType();
        //TreatSmall temp = currentPattern.getHighest();
        for(int i = 0; i < treatSmalls.length; i++){
         //   if(treatSmalls[i].equals(temp))
                highFoodIndex = i;
        }
    }
    public void setCanMakePatternsOff(){
        canMakePatterns = false;
    }


    @Override
    public void notifyLevels(LevelSystem level) {
        foodTypes = level.getTreatsToDrop();
        currentPattern.setDone();
        if(level.getLevel() == 1){
            treatPatterns.add(new TreatPatternBadScattered(treatFactory, rand));
            treatPatterns.add(new TreatPatternBadLine(treatFactory));
            treatPatterns.add(new TreatPatternBadPath1(treatFactory, BAD_PATH1));
            treatPatterns.add(new TreatPatternBadPath1Reverse(treatFactory, BAD_PATH1_REVERSE));
        }
    }

    @Override
    public void notifyAreaChange() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(patternType);
        parcel.writeInt(highFoodIndex);
    }

    public static final Parcelable.Creator<TreatPatternManager> CREATOR = new Parcelable.Creator<TreatPatternManager>(){
        public TreatPatternManager createFromParcel(Parcel in){
            return new TreatPatternManager(in);
        }

        public TreatPatternManager[] newArray(int size){
            return new TreatPatternManager[size];
        }

    };
}
