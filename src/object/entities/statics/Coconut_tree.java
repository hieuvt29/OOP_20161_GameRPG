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
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Coconut_tree extends StaticEntity {

    public Coconut_tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2);

        bounds.x = 32;
        bounds.y = 64;
        bounds.width = 64;
        bounds.height = 64;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.coconut_tree, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width , height, null);
        renderHealth(g);
    }

    @Override
    public void die() {
        handler.getMap().getItemManager().addItem(Item.goldItem.createNew((int) x, (int) y));
    }

}

