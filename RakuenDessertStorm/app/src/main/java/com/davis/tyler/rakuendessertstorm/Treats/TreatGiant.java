package com.davis.tyler.rakuendessertstorm.Treats;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.ObjectMove.IObjectMove;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

/**
 * Created by Tadto on 3/9/2018.
 */

public class TreatGiant implements Treat, Parcelable {
    private Coordinates location;
    private IObjectMove move;
    private ITreatTypeGood type;
    private ObjectMoveFactory moveFactory;
    private Rect rect;
    private boolean deleted;
    private int typeValue;

    public TreatGiant(ObjectMoveFactory moveFactory, Coordinates location){
        this.moveFactory = moveFactory;
        this.location = location;
        rect = new Rect();
        deleted = true;
        move = moveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL);
    }
    public TreatGiant(Parcel in){

        rect = new Rect();
        location = in.readParcelable(Coordinates.class.getClassLoader());
        deleted = false;
        if(in.readInt() == 1)
            deleted = true;
        typeValue = in.readInt();
    }
    public void parcelableInit(TreatFactory treatFactory, ObjectMoveFactory objectMoveFactory){
        this.moveFactory = objectMoveFactory;
        type = treatFactory.getTreatSmall(typeValue);
        //GET MOVE FACTORY, MAKE NEW TREAT TYPE
    }
    public void setMove(IObjectMove objMove){move = objMove;}

    public void draw(Canvas canvas){canvas.drawBitmap(type.getBitmap(), null, rect, null);}

    public void update(int elapsedTime){
        move.move(location, elapsedTime);
        rect.set( location.x - type.getMidWidth() ,  location.y - type.getMidHeight() ,
                type.getMidWidth() + location.x, type.getMidHeight() + location.y);
    }
    public Coordinates getCoordinates(){return location;}
    public int getPoints(){return type.getPoints();}
    public Rect getRect(){return rect;}
    public int getRectTop(){return location.y - type.getMidHeight();}

    @Override
    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public void reinit(ITreatTypeGood type, int x, int y) {
        typeValue = type.getType();
        this.type = type;
        location.x = x;
        location.y = y;
        move = moveFactory.getObjectMove(ObjectMoveFactory.TREAT_GIANT_FALL);
        rect.set( location.x - type.getMidWidth() ,  location.y - type.getMidHeight() ,
                type.getMidWidth() + location.x, type.getMidHeight() + location.y);
        deleted = false;
    }

    @Override
    public void delete() {
        deleted = true;
    }

    public ITreatTypeGood getType(){return type;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeParcelable(location, i);
        if(deleted)
            parcel.writeInt(1);
        else
            parcel.writeInt(0);
        parcel.writeInt(typeValue);



    }
    @Override
    public IObjectMove getMove() {
        return move;
    }
    public static final Parcelable.Creator<TreatGiant> CREATOR = new Parcelable.Creator<TreatGiant>(){
        public TreatGiant createFromParcel(Parcel in){
            return new TreatGiant(in);
        }

        public TreatGiant[] newArray(int size){
            return new TreatGiant[size];
        }

    };
}
