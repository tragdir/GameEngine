package com.tragdir.game;

import com.tragdir.engine.GameContainer;
import com.tragdir.engine.Renderer;
import com.tragdir.engine.AbstractGame;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import com.tragdir.engine.gfx.ImageTile;


public class GameManager extends AbstractGame {

    private ImageTile image;

    public GameManager() {
        image = new ImageTile("resource/Images/small explosion animation.jpg", 16, 16);
    }

    @Override
    public void update(GameContainer gc, float dt) {
        if (gc.getInput().isKeyDown(KeyEvent.VK_A))
            System.out.println("A has been pressed");
        if (gc.getInput().getMouseWheel() < 0)
            System.out.println("Mouse wheel has moved up");
        if (gc.getInput().isButtonDown(MouseEvent.BUTTON1))
            System.out.println("Mouse has been pressed");

        
    }

    @Override
    public void render(GameContainer gc, Renderer renderer) {
        renderer.drawImage(image, gc.getInput().getX(), gc.getInput().getY());
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}
