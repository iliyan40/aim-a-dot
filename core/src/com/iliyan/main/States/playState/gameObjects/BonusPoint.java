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

public class BonusPoint {
    public int x;
    public int y;
    private Texture tSprite;
    private Sprite sprite;


    public int rotation;
    private Sound takeSound;
    private ParticleEffect hitParticle;
    private Random random = new Random();


    public BonusPoint(){


        x = 64;
        y = -640;


        tSprite = new Texture(Gdx.files.internal("Objects/pentagramShape.png"));
        sprite = new Sprite(tSprite);
        rotation = 0;
        sprite.setColor(0.2f,0.6f,0.8f,1);

        takeSound = Gdx.audio.newSound(Gdx.files.internal("Sound/scoreHit.wav"));

        hitParticle = new ParticleEffect();
        hitParticle.load(Gdx.files.internal("ParticleEffects/scoreHit.paef"),Gdx.files.internal("ParticleEffects"));

        //sprite.setSize(24,24);
        //sprite.setCenter(12,-800);
    }
    public void spawnChance(){
        if(y<-250){
            int r = random.nextInt(100);
            if (r <= 20) {
                x = random.nextInt(WIDTH - 64) + 10;
                y = 1000;
            }
        }
    }
    public void takeOne(){

        hitParticle.getEmitters().first().setPosition(x+16,y+16);
        if(State.graphicsOn){
            hitParticle.getEmitters().get(0).getTint().setColors(new float[]{0.2f, 0.6f, 0.8f});
            hitParticle.start();
        }
        if(State.soundOn)
        takeSound.play(0.3f);
        y=-600;

    }

    public void update(){
        if(y>-300)
            y-= 200*Gdx.graphics.getDeltaTime();

        if(rotation<360){
            rotation++;
        }else rotation = 0;
        sprite.setPosition(x,y);
        sprite.setRotation(rotation);
        hitParticle.update(Gdx.graphics.getDeltaTime());
    }

    public void draw(Batch batch){

        sprite.draw(batch);

        hitParticle.draw(batch);
    }

    public void dispose(){

    }
}
