/**
 * Files dedicated to the implementation of the game screens.
 */
package gui.scenes;

import gui.SceneController;
import gui.SceneSetup;

import javax.imageio.ImageIO;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Set the scene on menu screen view.
 */
public class MenuScreen extends SceneController implements KeyListener {

    private BufferedImage backgroundImg;

    public MenuScreen(SceneSetup sceneSetup) {
        super(sceneSetup);

        try {
            URL bgPath = this.getClass().getResource("/elements/s.menu.png");
            backgroundImg = ImageIO.read(bgPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        if(pressed == KeyEvent.VK_V) {
            this.getSceneSetup().showView(2);
            System.out.println("✧ Agent Screen \uD83D\uDD2B");
        }
        else if(pressed == KeyEvent.VK_C) {
            this.getSceneSetup().showView(3);
            System.out.println("✧ Credits Screen \uD83D\uDCCC");
        }
        else if(pressed == KeyEvent.VK_X) {
            this.getSceneSetup().showView(0);
            System.out.println("✧ Main Screen ☢");
        }
        else {
            e.consume();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void display(Graphics2D graphics) {
        graphics.drawImage(backgroundImg,0,0,801,500,null);
    }

    @Override
    public void updateScreen(double refresh) {

    }

    @Override
    public void init(Canvas canvas) {
        canvas.addKeyListener(this);
    }
}