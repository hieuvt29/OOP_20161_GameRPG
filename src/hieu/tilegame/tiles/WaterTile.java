/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.tiles;

import hieu.tilegame.gfx.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class WaterTile extends Tile {
    
    public WaterTile(int id) {
        super(Assets.water, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
    
}
