package com.iliyan.main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iliyan.main.States.GameStateManager;
import com.iliyan.main.States.MenuStates.MainMenu.MainMenuState;
import com.iliyan.main.States.State;

public class Game extends com.badlogic.gdx.Game {
	public static final int WIDTH = 360;
	public static final int HEIGHT =748 ;
	public static final String TITLE = "Aim a dot";
	private GameStateManager gameStateManager;
	private SpriteBatch batch;
	public static Music music;
	public static Preferences prefs;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gameStateManager = new GameStateManager();
		Gdx.gl.glClearColor(0.11f, 0.11f, 0.11f, 1);
		music = Gdx.audio.newMusic(Gdx.files.internal("Sound/aSong.wav"));
		music.setLooping(true);
		music.setVolume(0.1f);
		//music.play();
		gameStateManager.push(new MainMenuState(gameStateManager));
		this.prefs = Gdx.app.getPreferences("Options");

		//for graphics option
		if(prefs.contains("graphicsOn")){
			State.graphicsOn = prefs.getBoolean("graphicsOn");
		}else{
			State.graphicsOn = true;
			prefs.putBoolean("graphicsOn",true);
		}

		//for sound option
		if(prefs.contains("soundOn")){
			State.soundOn = prefs.getBoolean("soundOn");
		}else{
			State.soundOn = true;
			prefs.putBoolean("soundOn",true);
		}

		// for music option
		if(prefs.contains("musicOn")){
			State.musicOn = prefs.getBoolean("musicOn");
		}else{
			State.musicOn = true;
			prefs.putBoolean("musicOn",true);
		}

		//for shapes
		if(prefs.contains("unlockedShapes")){
			State.unlockedShapesToString = prefs.getString("unlockedShapes");
		}else {
			prefs.putString("unlockedShapes","100000"); // 1 is owned and 0 is not
			State.unlockedShapesToString = "100000";
		}

		//for colors
		if(prefs.contains("unlockedColors")){
			State.unlockedColorsToString = prefs.getString("unlockedColors");
		}else {
			prefs.putString("unlockedColors","100000"); // 1 is owned and 0 is not
			State.unlockedColorsToString = "100000";
		}

		//for score
		if(prefs.contains("highScore")){
			State.highScore = prefs.getInteger("highScore",0);
		}else {
			prefs.putInteger("highScore",0);
			State.highScore = 0;
		}

		//for money
		if(prefs.contains("money")){
			State.money = prefs.getInteger("money",0);
		}else {
			prefs.putInteger("money",0);
			State.money = 0;
		}

		//for selected shape
		if(prefs.contains("selectedShape")){
			State.selectedShape = prefs.getInteger("selectedShape");
		}else {
			State.selectedShape = 0;
			prefs.putInteger("selectedShape",0);
		}

		//for selected color
		if(prefs.contains("selectedColor")){
			State.selectedColor = prefs.getInteger("selectedColor");
		}else {
			State.selectedColor = 0;
			prefs.putInteger("selectedColor",0);
		}
		prefs.flush();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameStateManager.update(Gdx.graphics.getDeltaTime());
		gameStateManager.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
	}
}
