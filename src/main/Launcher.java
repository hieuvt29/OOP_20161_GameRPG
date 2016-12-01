/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author LOREMSUM
 */
public class Launcher {
    
    public static void main(String[] args) {
        Game game = new Game("Tile game!", 640 , 480);
        game.start();
    }
    
}
