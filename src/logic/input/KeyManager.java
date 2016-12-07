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
            // cantPress[i] đã trả về giá trị đúng trong một lần gọi hàm update() gần nhất
            // thì ta không cho phép nó trả về giá trị true lần thứ hai
            // Khi mà nút đó đã được thả ra thì ta mới có thể cho giá trị cantPress = true một lần nữa.
                cantPress[i] = false;
            }else if(justPressed[i]){
            // tức là justPressed[i] đã trả về giá trị true trong một lần gọi hàm update() gần nhất
            // thì ta có thể gán cantPress[i] = true - chúng ta không muốn người chơi bấm nút đó cho đến khi họ nhả nút đó ra
            // tức nó không còn là "vừa được bấm" nữa
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
