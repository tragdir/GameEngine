package com.tragdir.engine;

import java.awt.image.DataBufferInt;
import com.tragdir.engine.gfx.Image;
import com.tragdir.engine.gfx.ImageTile;
import com.tragdir.engine.gfx.Font;

public class Renderer {

    //public static final Font STANDARD_FONT = new Font("resource/Fonts/font.JPG");

    private int pixelWidth, pixelHeight;
    private int[] pixels;
    private Font font;

    public Renderer(GameContainer gc) {
        pixelWidth = gc.getWidth();
        pixelHeight = gc.getHeight();

        pixels = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void drawText(String text, int offsetX, int offsetY, int color) {
        text = text.toUpperCase();
        int offset = 0;

        for (int i = 0; i < text.length(); i++) {
            int unicode = text.codePointAt(i) - 32; // SPACE is now 0
            for (int y = 0; y < font.getFontImage().getHeight(); y++) {
                for (int x = 0; x < font.getWidths()[unicode]; x++) {
                    if (font.getFontImage().getPixels()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getWidth()] == 0xffffffff) {
                        setPixel(x + offsetX + offset, y + offsetY + offset, color);
                    }   
                }
            }

            offset += font.getWidths()[unicode];

        }
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void setPixel(int x, int y, int value) { 
        if (x < 0 || x >= pixelWidth || y  < 0 || y >= pixelHeight || value == 0xffff00ff) {
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage (Image image, int offsetX, int offsetY) {

        int newX = 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        // Code for clipping
        if (offsetX < 0)
            newX -= offsetX;
        if (offsetY < 0)
            newY -= offsetY;
        if (newWidth + offsetX >= pixelWidth)
            newWidth -= newWidth + offsetX - pixelWidth;
        if (newHeight + offsetY >= pixelHeight) 
            newHeight -= newHeight + offsetY - pixelHeight;

        // Code to stop rendering
        if (offsetX < -newWidth) return;
        if (offsetY < -newHeight) return;
        if (offsetX >= pixelWidth) return;
        if (offsetY >= pixelHeight) return;

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offsetX, y + offsetY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawImageTile(ImageTile it, int offsetX, int offsetY, int tileX, int tileY) {
        int newX = 0;
        int newY = 0;
        int newWidth = it.getWidth();
        int newHeight = it.getHeight();

        // Code for clipping
        if (offsetX < 0)
            newX -= offsetX;
        if (offsetY < 0)
            newY -= offsetY;
        if (newWidth + offsetX >= pixelWidth)
            newWidth -= newWidth + offsetX - pixelWidth;
        if (newHeight + offsetY >= pixelHeight) 
            newHeight -= newHeight + offsetY - pixelHeight;

        // Code to stop rendering
        if (offsetX < -newWidth) return;
        if (offsetY < -newHeight) return;
        if (offsetX >= pixelWidth) return;
        if (offsetY >= pixelHeight) return;

        for (int y = newY; y < newHeight; y++) {
            for (int x = newX; x < newWidth; x++) {
                setPixel(x + offsetX, y + offsetY, it.getPixels()[(x + tileX + it.getWidth()) + (y + tileY + it.getHeight()) * it.getWidth()]);
            }
        }
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    public int getPixelHeight() {
        return pixelHeight;
    }
}
