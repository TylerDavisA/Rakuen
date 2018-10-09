package com.davis.tyler.rakuendessertstorm.Levels;

import com.davis.tyler.rakuendessertstorm.Animation.IAnimation;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.Treats.Treat;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Tadto on 6/9/2018.
 */

public class LevelSystem {
    private int targetTreat;
    private int[] treatsToDrop;
    private Random rand;
    private int level;
    private float[] treatDropSpeeds;
    private int[] goodTreatChance;
    private int[] amountToCollect;
    private final float SMALL_FALL_SPEED = Constants.SCREEN_HEIGHT/5000.0f;
    private MessageBoardLeebles messageBoardLeebles;

    public LevelSystem(MessageBoardLeebles messageBoardLeebles){
        this.messageBoardLeebles = messageBoardLeebles;
        initArrays();
        rand = new Random();
        treatsToDrop = new int[4];
        level = 0;
        setupLevel();
    }

    public void setupLevel(){
        for(int i = 0; i < 4; i++)
            treatsToDrop[i] = rand.nextInt(Treat.NUM_TREAT_TYPES);

        boolean isbad = true;
        while(isbad){
            isbad = false;
            targetTreat = rand.nextInt(Treat.NUM_TREAT_TYPES);
            for(int j = 0; j < 4; j++)
                if(targetTreat == treatsToDrop[j])
                    isbad = true;
        }
    }

    public int getLevel(){return level;}
    public int getTargetTreat(){return targetTreat;}
    public int[] getTreatsToDrop(){return treatsToDrop;}
    public float getTreatDropSpeed(){return treatDropSpeeds[level];}
    public int getGoodTreatChance(){return goodTreatChance[level];}
    public int getAmountToCollect(){return amountToCollect[level];}
    public void incrementLevel(){

        level++;

        if(level == 1)
            messageBoardLeebles.forceMessage("Watch out for the bad shrooms!", MessageBoardLeebles.SHIGI_BOUNCE);
        else{
            generateRandomMessage();
        }

    }
    private void generateRandomMessage(){
        int t = rand.nextInt(7);
        if(t == 0)
            messageBoardLeebles.forceMessage("Look at that leeble go!", MessageBoardLeebles.GUY_TALK);
        else if(t == 1)
            messageBoardLeebles.forceMessage("Where does he put all those treats?", MessageBoardLeebles.GUY_TALK);
        else if(t == 2)
            messageBoardLeebles.forceMessage("You're so awesome", MessageBoardLeebles.SHIGI_TALK);
        else if(t == 3)
            messageBoardLeebles.forceMessage("Keep an eye out for a gold coin", MessageBoardLeebles.SHIGI_TALK);
        else if(t == 4)
            messageBoardLeebles.forceMessage("Save some for me!", MessageBoardLeebles.SHIGI_TALK);
        else if(t == 5)
            messageBoardLeebles.forceMessage("Level up!", MessageBoardLeebles.SHIGI_BOUNCE);
        else if(t == 6)
            messageBoardLeebles.forceMessage("Way to go, buddy", MessageBoardLeebles.GUY_TALK);
        else if(t == 7)
            messageBoardLeebles.forceMessage("More sweet treats are coming <3", MessageBoardLeebles.SHIGI_BOUNCE);
    }
    public int getBadTreatValue(){return Treat.BAD_SHROOM;}
    public int getEnemyType() {
        return IAnimation.SHIMEJI_MARCH;
    }


    private void initArrays(){
        amountToCollect = new int[10];
        treatDropSpeeds = new float[10];
        goodTreatChance = new int[10];

        amountToCollect[0] = 3;
        amountToCollect[1] = 5;
        amountToCollect[2] = 10;
        amountToCollect[3] = 10;
        amountToCollect[4] = 15;
        amountToCollect[5] = 15;
        amountToCollect[6] = 15;
        amountToCollect[7] = 20;
        amountToCollect[8] = 20;
        amountToCollect[9] = 25;

        treatDropSpeeds[0] = SMALL_FALL_SPEED;
        treatDropSpeeds[1] = SMALL_FALL_SPEED * 1.1f;
        treatDropSpeeds[2] = SMALL_FALL_SPEED * 1.2f;
        treatDropSpeeds[3] = SMALL_FALL_SPEED * 1.3f;
        treatDropSpeeds[4] = SMALL_FALL_SPEED * 1.4f;
        treatDropSpeeds[5] = SMALL_FALL_SPEED * 1.5f;
        treatDropSpeeds[6] = SMALL_FALL_SPEED * 1.6f;
        treatDropSpeeds[7] = SMALL_FALL_SPEED * 1.7f;
        treatDropSpeeds[8] = SMALL_FALL_SPEED * 1.8f;
        treatDropSpeeds[9] = SMALL_FALL_SPEED * 1.9f;

        goodTreatChance[0] = 15;
        goodTreatChance[1] = 15;
        goodTreatChance[2] = 10;
        goodTreatChance[3] = 10;
        goodTreatChance[4] = 10;
        goodTreatChance[5] = 10;
        goodTreatChance[6] = 10;
        goodTreatChance[7] = 10;
        goodTreatChance[8] = 10;
        goodTreatChance[9] = 15;

    }
}
