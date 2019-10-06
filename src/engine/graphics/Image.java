package engine.graphics;

import java.io.File;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

/**
 * A simple wrapper around Java's BufferedImage class.
 * Handles loading of the image via the constructor.
 * 
 * You are supposed to load your Images once and share
 * them to the objects that need them, e.g. Sprites.
 */
public class Image {
    private BufferedImage data;

    /**
     * Create a new Image. This function will not return
     * until the image has been loaded from disk and decoded
     * into memory.
     * 
     * If the image is not found, the program will exit with
     * an error message.
     * 
     * @param file Path to the image file relative to the current working directory (usually project root)
     */
    public Image(String file) {
        try {
            data = ImageIO.read(new File(file));
        } catch (Exception e) {
            System.err.println("Error reading image " + file);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Gain direct access to the BufferedImage object
     * that this Image wraps.
     *  
     * @return a BufferedImage object
     */
    public BufferedImage getData() {
        return data;
    }

    /**
     * Get the width of this image, in pixels
     */
    public int getWidth() {
        return data.getWidth();
    }

    /**
     * Get the height of this image, in pixels
     */
    public int getHeight() {
        return data.getHeight();
    }
}
