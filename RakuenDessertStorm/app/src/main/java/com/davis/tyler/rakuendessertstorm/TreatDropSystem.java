package com.davis.tyler.rakuendessertstorm;

import com.davis.tyler.rakuendessertstorm.GamePieces.ICoreGameObjects;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPattern;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternBadHeart;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternBadLine;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternBadLine2;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternBadLine3;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternBadScattered;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternCrissCross;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternGiantDonut;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternPartyBall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternRandom;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternStars;
import com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns.TreatPatternX;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tadto on 6/9/2018.
 */

public class TreatDropSystem implements ISubscriber {


    private int[] treatsToDrop;
    private int targetTreat;
    private int targetTreatChance;
    private TreatSmall[] treatSmalls;
    private TreatBad[] treatBads;
    private Random rand;
    private TreatFactory treatFactory;
    private ArrayList<TreatPattern> treatPatterns;
    private ArrayList<TreatPattern> treatPatternsBad;
    private TreatPattern currentPattern;
    private TreatPatternRandom patternRandom;
    private boolean canMakePatterns = true;
    private int highFoodIndex;
    private TreatSmall buff;
    private boolean canDropBuff;
    private boolean nextBuffSet;
    private TreatPattern partyBallPattern;
    private boolean partyBallSpawned;

    public TreatDropSystem(StatsLevels stats, ICoreGameObjects coreGameObjects){
        stats.subscribe(this);
        this.treatSmalls = coreGameObjects.getSmallTreats();
        this.treatBads = coreGameObjects.getBadTreats();
        this.rand = coreGameObjects.getRandom();
        this.treatFactory = coreGameObjects.getTreatFactory();
        listInit(treatFactory);
        currentPattern = patternRandom;
        canDropBuff = false;
        canMakePatterns = true;
        partyBallSpawned = false;

    }


    private void listInit(TreatFactory treatFactory){
        treatPatterns = new ArrayList<TreatPattern>();
        partyBallPattern = new TreatPatternPartyBall(treatFactory, rand);

        patternRandom = new TreatPatternRandom(treatFactory, rand);
        treatPatterns.add(new TreatPatternX(treatFactory, rand));
        treatPatterns.add(new TreatPatternCrissCross(treatFactory, rand));
        treatPatterns.add(new TreatPatternStars(treatFactory, rand));
        treatPatterns.add(patternRandom);
        treatPatterns.add(new TreatPatternGiantDonut(treatFactory, rand));

        treatPatternsBad = new ArrayList<TreatPattern>();


    }

    public void update(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBad){

        if(canMakePatterns){
            if(nextBuffSet){
                currentPattern.setDone();
            }
            if(currentPattern.isDone()){
                makePattern(treatSmalls, treatGiants, treatBad);
                if(currentPattern.getType() == TreatPattern.PARTY_BALL){
                    canMakePatterns = false;
                    partyBallSpawned = true;
                }
            }
            currentPattern.update();
        }


    }
    private void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBad){
        currentPattern.reset();
        if(nextBuffSet){
            currentPattern = partyBallPattern;
            nextBuffSet = false;
            for(TreatSmall t:treatSmalls){
                if(!t.isDeleted() && t.getRect().bottom < 0)
                    t.delete();
            }
            for(TreatBad t:treatBads){
                if(!t.isDeleted() && t.getRect().bottom < 0)
                    t.delete();
            }
            for(TreatGiant t:treatGiants){
                if(!t.isDeleted() && t.getRect().bottom < 0)
                    t.delete();
            }
        }
        else {
            if (currentPattern.isBad()) {
                currentPattern = treatPatterns.get(rand.nextInt(treatPatterns.size()));

            } else if(treatPatternsBad.size() > 0)
                currentPattern = treatPatternsBad.get(rand.nextInt(treatPatternsBad.size()));
            else
                currentPattern = treatPatterns.get(rand.nextInt(treatPatterns.size()));
        }

        currentPattern.setFoodTypes(treatsToDrop, targetTreat, targetTreatChance);
        if(canDropBuff && buff.isDeleted()) {
            if(rand.nextInt(100) < 50)
                currentPattern.setBuff(buff);
        }
        currentPattern.makePattern(treatSmalls, treatGiants, treatBad);


        //THIS IS FOR SAVING HIGHEST FOOD FOR PARCELABLE
        /*TreatSmall temp = currentPattern.getHighest();
        for(int i = 0; i < treatSmalls.length; i++){
            if(treatSmalls[i].equals(temp))
                highFoodIndex = i;
        }*/
    }
    public void setBuffDrop(TreatSmall buff){
        canDropBuff = true;
        this.buff = buff;
    }
    public void setCanMakePatterns(boolean b){canMakePatterns = b; }
    public void setBuffDropOff(){canDropBuff = false;}
    @Override
    public void notifyLevels(LevelSystem level) {
        treatsToDrop = level.getTreatsToDrop();
        targetTreat = level.getTargetTreat();
        targetTreatChance = level.getGoodTreatChance();
        currentPattern.setDone();
        partyBallSpawned = false;
        if(level.getLevel() == 1) {
            treatPatternsBad.add(new TreatPatternBadLine(treatFactory));
            treatPatternsBad.add(new TreatPatternBadLine2(treatFactory, rand));
            treatPatternsBad.add(new TreatPatternBadLine3(treatFactory, rand));

        }
        else if(level.getLevel() == 2){
            treatPatternsBad.add(new TreatPatternBadScattered(treatFactory, rand));
            treatPatternsBad.add(new TreatPatternBadHeart(treatFactory, rand));
            treatPatterns.add(treatPatterns.get(0));
            treatPatterns.add(treatPatterns.get(1));
        }
    }

    public boolean getPartyBallSpawned(){return partyBallSpawned;}

    public void setNextPatternParty(){
        nextBuffSet = true;
    }
    @Override
    public void notifyAreaChange() {

    }
}
