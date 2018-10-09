package com.davis.tyler.rakuendessertstorm.Sound;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.R;

import java.util.Random;

/**
 * Created by Tadto on 3/31/2018.
 */

public class SoundManager {
    private SoundPool soundPool;
    private int soundIds[] = new int[10];
    private Random rand;
    private int randomNumLast, randomNumCur;

    public SoundManager(Context context){
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundIds[0] = soundPool.load(context, R.raw.collect1, 1);
        soundIds[1] = soundPool.load(context, R.raw.collect2, 1);
        soundIds[2] = soundPool.load(context, R.raw.collect3, 1);
        soundIds[3] = soundPool.load(context, R.raw.collect4, 1);
        soundIds[4] = soundPool.load(context, R.raw.collect5, 1);
        rand = new Random();
        randomNumLast = 0;
    }

    public void smallTreatCollideSound(){

        randomNumCur = rand.nextInt(5);
        while(randomNumCur == randomNumLast){
            randomNumCur = rand.nextInt(5);
        }
        randomNumLast = randomNumCur;
        soundPool.play(soundIds[randomNumCur], 1, 1, 1, 0, 1);
    }
}
