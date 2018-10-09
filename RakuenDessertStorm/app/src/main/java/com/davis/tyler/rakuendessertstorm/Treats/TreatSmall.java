package com.davis.tyler.rakuendessertstorm.Treats;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.ObjectMove.IObjectMove;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveTreatExplode;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveTreatFall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

/**
 * Created by Tadto on 3/9/2018.
 */

public class TreatSmall implements Treat, Parcelable{
    private Coordinates location;
    private IObjectMove move, moveBuffer;
    private ITreatTypeGood type;
    private ObjectMoveFactory moveFactory;
    private Rect rect;
    private boolean deleted;
    private int typeValue;
    private float sizeMultiplier;

    public TreatSmall(ObjectMoveFactory moveFactory, Coordinates location){
        this.moveFactory = moveFactory;
        this.location = location;
        moveBuffer = null;
        rect = new Rect();
        deleted = true;
        sizeMultiplier = 1;
    }

    public TreatSmall(Parcel in){
        typeValue = in.readInt();
        location = in.readParcelable(Coordinates.class.getClassLoader());
        rect = new Rect();
        String temp =  in.readString();
        if(temp.equals("explode")){
            move = new ObjectMoveTreatExplode(this, in.readDouble());
        }
        if(temp.equals("fall")){
            move = new ObjectMoveTreatFall(in.readFloat());
        }
        deleted = false;
        if(in.readInt() == 1){
            deleted = true;
        }
    }

    public void parcelableInit(TreatFactory treatFactory, ObjectMoveFactory objectMoveFactory){
        //RESET OBJMOVEFACTORY
        moveFactory = objectMoveFactory;
        type = treatFactory.getTreatSmall(typeValue);
    }
    public void setMove(IObjectMove objMove){
        if((move instanceof ObjectMoveTreatExplode)){
            moveBuffer = objMove;
        }
        else
            move = objMove;
    }

    @Override
    public IObjectMove getMove() {
        return move;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(type.getBitmap(), null, rect, null);
    }

    public void update(int elapsedTime){
        move.move(location, elapsedTime);
        //sizeMultiplier = .5f;
        rect.set( (int)((location.x - type.getMidWidth()* sizeMultiplier) ) ,  (int)((location.y - type.getMidHeight()*sizeMultiplier))
                , (int)((type.getMidWidth()*sizeMultiplier + location.x))
                , (int)((type.getMidHeight()*sizeMultiplier + location.y)));
    }
    public Coordinates getCoordinates(){return location;}


    public void setType(ITreatTypeGood type){
        this.type = type;
    }
    public void doneExploding(){// If there is a move in buffer, switch to it, else, treat falls down;
        if(moveBuffer != null){
            move = moveBuffer;
            moveBuffer = null;
        }
        else
            move = moveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL);
    }
    public int getPoints(){return type.getPoints();}
    public Rect getRect(){return rect;}
    public int getRectTop(){return location.y - type.getMidHeight();}

    public void setSize(float multiplier){
        sizeMultiplier = multiplier;
    }
    public void resetSize(){
        sizeMultiplier = 1;
    }
    public void reinit(ITreatTypeGood type, int x, int y){
        this.type = type;
        typeValue = type.getType();
        location.x = x;
        location.y = y;
        move = moveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL);
        moveBuffer = null;
        rect.set( location.x - type.getMidWidth() ,  location.y - type.getMidHeight() ,
                type.getMidWidth() + location.x, type.getMidHeight() + location.y);
        deleted = false;
        resetSize();
    }
    public void delete(){deleted = true;}

    @Override
    public ITreatTypeGood getType() {
        return type;
    }

    public boolean isDeleted(){return deleted;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        //System.out.println("ENTERING WRITE TO PARCEL:  TREATSMALL");

        parcel.writeInt(typeValue);
        parcel.writeParcelable(location, i);
        if(move instanceof ObjectMoveTreatExplode){
            parcel.writeString("explode");
            parcel.writeDouble(((ObjectMoveTreatExplode) move).getDist());
        }
        else if(move instanceof ObjectMoveTreatFall){
            parcel.writeString("fall");
            parcel.writeFloat(((ObjectMoveTreatFall) move).getSpeed());
        }
        if(deleted)
            parcel.writeInt(1);
        else
            parcel.writeInt(0);

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
