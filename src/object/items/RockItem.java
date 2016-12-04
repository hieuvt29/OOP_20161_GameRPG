/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import graphics.Assets;
import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class RockItem extends Item {

    public RockItem(String name, int id) {
        super(Assets.rock, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        RockItem item = new RockItem(name, id);
        item.setPosition(x, y);
        return item;
    }

}
