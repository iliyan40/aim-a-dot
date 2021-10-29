package com.iliyan.main.States.MenuStates;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.WIDTH;

public class DrawButton {
    public static void draw(BitmapFont audioWireBitmapFont,
                            SpriteBatch batch,
                            Texture texture,
                            boolean isTouched,
                            Texture texture2,
                            int position,
                            String text){
        batch.setColor(0f,0.7f,1f,1);
        if(!isTouched ){
            batch.draw(
                    texture,
                    WIDTH / 2f - 100,
                    HEIGHT - HEIGHT / 2f + 30 +  (position * 100),
                    200,
                    70
            );
        } else {
            batch.draw(
                    texture2,
                    WIDTH / 2f - 100,
                    HEIGHT - HEIGHT / 2f  + 30+ (position * 100),
                    200,
                    70
            );
        }
        audioWireBitmapFont.getData().setScale(1f);


        audioWireBitmapFont.draw(
                batch,
                text,
                WIDTH/3.5f+10,
                HEIGHT-HEIGHT/5f*2 + (position * 100)
        );
    }
    public static void drawReallySmall(BitmapFont audioWireBitmapFont,
                                       SpriteBatch batch,
                                       Texture texture,
                                       boolean isTouched,
                                       Texture texture2,
                                       int position,
                                       String text,
                                       boolean selected){
        if(!isTouched ){
            batch.draw(
                    texture,
                    WIDTH / 2.5f - 100 +  (position * 170),   //pos + 100 if need more small buttons
                    HEIGHT - HEIGHT / 5f,
                    94,
                    32
            );
        } else {
            batch.draw(
                    texture2,
                    WIDTH / 2.5f - 100+ (position * 170),
                    HEIGHT - HEIGHT / 5f,
                    94,
                    32
            );
        }
        audioWireBitmapFont.getData().setScale(0.7f);
        if(selected){
            audioWireBitmapFont.setColor(0.9f, 0.9f, 0.2f, 1);
        }else
            audioWireBitmapFont.setColor(1,1,1,1);
        audioWireBitmapFont.draw(
                batch,
                text,
                WIDTH/6f + (position * 170) - 10,
                HEIGHT-HEIGHT/5f+24
        );
    }
    public static void drawMini(SpriteBatch batch,
                                       Texture texture,
                                       boolean isTouched,
                                       Texture texture2,
                                       int positionX,
                                       int positionY){
        if(!isTouched ){
            batch.draw(
                    texture,
                    WIDTH / 2.5f - 100 +  (positionX * 170),   //pos + 100 if need more mini buttons
                    HEIGHT - HEIGHT / 5f-128 -  (positionY * 128),
                    94,
                    94
            );
        } else {
            batch.draw(
                    texture2,
                    WIDTH / 2.5f - 100+ (positionX * 170),
                    HEIGHT - HEIGHT / 5f-128 - (positionY * 128),
                    94,
                    94
            );
        }
    }


    public static boolean buttonTouched(Vector3 pointer, int position){
       return   pointer.x>WIDTH/2f-100 &&
               pointer.x<WIDTH/2f+100&&
               pointer.y > HEIGHT-HEIGHT/2f+30 + (position * 100) &&
               pointer.y<HEIGHT-HEIGHT/2f+30+70 + (position * 100);
    }
    public static boolean reallySmallButtonTouched(Vector3 pointer, int position){
        return   pointer.x>WIDTH/2.5f-100 + (position * 170) &&
                pointer.x<WIDTH/2.5f-100 + 94 + (position * 170)&&
                pointer.y > HEIGHT-HEIGHT/5f &&
                pointer.y<HEIGHT-HEIGHT/5f+32;
    }
    public static boolean miniTouched(Vector3 pointer, int positionX,int positionY){
        return   pointer.x>WIDTH/2.5f-100 + (positionX * 170) &&
                pointer.x<WIDTH/2.5f-100 + 94 + (positionX * 170)&&
                pointer.y > HEIGHT-HEIGHT/5f -128- (positionY * 128) &&
                pointer.y<HEIGHT-HEIGHT/5f+94 -128- (positionY * 128);
    }

}
