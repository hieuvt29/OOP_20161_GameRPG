/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.states;

import graphics.Assets;
import graphics.ui.ClickListener;
import graphics.ui.UIImageButton;
import graphics.ui.UIManager;
import java.awt.Color;
import java.awt.Graphics;
import main.Handler;

/**
 *
 * @author Dell
 */
public class WinState extends State {

    private UIManager uimanager;
    private State menuState;

    public WinState(Handler handler) {
        super(handler);
        uimanager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uimanager);

        uimanager.addObject(new UIImageButton(0, 0, 640, 480, Assets.bgwin, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));
        uimanager.addObject(new UIImageButton(250, 320, 140, 50, Assets.btnNewgame, new ClickListener() {

            @Override
            public void onClick() {
                menuState = new MenuState(handler);
                GameStatesManager.setCurrentState(menuState);

            }
        }));
        uimanager.addObject(new UIImageButton(250, 400, 140, 50, Assets.btnQuitGame, new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void update() {
        uimanager.update();
    }

    @Override
    public void render(Graphics g) {
        Color t = g.getColor();
        g.setColor(Color.magenta);
        g.fillRect(0, 0, handler.getGame().getWidth(), handler.getGame().getHeight());
        g.setColor(t);
        uimanager.render(g);

    }

}
