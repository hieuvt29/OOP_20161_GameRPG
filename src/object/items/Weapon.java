/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object.items;

import java.awt.image.BufferedImage;

/**
 *
 * @author user
 */
public class Weapon extends Item{
    
    public Weapon(BufferedImage texture, String name, int id) {
        super(texture, name, id);
    }

    @Override
    public Item createNew(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
