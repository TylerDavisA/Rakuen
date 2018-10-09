package com.davis.tyler.rakuendessertstorm.Animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

import java.util.HashMap;

/**
 * Created by Tadto on 3/24/2018.
 */

public class AnimationFactory {
    public final int HELPER_WIDTH = 11 * Constants.SCREEN_WIDTH / 100;
    public final int HELPER_HEIGHT = 12 * Constants.SCREEN_WIDTH / 100;
    public final int P_DOG_HEIGHT = HELPER_HEIGHT;
    public final int P_DOG_WIDTH = (int)(HELPER_HEIGHT * .6);
    public final int PLAYER_HEIGHT = 13 * Constants.SCREEN_WIDTH / 100;
    public final int SHIMEJI_WIDTH = 7 * Constants.SCREEN_WIDTH / 100;
    public final int SHIMEJI_HEIGHT = 8 * Constants.SCREEN_WIDTH / 100;

    public final int PLAYER_WIDTH = PLAYER_HEIGHT;
    private HashMap<Integer, Bitmap[]> animations;
    private Context context;

    public AnimationFactory(Context context){
        animations = new HashMap<>();
        this.context = context;
    }
    public Animation getAnimation(int type){
        if(type == IAnimation.SHIGI_BOUNCE){
            return new Animation(getFrames(type), 1.2f, type);
        }
        else if(type == IAnimation.SHIGI_WAVE){
            return new Animation(getFrames(type), .5f, type);
        }
        else if(type == IAnimation.SHIGI_TALK){
            return new Animation(getFrames(type), 1.2f, type);
        }
        else if(type == IAnimation.GUY_TALK){
            return new Animation(getFrames(type), 1.2f, type);
        }
        else if(type == IAnimation.LEEBLE_DANCE){
            return new Animation(getFrames(type), .8f, type);
        }
        else if(type == IAnimation.SHIMEJI_MARCH){
            return new Animation(getFrames(type), 1.2f, type);
        }
        else if(type == IAnimation.PDOG_CHEW){
            return new Animation(getFrames(type), 1.2f, type);
        }
        return new Animation(getFrames(0), 1.2f, type);
    }

    public Animation remakeAnimation(int type, int frameIndex){
        Animation temp = getAnimation(type);
        temp.setFrameIndex(frameIndex);
        return temp;
    }
    public Bitmap[] getFrames(int type){
        Bitmap[] anim = animations.get(type);
        if(anim == null){
            if(type == IAnimation.SHIGI_BOUNCE){
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultsmile1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultsmile2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], HELPER_WIDTH, HELPER_HEIGHT, true);
            }
            else if(type == IAnimation.SHIGI_WAVE){
                anim = new Bitmap[4];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultwave3);
                anim[2] = Bitmap.createScaledBitmap(anim[2], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[3] = anim[1];
            }
            else if(type == IAnimation.SHIGI_TALK){
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaulttalk1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaulttalk2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], HELPER_WIDTH, HELPER_HEIGHT, true);
            }
            else if(type == IAnimation.GUY_TALK){
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.guydefaulttalk1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], HELPER_WIDTH, HELPER_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.guydefaulttalk2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], HELPER_WIDTH, HELPER_HEIGHT, true);
            }
            else if(type == IAnimation.LEEBLE_DANCE){
                anim = new Bitmap[7];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance01);
                anim[0] = Bitmap.createScaledBitmap(anim[0], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance02);
                anim[1] = Bitmap.createScaledBitmap(anim[1], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance03);
                anim[2] = Bitmap.createScaledBitmap(anim[2], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance04);
                anim[3] = Bitmap.createScaledBitmap(anim[3], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance05);
                anim[4] = Bitmap.createScaledBitmap(anim[4], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance06);
                anim[5] = Bitmap.createScaledBitmap(anim[5], PLAYER_WIDTH, PLAYER_HEIGHT, true);
                anim[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.leebledance07);
                anim[6] = Bitmap.createScaledBitmap(anim[6], PLAYER_WIDTH, PLAYER_HEIGHT, true);
            }
            else if(type == IAnimation.SHIMEJI_MARCH){
                anim = new Bitmap[4];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shimeji1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], SHIMEJI_WIDTH, SHIMEJI_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shimeji2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], SHIMEJI_WIDTH, SHIMEJI_HEIGHT, true);
                anim[2] = anim[0];
                anim[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shimeji3);
                anim[3] = Bitmap.createScaledBitmap(anim[3], SHIMEJI_WIDTH, SHIMEJI_HEIGHT, true);
            }
            else if(type == IAnimation.PDOG_CHEW){
                anim = new Bitmap[2];
                anim[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pdogchew1);
                anim[0] = Bitmap.createScaledBitmap(anim[0], P_DOG_WIDTH, P_DOG_HEIGHT, true);
                anim[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pdogchew2);
                anim[1] = Bitmap.createScaledBitmap(anim[1], P_DOG_WIDTH, P_DOG_HEIGHT, true);
            }

            animations.put(type, anim);
        }

        return anim;
    }

    public Bitmap getMessageBackground(){
        Bitmap bitmap;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.darkrect);
        bitmap = Bitmap.createScaledBitmap(bitmap, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/10 * 3, true);
        return bitmap;
    }
    public void clear(int type){
        animations.remove(type);
    }
}
