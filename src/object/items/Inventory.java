/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import object.items.Item;
import main.Handler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class Inventory {

    private boolean active = false;
    private Handler handler;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();

    }

    public ArrayList<Item> getInventoryItems() {
        return inventoryItems;
    }

    public void setInventoryItems(ArrayList<Item> inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    public void update() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active) {
            return;
        }
        //Su dung HPItem
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_1)) {
            //Kiểm tra điều kiện nếu còn HPItem trong inventory
            // TĂng máu cho người chơi
            //Giảm số lượng HPItem đi 1
            // NẾu số lượng HPItem = 0 thì loại bỏ HPItem khỏi inventory
            Iterator<Item> it = inventoryItems.iterator();
            while (it.hasNext()) {
                Item i = it.next();
                if (i instanceof HPItem && i.getCount() > 0) {
                    handler.getMap().getEntityManager().getPlayer().increaseHP(((HPItem) i).getHPAmount());
                    i.setCount(i.getCount() - 1);
                    if (i.getCount() == 0) {
                        it.remove();
                    }
                    break;
                }
            }

        }
        //Su dung GoldItem
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)){
            // NẾu bấm 2, kiểm tra số lượng vàng, nếu có lớn 10 thì đổi 10 vàng thành 1 khiên
            Iterator<Item> it = inventoryItems.iterator();
            while(it.hasNext()){
                Item i = it.next();
                if(i instanceof DollarItem && i.getCount() > 10){
                    i.setCount(i.getCount() - 10);
                    inventoryItems.add(Item.shieldItem);
                    break;
                }
            }
        }
      
        
        System.out.println("INVENTORY: ");
        for (Item i : inventoryItems) {
            System.out.println(i.getName() + ": " + i.getCount());
        };
    }

    public void render(Graphics g) {
        if (!active) {
            return;
        }
        for (Item i : this.inventoryItems) {
            
            if (i instanceof HPItem) {
                i.render(g, 0, 0);
            } else if (i instanceof DollarItem) {
                i.render(g, Item.ITEM_WIDTH , 0);
            }else if (i instanceof ShieldItem){
                i.render(g, Item.ITEM_WIDTH * 2, 0);
            }
        }
    }

    public void addItem(Item item) {
        System.out.println("ID item :" + item.getId());
        Iterator<Item> it = inventoryItems.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return ;
            }
        }
        inventoryItems.add(item);
    }
}
