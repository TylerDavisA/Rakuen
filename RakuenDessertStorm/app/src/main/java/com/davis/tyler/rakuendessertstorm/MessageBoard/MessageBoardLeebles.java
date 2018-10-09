package com.davis.tyler.rakuendessertstorm.MessageBoard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.davis.tyler.rakuendessertstorm.Animation.Animation;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationFactory;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationManager;
import com.davis.tyler.rakuendessertstorm.Animation.AnimationShigiBounce;
import com.davis.tyler.rakuendessertstorm.Animation.IAnimation;
import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.StatsLevels;
import com.davis.tyler.rakuendessertstorm.R;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Tadto on 3/21/2018.
 */

public class MessageBoardLeebles implements IMessageBoard, Parcelable {
    public static final int SHIGI_TALK = 0;
    public static final int SHIGI_BOUNCE = 1;
    public static final int GUY_TALK = 2;
    public static final int PDOG_TALK = 3;

    private final int TIME_COUNT = 3000; // 3 seconds per message
    private final int SHIGI_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;
    private final int SHIGI_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;
    private int animWidth, animHeight;
    private MessageNode curMessage, bufferMessage;
    private AnimationManager animationManager;
    private Rect shigiRect;
    private int timer;
    private Paint paint;
    private float txtWidth,txtX, txtY, rectX, rectY;
    private Rect backgroundRect;
    private Bitmap backgroundBitmap;
    private boolean noAnim;

    public MessageBoardLeebles(AnimationFactory animationFactory) {
        animWidth = SHIGI_WIDTH;
        animHeight = SHIGI_HEIGHT;
        noAnim = false;
        timer = 0;
        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        backgroundRect = new Rect();
        backgroundRect.set(0, Constants.SCREEN_HEIGHT / 10 * 5 - Constants.PLAYER_WIDTH/2,
                Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10 * 5 + Constants.PLAYER_WIDTH/2);
        backgroundBitmap = animationFactory.getMessageBackground();
        shigiRect = new Rect(3 * Constants.SCREEN_WIDTH / 10 - Constants.PLAYER_WIDTH / 2, Constants.SCREEN_HEIGHT / 10 * 5 - Constants.PLAYER_WIDTH / 2,
                3 * Constants.SCREEN_WIDTH / 10 + Constants.PLAYER_WIDTH / 2, Constants.SCREEN_HEIGHT / 10 * 5 + Constants.PLAYER_WIDTH / 2);

        animationManager = new AnimationManager(new Animation[]{animationFactory.getAnimation(IAnimation.SHIGI_TALK)
                , animationFactory.getAnimation(IAnimation.SHIGI_BOUNCE)
                , animationFactory.getAnimation(IAnimation.GUY_TALK)
                , animationFactory.getAnimation(IAnimation.PDOG_CHEW)});

        curMessage = new MessageNode(GUY_TALK, "Collect the target treat shown above!");
        timer = TIME_COUNT;
    }

    public void parcelableInit(AnimationFactory animationFactory){
        animationManager.setBitmaps(animationFactory);
        shigiRect = new Rect(3 * Constants.SCREEN_WIDTH / 10 - Constants.PLAYER_WIDTH / 2, Constants.SCREEN_HEIGHT / 10 * 5 - Constants.PLAYER_WIDTH / 2,
                3 * Constants.SCREEN_WIDTH / 10 + Constants.PLAYER_WIDTH / 2, Constants.SCREEN_HEIGHT / 10 * 5 + Constants.PLAYER_WIDTH / 2);
    }
    public MessageBoardLeebles(Parcel in){
        paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);


        timer = in.readInt();
        animationManager = in.readParcelable(AnimationManager.class.getClassLoader());
        shigiRect = in.readParcelable(Rect.class.getClassLoader());
        curMessage = in.readParcelable(MessageNode.class.getClassLoader());
        bufferMessage = in.readParcelable(MessageNode.class.getClassLoader());

    }

    @Override
    public void update(int elapsedTime) {
        timer -= elapsedTime;
        if(timer < 0 && curMessage != null){
            noAnim = false;
            if(bufferMessage != null) {
                timer = TIME_COUNT;
                curMessage = bufferMessage;
                bufferMessage = null;
            }
            else
                curMessage = null;
        }
        if(timer > 0 && curMessage != null){
            txtWidth = paint.measureText(curMessage.getMsg());
            txtX = (Constants.SCREEN_WIDTH - txtWidth - shigiRect.width())/2;
            txtY = Constants.SCREEN_HEIGHT / 2 + 20;
            rectY = 2 * Constants.SCREEN_HEIGHT/5;
            rectX = txtX + txtWidth + 40;
            shigiRect.set((int)rectX, (int)rectY, (int)(rectX+ animWidth), (int)(rectY + animHeight));
            animationManager.playAnim(curMessage.getAnim());
            animationManager.update();
        }

    }

    @Override
    public void draw(Canvas canvas) {
        if(timer > 0 && curMessage != null){
            canvas.drawBitmap(backgroundBitmap, null, backgroundRect, paint);
            if(!noAnim)
                animationManager.draw(canvas, shigiRect);
            canvas.drawText(curMessage.getMsg(), txtX, txtY, paint);

        }
    }

    @Override
    public void queueMessage(String msg, int animation) {
        if(animation < 0  || bufferMessage != null)
            return ;
        if(curMessage == null){
            curMessage = new MessageNode(animation, msg);
            timer = TIME_COUNT;
        }
        else
            bufferMessage = new MessageNode(animation, msg);
    }

    @Override
    public void forceMessage(String msg, int animation,  int animWidth, int animHeight) {
        timer = TIME_COUNT;
        curMessage = new MessageNode(animation, msg);
        this.animWidth = animWidth;
        this.animHeight = animHeight;

    }

    @Override
    public void forceMessage(String msg, int animation) {
        timer = TIME_COUNT;
        curMessage = new MessageNode(animation, msg);
        this.animWidth = SHIGI_WIDTH;
        this.animHeight = SHIGI_HEIGHT;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {


        parcel.writeInt(timer);
        parcel.writeParcelable(animationManager, i);
        parcel.writeParcelable(curMessage, i);
        parcel.writeParcelable(bufferMessage, i);



    }

    public static final Parcelable.Creator<MessageBoardLeebles> CREATOR = new Parcelable.Creator<MessageBoardLeebles>(){
        public MessageBoardLeebles createFromParcel(Parcel in){
            return new MessageBoardLeebles(in);
        }

        public MessageBoardLeebles[] newArray(int size){
            return new MessageBoardLeebles[size];
        }

    };
}
