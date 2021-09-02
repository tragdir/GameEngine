package com.tragdir.engine;

import java.awt.Dimension;

/*
 * Will hold the game loop and be the main thread for future games
 */

public class GameContainer implements Runnable {

    // All instance variables to run a game
    private Thread thread;
    private Window window;
    private Renderer renderer;
    private InputClass input;
    private AbstractGame game;
    private boolean isRunning = false;
     
    // All instance variables to change the window properties
    private final double UPDATE_CAP = 1.0 / 60.0; // framerate
    private int width = 200, height = 150;
    private float scale = 4f;
    private String title = "2D Window";

    public GameContainer(AbstractGame game) {
        this.game = game;
    }

    // Getters and Setters
    public void setDimensions(int width, int height) {
        width = this.width;
        height = this.height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width, height);
    }

    public void setScale(float scale) {
        scale = this.scale;
    }

    public void setTitle(String title) {
        title = this.title;
    }

    public float getScale() {
        return scale;
    }

    public String getTitle() {
        return title;
    }

    public Window getWindow() {
        return window;
    }

    public InputClass getInput() {
        return input;
    }

    // Calls the Run function and initialized everything
    public void start() {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new InputClass(this);
        thread = new Thread(this); // this is the runnable we want to start
        thread.start(); 
        isRunning = true;
    }

    // Will end all processes and 
    public void end() {
        
    }

    // Game loop
    @Override
    public void run() {
        final double BILLION = 1000000000.0;

        boolean render = false;
        double startTime = 0;
        double lastTime = System.nanoTime() / BILLION;
        double passedTime = 0;
        double unprocessedTime = 0;

        int frames = 0;
        int fps = 0;
        double frameTime = 0;

        while(isRunning) {
            render = false;
            startTime = System.nanoTime() / BILLION;
            passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                game.update(this, (float)UPDATE_CAP);
                input.update();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render) {
                renderer.clear();
                game.render(this, renderer);
                //renderer.drawText("FPS: " + fps, 0, 0, 0xffffff00);
                window.update();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        dispose();
    }

    private void dispose() {
        ;
    }
}
