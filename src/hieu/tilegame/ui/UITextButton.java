/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class UITextButton extends UIObject{

    private String text;
    private ClickListener clicker;
    
    public UITextButton(float x, float y, int width, int height, String text, ClickListener clicker) {
        super(x, y, width, height);
        this.text = text;
        this.clicker = clicker;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        Color t = g.getColor();
        Font f = g.getFont();
        
        if(hovering){
            g.setColor(Color.BLACK);    
        }else{
            g.setColor(Color.WHITE);
        }
        
        g.setFont(new Font( "Arial", Font.BOLD, 20 ));
        g.drawString(text,(int) x,(int) y);
        
        
        g.setFont(f);
        g.setColor(t);
        
        
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
    
}
