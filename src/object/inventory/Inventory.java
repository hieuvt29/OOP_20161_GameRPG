/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.inventory;

import object.items.Item;
import main.Handler;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Inventory {
    private boolean active = false;
    private Handler handler;
    private ArrayList<Item> inventoryItems;
    
    public Inventory(Handler handler){
        this.handler = handler;
        inventoryItems = new ArrayList<>();
        
    }
    
    public void update(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
            active = !active;
        }
        if(!active){
            return;
        }
        System.out.println("INVENTORY: ");
        for(Item i: inventoryItems){
            System.out.println(i.getName() +": " + i.getCount());
        };
    }
    
    public void render(Graphics g){
        if(!active)
            return;
    }
    
    public void addItem(Item item){
        for(Item i : inventoryItems){
            if(i.getId() == item.getId()){
                item.setCount(item.getCount()+ i.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }
}
