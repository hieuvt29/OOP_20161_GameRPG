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
    public static BufferedImage[] player_hit_up, player_hit_down, player_hit_left, player_hit_right;

    public static BufferedImage grass, tree, coconut_tree, water,
            monster1, monster2,
            wood, gold, rock,
            HPItem, ShieldItem;

    private static final int WIDTH = 64, HEIGHT = 64;

    public static void init() {
        
        //Get graphics for player
        Sheet playersheet = new Sheet(ImagesLoader.loadImage("/res/textures/walk_lv1_64x64.png"));

        player_up = new BufferedImage[9];
        player_down = new BufferedImage[9];
        player_left = new BufferedImage[9];
        player_right = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {

            player_up[i] = playersheet.crop(WIDTH * i, 0, WIDTH, HEIGHT);
            player_left[i] = playersheet.crop(WIDTH * i, HEIGHT, WIDTH, HEIGHT);
            player_down[i] = playersheet.crop(WIDTH * i, HEIGHT * 2, WIDTH, HEIGHT);
            player_right[i] = playersheet.crop(WIDTH * i, HEIGHT * 3, WIDTH, HEIGHT);

        }

        Sheet playerhitsheet = new Sheet(ImagesLoader.loadImage("/res/textures/hit_large_lv1_64x64.png"));
        player_hit_up = new BufferedImage[6];
        player_hit_down = new BufferedImage[6];
        player_hit_left = new BufferedImage[6];
        player_hit_right = new BufferedImage[6];

        int WIDTH_HIT = WIDTH ;
        int HEIGHT_HIT = HEIGHT;

        for (int i = 0; i < 6; i++) {
            
            player_hit_up[i] = playerhitsheet.crop(WIDTH_HIT * i, 0, WIDTH_HIT, HEIGHT_HIT);
            player_hit_left[i] = playerhitsheet.crop(WIDTH_HIT * i, HEIGHT_HIT, WIDTH_HIT, HEIGHT_HIT);
            player_hit_down[i] = playerhitsheet.crop(WIDTH_HIT * i, HEIGHT_HIT * 2, WIDTH_HIT, HEIGHT_HIT);
            player_hit_right[i] = playerhitsheet.crop(WIDTH_HIT * i, HEIGHT_HIT * 3, WIDTH_HIT, HEIGHT_HIT);

        }

        
        //Get graphics for Tiles
        Sheet tileSet = new Sheet(ImagesLoader.loadImage("/res/textures/tileset.gif"));
        grass = tileSet.crop(WIDTH / 4, 0, WIDTH / 4, HEIGHT / 4);
        tree = tileSet.crop(0, HEIGHT / 4, WIDTH / 4, HEIGHT / 4);
        water = tileSet.crop(WIDTH / 4 * 2, HEIGHT / 4, WIDTH / 4, HEIGHT / 4);

        Sheet coconut_tree_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/1a.png"));
        Sheet monster1_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/1g.png"));
        Sheet monster2_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/1f.png"));

        coconut_tree = coconut_tree_sheet.crop(0, 0, WIDTH * 2, HEIGHT * 2);
        monster1 = monster1_sheet.crop(0, 0, WIDTH * 2, HEIGHT * 2);
        monster2 = monster2_sheet.crop(0, 0, WIDTH * 2, HEIGHT * 2);

        //items
        Sheet gold_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/gold.gif"));
        gold = gold_sheet.crop(0, 0, 64, 41);

        Sheet rock_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/rock.png"));
        rock = rock_sheet.crop(0, 0, 256, 256);

        Sheet wood_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/wood.png"));
        wood = wood_sheet.crop(0, 0, 64, 64);
        
        //Get graphics for items
        
       Sheet ItemSheet  = new Sheet(ImagesLoader.loadImage("/res/textures/34x34icons.png"));
       HPItem = ItemSheet.crop(0, 0, 34, 34);
       
       ShieldItem = ItemSheet.crop(34 * 7, 34 * 7, 34, 34);
    }

}
