/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.states;

import graphics.Assets;
import main.Game;
import main.Handler;
import graphics.ui.ClickListener;
import graphics.ui.UIImageButton;
import graphics.ui.UIManager;
import graphics.ui.UITextButton;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author LOREMSUM
 */
public class MenuState extends State {

    private UIManager uiManager;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(100, 100, 128, 128, Assets.player_down, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                handler.getGame().setState(handler.getGame().getGameState());
            }
        }));
        
        
        
    }

    @Override
    public void update() {
        uiManager.update();
    }

    @Override
    public void render(Graphics g) {
        Color t = g.getColor();
        g.setColor(Color.magenta);
        g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
        g.setColor(t);
        uiManager.render(g);
       
    }
    
}
