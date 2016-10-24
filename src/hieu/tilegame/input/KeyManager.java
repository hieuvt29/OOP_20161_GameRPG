/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author LOREMSUM
 */
public class KeyManager implements KeyListener{

    private boolean[] keys;
    public boolean up, down, left, right;
    //debug
    public boolean respawn;
    public boolean quit;
    //end;
    
    public KeyManager(){
        keys = new boolean[256];
    }
    
    public void update(){
        up = keys[KeyEvent.VK_W];
        down  = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        
        //debug
        respawn = keys[KeyEvent.VK_R];
        quit = keys[KeyEvent.VK_Q];
        //end;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
       keys[e.getKeyCode()] = false;
    }
    
}
