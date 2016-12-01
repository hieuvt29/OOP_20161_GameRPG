/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author LOREMSUM
 */
public class KeyManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, left, right;
    public boolean hit;

    //debug
    public boolean respawn;
    public boolean quit;
    //end;

    public KeyManager() {

        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void update() {

        for(int i = 0; i< keys.length; i++){
            if(cantPress[i] && !keys[i]){
            //cantPress[i] = true that means that value has already returned true for one update() frame
            // so we can't return true anymore so we cant press this particular key
            //!keys[i] means this key is no longer being pressed
            // so they should be able to press it again and justPressed[i] should return true;
                cantPress[i] = false;
            }else if(justPressed[i]){
            //that mean for one update() method we've already returned true that this key has been pressed 
            // then we're going to set cantPress[i] = true - we don't want them to press it util they release the key  
            // and it is no longer just been pressed 
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if(!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }
        //debug
        if (keyJustPressed(KeyEvent.VK_E)) {
            System.out.println("E pressed");
        }
        if (keyJustPressed(KeyEvent.VK_F)) {
            System.out.println("F pressed");
        }
        //end;

        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        hit = keys[KeyEvent.VK_SPACE];

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
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }

        keys[e.getKeyCode()] = true;
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
            return;
        }
        keys[e.getKeyCode()] = false;

        
    }

    public boolean keyJustPressed(int keyCode) {
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }
}
