/**
 * Game object classes and dependencies implementation.
 */
package game.features;

import java.awt.Point;
import java.awt.Canvas;
import java.awt.MouseInfo;
import javax.swing.SwingUtilities;

/**
 * Provides cursor position on screen.
 */
public class Target {
    private Point aim;
    private Canvas controller;

    public Target(Canvas game) {
        controller = game;
    }

    // get cursor position
    public int getTarget () {
        return aim.x;
    }

    // update cursor position
    public void updateTarget () {
        aim = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(aim, controller);
    }
}