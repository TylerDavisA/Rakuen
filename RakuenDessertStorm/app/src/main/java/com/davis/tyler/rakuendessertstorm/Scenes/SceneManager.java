package com.davis.tyler.rakuendessertstorm.Scenes;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.davis.tyler.rakuendessertstorm.Constants;
import com.davis.tyler.rakuendessertstorm.GamePanel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tadto on 3/9/2018.
 */

public class SceneManager implements Parcelable {
    //private ArrayList<IScene> scenes = new ArrayList<>();
    //public static int ACTIVE_SCENE;
    public static final int SCENE_TITLE = 0;
    public static final int SCENE_GAME = 1;

    public IScene curScene;
    private Context context;
    private RelativeLayout relativeLayout;

    public SceneManager(Context context, RelativeLayout relativeLayout){
        //TODO ONBACKPRESS CALL CURSCENE ONBACKPRESS WHICH WILL CHANGE CUR SCENE
        //ACTIVE_SCENE = 1;
        this.context = context;
        this.relativeLayout = relativeLayout;
        //scenes.add(new SceneGame(context, relativeLayout));
        //scenes.add(new SceneTitle(context, this));
        curScene = new SceneTitle(context, this);
    }
    public SceneManager(Parcel in){
        System.out.println("ENTERING PARCE CONSTRUCTOR: scene manager");
        String scene = in.readString();
        if(scene.equals("scenegame")){
            curScene = in.readParcelable(SceneGame.class.getClassLoader());

        }
        else if(scene.equals("scenetitle")){
            curScene = in.readParcelable(SceneTitle.class.getClassLoader());
        }

    }

    public void parcelableInit(Context context, RelativeLayout relativeLayout){
        this.context = context;
        this.relativeLayout = relativeLayout;
        curScene.parcelableInit(context, relativeLayout, this);


    }
    public void changeScene(int type){
        if(type == SCENE_TITLE)
            curScene = new SceneTitle(context, this);
        else if(type == SCENE_GAME)
            curScene = new SceneGame(context,relativeLayout,this);
    }
    public void setContext(Context context){this.context = context;}
    public void setRelativeLayout(RelativeLayout l){relativeLayout = l;}

    public void recieveTouch(MotionEvent event){
        curScene.receiveTouch(event);
        //scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }
    public void update(int elapsedTime){
        if(curScene != null)
        curScene.update(elapsedTime);
        //scenes.get(ACTIVE_SCENE).update(elapsedTime);
    }
    public void draw(Canvas canvas){
        if(curScene != null)
        curScene.draw(canvas);
        //scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public boolean onBackPressed(){
        if(curScene instanceof SceneTitle){
            return true;
        }
        else
            changeScene(SCENE_TITLE);
        return false;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        System.out.println("ENTERING WRITE TO PARCEL:  SCENEMANAGER");
        if(curScene instanceof SceneGame) {
            parcel.writeString("scenegame");
            parcel.writeParcelable((SceneGame)curScene, i);
        }
        else if(curScene instanceof SceneTitle){
            System.out.println("INSTANCE OF SCENE TITLE");
            parcel.writeString("scenetitle");
            parcel.writeParcelable((SceneTitle)curScene, i);

        }
    }

    public static final Parcelable.Creator<SceneManager> CREATOR = new Parcelable.Creator<SceneManager>(){
        public SceneManager createFromParcel(Parcel in){
            System.out.println("ENTERING CREATE FROM PARCEL:  SCENEMANAGER");
            return new SceneManager(in);
        }

        public SceneManager[] newArray(int size){
            return new SceneManager[size];
        }

    };
}
