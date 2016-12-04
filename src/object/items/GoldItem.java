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
public class GoldItem extends Item {

    public GoldItem(String name, int id) {
        super(Assets.gold, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        GoldItem item = new GoldItem(name, id);
        item.setPosition(x, y);
        return item;
    }

}
