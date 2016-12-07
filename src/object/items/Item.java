/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import graphics.Assets;
import java.awt.Color;
import main.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author LOREMSUM
 */
public abstract class Item {
    
    //THIS PART IS HANDLER - quickly way create essentially Item that we need in our Map2
    //If we have some items that have special abilities and things INSTEAD OF DOING THIS
    //we can always just override (extends) this Item class to make new class and create new instances of specific item class
    
    public static Item[] items = new Item[256]; //this array store essentially 1 instance of every single item in our game with different id
    public static Item HPItem = new HPItem("HPItem", 0);
    public static Item dollarItem = new DollarItem("Shield", 2);
    public static Item shieldItem = new ShieldItem("Shield", 3);
    public static Item swordItem = new SwordItem("Sword", 4);
    
    
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
    protected int count; // SỬ dụng để quản lý inventory
                         //nếu ta có 50 gỗ, thay vì tạo ra 50 đối tượng gỗ mới,  
                         //ta chỉ cần tạo ra 1 đối tượng gỗ mới với thuộc tính count = 50
    
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
    
    // Item có thể có 2 vị trí, một là nằm ở trên bản đồ, hai là ở trong inventory của người chơi
    // vì vậy ta sử dụng thêm 2 thuộc tính vị trí x, y để xác định ví trị item đc in ra trên bản đồ
    // ta có thể in ra ở vị trí inventory xác định.
    public void render(Graphics g){
        if(handler == null) //handler can be null because sometimes we don't need it in Item
            return;
        g.drawImage(texture,(int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT,  null);
        //In ra so luong cua Item do trong inventory
        g.drawString(new Integer(this.getCount()).toString(), x, y + this.bounds.height);

    }

    public abstract Item createNew(int x, int y);

    public void setPosition(int x, int y){
        //Sau khi được in ra bản đồ thì item mới có hình bao xác định collision
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }
    //Getters and setters
    
    public int getId() {
        return id;
    }
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

