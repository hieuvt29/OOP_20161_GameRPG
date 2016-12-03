/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.states;

import object.entities.creatures.Player;
import graphics.Assets;
import main.Game;
import main.Handler;
import graphics.maps.Map;
import object.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class GameState extends State{
    
    private Map map, map1, map2;
    private int mapIndex;
    private Player player;
    
    public GameState(Handler handler){
        super(handler);
        
        this.player = new Player(handler, 0, 0);
        
        //The order of map and player is important
        map1 = new Map(handler, player, ".\\src\\res\\textures\\maps\\map1.txt");
        map2 = new Map(handler, player,  ".\\src\\res\\textures\\maps\\map2.txt");
        mapIndex = 1;
        map = map1;
        
    }
    @Override
    public void update() {
        if(mapIndex == 1){
           map = map1; 
        }else if(mapIndex == 2){
            map = map2;
        }
        map.update();
        
    }

    @Override
    public void render(Graphics g) {
        
        map.render(g);
        
        
    }
    
}
