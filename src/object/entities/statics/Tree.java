/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities.statics;

import graphics.Assets;
import object.items.Item;
import main.Handler;
import object.tiles.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2);
        
        bounds.x = 19;
        bounds.y = 105;
        bounds.width = 30;
        bounds.height = 30;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()) - 28,
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        renderHealth(g, Color.GREEN);
//        renderCollisionBounds(g);
    }

    @Override
    public void die() {
        handler.getMap().getItemManager().addItem(Item.HPItem.createNew((int) x, (int) y));
    }
}
