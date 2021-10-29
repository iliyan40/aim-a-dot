package com.iliyan.main.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    public GameStateManager(){
        this.states = new Stack<State>();
    }
    public void push(State state){
        this.states.push(state);
    }
    public void pop(){
        this.states.pop();
    }
    public void set(State state){
        this.states.pop();
        this.states.push(state);
    }

    public void update(float deltaTime){
        this.states.peek().update(deltaTime);
    }
    public void render(SpriteBatch spriteBatch){
        this.states.peek().render(spriteBatch);
    }

}
