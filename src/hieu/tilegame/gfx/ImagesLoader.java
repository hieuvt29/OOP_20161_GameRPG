/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author LOREMSUM
 */
public class ImagesLoader {
    /**
     * Just loader images for us
     */
    //image will be stored in BufferedImage type in Java
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImagesLoader.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(ImagesLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
        return null;
    }
    
}
