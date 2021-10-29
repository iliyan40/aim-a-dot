package com.iliyan.main.States.playState.gameObjects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.iliyan.main.States.MenuStates.GameOver.gameOverState;
import com.iliyan.main.States.State;
import com.iliyan.main.States.playState.gamePlayState;

import static com.iliyan.main.Game.HEIGHT;
import static com.iliyan.main.Game.WIDTH;
import static com.iliyan.main.States.State.gms;
import static com.iliyan.main.States.State.graphicsOn;

public class
Player {
    private Texture sprite;
    public Vector3 coordinates;
    private int width,height;
    private int scaleX, scaleY;
    public int rotation;
    private boolean rotateDirRight = true;
    public Sprite pSprite;
    public boolean isMoving;
    private float alpha;
    private ParticleEffect appearEffects;
    private Sound playerAction;
    private Sound playerHardHit;
    public static int speed = 1000;


    public Player(World world){
        sprite = setupSprite();
        coordinates = new Vector3();
        coordinates.x = WIDTH/2f;
        coordinates.y = 64;
        width = 90;
        height = 90;
        scaleX = 1;
        scaleY = 1;
        rotation = 0;
        isMoving = false;
        pSprite = new Sprite(sprite);
        pSprite.setPosition(coordinates.x-45,coordinates.y-45);
        pSprite.setOrigin(45,22);
        alpha = 0;
        appearEffects = new ParticleEffect();
        appearEffects.load(Gdx.files.internal("ParticleEffects/playerAppear.paef"),Gdx.files.internal("ParticleEffects"));
        appearEffects.getEmitters().first().setPosition(coordinates.x-16,coordinates.y-16);
        setupEffectColor() ;
        if(graphicsOn)
            this.appearEffects.start();
        playerAction = Gdx.audio.newSound(Gdx.files.internal("Sound/playerAction.wav"));
        playerHardHit= Gdx.audio.newSound(Gdx.files.internal("Sound/playerHitHard.wav"));




    }
    public Color setUpColor(){
        switch (State.selectedColor){
            case 0 :
                return new Color(1,1,1,1);
            case 1 :
                return new Color(1,1f,0.3f,1);
            case 2 :
                return new Color(0.3f,1f,0.3f,1);
            case 3 :
                return new Color(0f,0.8f,1f,1);
            case 4 :
                return new Color(1,0f,0,1);
            case 5 :
                return new Color(0.7f,0,1f,1);
        }
        return new Color(1,1,1,1);
    }

    private Texture setupSprite(){
        switch (State.selectedShape){
            case 0:
                return new Texture(Gdx.files.internal("Objects/Player/1.png"));
            case 1:
                return new Texture(Gdx.files.internal("Objects/Player/2.png"));
            case 2:
                return new Texture(Gdx.files.internal("Objects/Player/3.png"));
            case 3:
                return new Texture(Gdx.files.internal("Objects/Player/4.png"));
            case 4:
                return new Texture(Gdx.files.internal("Objects/Player/5.png"));
            case 5:
                return new Texture(Gdx.files.internal("Objects/Player/6.png"));
        }
        return new Texture(Gdx.files.internal("Objects/Player/1.png"));
    }

    private void setupEffectColor() {
        switch (State.selectedColor){
            case 0 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{1, 1, 1});
                break;
            case 1 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{1, 1, 0.3f});
                break;
            case 2 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{0.3f, 1, 0.3f});
                break;
            case 3 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{0, 0.8f, 1});
                break;
            case 4 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{1, 0, 0});
                break;
            case 5 :
                appearEffects.getEmitters().get(0).getTint().setColors(new float[]{0.7f, 0, 1});
                break;
        }

    }


    public void update() {
        if(alpha<1){
            alpha+=0.01f;
        }
        if(alpha>1) alpha=1;
        int rotateSpd = 100; // 100 original
        if (!isMoving) {
            if (rotation > 45) {
                rotateDirRight = false;
            } else if (rotation < -45) {
                rotateDirRight = true;
            }

            if (rotateDirRight) {
                rotation += rotateSpd*Gdx.graphics.getDeltaTime();
            } else rotation -= rotateSpd*Gdx.graphics.getDeltaTime();
        }
        pSprite.setRotation(rotation);
        move();
        if (!isMoving) {
            pSprite.setSize(90, 90);
            pSprite.setPosition(coordinates.x - 45, coordinates.y - 45);
        } else {
            pSprite.setSize(70, 90);
            pSprite.setPosition(coordinates.x-35,coordinates.y-55);
        }
        if((coordinates.x<0||coordinates.x>WIDTH||coordinates.y<0||coordinates.y>HEIGHT)&&!Gdx.input.isTouched()){

            if(State.soundOn)
            playerHardHit.play(0.3f);
            gms.set(new gameOverState(gms));
        }

        appearEffects.update(Gdx.graphics.getDeltaTime());
        reset();
    }

    public void startMove(){
        if(State.soundOn)
            playerAction.play(0.3f);
        isMoving=true;
    }
    public void move(){
        if(isMoving) {
            coordinates.x -= MathUtils.cosDeg(rotation - 90) * speed * Gdx.graphics.getDeltaTime();
            coordinates.y -= MathUtils.sinDeg(rotation - 90) * speed * Gdx.graphics.getDeltaTime();
        }
    }
    public boolean hitWith(){
        return false;
    }
    public void gotHit(){

    }
    public void isSafe(){

    }
    public void draw(SpriteBatch batch){

        //batch.draw(sprite,coordinates.x-45,coordinates.y-45,width,height);
        setupColor();

        appearEffects.draw(batch);
        pSprite.setAlpha(alpha);
        pSprite.draw(batch);



    }

    private void setupColor() {
        switch (State.selectedColor){
            case 0 :
                pSprite.setColor(1,1,1,1);
                break;
            case 1 :
                pSprite.setColor(1,1f,0.3f,1);
                break;
            case 2 :
                pSprite.setColor(0.3f,1f,0.3f,1);
                break;
            case 3 :
                pSprite.setColor(0f,0.8f,1f,1);
                break;
            case 4 :
                pSprite.setColor(1,0f,0,1);
                break;
            case 5 :
                pSprite.setColor(0.7f,0,1f,1);
                break;
        }
    }
    private void reset(){
        if(!isMoving)
        if(coordinates.y>64) {
            coordinates.y -= 20;
        }

    }

    public void dispose(){
        sprite.dispose();
        appearEffects.dispose();
    }
}