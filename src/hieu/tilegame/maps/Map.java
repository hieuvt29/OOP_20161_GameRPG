/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.maps;

import hieu.tilegame.entities.EntityManager;
import hieu.tilegame.entities.creatures.Player;
import hieu.tilegame.entities.statics.Tree;
import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import hieu.tilegame.tiles.Tile;
import hieu.tilegame.utils.Utils;
import java.awt.Graphics;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;

/**
 *
 * @author LOREMSUM
 */
public class Map {

    private int width, height; //The number of tiles that map will get in width and height
    private int spawnX, spawnY; //where the player be appeared at first

    private int[][] map;

    private Handler handler;

    //Entities
    private EntityManager entityManager;
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Map(Handler handler, String path) {
        this.handler = handler;
        this.entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        this.entityManager.addEntity(new Tree(handler, 300, 500));
        loadMap(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    private void loadMap(String path) {
        String file = Utils.loadFileAsString(path);

        String[] tokens = file.split("\\s+");

        width = parseInt(tokens[0]);
        height = parseInt(tokens[1]);
        spawnX = parseInt(tokens[2]);
        spawnY = parseInt(tokens[3]);

        map = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = parseInt(tokens[(x + y * width) + 4]);
            }
        }

    }

    public void update() {
        entityManager.update();
    }

    public void render(Graphics g) {
//        //draw tiles according to the id in the map;
//        
//        //Because there so many tiles so we need to know where to draw it
//        // so THAT's WHY Tile.render() is different from some others class's render();
//        for(int y = 0 ; y < height; y ++){
//            for (int x = 0; x < width; x++){
//                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
//                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
//            }
//        }
        //Efficient render
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH); //add 1 to see the different
        int xEnd = (int) Math.min(width, (handler.getWidth() + handler.getGameCamera().getxOffset()) / Tile.TILE_WIDTH + 1); //omit 1 to see the different
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getHeight() + handler.getGameCamera().getyOffset()) / Tile.TILE_HEIGHT + 1);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
        
        //Entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        //if x, y is out of Map, so it can crash the game
        // so if player is out of Map, we're just going to say he's standing on the grass;
        if( x < 0 || x >= width || y < 0 || y >= height)
            return Tile.grassTile;
        
        Tile t = Tile.tiles[map[x][y]];
        
        if (t == null) {
            return Tile.grassTile;
        } else {
            return t;
        }
    }
}
