/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities.statics;

import graphics.Assets;
import java.awt.Color;
import object.items.Item;
import main.Handler;
import object.tiles.Tile;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class CoconutTree extends StaticEntity {

    public CoconutTree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2);

        bounds.x = 5;
        bounds.y = 105;
        bounds.width = 30;
        bounds.height = 30;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.coconutTree, (int) (x - handler.getGameCamera().getxOffset()) - 40,
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        //    renderHealth(g);
        Color temp_color = g.getColor();
        g.drawRect((int) (x - handler.getGameCamera().getxOffset()) - 15,
                (int) (y - handler.getGameCamera().getyOffset()) - 30, 100, 10);
        g.drawString(new Integer(getHealth()).toString(), (int) (x - handler.getGameCamera().getxOffset()) + 87,
                (int) (y - handler.getGameCamera().getyOffset()) - 20);

        g.setColor(Color.GREEN);
        g.fillRect((int) (x - handler.getGameCamera().getxOffset()) - 15,
                (int) (y - handler.getGameCamera().getyOffset()) - 30, getHealth() * 100 / getFull_health(), 10);
        g.setColor(temp_color);

    }

    @Override
    public void die() {
        handler.getMap().getItemManager().addItem(Item.dollarItem.createNew((int) x, (int) y));
    }

}