/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities;

import hieu.tilegame.entities.creatures.Player;
import hieu.tilegame.main.Handler;
import hieu.tilegame.maps.Map;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author LOREMSUM
 */
public class EntityManager {

    private Player player;

    private Handler handler;
    private ArrayList<Entity> entities;
    private Comparator<Entity> entityComparator = new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            if(o1.getY() + o1.bounds.y + o1.bounds.height < o2.getY() + o2.bounds.y + o2.bounds.height){
                return -1;
            }
            return 1;
        }
    };
    
    public EntityManager(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;

        entities = new ArrayList<>();
        addEntity(player);
    }

    public void update() {
        //we use original form of for loop to make sure it will not get bad when we add collision detection;
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.update();
            if(!e.isActive()){
                entities.remove(e);
            }
        }
        
        entities.sort(entityComparator);

    }

    public void render(Graphics g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }

    public void addEntity(Entity e) {
        entities.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
