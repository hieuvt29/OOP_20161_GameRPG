/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieu.tilegame.main;

import hieu.tilegame.display.Display;
import hieu.tilegame.gfx.Assets;
import hieu.tilegame.gfx.GameCamera;
import hieu.tilegame.gfx.ImagesLoader;
import hieu.tilegame.input.KeyManager;
import hieu.tilegame.input.MouseManager;
import hieu.tilegame.states.GameState;
import hieu.tilegame.states.GameStatesManager;
import hieu.tilegame.states.MenuState;
import hieu.tilegame.states.State;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.TilePane;

/**
 *
 * @author LOREMSUM
 */
public class Game implements Runnable{
    private Display display; //we need an instance of Display class to display everythint int the game
    private String title;
    private int width, height; //we also need game title and axis;
    
    private Thread thread ; //this class is a runnable class so we need a Thread to run it 
    
    private boolean running; // store the state of our game
    
    //something that we need for render
    private BufferStrategy bs; 
    // like a hidden screen, you draw everything on this buffer and after drawing, it move all the things to actual screen
    // so we can prevent any flickering on our game
    
    private Graphics g;

    
    
    // State
    private State gameState;
    private State menuState;
    
    
    //Input: KeyManager instance
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    //GameCamera
    private GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    
    //Constructors and methods
    public Game(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        
        mouseManager = new MouseManager();
    }

    public State getGameState() {
        return gameState;
    }

    public State getMenuState() {
        return menuState;
    }
    
    public MouseManager getMouseManager() {
        return mouseManager;
    }
      public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    private void init(){
        display = new Display(title, width, height);
        Assets.init();
        
        //handler
        handler = new Handler(this);
        
        //init camera -*- REMEMBER THAT WE HAVE TO CREATE CAMERA BEFORE CREATE GAMEState 
        gameCamera = new GameCamera(this.handler, 0,0);
        
        //State 
        gameState = new GameState(this.handler);  //we want to maintain this Game object through all the program
        menuState = new MenuState(this.handler);
        
        GameStatesManager.setCurrentState(gameState);
        
        //add keyListener 
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }
    public void setState(State state){
        GameStatesManager.setCurrentState(state);
    }
    
    private void update(){
        keyManager.update();
        
        if(GameStatesManager.getCurrentState() != null)
            GameStatesManager.getCurrentState().update();
    }
    
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            //we really do not need more than 3 buffers
            return;
        }
        // for now, we can sure that we have BufferStrategy so we can start drawing
        //Graphics object is like a "magic paintBrush" allows us draw things on canvas
        g = bs.getDrawGraphics(); //create a paintBrush
        
        //Before we draw anything, we have to clear the screen
        g.clearRect(0, 0, width, height);
        //Start Drawing
       
        if(GameStatesManager.getCurrentState() != null)
            GameStatesManager.getCurrentState().render(g);
        
        //End Drawing
        
        bs.show(); //inform to Java that we're done drawing, switch the Buffer and display it to the screen
        g.dispose(); //make sure that Graphic object gets done with properly
        
    }
    @Override
    public void run() {
        init();
        //CONFIGURE THE FPS
        int fps = 40;
        double timePerTick = 1000000000/ fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        //end;
        
        //DEBUG FOR FPS
        long timer = 0;
        long ticks = 0;
        //end;

        while(running){
//            try {
//                thread.sleep(100);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //TO RUN EXACTLY THE FPS THAT WE SET BEFORE
            now = System.nanoTime();
            delta += (now - lastTime) /timePerTick;
            //debug
              timer += now - lastTime;
                    
            //end;
              lastTime = now;
            
            if(delta >= 1){
                update();
                render();
                delta--;
                //debug
                ticks ++;
                    
                //end;
            }
            //END;
            //debug
            if(timer > 1000000000){
               // System.out.println("fps: "+ticks);
                timer = 0; 
                ticks = 0;
            }
                    
            //end;

        update();
        render();
            
        }
        stop();
    }
   
    public synchronized void start(){ //synchronized ?? basically for working with thread
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     //We want another class can access this KeyManager instance so we need a get method
    public KeyManager getKeyManager(){
        return keyManager;
    }

  
    
    
}
