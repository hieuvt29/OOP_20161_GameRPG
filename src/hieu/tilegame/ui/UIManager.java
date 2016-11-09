/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.ui;

import hieu.tilegame.main.Handler;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author LOREMSUM
 */
public class UIManager {

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler) {
        this.handler = handler;
        objects = new ArrayList<>();

    }

    public void update() {

    }

    public void render(Graphics g) {
        for (UIObject o : objects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : objects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject uo) {
        objects.add(uo);
    }

    public void removeObject(UIObject uo) {
        objects.remove(uo);
    }

    public Handler getHandler() {
        return handler;
    }

    public ArrayList<UIObject> getObjects() {
        return objects;
    }

}
