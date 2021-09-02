package com.tragdir.engine.gfx;

public class Font {
    /*
     * Fonts are a single array for your custom font. The top layer is a data layer to find the widths of each character.
     * Add a color to signal start and end of the character.
     * 
     * Add the image with your font in the resources folder
     */

    private Image fontImage;
    private int[] offsets;
    private int[] widths;

    public Font(String path) {
        fontImage = new Image(path);
        offsets = new int[59]; // typical number of characters that I would need
        widths = new int[59];

        int unicode = 0;

        for (int i = 0; i < fontImage.getWidth(); i++) {
            if (fontImage.getPixels()[i] == 0xff00ff00 /* add hex color for start here */)
                offsets[unicode] = i;
            if (fontImage.getPixels()[i] == 0xff0000ff /* add hex color for end here */) {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }

        }

    }

    public void setFontImage(Image fontImage) {
        this.fontImage = fontImage;
    }

    public void setWidths(int[] widths) {
        this.widths = widths;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public Image getFontImage() {
        return fontImage;
    }

    public int[] getWidths() {
        return widths;
    }

    public int[] getOffsets() {
        return offsets;
    }
    
}
