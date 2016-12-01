/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Tile {
    
    //THIS PART IS HANDLER - quickly way create essentially Tile object that we need
    public static Tile[] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile treeTile = new TreeTile(1);
    public static Tile waterTile = new WaterTile(2);
    
    
    //THIS PART IS OUR CLASS
    private BufferedImage texture;
    private final int id; //we can use it for making our map
    public static final int TILE_WIDTH = 64, TILE_HEIGHT = 64; //sizing our images
    
    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        
        //Assign Tiles element
        tiles[id] = this;
    }

    public int getId() {
        return id;
    }
    
    public void update(){
        
    }
    public void render(Graphics g, int x, int y){
        g.drawImage(this.texture, x, y, TILE_WIDTH, TILE_HEIGHT, null );
        
    }
    public boolean isSolid(){
        return false;
    }
}
