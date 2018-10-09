package com.davis.tyler.rakuendessertstorm.Treats;

import android.content.Context;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.Levels.LevelSystem;
import com.davis.tyler.rakuendessertstorm.ObjectMove.ObjectMoveFactory;
import com.davis.tyler.rakuendessertstorm.Subscriber.ISubscriber;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.ITreatTypeGood;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodBuffPartyBall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCabbage;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCakeCheeseSwirl;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCakeKiwiStrawberry;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCakePastelRainbow;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodChocoBananaSandwich;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodChocolateCake;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeCinnamon;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeMintChocolate;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakePinkYellow;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeRedVelvet;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeSmore;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeSundae;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutBlueSprinkles;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutChocolate;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutBerryJam;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutCereal;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutHalf;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutJellyChocolate;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutLightBlue;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutSilaBerry;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutWhiteNuts;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodGiantDonut;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodGiantMatchaParfait;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodGiantMintChip;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodBuffMagnet;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMatchaCake;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMatchaCakeRound;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMintChipSandwich;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeCherry;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMousseTripleChocolate;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMousseTripleLemon;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMousseTripleRaspberry;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodMousseTripleStrawberry;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodPartyBall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodReadBeanHeart;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodRedBeanCake;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodRedVelvetCake;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutSmall;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodDonutSprinkled;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodStrawberryCake;
import com.davis.tyler.rakuendessertstorm.Treats.TreatTypes.TreatTypeGoodCupcakeVanilla;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Tadto on 3/10/2018.
 */

public class TreatFactory implements ISubscriber{

    private HashMap<Integer,ITreatTypeGood> treatsSmall;
    private HashMap<Integer,ITreatTypeGood> treatsGiant;
    private HashMap<Integer,ITreatTypeBad> treatsBad;

    private Random rand;
    private int[] starClusterX, starClusterY, heartClusterX, heartClusterY;
    private int enemyType;
    private ObjectMoveFactory objectMoveFactory;
    private Context context;
    private ITreatTypeGood treatObj, temp;
    private ITreatTypeBad tempBadType;
    private TreatBad tempBad;
    private int startX, startY;
    private TreatSmall treatSmall;
    private int[] treatsToDropDonut;
    private int targetTreatChance = 0;
    private int targetTreat = 0;

    public TreatFactory(Context context, Random rand, ObjectMoveFactory objectMoveFactory){
        this.context = context;
        this.rand = rand;
        this.objectMoveFactory = objectMoveFactory;

        treatsSmall = new HashMap<>();
        treatsGiant = new HashMap<>();
        treatsBad = new HashMap<>();

        starClusterX = new int[10];
        starClusterY = new int[10];
        heartClusterX = new int[16];
        heartClusterY = new int[16];

        fillToDropArrays();

        fillStarCluster();
        fillHeartCluster();
    }

    public void spawnGiantTreatPattern(TreatGiant[] listTreatsGiant, int result, int startX, int startY) {
        ITreatTypeGood temp = getTreatGiant(result);
        TreatGiant treatGiant = findFirstAvailableGiant(listTreatsGiant);
        if(treatGiant != null) {
            treatGiant.reinit(getTreatGiant(result), startX, startY);
            treatGiant.setMove(objectMoveFactory.getObjectMove(ObjectMoveFactory.TREAT_FALL));
        }
    }
    public ITreatTypeGood getTreatGiant(int type){
        treatObj = treatsGiant.get(type);
        if(treatObj == null){
            if(type == Treat.GIANT_DONUT)
                treatObj = new TreatTypeGoodGiantDonut(context);
            else if(type == Treat.GIANT_MATCHA_PARFAIT)
                treatObj = new TreatTypeGoodGiantMatchaParfait(context);
            else if(type == Treat.GIANT_MINT_CHIP)
                treatObj = new TreatTypeGoodGiantMintChip(context);

            treatsGiant.put(type, treatObj);
        }

        return treatObj;
    }

