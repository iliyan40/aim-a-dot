package com.iliyan.main.States.MenuStates.MainMenu;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.iliyan.main.States.MenuStates.DrawButton;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;

class MainMenuDrawings {
    MainMenuDrawings(){
    }
    void DrawMainMenuText(BitmapFont audioWireBitmapFont, SpriteBatch batch, GlyphLayout glyphLayout){
        audioWireBitmapFont.draw(
                batch,
                "Main menu",
                WIDTH/2f-glyphLayout.width/1.7f,
                HEIGHT-HEIGHT/4f-glyphLayout.height/2);
    }



    void DrawStartButton(BitmapFont audioWireBitmapFont, SpriteBatch batch, Texture texture, boolean isTouched, Texture texture2) {
        DrawButton.draw( audioWireBitmapFont,  batch,  texture,  isTouched,  texture2,0,"  Start");
    }
    void DrawShopButton(BitmapFont audioWireBitmapFont, SpriteBatch batch, Texture texture, boolean isTouched, Texture texture2) {
        DrawButton.draw( audioWireBitmapFont,  batch,  texture,  isTouched,  texture2,-1,"  Shop");
    }
    void DrawOptionsButton(BitmapFont audioWireBitmapFont, SpriteBatch batch, Texture texture, boolean isTouched, Texture texture2) {
        DrawButton.draw( audioWireBitmapFont,  batch,  texture,  isTouched,  texture2,-2,"Options");
    }
    void DrawExitButton(BitmapFont audioWireBitmapFont, SpriteBatch batch, Texture texture, boolean isTouched, Texture texture2) {
        DrawButton.draw( audioWireBitmapFont,  batch,  texture,  isTouched,  texture2,-3,"   Exit");
    }
}