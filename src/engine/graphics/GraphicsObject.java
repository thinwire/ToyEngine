package engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

import engine.math.Vec2;

/**
 * The abstract base class for all objects that can be shown on screen by the
 * Toy Engine. It is not required to use this class, but it provides basic
 * positioning and visibility information, so that the user does not need to
 * replicate the standard boilerplate.
 */
public abstract class GraphicsObject implements Drawable {

    private double pos_x = 0.0;
    private double pos_y = 0.0;
    private double size_x = 0.0;
    private double size_y = 0.0;
    private boolean visible = true;

    public GraphicsObject() {}

    /**
     * Make this graphics object visible or invisible.
     * An invisible graphics object will not be drawn to screen,
     * but will otherwise behave the same as a visible object.
     * 
     * @param b a boolean value
     */
    public void setVisible(boolean b) {
        visible = b;
    }

    /**
     * Return true if this graphics object is visible. 
     * Graphics object are visible by default.
     * 
     * @return
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Set the size of this graphics object. This method is
     * not public on purpose, so that the size of graphics
     * objects are not accidentally changed.
     * 
     * @param width new width value
     * @param height new height value
     */
    protected void setSize(double width, double height) {
        size_x = width;
        size_y = height;
    }

    /**
     * Return the size of this graphics object as a 2D vector.
     * 
     * @return a Vec2 object with x set to width and y set to height
     */
    public Vec2 getSize() {
        return new Vec2(size_x, size_y);
    }

    /**
     * Return the width of the graphics object
     */
    public double getWidth() {
        return size_x;
    }

    /**
     * Return the height of this graphics object
     */
    public double getHeight() {
        return size_y;
    }

    /**
     * Set the position of this graphics object.
     * The position is in absolute screen coordinates;
     * 0, 0 is at the top left corner of the screen.
     * X grows toward the right and Y grows toward the
     * bottom.
     * 
     * @param x a double value
     * @param y a double value
     */
    public void setPosition(double x, double y) {
        pos_x = x;
        pos_y = y;
    }

    /**
     * Return the position of this graphics object as a
     * 2D vector.
     * 
     * @return a Vec2 object representing this object's current position
     */
    public Vec2 getPosition() {
        return new Vec2(pos_x, pos_y);
    }

    /**
     * Change the position of this graphics object. The
     * parameters will be added to the current position values.
     * 
     * @param dx change in X position
     * @param dy change in Y position
     */
    public void move(double dx, double dy) {
        pos_x += dx;
        pos_y += dy;
    }

    /**
     * Return the current X position of this graphics object
     */
    public double getX() {
        return pos_x;
    }

    /**
     * Return thie current Y position of this graphics object
     */
    public double getY() {
        return pos_y;
    }

    @Override
    public abstract void draw(Graphics2D g, ImageObserver obs);

}
