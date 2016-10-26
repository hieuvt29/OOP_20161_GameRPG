/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities;

import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author LOREMSUM
 */
public abstract class Entity {
    public static final int DEFAULT_HEALTH = 10;
    protected int health; //every creatures has its health
    
    protected float x, y; // every enntity need its coordinate
    protected int width, height; // and its size
    protected Handler handler; //We want every entity can access the Game instance
    protected Rectangle bounds; // bounding rectangle for every entity
    protected boolean active = true;

    public abstract void die();
    
    public void hurt(int amt){
        health -= amt;
        System.out.println(health);
        if(health <= 0){
            active = false;
            die();
        }
    }
    
    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        
        this.health = DEFAULT_HEALTH;
        
        bounds = new Rectangle(0,0, width, height);
    }
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        //loop through every entity we have in Map and check whether there are any other entities have collision with this entity
        for(Entity e: handler.getMap().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
    }
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
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
    
    
    public abstract void update();
    public abstract void render(Graphics g);
}
