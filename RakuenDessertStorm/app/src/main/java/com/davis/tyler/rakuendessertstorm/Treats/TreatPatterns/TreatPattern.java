package com.davis.tyler.rakuendessertstorm.Treats.TreatPatterns;

import com.davis.tyler.rakuendessertstorm.Treats.Treat;
import com.davis.tyler.rakuendessertstorm.Treats.TreatBad;
import com.davis.tyler.rakuendessertstorm.Treats.TreatGiant;
import com.davis.tyler.rakuendessertstorm.Treats.TreatSmall;

/**
 * Created by Tadto on 3/21/2018.
 */

public interface TreatPattern {
    public static final int RANDOM = 0;
    public static final int STARS = 1;
    public static final int X = 2;
    public static final int CRISS_CROSS = 3;
    public static final int BAD_SCATTERED = 4;
    public static final int BAD_LINE = 5;
    public static final int BAD_LINE2 = 6;
    public static final int GIANT_DONUT = 7;
    public static final int PARTY_BALL = 8;
    public static final int BAD_HEART = 9;
    public int getType();
    public boolean isDone();
    public void update();
    public void makePattern(TreatSmall[] treatSmalls, TreatGiant[] treatGiants, TreatBad[] treatBad);
    public void reset();
    public void setFoodTypes(int[] treats, int target, int chanceTargetTreat);
    public void setDone();
    public void setHighest(Treat treat);
    public Treat getHighest();
    public void setBuff(TreatSmall buff);
    public boolean isBad();
}
