package com.davis.tyler.rakuendessertstorm.MessageBoard;

import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;

import java.io.Serializable;

/**
 * Created by Tadto on 3/21/2018.
 */

public class MessageNode implements Parcelable {
    private int anim;
    private String msg;

    public MessageNode(int anim, String msg){
        this.anim = anim;
        this.msg = msg;
    }
    public MessageNode(Parcel in){
        anim = in.readInt();
        msg = in.readString();
    }
    public int getAnim(){return anim;}
    public String getMsg(){return msg;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(anim);
        parcel.writeString(msg);


    }

    public static final Parcelable.Creator<MessageNode> CREATOR = new Parcelable.Creator<MessageNode>(){
        public MessageNode createFromParcel(Parcel in){
            return new MessageNode(in);
        }

        public MessageNode[] newArray(int size){
            return new MessageNode[size];
        }

    };
}
