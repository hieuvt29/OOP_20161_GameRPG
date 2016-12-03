/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import graphics.Assets;
import main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author LOREMSUM
 */
public class Item {
    
    //THIS PART IS HANDLER - quickly way create essentially Item that we need in our Map2
    //If we have some items that have special abilities and things INSTEAD OF DOING THIS
    //we can always just override (extends) this Item class to make new class and create new instances of specific item class
    
    public static Item[] items = new Item[256]; //this array store essentially 1 instance of every single item in our game with different id
    public static Item woodItem = new Item(Assets.wood, "Wood", 0);
    public static Item rockItem = new Item(Assets.rock, "Rock", 1);
    public static Item goldItem = new Item(Assets.gold, "Gold", 2);
    
    
    //THIS PART IS OUR CLASS
    public static final int ITEM_WIDTH = 32,
                            ITEM_HEIGHT = 32;
                            
    protected Handler handler;
    protected String name;
    protected BufferedImage texture;
    protected final int id;
    //Id is very important because when we get to making save files, we want to make sure to know 
    //where every item in the game is and what type of item is as well as the amout of item that user has
    //we will do that by accessing or storing these IDs
    //and to make it easier for us to access item through these ID
    protected boolean picked_up;
    protected Rectangle bounds;

    protected int x, y ;
    protected int count; //use for inventory function: 
                         //if we have 50 wood item, instead of creating 50 new instances of wood item, 
                         //we just create 1 instance of wood item with count = 50
    
    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.picked_up = false;
        
        count = 1;
        
        bounds = new Rectangle(x,y, ITEM_WIDTH, ITEM_HEIGHT);
        
        items[id] = this;
        
    }

    public void update(){
        if(handler != null){
            if(handler.getMap().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
                picked_up = true;
                handler.getMap().getEntityManager().getPlayer().getInventory().addItem(this);
            }
        }
    }
    
    //why we need 2 render methods?
    //because an item can be either in the game map lying on the ground or the item can be in the players inventory
    //so (x,y) to specify where this item on the map and we us render(Graphics g) to render it
    //and we use render(Graphics g, int x, int y) to render this item to a specific spot on screen in a specific inventory slot.
    public void render(Graphics g){
        if(handler == null) //handler can be null because sometimes we don't need it in Item
            return;
        g.drawImage(texture,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT,  null);
    }

    public Item createNew(int x, int y){
        Item item = new Item(texture, name, id);
        item.setPosition(x, y);
        return item;
    }

    public int getId() {
        return id;
    }
    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    //Getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }
    
    public boolean isPicked_up() {
        return picked_up;
    }

    public void setPicked_up(boolean picked_up) {
        this.picked_up = picked_up;
    }
    
}

