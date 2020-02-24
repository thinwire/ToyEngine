package engine.core;

import java.awt.Graphics2D;

/**
 * Minimalist interface to connect Application to Screen.
 * TODO: refactor this interface such that either its name
 * follows Java conventions (i.e. it is in the form of an
 * adjective) or change it into an abstract class.
 */
public interface ScreenPainter {

    void paint(Graphics2D g);

}
