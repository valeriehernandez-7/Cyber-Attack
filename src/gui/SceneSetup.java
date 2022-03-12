/**
 * Files dedicated to the implementation of the game's graphical interface.
 */
package gui;

import game.threats.lineardatastructures.singly.SinglyLinked;
import gui.scenes.*;

import java.awt.Canvas;
import java.awt.Graphics2D;


/**
 * Screen back setup.
 */
public class SceneSetup {

    private Canvas canvas;
    private SinglyLinked<SceneController> views = new SinglyLinked<>();
    private int displayView;

    public SceneSetup (Canvas canvas) {
        this.canvas = canvas;
        SceneController mainScreen = new MainScreen(this);
        views.addNode(mainScreen); // index 0 = Main Screen
        SceneController menuScreen = new MenuScreen(this);
        views.addNode(menuScreen); // index 1 = Menu Screen
        SceneController agentScreen = new AgentScreen(this);
        views.addNode(agentScreen); // index 2 = Agent Screen
        SceneController creditsScreen = new CreditsScreen(this);
        views.addNode(creditsScreen); // index 3 = Credits Screen
        SceneController gameScreen = new GameScreen(this);
        views.addNode(gameScreen); // index 4 = Game Screen
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void showView (int sV) {
        for (int i = 0; i < canvas.getKeyListeners().length; i++ ) canvas.removeKeyListener(canvas.getKeyListeners()[i]); // clear events on key listener
        displayView = sV;
        views.getNode(displayView).init(canvas);
    }

    public void display(Graphics2D graphics) {
        views.getNode(displayView).display(graphics);
    }

    public void updateScreen(double refresh) {
        views.getNode(displayView).updateScreen(refresh);
    }
}