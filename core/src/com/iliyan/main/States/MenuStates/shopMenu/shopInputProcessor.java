package com.iliyan.main.States.MenuStates.shopMenu;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.iliyan.main.States.MenuStates.DrawButton;
import com.iliyan.main.States.MenuStates.MainMenu.MainMenuState;
import com.iliyan.main.States.MenuStates.moneyAndScoreHandler;
import com.iliyan.main.States.State;

import static com.iliyan.main.Game.prefs;
import static com.iliyan.main.States.State.gms;
import static com.iliyan.main.States.State.money;
import static com.iliyan.main.States.State.selectedColor;
import static com.iliyan.main.States.State.selectedShape;
import static com.iliyan.main.States.State.soundOn;

public class shopInputProcessor implements InputProcessor {
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
        if(DrawButton.buttonTouched(shopMenuState.getPointer(),-3)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            prefs.putString("unlockedColors",
                    moneyAndScoreHandler.convertUnlockedBooleanToString(shopMenuState.colorsUnlocked));
            prefs.putString("unlockedShapes",
                    moneyAndScoreHandler.convertUnlockedBooleanToString(shopMenuState.shapesUnlocked));
            selectedColor = shopMenuState.selectedColor;
            selectedShape = shopMenuState.selectedShape;
            prefs.putInteger("selectedColor",selectedColor);
            prefs.putInteger("selectedShape",selectedShape);
            money = shopMenuState.money;
            prefs.putInteger("money",money);
            gms.set(new MainMenuState(gms));
        }
        if(DrawButton.reallySmallButtonTouched(shopMenuState.getPointer(),0)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            shopMenuState.setToShape();
        }
        if(DrawButton.reallySmallButtonTouched(shopMenuState.getPointer(),1)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            shopMenuState.setToColor();
        }
        //sel 1 (white)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),0,0)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                shopMenuState.selectedColor=0;
            }else {
                shopMenuState.selectedShape=0;
            }

        }
        //sel 2 (yellow)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),1,0)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                if(shopMenuState.colorsUnlocked[1]){
                    shopMenuState.selectedColor = 1;
                }else {
                    if(shopMenuState.money>=shopMenuState.colorPrices[1]){
                        shopMenuState.selectedColor = 1;
                        shopMenuState.colorsUnlocked[1] = true;
                        shopMenuState.money-=shopMenuState.colorPrices[1];
                    }
                }
            }else {
                if(shopMenuState.shapesUnlocked[1]){
                    shopMenuState.selectedShape = 1;
                }else {
                    if(shopMenuState.money>=shopMenuState.shapePrices[1]){
                        shopMenuState.selectedShape = 1;
                        shopMenuState.shapesUnlocked[1] = true;
                        shopMenuState.money-=shopMenuState.shapePrices[1];
                    }
                }
            }

        }
        //sel 3 (green)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),0,1)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                if(shopMenuState.colorsUnlocked[2]){
                    shopMenuState.selectedColor = 2;
                }else {
                    if(shopMenuState.money>=shopMenuState.colorPrices[2]){
                        shopMenuState.selectedColor = 2;
                        shopMenuState.colorsUnlocked[2] = true;
                        shopMenuState.money-=shopMenuState.colorPrices[2];
                    }
                }
            }else {
                if(shopMenuState.shapesUnlocked[2]){
                    shopMenuState.selectedShape = 2;
                }else {
                    if(shopMenuState.money>=shopMenuState.shapePrices[2]){
                        shopMenuState.selectedShape = 2;
                        shopMenuState.shapesUnlocked[2] = true;
                        shopMenuState.money-=shopMenuState.shapePrices[2];
                    }
                }
            }

        }
        //sel 4 (blue)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),1,1)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                if(shopMenuState.colorsUnlocked[3]){
                    shopMenuState.selectedColor = 3;
                }else {
                    if(shopMenuState.money>=shopMenuState.colorPrices[3]){
                        shopMenuState.selectedColor = 3;
                        shopMenuState.colorsUnlocked[3] = true;
                        shopMenuState.money-=shopMenuState.colorPrices[3];
                    }
                }
            }else {
                if(shopMenuState.shapesUnlocked[3]){
                    shopMenuState.selectedShape = 3;
                }else {
                    if(shopMenuState.money>=shopMenuState.shapePrices[3]){
                        shopMenuState.selectedShape = 3;
                        shopMenuState.shapesUnlocked[3] = true;
                        shopMenuState.money-=shopMenuState.shapePrices[3];
                    }
                }
            }

        }
        //sel 5 (red)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),0,2)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                if(shopMenuState.colorsUnlocked[4]){
                    shopMenuState.selectedColor = 4;
                }else {
                    if(shopMenuState.money>=shopMenuState.colorPrices[4]){
                        shopMenuState.selectedColor = 4;
                        shopMenuState.colorsUnlocked[4] = true;
                        shopMenuState.money-=shopMenuState.colorPrices[4];
                    }
                }
            }else {
                if(shopMenuState.shapesUnlocked[4]){
                    shopMenuState.selectedShape = 4;
                }else {
                    if(shopMenuState.money>=shopMenuState.shapePrices[4]){
                        shopMenuState.selectedShape = 4;
                        shopMenuState.shapesUnlocked[4] = true;
                        shopMenuState.money-=shopMenuState.shapePrices[4];
                    }
                }
            }

        }
        //sel 6 (purple)
        if(DrawButton.miniTouched(shopMenuState.getPointer(),1,2)){
            if(soundOn)
                shopMenuState.click.play(0.5f);
            if(shopMenuState.getShapeOrColor().equals("Color")){
                if(shopMenuState.colorsUnlocked[5]){
                    shopMenuState.selectedColor = 5;
                }else {
                    if(shopMenuState.money>=shopMenuState.colorPrices[5]){
                        shopMenuState.selectedColor = 5;
                        shopMenuState.colorsUnlocked[5] = true;
                        shopMenuState.money-=shopMenuState.colorPrices[5];
                    }
                }
            }else {
                if(shopMenuState.shapesUnlocked[5]){
                    shopMenuState.selectedShape = 5;
                }else {
                    if(shopMenuState.money>=shopMenuState.shapePrices[5]){
                        shopMenuState.selectedShape = 5;
                        shopMenuState.shapesUnlocked[5] = true;
                        shopMenuState.money-=shopMenuState.shapePrices[5];
                    }
                }
            }

        }
        prefs.flush();
        shopMenuState.resetPointer();
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
