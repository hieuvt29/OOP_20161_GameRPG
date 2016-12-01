/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities.creatures;

import object.entities.Entity;
import graphics.Animation;
import graphics.Assets;
import object.inventory.Inventory;
import main.Game;
import main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Player extends Creature {

    //Animation
    private Animation animUp, animDown, animLeft, animRight;
    private int direction; // 1- up, 2 - right, 3- down, 4 - left

    //Attack control
    private long lastAttackTimer, attackCoolDown = 200, attackTimer = 0;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animation
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

        direction = 3;

        //inventory
        inventory = new Inventory(handler);
    }

    @Override
    public void update() {
        getInput(); //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        move(); //so we need to call this method to move our Player

        handler.getGameCamera().centerOnEntity(this);

        //Attack
        checkAttacks();

        //inventory
        inventory.update();

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentPlayerFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//      //debug
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width,
//                bounds.height);

        //inventory
        inventory.render(g);

    }

    private BufferedImage getCurrentPlayerFrame() {
        if (xMove > 0) {
            direction = 2;
            return animRight.getCurrentFrame();
        } else if (xMove < 0) {
            direction = 4;
            return animLeft.getCurrentFrame();
        } else if (yMove < 0) {
            direction = 1;
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            direction = 3;
            return animDown.getCurrentFrame();
        } else {
            switch (direction) {
                case 1:
                    return animUp.getCurrentFrame();
                case 2:
                    return animRight.getCurrentFrame();
                case 4:
                    return animLeft.getCurrentFrame();
                default:
                    return animDown.getCurrentFrame();
            }
        }
    }

    private void checkAttacks() {
        //check for waiting time
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCoolDown) {
            return;
        }
        //end;

        Rectangle cb = getCollisionBounds(0f, 0f);//collision Bound
        Rectangle ar = new Rectangle(); //attack Range
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().hit) {
            if (this.direction == 1) { //up
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y - arSize;
            } else if (this.direction == 2) { //right
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else if (this.direction == 3) {//down
                ar.x = cb.x + cb.width / 2 - arSize / 2;
                ar.y = cb.y + cb.height;
            } else if (this.direction == 4) { //left
                ar.x = cb.x - arSize;
                ar.y = cb.y + cb.height / 2 - arSize / 2;
            } else {
                return;
            }
        }
        //wait another period of time
        attackTimer = 0;
        //end;

        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            } else if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                e.hurt(1);
                return;
            }
        }

    }

    private void getInput() {
        //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        //something to get quicker
        xMove = 0;
        yMove = 0;
        //done Strange things

        if (handler.getKeyManager().up) {
            yMove = -speed;
            animUp.update();
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            animDown.update();
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            animLeft.update();
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            animRight.update();
        }

        //debug
        if (handler.getKeyManager().respawn) {
            x = width / 2;
            y = width / 2;
        }
        if (handler.getKeyManager().quit) {
            System.exit(0);
        }
        //end;

    }

    @Override
    public void die() {
        System.out.println("You lose!");
    }

    public int getDirection() {
        return direction;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
