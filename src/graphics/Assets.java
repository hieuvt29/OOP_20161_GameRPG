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
    public static BufferedImage[] player_up_spear, player_down_spear, player_left_spear, player_right_spear;
    public static BufferedImage[] player_hit_up_spear, player_hit_down_spear, player_hit_left_spear, player_hit_right_spear;

    public static BufferedImage[] monster_up, monster_down, monster_left, monster_right;
    public static BufferedImage[] senior_monster_up, senior_monster_down, senior_monster_left, senior_monster_right;
    public static BufferedImage[] backgroundmenu, btnStart, btnQuit, btnInstructions, btnNewgame, btnQuitGame, bggameover,bgwin;

    public static BufferedImage grass, tree, coconutTree, water, dollarItem, door, shieldItem, HPItem, swordItem, flagWin;

    private static final int WIDTH = 64, HEIGHT = 64;

    public static void init() {

        //player
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

        Sheet playerhitsheet = new Sheet(ImagesLoader.loadImage("/res/textures/player_hit_sword.png"));
        player_hit_up = new BufferedImage[6];
        player_hit_down = new BufferedImage[6];
        player_hit_left = new BufferedImage[6];
        player_hit_right = new BufferedImage[6];

        for (int i = 0; i < 6; i++) {

            player_hit_up[i] = playerhitsheet.crop(WIDTH * i, 0, WIDTH, HEIGHT);
            player_hit_left[i] = playerhitsheet.crop(WIDTH * i, HEIGHT, WIDTH, HEIGHT);
            player_hit_down[i] = playerhitsheet.crop(WIDTH * i, HEIGHT * 2, WIDTH, HEIGHT);
            player_hit_right[i] = playerhitsheet.crop(WIDTH * i, HEIGHT * 3, WIDTH, HEIGHT);

        }

        //Player with Spear
        Sheet playersheet_spear = new Sheet(ImagesLoader.loadImage("/res/textures/player_hit_walk_spear.png"));
        player_hit_up_spear = new BufferedImage[8];
        player_hit_down_spear = new BufferedImage[8];
        player_hit_left_spear = new BufferedImage[8];
        player_hit_right_spear = new BufferedImage[8];

        player_up_spear = new BufferedImage[9];
        player_down_spear = new BufferedImage[9];
        player_left_spear = new BufferedImage[9];
        player_right_spear = new BufferedImage[9];

        for (int i = 0; i < 8; i++) {

            player_hit_up_spear[i] = playersheet_spear.crop(WIDTH * i, 0, WIDTH, HEIGHT);
            player_hit_left_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT, WIDTH, HEIGHT);
            player_hit_down_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 2, WIDTH, HEIGHT);
            player_hit_right_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 3, WIDTH, HEIGHT);

        }
        for (int i = 0; i < 9; i++) {

            player_up_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 4, WIDTH, HEIGHT);
            player_left_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 5, WIDTH, HEIGHT);
            player_down_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 6, WIDTH, HEIGHT);
            player_right_spear[i] = playersheet_spear.crop(WIDTH * i, HEIGHT * 7, WIDTH, HEIGHT);

        }
        //monster map 1
        Sheet monster_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/monster_walk_lv1.png"));
        monster_up = new BufferedImage[9];
        monster_down = new BufferedImage[9];
        monster_left = new BufferedImage[9];
        monster_right = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {

            monster_up[i] = monster_sheet.crop(WIDTH * i, 0, WIDTH, HEIGHT);
            monster_left[i] = monster_sheet.crop(WIDTH * i, HEIGHT, WIDTH, HEIGHT);
            monster_down[i] = monster_sheet.crop(WIDTH * i, HEIGHT * 2, WIDTH, HEIGHT);
            monster_right[i] = monster_sheet.crop(WIDTH * i, HEIGHT * 3, WIDTH, HEIGHT);

        }

        //senior monster map 2
        Sheet senior_monster_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/monster_hit_walk_die_lv2.png"));
        senior_monster_up = new BufferedImage[9];
        senior_monster_down = new BufferedImage[9];
        senior_monster_left = new BufferedImage[9];
        senior_monster_right = new BufferedImage[9];

        for (int i = 0; i < 9; i++) {

            senior_monster_up[i] = senior_monster_sheet.crop(WIDTH * i, HEIGHT * 4, WIDTH, HEIGHT);
            senior_monster_left[i] = senior_monster_sheet.crop(WIDTH * i, HEIGHT * 5, WIDTH, HEIGHT);
            senior_monster_down[i] = senior_monster_sheet.crop(WIDTH * i, HEIGHT * 6, WIDTH, HEIGHT);
            senior_monster_right[i] = senior_monster_sheet.crop(WIDTH * i, HEIGHT * 7, WIDTH, HEIGHT);

        }

        //grass
        Sheet grass_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/grass.png"));
        grass = grass_sheet.crop(50, 50, WIDTH, HEIGHT);

        //water
        Sheet water_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/water.gif"));
        water = water_sheet.crop(3, 3, WIDTH, HEIGHT);

        //trees
        Sheet coconut_tree_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/tree1.png"));
        Sheet tree_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/tree2.png"));
        coconutTree = coconut_tree_sheet.crop(0, 295, 140, 150);
        tree = tree_sheet.crop(545, 0, 175, 200);

        //DollarItem
        Sheet dollarItem_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/Dollar.png"));
        dollarItem = dollarItem_sheet.crop(0, 0, 33, 34);

        //hp
        Sheet hp_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/heart.png"));
        HPItem = hp_sheet.crop(0, 0, 33, 31);

        //door
        Sheet door_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/door.png"));
        door = door_sheet.crop(0, 0, 66, 61);

        //shieldItem
        Sheet shieldItem_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/shield.png"));
        shieldItem = shieldItem_sheet.crop(0, 0, WIDTH, HEIGHT);
        
        //swordItem
        Sheet swordItem_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/sword.png"));
        swordItem = swordItem_sheet.crop(0,0, WIDTH, HEIGHT);
        
        //flagWin
        Sheet flagWin_sheet = new Sheet(ImagesLoader.loadImage("/res/textures/Flag.png"));
        flagWin = flagWin_sheet.crop(0, 0, 57, 62);

        //menu
        backgroundmenu = new BufferedImage[2];
        btnStart = new BufferedImage[2];
        btnQuit = new BufferedImage[2];
        btnNewgame = new BufferedImage[2];
        btnQuitGame = new BufferedImage[2];
        btnInstructions = new BufferedImage[2];
        bggameover = new BufferedImage[2];
        bgwin = new BufferedImage[2];
        btnStart[0] = ImagesLoader.loadImage("/res/textures/btPlay1.png");
        btnStart[1] = ImagesLoader.loadImage("/res/textures/btPlay2.png");
        btnQuit[0] = ImagesLoader.loadImage("/res/textures/btnQuit.png");
        btnQuit[1] = ImagesLoader.loadImage("/res/textures/btnQuit.png");
        backgroundmenu[0] = ImagesLoader.loadImage("/res/textures/backgroundmenu.jpg");
        backgroundmenu[1] = ImagesLoader.loadImage("/res/textures/backgroundmenu.jpg");
        btnInstructions[0] = ImagesLoader.loadImage("/res/textures/instruction1.png");
        btnInstructions[1] = ImagesLoader.loadImage("/res/textures/instruction2.png");
        btnNewgame[0] = ImagesLoader.loadImage("/res/textures/newgame.png");
        btnNewgame[1] = ImagesLoader.loadImage("/res/textures/newgame.png");
        btnQuitGame[0] = ImagesLoader.loadImage("/res/textures/quitgame.png");
        btnQuitGame[1] = ImagesLoader.loadImage("/res/textures/quitgame.png");
        bggameover[0] = ImagesLoader.loadImage("/res/textures/bggameover.jpg");
        bggameover[1] = ImagesLoader.loadImage("/res/textures/bggameover.jpg");
        bgwin[0] = ImagesLoader.loadImage("/res/textures/bgyouwin.jpg");
        bgwin[1] = ImagesLoader.loadImage("/res/textures/bgyouwin.jpg");
        
        
    }

}
