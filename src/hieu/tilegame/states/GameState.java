/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.states;

import hieu.tilegame.entities.creatures.Player;
import hieu.tilegame.gfx.Assets;
import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import hieu.tilegame.maps.Map;
import hieu.tilegame.tiles.Tile;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class GameState extends State{
    
    private Map map;
    
    public GameState(Handler handler){
        super(handler);
        
        //The order of map and player is important
        map = new Map(handler, ".\\src\\res\\textures\\maps\\map1.txt");
        handler.setMap(map);
        

    }
    @Override
    public void update() {
        
        map.update();
        
    }

    @Override
    public void render(Graphics g) {
        
        map.render(g);
        
        
    }
    
}
