/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author LOREMSUM
 */
public class Assets {
    public static BufferedImage[] player_up, player_down, player_left, player_right;
    public static BufferedImage grass, tree, coconut_tree, water,
                                monster1, monster2,
                                wood, gold, rock;

    
    private static final int WIDTH = 16, HEIGHT = 16;
    
    public static void init(){
        SpriteSheet playersheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/playersprites.gif"));
        player_up = new BufferedImage[2];
        player_down = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];
        
        player_down[0] = playersheet.crop(0, 0, WIDTH, HEIGHT);
        player_down[1] = playersheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        
        player_left[0] = playersheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        player_left[1] = playersheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
        
        player_right[0] = playersheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
        player_right[1] = playersheet.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
        
        player_up[0] = playersheet.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
        player_up[1] = playersheet.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
        
        SpriteSheet tileSet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/tileset.gif"));
        grass = tileSet.crop(WIDTH, 0, WIDTH, HEIGHT);
        tree = tileSet.crop(0, HEIGHT, WIDTH, HEIGHT);
        water = tileSet.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
        
        SpriteSheet coconut_tree_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/1a.png"));
        SpriteSheet monster1_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/1g.png"));
        SpriteSheet monster2_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/1f.png"));
        
        coconut_tree = coconut_tree_sheet.crop(0, 0, WIDTH * 8, HEIGHT * 8);
        monster1 = monster1_sheet.crop(0, 0, WIDTH * 8, HEIGHT * 8);
        monster2 = monster2_sheet.crop(0, 0, WIDTH * 8, HEIGHT * 8);
        
        //items
        SpriteSheet gold_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/gold.gif"));
        gold = gold_sheet.crop(0, 0, 64, 41);
        
        SpriteSheet rock_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/rock.png"));
        rock = rock_sheet.crop(0, 0, 256, 256);
        
        SpriteSheet wood_sheet = new SpriteSheet(ImagesLoader.loadImage("/res/textures/wood.png"));
        wood = wood_sheet.crop(0, 0, 64, 64);
        
    }
    
}
