package engine.core;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for handling user input.
 * Any Java Virtual Keycode can be mapped to any named input
 * action. 
 *
 * By default we provide "LEFT", "RIGHT", "UP", "DOWN",
 * "FIRE" and "EXIT", mapped to left arrow, right arrow, up arrow,
 * down array, space and escape, respectively: this mapping
 * is defined in the Application class.
 */
public class Input {

    private Map<String, Integer> keyMap = new HashMap<>();
    private Set<Integer> activeKeys     = new HashSet<>();
    private Set<Integer> currentKeys    = new HashSet<>();
    private Set<Integer> lastKeys       = new HashSet<>();
    
    /**
     * Create a new Input object that attaches itself to an AWT Frame. This lets us
     * listen to input events that pass through the Frame.
     * 
     * @param f an AWT Frame object; see Screen.java for more on that
     */
    public Input(Frame f) {

        // Attach a KeyListener to the window to be able to listen
        // to the keyboard being used
        f.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // We do not need to react to this event at all
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Add the pressed key to the set of actively pressed keys.
                activeKeys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Remove the released key's symbol from the set of actively pressed keys.
                activeKeys.remove(e.getKeyCode());
            }
        });
    }
    
    /**
     * Clear all active key bindings.
     */
    public void clearBindings() {
        keyMap.clear();
    }
    
    /**
     * Bind a keyboard symbol to an input name. You can find a list of keyboard symbols
     * in the Java KeyEvent class; see {@link https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html}
     * 
     * Example: {@code bind("FIRE", KeyEvent.VK_SPACE);}
     * 
     * @param inputName any string you may want to use as input name, e.g. "LEFT", "FIRE" or "GEORGE".
     * @param keySym  
     */
    public void bind(String inputName, int keySym) {
        keyMap.put(inputName, keySym);
    }
    
    /**
     * Remove a previously added key binding.
     * 
     * @param inputName a user defined input name, like "LEFT" or "FIRE".
     */
    public void unbind(String inputName) {
        keyMap.remove(inputName);
    }
    
    /**
     * Find a key for the given input name
     * 
     * @param inputName a user-defined input name
     * 
     * @return an Integer, or 0 if lookup fails
     */
    private Integer getKeyForInput(String inputName) {
        Integer key = keyMap.get(inputName);
        if(key == null) {
            System.err.println(String.format("Warning: no key mapped to input \"%s\"", inputName));
            return 0;
        }
        return key;
    }

    /**
     * Check if an input is currently down.
     * 
     * @param input an input symbol, see static fields of this class
     * 
     * @return true if a input is currently held down
     */
    public boolean isDown(String input) {
        return currentKeys.contains(getKeyForInput(input));
    }

    /**
     * Check if an input was pressed at the start of this frame, i.e. it is now
     * down, but was up last frame.
     * 
     * @param input an input symbol, see static fields of this class
     * 
     * @return true if button was pressed this frame
     */
    public boolean isPressed(String input) {
        Integer key = getKeyForInput(input);
        return currentKeys.contains(key) && !lastKeys.contains(key);
    }

    /**
     * Check if an input was released at the start of this frame, i.e. if it is now
     * up, but was down last frame.
     * 
     * @param input an input symbol, see static fields of this class
     * 
     * @return true if button was released this frame
     */
    public boolean isReleased(String input) {
        Integer key = getKeyForInput(input);
        return !currentKeys.contains(key) && lastKeys.contains(key);
    }

    /**
     * Update function - this cycles the input states. 
     * This function should be called once every frame.
     * 
     * This function is package protected so that only
     * Application may call it. 
     */
    void update() {
        lastKeys.clear();
        lastKeys.addAll(currentKeys);
        currentKeys.clear();
        currentKeys.addAll(activeKeys);
    }
}
