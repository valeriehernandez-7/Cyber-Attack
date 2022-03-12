/**
 * Game object classes and dependencies implementation.
 */
package game.features;

import java.awt.Graphics2D;

/**
 * On-screen game element generator methods.
 */
public interface Generator {
    /**
     * Displays game element.
     */
    void display(Graphics2D graphics);
    /**
     * Update onscreen game element.
     */
    void updateScreen(double refresh);
}