/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities;

import java.awt.Color;
import main.Game;
import main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author LOREMSUM
 */
public abstract class Entity {

    protected int full_health = 100;

    protected int health;
    protected int attackAmount;

    protected float x, y;// Toa do cua thuc the
    protected int width, height; // Kich thuoc cua thuc the
    protected Handler handler;
    protected Rectangle bounds; // Hinh bao xac dinh collision
    protected boolean active = true; // Trang thai ton tai cua thuc the

    public abstract void die();

    public void hurt(int amt) {
        health -= amt;
        System.out.println("Health: " + health);
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public void increaseHP(int Hp) {
        if (health + Hp <= full_health) {
            health += Hp;
        } else {
            health = full_health;
        }
    }

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.health = full_health;

        bounds = new Rectangle(0, 0, width, height);
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        //loop through every entity we have in Map2 and check whether there are any other entities have collision with this entity

        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
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

    public int getFull_health() {
        return full_health;
    }

    public void setFull_health(int full_health) {
        this.full_health = full_health;
    }

    protected void renderHealth(Graphics g) {
        Color temp_color = g.getColor();
        g.drawRect((int) (x - handler.getGameCamera().getxOffset()) - 15,
                (int) (y - handler.getGameCamera().getyOffset()) - 30, 100, 10);
        g.drawString(new Integer(getHealth()).toString(), (int) (x - handler.getGameCamera().getxOffset()) + 87,
                (int) (y - handler.getGameCamera().getyOffset()) - 20);

        g.setColor(Color.RED);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset()) - 15,
                (int) (y - handler.getGameCamera().getyOffset()) - 30, getHealth() * 100 / getFull_health(), 10);
        g.setColor(temp_color);
    }

}
