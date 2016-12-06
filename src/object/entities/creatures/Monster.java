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

    private Animation[] walkAnims;
    private int direction; // 0- up, 1 - right, 2- down, 3 - left

    //Attack control
    private long lastAttackTimer, attackCoolDown = 200, attackTimer = 0;

    public Monster(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        //Attack
        attackAmount = 2;

        //Bounding rectangle
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animation
        speed = 0.2f;

        walkAnims = new Animation[4];

        walkAnims[0] = new Animation(100, Assets.monster_up);
        walkAnims[1] = new Animation(100, Assets.monster_right);
        walkAnims[2] = new Animation(100, Assets.monster_down);
        walkAnims[3] = new Animation(100, Assets.monster_left);

        direction = 2;

    }

    @Override
    public void update() {
        float playerX = handler.getMap().getEntityManager().getPlayer().getX();
        float playerY = handler.getMap().getEntityManager().getPlayer().getY();
//        newPosition[0] = handler.getMap().getEntityManager().getPlayer().getX();
//        newPosition[1] = handler.getMap().getEntityManager().getPlayer().getY();
        if (Math.sqrt((playerX - x) * (playerX - x) + (playerY - y) * (playerY - y)) < 400) {
            chase(playerX, playerY);
            move();
            checkAttacks();
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(getCurrentPlayerFrame(walkAnims), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        renderHealth(g);
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

        walkAnims[this.direction].update();
        if (this.direction == 0) { //up
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;

        } else if (this.direction == 1) { //right
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;

        } else if (this.direction == 2) {//down
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;

        } else if (this.direction == 3) { //left
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;

        } else {
            return;
        }

        //wait another period of time
        attackTimer = 0;
        //end;

        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this) || e instanceof Monster || e instanceof StaticEntity || e instanceof SeniorMonster) {
                continue;
            } else if (e.getCollisionBounds(0f, 0f).intersects(ar)) {
                e.hurt(attackAmount);
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
            xMove = speed;
            walkAnims[0].update();
        }
        if (xMove < -1) {
            xMove = -speed;
            walkAnims[2].update();
        }
        if (yMove > 1) {
            yMove = speed;
            walkAnims[1].update();
        }
        if (yMove < -1) {
            yMove = -speed;
            walkAnims[3].update();
        }

    }

    @Override
    public void die() {
        handler.getMap().getEntityManager().setNumMonster(handler.getMap().getEntityManager().getNumMonster() - 1);
    }

    //private float[] randomPosition() {
    //    float x = (float) (Math.random() * handler.getMap().getWidth() + 1);
    //    float y = (float) (Math.random() * handler.getMap().getHeight() + 1);
    //    float[] res = new float[2];
    //    res[0] = x;
    //    res[1] = y;
    //    return res;
    //}
}
