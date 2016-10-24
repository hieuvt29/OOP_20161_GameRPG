/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities.creatures;

import hieu.tilegame.entities.Entity;
import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import hieu.tilegame.tiles.Tile;

/**
 *
 * @author LOREMSUM
 */
public abstract class Creature extends Entity{
    
    public static final int DEFAULT_HEALTH = 10; //I can change this value for each creature so it is more convenient to have a DEFAULT variable
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, 
                            DEFAULT_CREATURE_HEIGHT = 64;
    
    protected int health; //every creatures has its health
    protected float speed ; // and speed to move
    protected float xMove, yMove; //to move our creature in a OOP way, and follow a speed as well;
    
    public Creature(Handler handler, float x, float y, int width, int height){
        super(handler, x, y, width, height);
        
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }
    public void move(){
        if(!checkEntityCollisions(xMove, 0f)){
            moveX();
        }
        if(!checkEntityCollisions(0f, yMove)){
            moveY();
        }
    }
    private void moveX(){
        int newTileIndexX;
        if(xMove > 0){ //move right
            
            newTileIndexX = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if(handler.getMap().getTile(newTileIndexX, (int) (y + bounds.y) / Tile.TILE_HEIGHT).isSolid()  //check the upper-right corner
               || handler.getMap().getTile(newTileIndexX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT).isSolid() ){ //check the lower-right corner
                x = newTileIndexX * Tile.TILE_WIDTH - bounds.width - bounds.x - 1;
            }else{
                x += xMove;
            }
            
        }else if(xMove < 0){ //move left
            newTileIndexX = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if(handler.getMap().getTile(newTileIndexX, (int) (y + bounds.y) / Tile.TILE_HEIGHT).isSolid()  //check the upper-left corner
               || handler.getMap().getTile(newTileIndexX, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT).isSolid() ){ //check the lower-left corner
                x = (newTileIndexX + 1)* Tile.TILE_WIDTH - bounds.x;
            }else{
                x += xMove;
            }
        }
    }
    private void moveY(){
        int newTileIndexY;
        
        if(yMove > 0){//move down
            newTileIndexY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if(handler.getMap().getTile((int) (x + bounds.x) / Tile.TILE_WIDTH, newTileIndexY).isSolid()  //check the lower-left corner
               || handler.getMap().getTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH ,newTileIndexY).isSolid() ){ //check the lower-right corner
                y = newTileIndexY*Tile.TILE_HEIGHT - bounds.height - bounds.y - 1;
            }else{
                y += yMove;
            }
            
        }else if(yMove < 0){ //move up
            newTileIndexY = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if(handler.getMap().getTile((int) (x + bounds.x) / Tile.TILE_WIDTH, newTileIndexY).isSolid()  //check the upper-left corner
               || handler.getMap().getTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH ,newTileIndexY).isSolid() ){ //check the upper-right corner
                y = newTileIndexY*Tile.TILE_HEIGHT + bounds.y + 1;
            }else{
                y += yMove;
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
         
}
