/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.entities.statics;

import hieu.tilegame.entities.Entity;
import hieu.tilegame.main.Handler;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public abstract class StaticEntity extends Entity{

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
