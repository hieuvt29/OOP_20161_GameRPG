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
public class HpItem extends Item {

    private int HPAmount;

    public HpItem(String name, int id) {
        super(Assets.HPItem, name, id);
        HPAmount = 10;
    }

    public int getHPAmount() {
        return HPAmount;
    }

    public void setHPAmount(int HPAmount) {
        this.HPAmount = HPAmount;
    }

    @Override
    public Item createNew(int x, int y) {
        HpItem item = new HpItem(name, id);
        item.setPosition(x, y);
        return item;
    }

}
