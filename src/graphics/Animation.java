/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author LOREMSUM
 */
public class Animation {

    // OUT OF CLASS
    public static Animation[] walkSwordAnims, hitSwordAnims, walkSpearAnims, hitSpearAnims;
    public static Animation[][] currentAnimSet, animSetSword, animSetSpear;

    //CLASS
    private int speed;
    private BufferedImage[] frames;
    private int index;
    private long lastTime;
    private long timer = 0;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;

        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public static void init() {
        walkSwordAnims = new Animation[4];
        hitSwordAnims = new Animation[4];

        walkSwordAnims[0] = new Animation(100, Assets.player_up);
        walkSwordAnims[1] = new Animation(100, Assets.player_right);
        walkSwordAnims[2] = new Animation(100, Assets.player_down);
        walkSwordAnims[3] = new Animation(100, Assets.player_left);

        hitSwordAnims[0] = new Animation(100, Assets.player_hit_up);
        hitSwordAnims[1] = new Animation(100, Assets.player_hit_right);
        hitSwordAnims[2] = new Animation(100, Assets.player_hit_down);
        hitSwordAnims[3] = new Animation(100, Assets.player_hit_left);

        walkSpearAnims = new Animation[4];
        hitSpearAnims = new Animation[4];

        walkSpearAnims[0] = new Animation(100, Assets.player_up_spear);
        walkSpearAnims[1] = new Animation(100, Assets.player_right_spear);
        walkSpearAnims[2] = new Animation(100, Assets.player_down_spear);
        walkSpearAnims[3] = new Animation(100, Assets.player_left_spear);

        hitSpearAnims[0] = new Animation(100, Assets.player_hit_up_spear);
        hitSpearAnims[1] = new Animation(100, Assets.player_hit_right_spear);
        hitSpearAnims[2] = new Animation(100, Assets.player_hit_down_spear);
        hitSpearAnims[3] = new Animation(100, Assets.player_hit_left_spear);

        animSetSword = new Animation[2][4];
        animSetSword[0] = walkSwordAnims;
        animSetSword[1] = hitSwordAnims;

        animSetSpear = new Animation[2][4];
        animSetSpear[0] = walkSpearAnims;
        animSetSpear[1] = hitSpearAnims;

    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            timer = 0;
            index++;
            System.out.println("Frame index: " + index);
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
