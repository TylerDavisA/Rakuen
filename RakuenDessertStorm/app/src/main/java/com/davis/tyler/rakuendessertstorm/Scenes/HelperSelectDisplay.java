package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.graphics.Canvas;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Coordinates;
import com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons.HelperIconPDog;
import com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons.HelperIconShigiLeeble;
import com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons.IHelperIcon;

public class HelperSelectDisplay {
    private IHelperIcon[] icons;
    private int curSelected;
    private int startX, startY;
    private int spaceX, spaceY;
    private int widthBox;
    private int heightBox;
    private int spaceWidth;
    private int paddingWidth;
    private int boxPlacementY;
    private Coordinates temp;

    public HelperSelectDisplay(Context context){
        curSelected = 0;// LATER YOU CAN RECALL WHAT WAS LAST CALLED BY LOOKING AT PREFERENCES
        paddingWidth = Constants.SCREEN_WIDTH /9;
        widthBox = Constants.SCREEN_WIDTH /9;
        spaceWidth = Constants.SCREEN_WIDTH /9;
        boxPlacementY = Constants.SCREEN_HEIGHT/3;
        temp = new Coordinates(paddingWidth, boxPlacementY);
        icons[0] = new HelperIconShigiLeeble(context, temp);
        temp = new Coordinates(paddingWidth+widthBox+spaceWidth, boxPlacementY);
        icons[2] = new HelperIconPDog(context, temp);

    }
    //ARRAY OF HELPER ICONS, EACH ICON TAKES CARE OF ITS DIMENSIONS AND COORDINATES AND BITMAPS;
    //methods, getCurSelected() *if none is selected pick shigi as default
    //successfulSelection(int x, int y) *checks to see if touch coordinates hit a rect from helper icons array
    //possibly later add a scroll section, max of 8 helpers shown on screen at a time?

    public void update(int elapsedTime){
        for(IHelperIcon helperIcon: icons)
            helperIcon.update(elapsedTime);
    }
    public void draw(Canvas canvas){
        for(IHelperIcon helperIcon: icons)
            helperIcon.draw(canvas);
    }
    public int getCurSelected(){
        return icons[curSelected].getType();
    }
    public boolean successfulSelection(int x, int y){
        for(int i = 0; i < icons.length; i++){
            if(icons[i].getRect().contains(x,y)) {
                selectBox(i);
                return true;
            }
        }
        return false;
    }

    private void selectBox(int i){
        for(int x = 0; x < icons.length; x++)
            icons[i].deselect();
        icons[i].select();
    }




}
