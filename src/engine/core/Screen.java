package engine.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;

/**
 * Game screen. 
 * Handles the game window and displaying
 * graphics on it.
 */
public class Screen {

    private Toolkit toolkit;
    private JFrame frame;
    private int width;
    private int height;
    private int frameWidth;
    private int frameHeight;
    private int yoffset;

    private ScreenPainter painter;
    private BufferStrategy bufstrat;

    /**
     * Create a new Screen with the specified width and height.
     * This method is package-protected so that only Application
     * may create the Screen.
     *  
     * @param width width of usable screen area
     * @param height height of usable screen area
     * @param painter the class doing the paint marshalling (i.e. the Application instance).
     */
    Screen(int width, int height, ScreenPainter painter) {
        // Assign painter
        this.painter = painter;

        // Create a frame (the actual visible app window)
        frame = new JFrame("Untitled Game");

        // Set the size of the frame and show it. Our app
        // will run as long as the frame is visible
        this.width = width;
        this.height = height;

        frame.setResizable(false);
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);

        frameWidth = frame.getWidth() + 1;
        frameHeight = frame.getHeight() + 1;
        yoffset = frameHeight - height;

        System.out.println("Screen width " + width + " height " + height + " requested");
        System.out.println("Window width " + frame.getWidth() + " height " + frame.getHeight());

        // We do _not_ want to repaint the panel contents when the operating system
        // wants to do so; we want to repaint when _we_ feel like it.
        // Set up double buffered drawing and store a reference to the buffer strategy
        // to let us control painting
        frame.setIgnoreRepaint(true);
        frame.createBufferStrategy(2);
        bufstrat = frame.getBufferStrategy();

        toolkit = frame.getToolkit();
    }

    /**
     * Get access to the underlying java.awt.Frame object
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Get access to the ImageObserver object (i.e. the Frame)
     */
    public ImageObserver getObserver() {
        return frame;
    }

    /**
     * Get the width of the currently displayable area
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the currently displayable area
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the title of the application window 
     * @param title a String to use as the window title
     */
    public void setTitle(String title) {
        frame.setTitle(title);
    }
    
    /**
     * Get the current title of the application window
     */
    public String getTitle() {
        return frame.getTitle();
    }

    /**
     * Update the screen, i.e. draw graphics as dictated by
     * the ScreenPainter (the Application instance).
     * 
     * This function is package protected so that only Application
     * may call it.
     */
    void update() {

        // Get a fresh graphics object from the buffer strategy
        Graphics g = bufstrat.getDrawGraphics();

        // Set color for clearing the screen
        g.setColor(Color.BLACK);

        // Translate the graphics context
        g.translate(0, yoffset);

        // Clear existing stuff on screen
        // The -10, +20 stuff works around the fact that the coordinates
        // as used by Java are not exact, and there may well be a one- or
        // even two pixel error in the actual drawable area.
        g.fillRect(-10, -10, frameWidth + 20, frameHeight + 20);
        
        // Let the client draw graphics to screen
        painter.paint((Graphics2D) g);

        // We're done painting. Apparently these need
        // to be disposed of to help the memory manager
        // work better.
        g.dispose();

        // Flip buffers to present a fresh new frame
        bufstrat.show();

        // Request synchronization of OS-side display. This allows
        // us to run smoothly (and possibly vsynced).
        toolkit.sync();
    }

}
