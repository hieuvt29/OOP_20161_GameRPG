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
public class SwordItem extends Item {

    public static int attackAmount = 200;
    
    public SwordItem(String name, int id) {
        super(Assets.swordItem, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        SwordItem item = new SwordItem(name, id);
        item.setPosition(x, y);
        return item;
    }
    
}
