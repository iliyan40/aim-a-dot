package com.iliyan.main.States.MenuStates.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.iliyan.main.States.MenuStates.DrawButton;
import com.iliyan.main.States.MenuStates.optionsMenu.optionsMenuState;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gamePlayState;
import com.iliyan.main.States.MenuStates.shopMenu.shopMenuState;

import static com.iliyan.main.States.State.gms;
import static com.iliyan.main.States.State.soundOn;


public class MainInputProcessor implements InputProcessor {
    @Override
    public boolean keyDown(int keycode) {
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
        if(DrawButton.buttonTouched(MainMenuState.getPointer(),0)){
            if(soundOn)
            MainMenuState.click.play(0.5f);
            if(State.soundOn)
                MainMenuState.playerCreate.play(0.3f);
            gms.set(new gamePlayState(gms));
        }
        if(DrawButton.buttonTouched(MainMenuState.getPointer(),-1)){
            if(soundOn)
            MainMenuState.click.play(0.5f);
            gms.set(new shopMenuState(gms));
        }
        if(DrawButton.buttonTouched(MainMenuState.getPointer(),-2)){
            if(soundOn)
            MainMenuState.click.play(0.5f);
            gms.set(new optionsMenuState(gms));
        }
        if(DrawButton.buttonTouched(MainMenuState.getPointer(),-3)){
            if(soundOn)
            MainMenuState.click.play(0.5f);
            Gdx.app.exit();
        }
     MainMenuState.resetPointer();
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
