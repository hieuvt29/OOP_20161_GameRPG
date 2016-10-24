/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.tiles;

import hieu.tilegame.gfx.Assets;

/**
 *
 * @author LOREMSUM
 */
public class TreeTile extends Tile{
    
    public TreeTile(int id){
        super(Assets.tree, id);
    }
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
