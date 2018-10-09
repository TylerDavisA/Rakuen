package com.davis.tyler.rakuendessertstorm.GamePieces;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Scenes.GameModeLevels;
import com.davis.tyler.rakuendessertstorm.Scenes.SceneGame;

import java.io.Serializable;

/**
 * Created by Tadto on 3/9/2018.
 */

public class Coordinates implements Parcelable {
    public int x, y;
    public float dx, dy;
    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Coordinates(Parcel in){

        x = in.readInt();
        y = in.readInt();
        dx = in.readFloat();
        dy = in.readFloat();

        System.out.println(this + "  x: "+x+" y: "+y+" dy: "+dy);
    }

    public void offset(int x, int y){
        this.x += x;
        this.y += y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(x);
        parcel.writeInt(y);
        parcel.writeFloat(dx);
        parcel.writeFloat(dy);


    }

    public static final Parcelable.Creator<Coordinates> CREATOR = new Parcelable.Creator<Coordinates>(){
        public Coordinates createFromParcel(Parcel in){
            return new Coordinates(in);
        }

        public Coordinates[] newArray(int size){
            return new Coordinates[size];
        }

    };
}
