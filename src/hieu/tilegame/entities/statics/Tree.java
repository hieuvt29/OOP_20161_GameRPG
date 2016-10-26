/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities.statics;

import hieu.tilegame.gfx.Assets;
import hieu.tilegame.main.Handler;
import hieu.tilegame.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class Tree extends StaticEntity {
    
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        
        bounds.x = 0;
        bounds.y = height/2;
        bounds.width = width;
        bounds.height = 2;
    }
    
    @Override
    public void update(){
        
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), 
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
     
    }

    @Override
    public void die() {
        
    }
}
