/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author LOREMSUM
 */
public class Assets {
    public static BufferedImage[] player_up, player_down, player_left, player_right;
    public static BufferedImage grass;
    public static BufferedImage tree;
    public static BufferedImage water;
    private static final int WIDTH = 16, HEIGHT = 16;
    
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/playersprites.gif"));
        player_up = new BufferedImage[2];
        player_down = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        
        player_down[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
        player_down[1] = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        
        player_left[0] = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        player_left[1] = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        
        player_right[0] = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
        player_right[1] = sheet.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
        
        player_up[0] = sheet.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
        player_up[1] = sheet.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
        
        SpriteSheet tileSet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/tileset.gif"));
        grass = tileSet.crop(WIDTH, 0, WIDTH, HEIGHT);
        tree = tileSet.crop(0, HEIGHT, WIDTH, HEIGHT);
        water = tileSet.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
      
    }
    
}
