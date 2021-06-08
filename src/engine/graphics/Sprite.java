package engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import engine.math.Vec2;

/**
 * A Sprite is an image on screen. It takes a source
 * image, and draws it in some position.  
 */
public class Sprite extends GraphicsObject {

    private Image image;
    private BufferedImage source;
    private double offset_x = 0;
    private double offset_y = 0;

    /**
     * Create a new Sprite. By default the Sprite's offset
     * is set to the center of the image.
     *  
     * @param src an Image object.
     */
    public Sprite(Image src) {
        setImage(src);
        setOffset(src.getWidth() * 0.5, src.getHeight() * 0.5);
    }
    
    /**
     * Set the image to use for this Sprite.
     * 
     * @param src an Image object.
     */
    public void setImage(Image src) {
        image = src;
        source = src.getData();
        setSize(src.getWidth(), src.getHeight());
    }
    
    /**
     * Get the Image currently used for this Sprite.
     * 
     * @return an Image object
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * Set the image offset used for painting.
     * By default the offset corresponds to the center
     * of the image - i.e., it is set to 
     * {@code (getWidth() * 0.5, getHeight() * 0.5)}.
     * 
     * You can set this to whatever you want - a value of 0, 0
     * would place the top-left corner of the source image
     * at the sprite's position.
     * Think of this as the coordinate on the source image you
     * want to treat as the "center" of the sprite.
     * 
     * @param x 
     * @param y
     */
    public void setOffset(double x, double y) {
        offset_x = x;
        offset_y = y;
    }
    
    /**
     * Get the current sprite offset as a 2D vector.
     * 
     * @return a new Vec2 object
     */
    public Vec2 getOffset() {
        return new Vec2(offset_x, offset_y);
    }

    /**
     * Get the current sprite X offset. See the {@link #setOffset(double, double)}
     * function for an explanation.
     */
    public double getOffsetX() {
        return offset_x;
    }

    /**
     * Get the current sprite Y offset. See the {@link #setOffset(double, double)}
     * function for an explanation.
     */
    public double getOffsetY() {
        return offset_y;
    }
    
    /**
     * Get the left edge coordinate of the bounding box of this Sprite
     */
    public double getX0() {
        return getX() - offset_x;
    }
    
    /**
     * Get the to edge coordinate of the bounding box of this Sprite
     */
    public double getY0() {
        return getY() - offset_y;
    }
    
    /**
     * Get the right edge coordinate of the bounding box of this Sprite
     */
    public double getX1() {
        return getX0() + getWidth();
    }
    
    /**
     * Get the bottom edge coordinate of the bounding edge of this Sprite
     */
    public double getY1() {
        return getY0() + getHeight();
    }

    public void draw(Graphics2D g, ImageObserver obs) {

        // Exit out of the function without drawing anything if
        // we're not supposed to be visible.
        if (!isVisible()) {
            return;
        }

        // Figure out screen coordinates for where to draw the sprite.
        // We use the offset variables to shift the image such that the
        // sprite's coordinate matches up with the offset position in
        // the image (by default the center of the image).
        int x = (int) ((getX() - offset_x) + 0.5);
        int y = (int) ((getY() - offset_y) + 0.5);

        g.drawImage(source, x, y, obs);
    }

}
