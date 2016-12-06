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
public class DollarItem extends Item {

    public DollarItem(String name, int id) {
        super(Assets.dollarItem, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        DollarItem item = new DollarItem(name, id);
        item.setPosition(x, y);
        return item;
    }

}
