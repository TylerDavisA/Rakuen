package com.davis.tyler.rakuendessertstorm;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 3/9/2018.
 */

public class Constants implements Parcelable{
    public static int SCREEN_WIDTH; //2560
    public static int SCREEN_HEIGHT; // 1440
    public static int PLAYER_WIDTH;
    private int screenWidth;
    private int screenHeight;
    private int playerWidth;


    public Constants(int screenWidth, int screenHeight,  int playerWidth ){
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.playerWidth = playerWidth;
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
        PLAYER_WIDTH = playerWidth;
    }
    public Constants(Parcel in){
        screenWidth = in.readInt();
        screenHeight = in.readInt();
        playerWidth = in.readInt();
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHeight;
        PLAYER_WIDTH = playerWidth;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(screenWidth);
        parcel.writeInt(screenHeight);
        parcel.writeInt(playerWidth);

    }

    public static final Parcelable.Creator<TreatSmall> CREATOR = new Parcelable.Creator<TreatSmall>(){
        public TreatSmall createFromParcel(Parcel in){
            return new TreatSmall(in);
        }

        public TreatSmall[] newArray(int size){
            return new TreatSmall[size];
        }

    };
}
