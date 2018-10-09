package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePieces.Helpers.IHelper;
import com.davis.tyler.rakuendessertstorm.R;
import com.davis.tyler.rakuendessertstorm.Scenes.HelperIcons.IHelperIcon;

public class SceneHelperSelect implements IScene {
    //ARRAY OF BITMAPS, DISPLAYED AS A GRID, WHEN SELECTED, MAKE THE BITMAP BIGGER, WHEN PLAY BUTTON IS CLICKED,
    //SAVE CUR HELPER TO PREFERENCES, AND READ IN WHEN GAME IS CREATED
    //FOR EACH HELPERICON IN THE ARRAY, MAKE A COORDINATES, THIS CLASS DECIDES WHERE THE COORDINATES ARE
    //THIS CLASS ALSO DRAWS THE BACKDROP FOR EACH HELPER ICON. ACTUALLY PUT IT IN HELPERS DISPLAY

    //TODO MAKE METHOD TO HANDLE BUTTON PUSH, DECIDE WHERE TO PUT BUTTON.
    private Context context;
    private HelperSelectDisplay helperSelectDisplay;
    private Rect rect;
    private SceneManager sceneManager;
    private Paint paint;
    private int action, xPos, yPos, index;
    public SceneHelperSelect(Context context, SceneManager sceneManager){
        this.context = context;
        helperSelectDisplay = new HelperSelectDisplay(context);
        this.sceneManager = sceneManager;
        paint = new Paint();
    }


    @Override
    public void parcelableInit(Context context, RelativeLayout relativeLayout, SceneManager sceneManager) {
        this.context = context;
        this.sceneManager = sceneManager;
        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
        int height = Resources.getSystem().getDisplayMetrics().heightPixels;
        rect = new Rect(0,0,width, height);
    }
    @Override
    public void update(int elapsedTime) {
        helperSelectDisplay.update(elapsedTime);
    }

    @Override
    public void draw(Canvas canvas) {
        //canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        canvas.drawColor(Color.WHITE);
        //canvas.drawBitmap(, null, rect, null);
        helperSelectDisplay.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        action = event.getActionMasked();
        index = event.getActionIndex();
        xPos = (int) event.getX( index);
        yPos =(int) event.getY( index);

        switch (action){
            case MotionEvent.ACTION_DOWN:
                if(!helperSelectDisplay.successfulSelection(xPos, yPos));
                    //CALL METHOD TO SEE IF PLAY BUTTON IS PUSHED
                break;
        }
    }

}
