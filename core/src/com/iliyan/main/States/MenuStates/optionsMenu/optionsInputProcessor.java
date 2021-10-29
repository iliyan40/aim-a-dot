package com.iliyan.main.States.MenuStates.optionsMenu;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.iliyan.main.States.MenuStates.DrawButton;
import com.iliyan.main.States.MenuStates.MainMenu.MainMenuState;

import static com.iliyan.main.Game.music;
import static com.iliyan.main.States.State.gms;
import static com.iliyan.main.States.State.graphicsOn;
import static com.iliyan.main.States.State.musicOn;
import static com.iliyan.main.Game.prefs;
import static com.iliyan.main.States.State.soundOn;


public class optionsInputProcessor implements InputProcessor {
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
        if(DrawButton.buttonTouched(optionsMenuState.getPointer(),0)){
            if(soundOn)
            optionsMenuState.click.play(0.5f);
            optionsMenuState.graphicsOn = !graphicsOn;
            prefs.putBoolean("graphicsOn",graphicsOn);
        }
        if(DrawButton.buttonTouched(optionsMenuState.getPointer(),-1)){
            if(!soundOn)
            optionsMenuState.click.play(0.5f);
            optionsMenuState.soundOn = !soundOn;
            prefs.putBoolean("soundOn",soundOn);
        }
        if(DrawButton.buttonTouched(optionsMenuState.getPointer(),-2)){
            if(soundOn)
            optionsMenuState.click.play(0.5f);
            optionsMenuState.musicOn = !musicOn;
            prefs.putBoolean("musicOn",musicOn);
            if(!musicOn){
                music.stop();
            }else if(!music.isPlaying())
                music.play();
        }
        if(DrawButton.buttonTouched(optionsMenuState.getPointer(),-3)){
            if(soundOn)
            optionsMenuState.click.play(0.5f);
            gms.set(new MainMenuState(gms));
        }
        prefs.flush();
        optionsMenuState.resetPointer();
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
