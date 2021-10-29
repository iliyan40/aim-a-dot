package com.iliyan.main.States.MenuStates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.TITLE;
import static com.iliyan.main.Game.WIDTH;

public class menuDrawings {
    public static void DrawTitle(BitmapFont audioWireBitmapFont, SpriteBatch batch, GlyphLayout glyphLayout){
        audioWireBitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        audioWireBitmapFont.getData().setScale(1.3f,1.3f);
        audioWireBitmapFont.setColor(0,0.8f,1,1);
        audioWireBitmapFont.draw(
                batch,
                TITLE,
                WIDTH/2f-glyphLayout.width*1.3f/2,
                HEIGHT-HEIGHT/6f-glyphLayout.height/2);
        audioWireBitmapFont.getData().setScale(1,1);
        audioWireBitmapFont.setColor(1,1,1,1);
    }
    public static void DrawHighScoreAndMoney(BitmapFont audioWireBitmapFont, SpriteBatch batch, int highScore, int money) {
        audioWireBitmapFont.getData().setScale(0.4f);
        audioWireBitmapFont.draw(
                batch,
                "highscore: " + highScore,
                8,
                HEIGHT - 8);
        audioWireBitmapFont.draw(
                batch,
                "money: " + money,
                8,
                HEIGHT - 22);
    }

}
