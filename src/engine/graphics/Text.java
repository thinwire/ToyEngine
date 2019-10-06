package engine.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * Text - an object that can put text onto the game screen.
 * Useful for things like score counters, short messages, etc.
 * Uses java.awt.Font and TrueType fonts for actual rendering;
 * see the {@link #loadFont(String)} and {@link #createFont(String, int, int)}
 * methods below. 
 */
public class Text extends GraphicsObject {

    /**
     * Load a TrueType font from disk. If the font cannot be loaded, the program
     * will exit with an error message. 
     */
    public static void loadFont(String file) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(file)));
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load font " + file);
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Create a new Java Font object. The Font needs to be available to
     * the system (i.e. previously loaded) in order for this to work.
     * Use the {@link #loadFont(String)} function to load a custom font from
     * disk.
     * 
     * This just directly calls the java.awt.Font constructor; see
     * {@link https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html#Font(java.lang.String,%20int,%20int)}
     * for more information.
     * 
     * @param family the font family name, e.g. "Arial". See the actual font file for the actual name.
     * @param style the style of font in the family, for example {@code Font.PLAIN} or {@code Font.BOLD}. See {@link https://docs.oracle.com/javase/7/docs/api/java/awt/Font.html} for full list.
     * @param size the size of the font, in points.
     * @return
     */
    public static Font createFont(String family, int style, int size) {
        return new Font(family, style, size);
    }

    private Font font;
    private Color color;
    private char[] data = null;
    private int length = 0;

    /**
     * Create a new Text object, for displaying a string of text on screen.
     * This requires one java.awt.Font object as parameter, as that will be
     * used by Java to actually figure out how to get the text onto screen.
     * 
     * @param font a java.awt.Font object, see {@link #createFont(String, int, int)} for information on how to get a Font object. 
     */
    public Text(Font font) {
        this.font = font;
        color = Color.WHITE;
    }

    /**
     * Set the text to display. The text is displayed at the size and
     * style indicated by the Font object this Text was created with.
     * 
     * @param text
     */
    public void setText(String text) {
        if (text == null || text.isEmpty()) {
            data = null;
            return;
        }

        length = text.length();
        data = text.toCharArray();

        // Set width and height to 0 so that they can be updated
        // when the text is drawn
        setSize(0, 0);
    }
    
    /**
     * Set the color to use for drawing the text.
     * The parameter takes in a java.awt.Color object;
     * see the Java documentation for more information:
     * {@link https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html}
     * 
     * @param color a java.awt.Color object
     */
    public void setColor(Color color) {
        assert(color != null);
        this.color = color;
    }
    
    /**
     * Get the color object currently used for drawing
     * the text.
     * 
     * @return a java.awt.Color object
     */
    public Color getColor() {
        return color;
    }

    @Override
    public void draw(Graphics2D g, ImageObserver obs) {

        // Exit out of the function without drawing anything if
        // we're not supposed to be visible.
        if (!isVisible()) {
            return;
        }

        if (data != null) {
            g.setFont(font);
            g.setColor(color);
            g.drawChars(data, 0, length, (int) (getX() + 0.5), (int) (getY() + 0.5));
        }
    }
}
