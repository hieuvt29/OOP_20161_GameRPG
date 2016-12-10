/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import object.entities.EntityManager;
import object.entities.creatures.Player;
import object.entities.statics.Tree;
import object.items.ItemManager;
import main.Game;
import main.Handler;
import object.tiles.Tile;
import logic.input.Utils;
import java.awt.Graphics;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import main.states.GameStatesManager;
import main.states.MenuState;
import main.states.PlayState;
import main.states.State;
import main.states.WinState;
import object.entities.creatures.Monster;
import object.entities.creatures.SeniorMonster;
import object.entities.statics.CoconutTree;

/**
 *
 * @author LOREMSUM
 */
public class Map {

    private int width, height; //The number of tiles that map will get in width and height
    private int spawnX, spawnY; //where the player be appeared at first

    private int gateX, gateY;// TỌa độ của cổng chuyển tiếp giữa 2 map theo bản đồ- Quy ước là 50
    private int endX, endY; //Tọa độ của ô kết thúc theo bản đồ- Quy ước là 100

    private ArrayList<ArrayList> entitiesCoordinate;// Toạ độ các thực thể theo bản đồ

    private int[][] map;

    private Handler handler;
    private State winstate;
    //Entities
    private EntityManager entityManager;

    //Item
    private ItemManager itemManager;

    public Map(Handler handler, Player player, String path) {
        this.handler = handler;

        entitiesCoordinate = new ArrayList<>();
        //(value, x, y) 
        //Quy ước value: 5 - cây ăn quả. 6 - cây lấy gỗ
        //  10 - quái vật

        loadMap(path);
        spawnX = 1;
        spawnY = 1;
        //init entityManager
        //Thêm người chơi và khởi tạo vị trí xuất hiện trên bản đồ
        this.entityManager = new EntityManager(handler, player);

        entityManager.getPlayer().setX(spawnX * Tile.TILE_WIDTH);
        entityManager.getPlayer().setY(spawnY * Tile.TILE_HEIGHT);

        for (int i = 0; i < entitiesCoordinate.size(); i++) {
            ArrayList<Integer> arr = entitiesCoordinate.get(i);
            // Thêm các đối tượng static
            int id = arr.get(0);
            int corX = arr.get(1) * Tile.TILE_WIDTH;
            int corY = arr.get(2) * Tile.TILE_HEIGHT;

            if (id == 5) {
                this.entityManager.addEntity(new Tree(handler, corX, corY));
            } else if (id == 6) {
                this.entityManager.addEntity(new CoconutTree(handler, corX, corY));
            } else if (id == 10) {
                entityManager.addEntity(new Monster(handler, corX, corY));
                entityManager.setNumMonster(entityManager.getNumMonster() + 1);
            } else if (id == 11) {
                entityManager.addEntity(new SeniorMonster(handler, corX, corY));
                entityManager.setNumMonster(entityManager.getNumMonster() + 1);
            }

        }
        //init itemManager
        itemManager = new ItemManager(handler);

    }

    private void loadMap(String path) {
        String file = Utils.loadFileAsString(path);

        String[] tokens = file.split("\\s+");

        width = parseInt(tokens[0]);
        height = parseInt(tokens[1]);

        map = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = parseInt(tokens[(x + y * width) + 2]);
                if (map[x][y] == 50) {
                    gateX = x;
                    gateY = y;
                } else if (map[x][y] == 100) {
                    endX = x;
                    endY = y;
                } else if (map[x][y] > 4) {
                    ArrayList<Integer> entityCor = new ArrayList();
                    entityCor.add(map[x][y]);
                    entityCor.add(x);
                    entityCor.add(y);
                    entitiesCoordinate.add(entityCor);
                }

            }
        }

    }

    public void update() {
        entityManager.update();
        itemManager.update();
        //Kiểm tra người chơi đi vào vùng chuyển map hoặc kết thúc
        checkChangeMap();
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

        //Items
        itemManager.render(g);
    }

    public Tile getTile(int x, int y) {
        //if x, y is out of Map, so it can crash the game
        // so if player is out of Map, we're just going to say he's standing on the grass;
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[map[x][y]];

        if (t == null) {
            return Tile.grassTile;
        } else {
            return t;
        }
    }

    private void checkChangeMap() {
        Player player = this.getEntityManager().getPlayer();
        if ((int) (player.getX() + player.getWidth()) / Tile.TILE_WIDTH == this.getGateX()
                && (int) (player.getY() + player.getHeight()) / Tile.TILE_HEIGHT == this.getGateY()) {
            PlayState ps = (PlayState) handler.getGame().getPlayState();
            if (ps.getMapIndex() == 1) {
                ps.setMapIndex(2);
                System.out.println("Dang o map 2");
            } else if (ps.getMapIndex() == 2) {
                ps.setMapIndex(1);
                System.out.println("Dang o map 1");
            }
        }
        System.out.println("So luong monster:" + this.getEntityManager().getNumMonster());
        if (//this.getEntityManager().getNumMonster() == 0
                 (int) (player.getX() + player.getWidth()) / Tile.TILE_WIDTH == this.getEndX()
                && (int) (player.getY() + player.getHeight()) / Tile.TILE_HEIGHT == this.getEndY()) {
            winstate = new WinState(handler);
             GameStatesManager.setCurrentState(winstate);
        }
    }

    //Getters and setters
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public int getGateX() {
        return gateX;
    }

    public void setGateX(int gateX) {
        this.gateX = gateX;
    }

    public int getGateY() {
        return gateY;
    }

    public void setGateY(int gateY) {
        this.gateY = gateY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

}
