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
 * Set the scene on credits screen view.
 */
public class CreditsScreen extends SceneController implements KeyListener {

    private BufferedImage backgroundImg;

    public CreditsScreen(SceneSetup sceneSetup) {
        super(sceneSetup);

        try {
            URL bgPath = this.getClass().getResource("/elements/s.credits.png");
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
        if(pressed == KeyEvent.VK_Z) {
            this.getSceneSetup().showView(1);
            System.out.println("✧ Menu Screen \uD83C\uDFAE");
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