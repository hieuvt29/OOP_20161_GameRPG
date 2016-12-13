/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities.creatures;

import object.entities.Entity;
import graphics.Animation;
import graphics.Assets;
import main.Game;
import main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import main.states.GameOverState;
import main.states.MenuState;
import main.states.PlayState;
import object.items.Inventory;
import object.items.Item;
import object.items.ShieldItem;
import object.tiles.Tile;

/**
 *
 * @author LOREMSUM
 */
public class Player extends Creature {

    //Animation
    private Animation[][] currentAnimSet;
    private int playerState;

    private int direction; // 0- up, 1 - right, 2- down, 3 - left

    //Attack control
    private long lastAttackTimer, attackCoolDown = 200, attackTimer = 0;
    private int attackRange;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        //Attack
        attackAmount = 10;
        attackRange = 20;

        //Bounding rectangle
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animation
        speed = 2f;
        direction = 2;
        
        playerState = 0;

        currentAnimSet = Animation.animSetSpear;

        //inventory
        inventory = new Inventory(handler);
    }

    @Override
    public void update() {
        handler.getGameCamera().centerOnEntity(this);

        //inventory
        inventory.update();

        getInput(); //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        move(); //so we need to call this method to move our Player

        //Attack
        checkAttacks();

        
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentPlayerFrame(currentAnimSet[playerState]), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        //inventory
        inventory.render(g);
        renderHealth(g, Color.RED);
//        renderCollisionBounds(g);

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
        Rectangle ar = new Rectangle(attackRange, attackRange); //attack Range

        if (handler.getKeyManager().hit) {
            playerState = 1;
            currentAnimSet[playerState][this.direction].update();
            if (this.direction == 0) { //up
                ar.x = cb.x + cb.width / 2 - attackRange / 2;
                ar.y = cb.y - attackRange;

            } else if (this.direction == 1) { //right
                ar.x = cb.x + cb.width;
                ar.y = cb.y + cb.height / 2 - attackRange / 2;

            } else if (this.direction == 2) {//down
                ar.x = cb.x + cb.width / 2 - attackRange / 2;
                ar.y = cb.y + cb.height;

            } else if (this.direction == 3) { //left
                ar.x = cb.x - attackRange;
                ar.y = cb.y + cb.height / 2 - attackRange / 2;

            } else {
                return;
            }
        }else{
            playerState = 0;
        }
        //wait another period of time
        attackTimer = 0;
        //end;

        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            } else if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                e.hurt(attackAmount);
                return;
            }
        }

    }

    @Override
    public void hurt(int amt) {
        boolean hasShield = false;
        //Kiem tra xem nguoi choi co ShieldItem hay khong?
        Iterator<Item> it = this.getInventory().getInventoryItems().iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i instanceof ShieldItem) {
                hasShield = true;
                //Neu co ShieldItem thi tru HP cua Shield truoc
                //tru bao gio het ShieldItem trong inventory moi tru den HP cua nguoi choi
                int value = ((ShieldItem) i).getDefenceAmount() - amt;
                if (value > 0) {
                    ((ShieldItem) i).setDefenceAmount(value);
                } else {
                    int numShield = i.getCount() - 1;
                    if (numShield > 0) {
                        ((ShieldItem) i).setDefenceAmount(((ShieldItem) i).FullDefenceAmount + value);
                    } else {
                        it.remove();
                        health += value;
                    }
                }
                break;
            }
        }
        if (!hasShield) {
            //Kiem tra neu khong co ShieldItem trong inventory thi tru HP cua nguoi choi
            health -= amt;
        }
        if (health <= 0) {
            active = false;
            die();
        }
        System.out.println("Health: " + health);

    }

    private BufferedImage getCurrentPlayerFrame(Animation[] anim) {
        if (xMove > 0) {
            direction = 1;
        } else if (xMove < 0) {
            direction = 3;
        } else if (yMove < 0) {
            direction = 0;
        } else if (yMove > 0) {
            direction = 2;
        }
        return anim[direction].getCurrentFrame();
    }

    private void getInput() {
        //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        //something to get quicker
        xMove = 0;
        yMove = 0;
        //done Strange things

        if (handler.getKeyManager().up) {
            yMove = -speed;
            //walkSwordAnims[0].update();
            currentAnimSet[playerState][0].update();
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            //walkSwordAnims[2].update();
            currentAnimSet[playerState][2].update();
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            //walkSwordAnims[3].update();
            currentAnimSet[playerState][3].update();
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            //walkSwordAnims[1].update();
            currentAnimSet[playerState][1].update();
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
        handler.getGame().setState(new GameOverState(handler));
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

    public Animation[][] getCurrentAnimSet() {
        return currentAnimSet;
    }

    public void setCurrentAnimSet(Animation[][] currentAnimSet) {
        this.currentAnimSet = currentAnimSet;
    }


    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }
}
