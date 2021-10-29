package com.iliyan.main.States.playState.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScoreOnScreenDraw {
    private int x;
    private int xMoney;
    private int y;
    private int yMoney;
    private float alpha;
    private float alphaMoney;
    private int score;
    private int money;
    public ScoreOnScreenDraw(){
        x = 0;
        y = 0;
        alpha = 0;
        score = 0;
    }
    public void startMoney(int x, int y, int money){
        xMoney = x;
        yMoney = y;
        this.money = money;
        alphaMoney = 1;
    }
    public void start(int x, int y,int score){
        this.x = x;
        this.y = y;
        this.alpha = 1;
        this.score = score;
    }
    public void update(){
        if(alpha>0){
            alpha-=0.01f;
            y-=3* Gdx.graphics.getDeltaTime();
            x-=3* Gdx.graphics.getDeltaTime();
        }
        if(alphaMoney>0){
            alphaMoney-=0.01f;
            yMoney-=2* Gdx.graphics.getDeltaTime();
            xMoney-=2* Gdx.graphics.getDeltaTime();
        }
    }
    public void draw(Batch batch, BitmapFont bitmapFont){
        bitmapFont.setColor(1,1,0.3f,alpha);
        bitmapFont.getData().setScale(0.5f,0.5f);
        bitmapFont.draw(batch,"+ " + score,x,y);
        bitmapFont.setColor(1,1,1,1);

        bitmapFont.setColor(0.3f,1,0.3f,alphaMoney);
        bitmapFont.getData().setScale(0.5f,0.5f);
        bitmapFont.draw(batch,"+ " + money + "$",xMoney,yMoney);
        bitmapFont.setColor(1,1,1,1);
    }
}
