package com.iliyan.main.States.MenuStates.shopMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class shopDrawings {
    private static Texture shape1;
    private static Texture shape2;
    private static Texture shape3;
    private static Texture shape4;
    private static Texture shape5;
    private static Texture shape6;
    private static Texture color;
    private static Texture colorSelected;
    private static Texture colorLocked;
    shopDrawings(){
        shape1 = new Texture(Gdx.files.internal("Objects/Player/1.png"));
        shape2 = new Texture(Gdx.files.internal("Objects/Player/2.png"));
        shape3 = new Texture(Gdx.files.internal("Objects/Player/3.png"));
        shape4 = new Texture(Gdx.files.internal("Objects/Player/4.png"));
        shape5 = new Texture(Gdx.files.internal("Objects/Player/5.png"));
        shape6 = new Texture(Gdx.files.internal("Objects/Player/6.png"));
        color = new Texture(Gdx.files.internal("ButtonSkin/colors.png"));
        colorSelected = new Texture(Gdx.files.internal("ButtonSkin/colorSelected.png"));
        colorLocked = new Texture(Gdx.files.internal("ButtonSkin/colorsLocked.png"));
    }
    private static void setColor(Batch batch,boolean shapesUnlocked,int selected,int need){

            if (shapesUnlocked)
                if(selected!=need) {
                    batch.setColor(0.5f, 0.5f, 0.5f, 1);
                }
            else
                batch.setColor(1,1,1,1);
            else
                batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);

    }
    public static void drawShapes(Batch batch,boolean[] shapesUnlocked,int selected){
        setColor(batch,shapesUnlocked[0],selected,0);
        batch.draw(shape1,50,480,85,85);
        setColor(batch,shapesUnlocked[1],selected,1);
        batch.draw(shape2,220,480,85,85);
        setColor(batch,shapesUnlocked[2],selected,2);
        batch.draw(shape3,50,352,85,85);
        setColor(batch,shapesUnlocked[3],selected,3);
        batch.draw(shape4,220,352,85,85);
        setColor(batch,shapesUnlocked[4],selected,4);
        batch.draw(shape5,50,224,85,85);
        setColor(batch,shapesUnlocked[5],selected,5);
        batch.draw(shape6,220,224,85,85);
    }
    public static void drawColors(Batch batch, boolean[] colorsUnlocked, int selected){
        batch.setColor(1, 1, 1, 1);
        if(colorsUnlocked[0]) {
            if(selected==0)
                batch.draw(colorSelected, 60, 485, 64, 64);
            else
                batch.draw(color, 60, 485, 64, 64);
        }else {
            batch.draw(colorLocked, 60, 485, 64, 64);
        }

        batch.setColor(0.9f, 0.9f, 0.2f, 1);
        if(colorsUnlocked[1]) {
            if(selected==1)
                batch.draw(colorSelected, 230, 485, 64, 64);
            else
                batch.draw(color, 230, 485, 64, 64);
        }else {
            batch.draw(colorLocked, 230, 485, 64, 64);
        }

        batch.setColor(0.3f, 0.8f, 0.3f, 1);
        if(colorsUnlocked[2]) {
            if(selected==2)
                batch.draw(colorSelected, 60, 357, 64, 64);
            else
                batch.draw(color, 60, 357, 64, 64);
        }else {
            batch.draw(colorLocked, 60, 357, 64, 64);
        }

        batch.setColor(0.3f, 0.7f, 1f, 1);
        if(colorsUnlocked[3]) {
            if(selected==3)
                batch.draw(colorSelected, 230, 357, 64, 64);
            else
                batch.draw(color, 230, 357, 64, 64);
        }else {
            batch.draw(colorLocked, 230, 357, 64, 64);
        }

        batch.setColor(0.8f, 0.3f, 0.3f, 1);
        if(colorsUnlocked[4]) {
            if(selected==4)
                batch.draw(colorSelected, 60, 229, 64, 64);
            else
                batch.draw(color, 60, 229, 64, 64);
        }else {
            batch.draw(colorLocked, 60, 229, 64, 64);
        }

        batch.setColor(0.7f, 0.5f, 1f, 1);
        if(colorsUnlocked[5]) {
            if(selected==5)
                batch.draw(colorSelected, 230, 229, 64, 64);
            else
                batch.draw(color, 230, 229, 64, 64);
        }else {
            batch.draw(colorLocked, 230, 229, 64, 64);
        }

        batch.setColor(1,1,1,1);

    }
}
