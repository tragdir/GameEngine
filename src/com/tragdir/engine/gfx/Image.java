package com.tragdir.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Image {
    
    private int width, height;
    private int[] pixels;

    public Image(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new FileInputStream(path));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        width = image.getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0, width);

        image.flush();
    }

    public void setWidth(int w) {
        width = w;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int h) {
        height = h;
    }

    public int getHeight() {
        return height;
    }

    public void setPixels(int[] p) {
        pixels = p;
    }

    public int[] getPixels() {
        return pixels;
    }
}

