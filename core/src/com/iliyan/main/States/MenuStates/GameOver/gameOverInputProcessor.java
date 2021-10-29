package com.iliyan.main.States.MenuStates.GameOver;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.iliyan.main.States.MenuStates.DrawButton;
import com.iliyan.main.States.MenuStates.MainMenu.MainMenuState;
import com.iliyan.main.States.MenuStates.optionsMenu.optionsMenuState;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gamePlayState;

import static com.iliyan.main.Game.music;
import static com.iliyan.main.Game.prefs;
import static com.iliyan.main.States.State.gms;
import static com.iliyan.main.States.State.graphicsOn;
import static com.iliyan.main.States.State.musicOn;
import static com.iliyan.main.States.State.soundOn;


public class gameOverInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            gms.set(new MainMenuState(gms));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(DrawButton.buttonTouched(gameOverState.getPointer(),-2)){
            if(soundOn)
                gameOverState.click.play(0.5f);
            if(State.soundOn)
                MainMenuState.playerCreate.play(0.3f);
            gms.set(new gamePlayState(gms));
        }
        if(DrawButton.buttonTouched(gameOverState.getPointer(),-3)){
            if(soundOn)
                gameOverState.click.play(0.5f);
            gms.set(new MainMenuState(gms));
        }
        prefs.flush();
        gameOverState.resetPointer();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
