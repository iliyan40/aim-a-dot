package com.iliyan.main.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.WIDTH;

public abstract class State {
    public static OrthographicCamera camera;
     protected Viewport viewPort;
     protected static Vector3 pointer;
     public static GameStateManager gms;
     public static boolean soundOn;
     public static boolean graphicsOn;
     public static boolean musicOn;
     public static int highScore;
     public static int money;
     public static String unlockedShapesToString;
     public static String unlockedColorsToString;
     public static int selectedShape;
     public static int selectedColor;
     protected State(GameStateManager gms){
         this.gms = gms;
         this.camera = new OrthographicCamera(WIDTH,HEIGHT);
         viewPort = new FitViewport(WIDTH,HEIGHT,camera);
         viewPort.apply();
         this.pointer = new Vector3();
     }
     protected abstract void handleInput();
     public abstract void update(float deltaTime);
     public abstract void render(SpriteBatch spriteBatch);
     public abstract void dispose();

}
