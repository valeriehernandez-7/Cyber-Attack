/**
 * Files dedicated to the implementation of the game screens.
 */
package gui.scenes;

import gui.SceneController;
import gui.SceneSetup;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Set the scene on agent ship screen view.
 */
public class AgentScreen extends SceneController implements KeyListener {

    private BufferedImage backgroundImg;

    public AgentScreen(SceneSetup sceneSetup) {
        super(sceneSetup);

        try {
            URL bgPath = this.getClass().getResource("/elements/s.agent.png");
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
        if(pressed == KeyEvent.VK_A) {
            GameScreen.ship = "/agent/a.ship-1.png";
//            System.out.println("✦ Ship: VA07");
            this.getSceneSetup().showView(4);
            System.out.println("✧ Game Screen ☢");
        }
        else {
            e.consume();
        }
        if(pressed == KeyEvent.VK_B) {
            GameScreen.ship = "/agent/a.ship-2.png";
//            System.out.println("✦ Ship: VB07");
            this.getSceneSetup().showView(4);
            System.out.println("✧ Game Screen ☢");
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