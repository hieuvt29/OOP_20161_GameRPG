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
public class ShieldItem extends Item {

    public final int FullDefenceAmount = 20;
    private int defenceAmount;

    public ShieldItem(String name, int id) {
        super(Assets.ShieldItem, name, id);
        defenceAmount = FullDefenceAmount;
    }

    @Override
    public Item createNew(int x, int y) {
        ShieldItem item = new ShieldItem(name, id);
        item.setPosition(x, y);
        return item;

    }

    public int getDefenceAmount() {
        return defenceAmount;
    }

    public void setDefenceAmount(int defenceAmount) {
        this.defenceAmount = defenceAmount;
    }
}
