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
public class DolaItem extends Item {

    public DolaItem(String name, int id) {
        super(Assets.dola, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        DolaItem item = new DolaItem(name, id);
        item.setPosition(x, y);
        return item;
    }

}
