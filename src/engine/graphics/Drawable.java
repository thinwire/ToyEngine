package engine.graphics;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

public interface Drawable {

    /**
     * Draw graphics of this Drawable using Java's Graphics2D interface. The
     * ImageObserver instance is passed in in order to support methods like
     * Graphics2D.drawImage(), which requires it.
     * 
     * This function is called by the engine in order to make the Drawable display
     * itself.
     * 
     * @param g   a Graphics2D context, passed in from the engine side. The Drawable
     *            object will use this to command Java to put graphics on screen.
     * @param obs an ImageObserver instance provided by the engine, required for
     *            some Graphics2D calls.
     */
    public void draw(Graphics2D g, ImageObserver obs);

}
