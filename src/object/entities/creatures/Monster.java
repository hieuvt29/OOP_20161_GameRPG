package object.entities.creatures;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import graphics.Animation;
import graphics.Assets;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.Handler;
import object.entities.Entity;
import object.entities.creatures.Creature;
import object.entities.statics.StaticEntity;

/**
 *
 * @author minhh
 */
public class Monster extends Creature {
    //Animation

    private Animation animUp, animDown, animLeft, animRight;
    private int direction; // 1- up, 2 - right, 3- down, 4 - left

    //Attack control
    private long lastAttackTimer, attackCoolDown = 300, attackTimer = 0;

    public Monster(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animation
        speed = 0.5f;
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft = new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);

        direction = 3;

    }


    @Override
    public void update() {
        float playerX = handler.getMap().getEntityManager().getPlayer().getX();
        float playerY = handler.getMap().getEntityManager().getPlayer().getY();
//        newPosition[0] = handler.getMap().getEntityManager().getPlayer().getX();
//        newPosition[1] = handler.getMap().getEntityManager().getPlayer().getY();
        if (Math.sqrt((playerX - x)*(playerX - x) + (playerY - y)* (playerY - y)) < 400)  {
            chase(playerX, playerY);
            move();
            checkAttacks();
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentPlayerFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        renderHealth(g);
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
        //wait another period of time
        attackTimer = 0;
        //end;

        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this) || e instanceof Monster || e instanceof StaticEntity) {
                continue;
            } else if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                
                e.hurt(5);
                return;
            }
        }

    }

    private void chase(float targetX, float targetY) {
        //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        //something to get quicker
        xMove = 0;
        yMove = 0;
        //done Strange things

        xMove = targetX - x;
        yMove = targetY - y;

        if (xMove > 1) {
            xMove = 1;
            animUp.update();
        }
        if (xMove < -1) {
            xMove = -1;
            animDown.update();
        }
        if (yMove > 1) {
            yMove = 1;
            animRight.update();
        }
        if (yMove < -1) {
            yMove = -1;
            animLeft.update();
        }

    }
    @Override
    public void die(){
        handler.getMap().getEntityManager().setNumMonster(handler.getMap().getEntityManager().getNumMonster() - 1);
    }
    private float[] randomPosition() {
        float x = (float) (Math.random() * handler.getMap().getWidth() + 1);
        float y = (float) (Math.random() * handler.getMap().getHeight() + 1);
        float[] res = new float[2];
        res[0] = x;
        res[1] = y;
        return res;

    }

}
