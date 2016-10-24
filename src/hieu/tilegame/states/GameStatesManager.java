/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.states;

import hieu.tilegame.states.State;

/**
 *
 * @author LOREMSUM
 */
public class GameStatesManager {
    private static State currentState = null;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        GameStatesManager.currentState = currentState;
    }
    
}
