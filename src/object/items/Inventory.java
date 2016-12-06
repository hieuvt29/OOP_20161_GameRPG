/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import graphics.Animation;
import java.awt.Color;
import object.items.Item;
import main.Handler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import object.entities.creatures.Player;

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
            useHPItem();

        }
        //Su dung DollarItem
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_2)) {
            useDollarItem();
        }

        //Su dung SwordItem
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_4)) {
            if(handler.getMap().getEntityManager().getPlayer().getCurrentAnimSet() == Animation.animSetSpear){
                useSwordItem();
            }else if(handler.getMap().getEntityManager().getPlayer().getCurrentAnimSet() == Animation.animSetSword)
                useSpearItem();
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
        if (this.inventoryItems.size() != 0) {
            Color c = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Item.ITEM_WIDTH * 4, Item.ITEM_HEIGHT);
            g.setColor(c);
        }

        for (Item i : this.inventoryItems) {

            if (i instanceof HPItem) {
                i.render(g, 0, 0);
            } else if (i instanceof DollarItem) {
                i.render(g, Item.ITEM_WIDTH, 0);
            } else if (i instanceof ShieldItem) {
                i.render(g, Item.ITEM_WIDTH * 2, 0);
            } else if (i instanceof SwordItem) {
                i.render(g, Item.ITEM_WIDTH * 3, 0);
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
                return;
            }
        }
        inventoryItems.add(item);
    }

    private void useHPItem() {
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

    private void useDollarItem() {
        // NẾu bấm 2, kiểm tra số lượng DollarItem,
        //Nếu lớn hơn hoặc bằng 10 thì đổi 10 Dollar bằng 1 giáo
        //Nếu lớn hơn hoặc bằng 5 thì đổi 5 Dollar bằng 1 khiên
        Iterator<Item> it = inventoryItems.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i instanceof DollarItem) {
                if (i.getCount() >= 10) {
                    i.setCount(i.getCount() - 10);
                    addItem(Item.swordItem);
                } else if (i.getCount() >= 5) {
                    i.setCount(i.getCount() - 5);
                    addItem(Item.shieldItem);
                }
                break;
            }
        }
    }

    private void useSwordItem() {
        /*
        Kiểm tra xem trong inventory có SwordItem hay khoong
        Nếu có ta sẽ: 
            tăng attack Range của Player và bắt đầu
            thay đổi Animation
        
         */
        Iterator<Item> it = inventoryItems.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i instanceof SwordItem && i.getCount() > 0) {

                Player player = handler.getMap().getEntityManager().getPlayer();
                player.setAttackRange(50);
                player.setCurrentAnimSet(Animation.animSetSword);
//                i.setCount(i.getCount() - 1);
//                if (i.getCount() == 0) {
//                    it.remove();
//                }
                break;
            }
        }

    }

    private void useSpearItem() {
        Player player = handler.getMap().getEntityManager().getPlayer();
        player.setAttackRange(20);
        player.setCurrentAnimSet(Animation.animSetSpear);
    }
}
