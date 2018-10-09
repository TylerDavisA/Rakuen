package com.davis.tyler.rakuendessertstorm.Treats;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.ObjectMove.IObjectMove;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;

/**
 * Created by Tadto on 3/9/2018.
 */

public interface Treat {
    public static final int GIANT_DONUT = 0;
    public static final int GIANT_MATCHA_PARFAIT = 1;
    public static final int GIANT_MINT_CHIP = 2;
    public static final int CUPCAKE_CHERRY = 0;
    public static final int CUPCAKE_VANILLA = 1;
    public static final int CUPCAKE_SUNDAE = 2;
    public static final int SMALL_DONUT = 3;
    public static final int DONUT_PINK_SPRINKLES = 4;
    public static final int DONUT_CHOCOLATE = 5;
    public static final int MATCHA_CAKE = 6;
    public static final int STRAWBERRY_CAKE = 7;
    public static final int CHOCOLATE_CAKE = 8;
    public static final int CHOCO_BANANA_SANDWICH = 9;
    public static final int MINT_CHIP_SANDWICH = 10;
    public static final int RED_VELVET_CAKE = 11;
    public static final int MATCHA_CAKE_ROUND = 12;
    public static final int DONUT_WHITE_NUTS = 13;
    public static final int DONUT_BERRY_JAM = 14;
    //public static final int DONUT_CARAMEL = 15;
    public static final int DONUT_CEREAL = 15;
    //public static final int DONUT_CHOCOLATE_WHITE = 17;
    public static final int DONUT_HALF = 16;
    public static final int DONUT_JELLY_CHOCOLATE = 17;
    public static final int DONUT_LIGHT_BLUE = 18;
    //public static final int DONUT_RED_POWDER = 21;
    public static final int CUPCAKE_CINNAMON = 19;
    public static final int CUPCAKE_MINT_CHOCOLATE = 20;
    public static final int CUPCAKE_PINK_YELLOW = 21;
    public static final int CUPCAKE_RED_VELVET = 22;
    public static final int CUPCAKE_SMORE = 23;
    public static final int DONUT_BLUE_SPRINKLES = 24;
    //public static final int DONUT_MINT_TRI = 28;
    //public static final int DONUT_PURPLE_FLOWER = 29;
    public static final int CAKE_KIWI_STRAWBERRY = 25;
    public static final int DONUT_SILA_BERRY = 26;
    public static final int MOUSSE_TRIPLE_CHOCOLATE = 27;
    public static final int CAKE_PASTEL_RAINBOW = 28;
    public static final int CAKE_CHEESE_SWIRL = 29;
    public static final int MOUSSE_TRIPLE_RASPBERRY = 30;
    public static final int MOUSSE_TRIPLE_LEMON = 31;
    public static final int MOUSSE_TRIPLE_STRAWBERRY= 32;


    public static final int NUM_TREAT_TYPES = 33;
    public static final int NULL = 100;
    public static final int RED_BEAN_HEART = 101;
    public static final int RED_BEAN_CAKE = 102;
    public static final int BAD_SHROOM = 103;
    public static final int BUFF_MAGNET = 104;
    public static final int CABBAGE = 105;
    public static final int PARTY_BALL = 106;
    public static final int BUFF_PARTY_BALL = 107;



    public void setMove(IObjectMove objMove);
    public IObjectMove getMove();
    public void draw(Canvas canvas);
    public void update(int elapsedTime);
    public Coordinates getCoordinates();
    public int getPoints();
    public Rect getRect();
    public int getRectTop();
    public boolean isDeleted();
    public void reinit(ITreatTypeGood type, int x, int y);
    public void delete();
    public ITreatTypeGood getType();
}
