/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author LOREMSUM
 */
public abstract class UIObject {
    
    protected float x, y;
    protected int width, height;    
    protected boolean hovering = false;
    protected Rectangle bounds;
    
    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void update();
    public abstract void render(Graphics g);
    public abstract void onClick();
    
    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(), e.getY())){
            hovering = true;
        }else{
            hovering = false;
        }
    }
    public void onMouseRelease(MouseEvent e){
        if(hovering){
            onClick();
        }
    }
    //Setters and getters
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
