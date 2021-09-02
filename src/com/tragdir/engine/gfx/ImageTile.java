package com.tragdir.engine.gfx;

public class ImageTile extends Image{
    
    private int tileW, tileH;
    
    public ImageTile(String path, int tileW, int tileH) {
        super(path);
        this.tileW = tileW;
        this.tileH = tileH;
    }

    // Getters and setters
    public int getWidth() {
        return tileW;
    }

    public int getHeight() {
        return tileH;
    }

    public void setWidth(int newWidth) {
        tileW = newWidth;
    }

    public void setHeight(int newHeight) {
        tileH = newHeight;
    }
}
