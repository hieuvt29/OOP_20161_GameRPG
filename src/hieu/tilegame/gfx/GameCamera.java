/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.gfx;

import hieu.tilegame.entities.Entity;
import hieu.tilegame.main.Game;
import hieu.tilegame.main.Handler;
import hieu.tilegame.tiles.Tile;

/**
 *
 * @author LOREMSUM
 */
public class GameCamera {

    private float xOffset, yOffset; 
    //offset is basically a numbers that tell us how far off do we draw something from its original position
    //private Game game; //we need it for accessing to width and heigth of the Frame;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }

    private void clearBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset >= handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth()) {
            xOffset = handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset >= handler.getMap().getHeight() * Tile.TILE_HEIGHT - handler.getHeight()) { //wtfit?
            yOffset = handler.getMap().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
        }

    }

    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
    }

    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
        clearBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
