package com.iliyan.main.States.playState.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gamePlayState;

import java.util.Random;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.WIDTH;

public class ScorePoint {
    public int x;
    public int y;
    private Texture tSprite;
    private Sprite sprite;

    private Texture tSpriteMoney;
    private Sprite spriteMoney;
    public boolean givesMoney;

    public int rotation;
    public boolean isDown;
    private Sound takeSound;
    private ParticleEffect hitParticle;
    private int finalPosY;
    boolean directionRight;
    Random random = new Random();


    public ScorePoint(int objectNumber){

        finalPosY = HEIGHT/3+32;

        x=WIDTH/2-12;
        y=finalPosY*objectNumber;


        tSprite = new Texture(Gdx.files.internal("Objects/pentagramShape.png"));
        sprite = new Sprite(tSprite);
        rotation = 0;
        sprite.setColor(1,1,0.3f,1);

        tSpriteMoney = new Texture(Gdx.files.internal("Objects/pentagramShapeMoney.png"));
        spriteMoney = new Sprite(tSpriteMoney);
        spriteMoney.setColor(0.3f,0.9f,0.3f,1);

        givesMoney = false;


        isDown = objectNumber == 1;
        takeSound = Gdx.audio.newSound(Gdx.files.internal("Sound/scoreHit.wav"));

        hitParticle = new ParticleEffect();
        hitParticle.load(Gdx.files.internal("ParticleEffects/scoreHit.paef"),Gdx.files.internal("ParticleEffects"));
        directionRight = true;

        //sprite.setSize(24,24);
        //sprite.setCenter(12,-800);
    }
    public void takeOne(){

        hitParticle.getEmitters().first().setPosition(x+16,y+16);
        if(State.graphicsOn){
            if(!givesMoney){
                hitParticle.getEmitters().get(0).getTint().setColors(new float[]{1, 1, 0});
            }
            else {
                hitParticle.getEmitters().get(0).getTint().setColors(new float[]{0.3f, 0.9f, 0.3f});
            }
            hitParticle.start();
        }
        if(State.soundOn)
        takeSound.play(0.3f);
        x= random.nextInt(WIDTH-64)+10;
        y+=HEIGHT/2-12+200;

        int r = random.nextInt(100);
        if(r<=25){
            givesMoney = true;
        }else givesMoney = false;


    }

    public void update(){
        if(!isDown&y>finalPosY*2)
            y-=Player.speed*Gdx.graphics.getDeltaTime();
        else if(isDown&y>finalPosY) {
            y-=Player.speed*Gdx.graphics.getDeltaTime();
        }

        if(rotation<360){
            rotation++;
        }else rotation = 0;
        sprite.setPosition(x,y);
        sprite.setRotation(rotation);
        spriteMoney.setPosition(x,y);
        spriteMoney.setRotation(rotation);
        hitParticle.update(Gdx.graphics.getDeltaTime());
        if(gamePlayState.score>=30) {
            if (directionRight) {
                x += 45*2 * Gdx.graphics.getDeltaTime();
            } else {
                x -= 45 * Gdx.graphics.getDeltaTime();
            }
        }
        if(x>(WIDTH - 64) + 10){
            directionRight = false;
        }
        if(x<10){
            directionRight = true;
        }
    }

    public void draw(Batch batch){
        if(!givesMoney){
            sprite.draw(batch);
        }
        else {
            spriteMoney.draw(batch);
        }

        hitParticle.draw(batch);
    }

    public void dispose(){

    }
}
