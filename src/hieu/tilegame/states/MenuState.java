/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.states;

import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class MenuState extends State {

    public MenuState(Handler handler) {
        super(handler);
    }

    @Override
    public void update() {
        System.out.println(handler.getMouseManager().isLeftPressed() +" "+ handler.getMouseManager().isRightPressed());
        
        if(handler.getMouseManager().isLeftPressed() && handler.getMouseManager().isRightPressed()){
            handler.getGame().setState(handler.getGame().getGameState());
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)handler.getMouseManager().getMouseX(),(int) handler.getMouseManager().getMouseY(), 10, 10);
    }
    
}