    public ITreatTypeBad getTreatBad(int type){
        ITreatTypeBad treatObj = treatsBad.get(type);
        if(treatObj == null){
            if(type == Treat.BAD_SHROOM)
                treatObj = new TreatTypeBadShroom(context);

            treatsBad.put(type, treatObj);
        }

        return treatObj;
    }

    //Flyweight
    public ITreatTypeGood getTreatSmall(int type){
        treatObj = treatsSmall.get(type);
        if(treatObj == null){
            if(type == Treat.CUPCAKE_CHERRY)
                treatObj = new TreatTypeGoodCupcakeCherry(context);
            else if(type == Treat.CUPCAKE_VANILLA)
                treatObj = new TreatTypeGoodCupcakeVanilla(context);
            else if(type == Treat.CUPCAKE_SUNDAE)
                treatObj = new TreatTypeGoodCupcakeSundae(context);
            else if(type == Treat.SMALL_DONUT)
                treatObj = new TreatTypeGoodDonutSmall(context);
            else if(type == Treat.DONUT_PINK_SPRINKLES)
                treatObj = new TreatTypeGoodDonutSprinkled(context);
            else if(type == Treat.DONUT_CHOCOLATE)
                treatObj = new TreatTypeGoodDonutChocolate(context);
            else if(type == Treat.MATCHA_CAKE)
                treatObj = new TreatTypeGoodMatchaCake(context);
            else if(type == Treat.STRAWBERRY_CAKE)
                treatObj = new TreatTypeGoodStrawberryCake(context);
            else if(type == Treat.CHOCOLATE_CAKE)
                treatObj = new TreatTypeGoodChocolateCake(context);
            else if(type == Treat.CHOCO_BANANA_SANDWICH)
                treatObj = new TreatTypeGoodChocoBananaSandwich(context);
            else if(type == Treat.RED_BEAN_HEART)
                treatObj = new TreatTypeGoodReadBeanHeart(context);
            else if(type == Treat.RED_BEAN_CAKE)
                treatObj = new TreatTypeGoodRedBeanCake(context);
            else if(type == Treat.MINT_CHIP_SANDWICH)
                treatObj = new TreatTypeGoodMintChipSandwich(context);
            else if(type == Treat.RED_VELVET_CAKE)
                treatObj = new TreatTypeGoodRedVelvetCake(context);
            else if(type == Treat.MATCHA_CAKE_ROUND)
                treatObj = new TreatTypeGoodMatchaCakeRound(context);
            else if(type == Treat.DONUT_WHITE_NUTS)
                treatObj = new TreatTypeGoodDonutWhiteNuts(context);
            //else if(type == Treat.DONUT_RED_POWDER)
              //  treatObj = new TreatTypeGoodDonutRedPowder(context);
            else if(type == Treat.DONUT_LIGHT_BLUE)
                treatObj = new TreatTypeGoodDonutLightBlue(context);
            else if(type == Treat.DONUT_JELLY_CHOCOLATE)
                treatObj = new TreatTypeGoodDonutJellyChocolate(context);
            else if(type == Treat.DONUT_HALF)
                treatObj = new TreatTypeGoodDonutHalf(context);
            //else if(type == Treat.DONUT_CHOCOLATE_WHITE)
              //treatObj = new TreatTypeGoodDonutChocolateWhite(context);
            else if(type == Treat.DONUT_CEREAL)
                treatObj = new TreatTypeGoodDonutCereal(context);
            //else if(type == Treat.DONUT_CARAMEL)
                //treatObj = new TreatTypeGoodDonutCaramel(context);
            else if(type == Treat.DONUT_BERRY_JAM)
                treatObj = new TreatTypeGoodDonutBerryJam(context);
            else if(type == Treat.CUPCAKE_SMORE)
                treatObj = new TreatTypeGoodCupcakeSmore(context);
            else if(type == Treat.CUPCAKE_RED_VELVET)
                treatObj = new TreatTypeGoodCupcakeRedVelvet(context);
            else if(type == Treat.CUPCAKE_PINK_YELLOW)
                treatObj = new TreatTypeGoodCupcakePinkYellow(context);
            else if(type == Treat.CUPCAKE_MINT_CHOCOLATE)
                treatObj = new TreatTypeGoodCupcakeMintChocolate(context);
            else if(type == Treat.CUPCAKE_CINNAMON)
                treatObj = new TreatTypeGoodCupcakeCinnamon(context);
            else if(type == Treat.BUFF_MAGNET)
                treatObj = new TreatTypeGoodBuffMagnet(context);
            else if(type == Treat.BUFF_PARTY_BALL)
                treatObj = new TreatTypeGoodBuffPartyBall(context);
            else if(type == Treat.PARTY_BALL)
                treatObj = new TreatTypeGoodPartyBall(context);
            else if(type == Treat.DONUT_SILA_BERRY)
                treatObj = new TreatTypeGoodDonutSilaBerry(context);
            //else if(type == Treat.DONUT_PURPLE_FLOWER)
              //  treatObj = new TreatTypeGoodDonutPurpleFlower(context);
            else if(type == Treat.DONUT_BLUE_SPRINKLES)
                treatObj = new TreatTypeGoodDonutBlueSprinkles(context);
            //else if(type == Treat.DONUT_MINT_TRI)
              ///  treatObj = new TreatTypeGoodDonutMintTri(context);
            else if(type == Treat.MOUSSE_TRIPLE_CHOCOLATE)
                treatObj = new TreatTypeGoodMousseTripleChocolate(context);
            else if(type == Treat.CAKE_CHEESE_SWIRL)
                treatObj = new TreatTypeGoodCakeCheeseSwirl(context);
            else if(type == Treat.CAKE_PASTEL_RAINBOW)
                treatObj = new TreatTypeGoodCakePastelRainbow(context);
            else if(type == Treat.MOUSSE_TRIPLE_RASPBERRY)
                treatObj = new TreatTypeGoodMousseTripleRaspberry(context);
            else if(type == Treat.CAKE_KIWI_STRAWBERRY)
                treatObj = new TreatTypeGoodCakeKiwiStrawberry(context);
            else if(type == Treat.MOUSSE_TRIPLE_LEMON)
                treatObj = new TreatTypeGoodMousseTripleLemon(context);
            else if(type == Treat.MOUSSE_TRIPLE_STRAWBERRY)
                treatObj = new TreatTypeGoodMousseTripleStrawberry(context);
            else if(type == Treat.CABBAGE)
                treatObj = new TreatTypeGoodCabbage(context);


            treatsSmall.put(type, treatObj);
        }

        return treatObj;
    }
    public void spawnBadTreat(int startX,int startY, TreatBad[] treatBad){
        tempBadType = getTreatBad(enemyType);
        tempBad = findFirstAvailableBad(treatBad);
        if( tempBad != null) {
            //SET POSSIBLE BAD-FOOD MOVEOBJECT FOR SLOWER/FASTER SPEED
            tempBad.reinit(tempBadType, startX, startY);
        }
    }
    public void spawnSmallTreatByNumber(int startX, int startY, int type, TreatSmall[] treatSmalls){
        temp = getTreatSmall(type);
        treatSmall = findFirstAvailableSmall(treatSmalls);
        if( treatSmall != null)
            treatSmall.reinit(temp,startX, startY);

    }
    public void spawnSmallTreatRandom(int type, TreatSmall[] treatSmalls){
        startX = rand.nextInt(Constants.SCREEN_WIDTH/3 * 2)+ Constants.SCREEN_WIDTH/6;
        startY = -getTreatSmall(type).getHeight();
        temp = getTreatSmall(type);
        treatSmall = findFirstAvailableSmall(treatSmalls);
        if( treatSmall != null)
            treatSmall.reinit(temp,startX, startY);

    }
    public void spawnTreatHeart(int[] type, int startX, int startY, TreatSmall[] treatSmalls){
        for (int i = 0; i < heartClusterX.length; i++) {
            if(rand.nextInt(100) < targetTreatChance)
            spawnSmallTreatByNumber(startX + heartClusterX[i], heartClusterY[i] + startY,
                    targetTreat, treatSmalls);
            else
                spawnSmallTreatByNumber(startX + heartClusterX[i], heartClusterY[i] + startY,
                        type[rand.nextInt(type.length)], treatSmalls);
        }
    }

