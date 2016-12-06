/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.states;

import main.Game;
import main.Handler;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public abstract class State {

    //protected Game game;
    //we want to put an instance of Game class to very single one of the states in our game
    protected Handler handler; // instead add Game object, to optimize the way we access a lot of method, we just add handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    //all the state are gonna have these common methods
    public abstract void update();

    //We pass a Graphics object for render() method to allow it draw things directly
    public abstract void render(Graphics g);
}
