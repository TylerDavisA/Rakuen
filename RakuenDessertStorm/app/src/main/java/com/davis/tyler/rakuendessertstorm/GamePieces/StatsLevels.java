package com.davis.tyler.rakuendessertstorm.GamePieces;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.MessageBoard.MessageBoardLeebles;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscribed;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tadto on 3/17/2018.
 */

public class StatsLevels implements IStats, ISubscribed, Parcelable {
    private List<ISubscriber> subscribers;
    private int score;
    private int combo;
    private int helperScore;
    private ITreatTypeGood previousTreat;
    private int[] treatsCollected;
    private boolean multiplyScore;
    private int multiplier;
    private boolean gameOver;
    private LevelSystem levelSystem;
    private int targetTreatCounter;
    private MessageBoardLeebles messageBoardLeebles;

    public StatsLevels(MessageBoardLeebles messageBoardLeebles){
        this.messageBoardLeebles = messageBoardLeebles;
        gameOver = false;
        multiplier = 1;
        score = 0;
        helperScore = 0;
        multiplyScore = false;
        this.levelSystem = new LevelSystem(messageBoardLeebles);
        subscribers = new ArrayList<ISubscriber>();
        treatsCollected = new int[150];
        for(int i = 0; i < treatsCollected.length; i++)
            treatsCollected[i] = 0;
        targetTreatCounter = 0;
    }


    public StatsLevels(Parcel in){
        subscribers = new ArrayList<>();
        score = in.readInt();
    }
    public LevelSystem getLevelSystem(){return levelSystem;}
    @Override
    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void notifyLevelUp() {
        for(ISubscriber s : subscribers){
            s.notifyLevels(levelSystem);
        }
    }

    @Override
    public void incrementScore(int toAdd) {
        for(int i = 0; i < multiplier; i++)
            score += toAdd;

    }

    @Override
    public void incrementHelperScore(int toAdd) {
        for(int i = 0; i < multiplier; i++)
            helperScore += toAdd;
    }


    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public void incrementTargetTreat() {
        targetTreatCounter++;
        if(targetTreatCounter == levelSystem.getAmountToCollect()){
            levelSystem.incrementLevel();
            levelSystem.setupLevel();
            score = 0;
            targetTreatCounter = 0;
            notifyLevelUp();
        }
    }


    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void addToCombo(ITreatTypeGood type) {
        if(previousTreat == type){
            combo ++;
        }
        else{
            combo = 1;
        }
        previousTreat = type;
    }

    @Override
    public int getCombo() {
        return combo;
    }


    @Override
    public void resetCombo() {
        combo = 0;
    }

    @Override
    public void registerTreatCollected(ITreatTypeGood type) {
        treatsCollected[type.getType()] += 1;
    }

    @Override
    public int findMostCollectedType() {
        int index = 0;
        int most = 0;
        for(int i = 0; i < treatsCollected.length; i++){
            if(treatsCollected[i] > most){
                most = treatsCollected[i];
                index = i;
            }
        }
        return index;
    }

    @Override
    public int findNumberCollected(int type) {
        return treatsCollected[type];
    }

    @Override
    public void setDoublePoints(boolean b) {
        multiplyScore = b;
        if(multiplyScore == true){
            multiplier ++;
        }
        if(multiplyScore == false){
            multiplier = 1;
        }
    }

    @Override
    public int getHelperScore() {
        return helperScore;
    }

    @Override
    public boolean isMultiplier() {
        return multiplyScore;
    }

    @Override
    public int getPointMultiplier() {
        return multiplier;
    }

    @Override
    public void setGameOver() {
        gameOver = true;
    }

    @Override
    public int getLevel() {
        return levelSystem.getLevel();
    }

    @Override
    public int getTargetTreat() {
        return levelSystem.getTargetTreat();
    }

    @Override
    public int getTargetCount() {
        return targetTreatCounter;
    }

    @Override
    public int getTargetGoal() {
        return levelSystem.getAmountToCollect();
    }

    @Override
    public int[] getTreatsToDrop() {
        return levelSystem.getTreatsToDrop();
    }






    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(score);
    }

    public static final Parcelable.Creator<StatsLevels> CREATOR = new Parcelable.Creator<StatsLevels>(){
        public StatsLevels createFromParcel(Parcel in){
            return new StatsLevels(in);
        }

        public StatsLevels[] newArray(int size){
            return new StatsLevels[size];
        }

    };
}