    public void spawnTreatStar(int[] type, int startX, int startY, TreatSmall[] treatSmalls){
        for (int i = 0; i < starClusterX.length; i++) {
            if(rand.nextInt(100) < targetTreatChance)
                spawnSmallTreatByNumber(startX + starClusterX[i], starClusterY[i] + startY,
                        targetTreat, treatSmalls);
            else
                spawnSmallTreatByNumber(startX + starClusterX[i], starClusterY[i] + startY,
                    type[rand.nextInt(type.length)], treatSmalls);
        }
    }
    public void spawnExplodedTreatsPartyBall(TreatSmall[] treatSmalls, int[] treatsToDrop, int tapScore){
        tapScore = tapScore * 4;
        if(tapScore > 60)
            tapScore = 60;
        if(tapScore < 20)
            tapScore = 20;
        startX = Constants.SCREEN_WIDTH/2;
        startY = Constants.SCREEN_HEIGHT/2;
        int numTypes;
        TreatSmall temp;
        int i, j;
            numTypes = treatsToDrop.length;
            tapScore = tapScore / numTypes;
            for(i = 0; i < treatsToDrop.length; i++){
                for(j = 0; j < tapScore; j++){
                    temp = findFirstAvailableSmall(treatSmalls);
                    if( temp != null) {
                        temp.reinit(getTreatSmall(treatsToDrop[i]), startX, startY);
                        temp.setMove(objectMoveFactory.objectMoveTreatExplodeLarge(temp));
                    }
                }
            }
    }
    public void spawnExplodedTreats(Treat treatGiant, TreatSmall[] treatSmalls){
        int amtSpawned = 15;
        int type = treatGiant.getType().getType();
        TreatSmall temp;
        int[] toDrop = null;

        if (type == Treat.GIANT_DONUT)
            toDrop = treatsToDropDonut;

        startX = treatGiant.getCoordinates().x;
        startY = treatGiant.getCoordinates().y;
        int i, j;

        for(i = 0; i < toDrop.length; i++){
            for(j = 0; j < amtSpawned/toDrop.length; j++){
                temp = findFirstAvailableSmall(treatSmalls);
                if( temp != null) {
                    if(rand.nextInt(100) < targetTreatChance)
                        temp.reinit(getTreatSmall(targetTreat), startX, startY);
                    else
                        temp.reinit(getTreatSmall(toDrop[i]), startX, startY);
                    temp.setMove(objectMoveFactory.objectMoveTreatExplode(temp));
                }
            }
        }
    }

