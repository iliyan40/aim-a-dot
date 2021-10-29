package com.iliyan.main.States.playState;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import static com.iliyan.main.States.playState.gamePlayState.bonusPoint;
import static com.iliyan.main.States.playState.gamePlayState.scorePoint;
import static com.iliyan.main.States.playState.gamePlayState.scorePoint2;

public class gamePlayContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        if(fa.getBody().getUserData()=="player"&&fb.getBody().getUserData()=="scorePoint"){
            if(scorePoint.isDown){
                gamePlayState.score++;
                if(scorePoint.givesMoney){
                    gamePlayState.giveMoney(5);
                    gamePlayState.scoreOnScreenDraw.startMoney(scorePoint.x,scorePoint.y,5);
                }
                gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,1);
                if(!gamePlayState.player.isMoving){
                    gamePlayState.score+=4;

                    gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,7 );
                }
            }else {
                gamePlayState.score+=2;
                gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,2);
                if(scorePoint.givesMoney){
                    gamePlayState.giveMoney(10);
                    gamePlayState.scoreOnScreenDraw.startMoney(scorePoint.x,scorePoint.y,10);
                }
            }
            scorePoint.takeOne();
            scorePoint.isDown=false;
            scorePoint2.isDown=true;
            gamePlayState.bonusPoint.spawnChance();


            gamePlayState.player.isMoving=false;
        }

        if(fa.getBody().getUserData()=="player"&&fb.getBody().getUserData()=="scorePoint2"){
            if(scorePoint2.isDown){
                gamePlayState.score++;
                if(scorePoint2.givesMoney){
                    gamePlayState.giveMoney(5);
                    gamePlayState.scoreOnScreenDraw.startMoney(scorePoint2.x,scorePoint2.y,5);
                }
                gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,1);
                if(!gamePlayState.player.isMoving){
                    gamePlayState.score+=4;

                    gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,7);
                }
            }else {
                gamePlayState.score+=2;
                gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,2);
                if(scorePoint2.givesMoney){
                    gamePlayState.giveMoney(10);
                    gamePlayState.scoreOnScreenDraw.startMoney(scorePoint2.x,scorePoint2.y,10);
                }
            }
            scorePoint2.takeOne();
            scorePoint.isDown=true;
            scorePoint2.isDown=false;
            gamePlayState.bonusPoint.spawnChance();


            gamePlayState.player.isMoving=false;
        }if(fa.getBody().getUserData()=="player"&&fb.getBody().getUserData()=="bonusPoint"){
                    gamePlayState.score+=10;
                    gamePlayState.giveMoney(10);
                    gamePlayState.scoreOnScreenDraw.startMoney(bonusPoint.x,bonusPoint.y,10);
            gamePlayState.scoreOnScreenDraw.start((int)gamePlayState.player.coordinates.x,(int)gamePlayState.player.coordinates.y,10);

            bonusPoint.takeOne();
            gamePlayState.bonusPoint.spawnChance();


            gamePlayState.player.isMoving=false;
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
