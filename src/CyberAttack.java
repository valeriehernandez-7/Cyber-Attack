/**
 * @version 1.0
 * @authpr Valerie M. Hernández Fernández
 * @see <a href='https://valeriehernandez-7.github.io/Cyber-Attack/'>Cyber Attack</a>
 */

import gui.SceneSetup;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

/**
 * Cyber Attack initial implementation.
 */
public class CyberAttack extends Canvas implements Runnable {

    private JFrame scene;
    private static final int width = 800 , height = 500;
    private static SceneSetup view;
    private Thread thread;
    private int fps;
    private boolean running = false;

    public CyberAttack () {
        this.setSize (width , height);
        view = new SceneSetup(this);
        view.showView(0);
    }

    public JFrame getScene() {
        return scene;
    }

    public void setScene(JFrame scene) {
        this.scene = scene;
    }

    /**
     * Set true the running status. (game is running).
     */
    public synchronized void play() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Shows graphics on screen and disposes graphics context and releases any system resources that it is using.
     */
    public void show(BufferStrategy bufferStrategy) {
        do {
            do {
                Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
                view.display(graphics);
                graphics.dispose();
            } while (bufferStrategy.contentsRestored());
            bufferStrategy.show();
        } while (bufferStrategy.contentsLost());
    }

    /**
     * Updates the screen depending on the selected scene.
     */
    public void updateScene(double refresh) {
        view.updateScreen(refresh);
    }

    /**
     * Provides a common protocol for objects that wish to execute code while they are active, is implemented by thread class.
     */
    @Override
    public void run() {
        int frames = 0;
        final int time = 60;
        final long handler = 1000000000 / time;
        long sample = System.nanoTime();
        long playTime = System.currentTimeMillis();

        this.createBufferStrategy(3);
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        while(running) {
            long current = System.nanoTime();
            long timer = current - sample;
            sample = current;
            double refresh = timer / (double) handler;
            frames ++;
            if (1000 < System.currentTimeMillis() - playTime) {
                playTime += 1000;
                fps = frames;
                frames = 0;
            }
            show(bufferStrategy);
            updateScene(refresh);
            try {
                Thread.sleep((sample - System.nanoTime() + handler) / 1000000);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Starting point for JVM to start execution of Cyber Attack game.
     */
    public static void main(String[] args) {
        CyberAttack cyberAttack = new CyberAttack();
        JFrame scene = new JFrame();
        scene.add(cyberAttack);
        scene.pack();
        scene.setResizable(false);
        scene.setVisible(true);
        ImageIcon icon = new ImageIcon("sourcecode/resources/elements/icon.png");
        scene.setIconImage(icon.getImage());
        scene.setTitle("CyberAttack v1.0");
        scene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cyberAttack.setScene(scene);
        cyberAttack.play();
    }
}