    public void spawnExplodedTreatsMini(Treat treat, TreatSmall[] treatSmalls){
        Coordinates coords = treat.getCoordinates();
        TreatSmall temp;
        for (int i = 0; i < 4; i++){
            temp = findFirstAvailableSmall(treatSmalls);
            if( temp != null) {
                temp.reinit(getTreatSmall(Treat.RED_BEAN_HEART), coords.x, coords.y);
                temp.setMove(objectMoveFactory.objectMoveTreatExplodeMini(temp));
            }
        }
    }
    private void fillToDropArrays(){
        treatsToDropDonut = new int[6];
        treatsToDropDonut[0] = Treat.SMALL_DONUT;
        treatsToDropDonut[1] = Treat.DONUT_BERRY_JAM;
        treatsToDropDonut[2] = Treat.DONUT_HALF;
        treatsToDropDonut[3] = Treat.DONUT_JELLY_CHOCOLATE;
        treatsToDropDonut[4] = Treat.DONUT_LIGHT_BLUE;
        treatsToDropDonut[5] = Treat.DONUT_CHOCOLATE;
    }
    private void fillStarCluster(){

        int screenHeightUnit = Constants.SCREEN_HEIGHT /100;
        int screenWidthUnit = Constants.SCREEN_WIDTH/100;

        starClusterX[0] = screenWidthUnit * 3;
        starClusterX[1] = screenWidthUnit * 5;
        starClusterX[2] = screenWidthUnit * -3;
        starClusterX[3] = screenWidthUnit * 7;
        starClusterX[4] = screenWidthUnit * 10;
        starClusterX[5] = screenWidthUnit * 15;
        starClusterX[6] = screenWidthUnit * 24;
        starClusterX[7] = screenWidthUnit * 17;
        starClusterX[8] = screenWidthUnit * 18;
        starClusterX[9] = screenWidthUnit * 10;

        starClusterY[0] = screenHeightUnit * 42;
        starClusterY[1] = screenHeightUnit * 27;
        starClusterY[2] = screenHeightUnit * 17;
        starClusterY[3] = screenHeightUnit * 15;
        starClusterY[4] = screenHeightUnit * 0;
        starClusterY[5] = screenHeightUnit * 15;
        starClusterY[6] = screenHeightUnit * 17;
        starClusterY[7] = screenHeightUnit * 27;
        starClusterY[8] = screenHeightUnit * 42;
        starClusterY[9] = screenHeightUnit * 34;

    }

