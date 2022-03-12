/**
 * Files dedicated to the implementation of the game's graphical interface.
 */
package gui;

import java.awt.Canvas;
import java.awt.Graphics2D;

/**
 * Screen controller generator and features setup.
 */
public abstract class SceneController {

    private SceneSetup sceneSetup;

    public SceneController (SceneSetup sceneSetup) {
        this.sceneSetup = sceneSetup;
    }

    public SceneSetup getSceneSetup() {
        return sceneSetup;
    }

    public void setSceneSetup(SceneSetup sceneSetup) {
        this.sceneSetup = sceneSetup;
    }

    /**
     * Displays generated objects on screen.
     */
    public abstract void display(Graphics2D graphics);

    /**
     * Scene update on screen.
     */
    public abstract void updateScreen(double refresh);

    /**
     * Initializes scenes functions and features.
     */
    public abstract void init(Canvas canvas);
}