/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities.creatures;

import object.entities.Entity;
import graphics.Animation;
import graphics.Assets;
import object.items.Inventory;
import main.Game;
import main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.states.MenuState;
import main.states.PlayState;
import object.tiles.Tile;

/**
 *
 * @author LOREMSUM
 */
public class Player extends Creature {

    //Animation
    
    private Animation[] walkAnims;
    private Animation[] hitAnims;
    private int direction; // 0- up, 1 - right, 2- down, 3 - left

    //Attack control
    private long lastAttackTimer, attackCoolDown = 200, attackTimer = 0;

    //Inventory
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
       
        //Attack
        attackAmount = 10;
        
        
        //Bounding rectangle
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        //Animation
        speed = 2f;
        walkAnims = new Animation[4];
        hitAnims = new Animation[4];
        
        walkAnims[0] = new Animation(100, Assets.player_up);
        walkAnims[1] = new Animation(100, Assets.player_right);
        walkAnims[2] = new Animation(100, Assets.player_down);
        walkAnims[3] = new Animation(100, Assets.player_left);
        
        hitAnims[0] = new Animation(100, Assets.player_hit_up);
        hitAnims[1] = new Animation(100, Assets.player_hit_right);
        hitAnims[2] = new Animation(100, Assets.player_hit_down);
        hitAnims[3] = new Animation(100, Assets.player_hit_left);
        

        direction = 2;

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
        
        //Kiểm tra người chơi vào vùng chuyển tiếp giữa 2 map hay chưa
        
        if( (int)(this.x + this.width)/Tile.TILE_WIDTH  == handler.getMap().getGateX() && 
            (int)(this.y+this.height)/Tile.TILE_HEIGHT == handler.getMap().getGateY())
        {
            PlayState ps = (PlayState) handler.getGame().getPlayState();
            if(ps.getMapIndex() == 1){
                ps.setMapIndex(2);
                System.out.println("Dang o map 2");
            }else if(ps.getMapIndex() == 2){
                ps.setMapIndex(1);
                System.out.println("Dang o map 1");
            }
        }
        if(handler.getMap().getEntityManager().getNumMonster() == 0 && 
           (int)(this.x + this.width)/Tile.TILE_WIDTH == handler.getMap().getEndX()&& 
           (int)(this.y+this.height)/Tile.TILE_HEIGHT == handler.getMap().getEndY())
        {
            handler.getGame().setState(new MenuState(handler));
        }

        
    }

    @Override
    public void render(Graphics g) {
        if(handler.getKeyManager().hit){
            g.drawImage(getCurrentPlayerFrame(hitAnims), (int) (x - handler.getGameCamera().getxOffset()) - width,
                (int) (y - handler.getGameCamera().getyOffset()) - height, width*3, height*3, null);
        }else{
            g.drawImage(getCurrentPlayerFrame(walkAnims), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        }
        
//      //debug
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width,
//                bounds.height);

        //inventory
        inventory.render(g);
        renderHealth(g);
//        Color temp_color = g.getColor();
//        g.drawRect((int) (x - handler.getGameCamera().getxOffset()) - 10,
//                (int) (y - handler.getGameCamera().getyOffset()) - 15, 100, 10);
//        g.setColor(Color.RED);
//        g.fillRect((int) (x - handler.getGameCamera().getxOffset()) - 10,
//                (int) (y - handler.getGameCamera().getyOffset()) - 15, getHealth()* 100 / getFull_health() , 10);
//        System.out.println(getHealth() / getFull_health() * 100);
//        g.setColor(temp_color);

    }

    private BufferedImage getCurrentPlayerFrame(Animation[] anim) {
        if (xMove > 0) {
            direction = 1;
        } else if (xMove < 0) {
            direction = 3;
        } else if (yMove < 0) {
            direction =0;
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

        if (handler.getKeyManager().hit) {
            hitAnims[this.direction].update();
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

    private void getInput() {
        //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        //something to get quicker
        xMove = 0;
        yMove = 0;
        //done Strange things

        if (handler.getKeyManager().up) {
            yMove = -speed;
            walkAnims[0].update();
        }
        if (handler.getKeyManager().down) {
            yMove = speed;
            walkAnims[2].update();
        }
        if (handler.getKeyManager().left) {
            xMove = -speed;
            walkAnims[3].update();
        }
        if (handler.getKeyManager().right) {
            xMove = speed;
            walkAnims[1].update();
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
        System.exit(0);
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