    private void fillHeartCluster(){
        int screenHeightUnit = - Constants.SCREEN_HEIGHT /100;

        heartClusterX[15] = screenHeightUnit * -9;
        heartClusterX[14] = screenHeightUnit * -17;
        heartClusterX[13] = screenHeightUnit * -23;
        heartClusterX[12] = screenHeightUnit * -26;
        heartClusterX[11] = screenHeightUnit * -22;
        heartClusterX[10] = screenHeightUnit * -13;
        heartClusterX[9] = screenHeightUnit * -3;
        heartClusterX[8] = screenHeightUnit * 0;
        heartClusterX[7] = screenHeightUnit * 3;
        heartClusterX[6] = screenHeightUnit * 13;
        heartClusterX[5] = screenHeightUnit * 22;
        heartClusterX[4] = screenHeightUnit * 26;
        heartClusterX[3] = screenHeightUnit * 23;
        heartClusterX[2] = screenHeightUnit * 17;
        heartClusterX[1] = screenHeightUnit * 9;
        heartClusterX[0] = screenHeightUnit * 0;


        heartClusterY[15] = screenHeightUnit * -15;
        heartClusterY[14] = screenHeightUnit * -7;
        heartClusterY[13] = screenHeightUnit * 0;
        heartClusterY[12] = screenHeightUnit * 8;
        heartClusterY[11] = screenHeightUnit * 17;
        heartClusterY[10] = screenHeightUnit * 22;
        heartClusterY[9] = screenHeightUnit * 18;
        heartClusterY[8] = screenHeightUnit * 11;
        heartClusterY[7] = screenHeightUnit * 18;
        heartClusterY[6] = screenHeightUnit * 22;
        heartClusterY[5] = screenHeightUnit * 17;
        heartClusterY[4] = screenHeightUnit * 8;
        heartClusterY[3] = screenHeightUnit * 0;
        heartClusterY[2] = screenHeightUnit * -7;
        heartClusterY[1] = screenHeightUnit * -15;
        heartClusterY[0] = screenHeightUnit * -25;
    }
    @Override
    public void notifyLevels(LevelSystem level) {
        //treatsSmall.clear();
        enemyType = level.getBadTreatValue();
        targetTreatChance = level.getGoodTreatChance();
        targetTreat = level.getTargetTreat();
    }

    @Override
    public void notifyAreaChange() {
    }

    public TreatSmall findFirstAvailableSmall(TreatSmall[] listTreats){
        for(TreatSmall t : listTreats){
            if(t.isDeleted())
                return t;
        }
        return null;
    }
    public TreatBad findFirstAvailableBad(TreatBad[] listTreats){
        for(TreatBad t : listTreats){
            if(t.isDeleted())
                return t;
        }
        return null;
    }

    private TreatGiant findFirstAvailableGiant(TreatGiant[] listTreatsGiant){
        for(TreatGiant t : listTreatsGiant){
            if(t.isDeleted())
                return t;
        }
        return null;
    }
}
