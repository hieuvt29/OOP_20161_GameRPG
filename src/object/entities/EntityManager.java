/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.entities;

import object.entities.creatures.Player;
import main.Handler;
import object.Map;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author LOREMSUM
 */
public class EntityManager {

    private Player player;
    private int numMonster; //Số lượng quái trong bản đồ

    private Handler handler;
    private ArrayList<Entity> entities;
    
    private Comparator<Entity> entityComparator = new Comparator<Entity>() {
        @Override
        public int compare(Entity o1, Entity o2) {
            if (o1.getY() + o1.bounds.y + o1.bounds.height < o2.getY() + o2.bounds.y + o2.bounds.height) {
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
        //we use iterator for looping through Array to make sure nothing goes wrong or none of entities is omitted.
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()) {
            Entity e = it.next();
            e.update();
            if (!e.isActive()) {
                it.remove();
            }
        }
        //sort based on relative of coordinate of every entities to make it more real when rendering
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

    public int getNumMonster() {
        return numMonster;
    }

    public void setNumMonster(int numMonster) {
        this.numMonster = numMonster;
    }
}
