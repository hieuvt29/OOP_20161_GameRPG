/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities.creatures;

import hieu.tilegame.gfx.Animation;
import hieu.tilegame.gfx.Assets;
import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Player extends Creature{

    //Animation
    private Animation animUp, animDown, animLeft, animRight;
    
    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;
        
        //Animation
        animDown = new Animation(500, Assets.player_down);
        animUp = new Animation(500, Assets.player_up);
        animLeft =new Animation(500, Assets.player_left);
        animRight = new Animation(500, Assets.player_right);
    }

    @Override
    public void update() {
       getInput(); //this method just change the distance that we need to move in a movement dont actually change Player coordinate
       move(); //so we need to call this method to move our Player
       
       handler.getGameCamera().centerOnEntity(this); 
       
       animDown.update();
       animUp.update();
       animLeft.update();
       animRight.update();
    }
    
    private void getInput(){
        //this method just change the distance that we need to move in a movement dont actually change Player coordinate
        //something to get quicker
        xMove = 0;
        yMove = 0;
        //done Strange things
        
        if(handler.getKeyManager().up){
           yMove = -speed;
       }
       if(handler.getKeyManager().down){
           yMove = speed;
       }
       if(handler.getKeyManager().left){
           xMove = -speed;
       }
       if(handler.getKeyManager().right){
           xMove = speed;
       }
       
       //debug
       if(handler.getKeyManager().respawn){
           x = width/2 ;
           y = width/2;
       }
       if(handler.getKeyManager().quit){
           System.exit(0);
       }
       //end;
        
        
    }
    
    @Override
    public void render(Graphics g) {
        
        g.drawImage(getCurrentPlayerFrame(), (int)(x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
//        g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()), 
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width,
//                bounds.height);
    }
    private BufferedImage getCurrentPlayerFrame(){
        if(xMove > 0){
            return animRight.getCurrentFrame();
        }else if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
        }else{
            return animDown.getCurrentFrame();
        }
    }
}
