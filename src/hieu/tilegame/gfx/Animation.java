/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Animation {
    private int speed;
    private BufferedImage[] frames;
    private int index;
    private long lastTime;
    private long timer = 0;
    
    
    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
        
    }
    
    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed){
            timer = 0;
            index ++;
            if(index >= frames.length){
                index = 0;
            }
        }
    }
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
