/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import graphics.Assets;
import main.Handler;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author LOREMSUM
 */
public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        items = new ArrayList<>();

    }

    public void update() {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            item.update();
            if (item.isPicked_up()) {
                //if item is picked up so we'll remove it from map and place it into player inventory
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (Item i : items) {
            i.render(g);
        }
    }

    public void addItem(Item i) {
        i.setHandler(handler); //this item doesn't have handler when it was created
        items.add(i);
    }
    //Getters and setters

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

}
