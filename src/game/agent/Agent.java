/**
 * Files dedicated to agent features and methods implementation.
 */
package game.agent;

import game.features.Generator;
import game.features.Handler;
import game.features.Motion;
import game.threats.lineardatastructures.singly.SinglyLinked;
import gui.scenes.GameScreen;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * Agent's features setup.
 */
public class Agent extends Motion implements Generator, MouseListener {

    private int resistance;
    private boolean playing, blast;
    private Handler handler;
    private SinglyLinked<Laser> laserBlastShots;

    public Agent(int fitHeight, int fitWidth, int layoutX, int layoutY, boolean visible, String imageResource) {
        super(10, fitHeight, fitWidth, layoutX, layoutY, visible, imageResource);
        this.resistance = 10; // player resistance
        this.playing = true; // player is alive
        this.blast = false; // player is not shooting
        this.laserBlastShots = new SinglyLinked<Laser>();
        this.handler = new Handler();
    }

    /**
     * Ship movement control.
     */
    public void shipController(int xPos) {
        if (xPos < (800 - this.getFitWidth() + 25)) {
            this.setLayoutX(xPos);
        }
    }

    /**
     * Laser blast shoot generator.
     */
    public void damage() {
//        System.out.println("⚡ Under attack");
        if (resistance > 0) {
            --resistance; // lost 1 life
        }
        else{
            System.out.println("\n\uD83D\uDC80 GAME OVER \uD83D\uDC80");
            this.playing = false; // player is dead
        }
    }

    /**
     * Laser blast shoot generator.
     */
    public void laserBlastShooting() {
        if (handler.sampleStatus(500)) {
            Laser laser = new Laser(-20,25,30,(this.getLayoutX()+9),(this.getLayoutY()-50),true,"/agent/blast/blast(1).png");
            laserBlastShots.addNode(laser);
//            System.out.println("⚡ Shot on ("+MouseInfo.getPointerInfo().getLocation().getX()+","+MouseInfo.getPointerInfo().getLocation().getY());
            notificationAlert();
        }
    }

    /**
     * Set random message at display.
     */
    public void notificationAlert() {
        Random code = new Random();
        int id = code.nextInt(1000);
        Random ind = new Random();
        int i = ind.nextInt(6);
        String[] messages = {"loading","updating", "scanning", "warning", "danger", "ID" + id};
        String alert = messages[i];
        GameScreen.messages = alert; // set it on game screen
//        System.out.println("\uD83D\uDE80 Status: "+ alert);
    }

    public int getResistance() {
        return resistance;
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isBlast() {
        return blast;
    }

    public void setBlast(boolean blast) {
        this.blast = blast;
    }

    public SinglyLinked<Laser> getLaserBlastShots() {
        return laserBlastShots;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        blast = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        blast = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void display(Graphics2D graphics) {
        graphics.drawImage(this.getImage(),this.getLayoutX(),this.getLayoutY(),this.getFitWidth(),this.getFitHeight(),null);
        for (int i = 0; i < laserBlastShots.getListLength(); i++) {
            laserBlastShots.getNode(i).display(graphics);
        }
    }

    @Override
    public void updateScreen(double refresh) {
        for (int i = 0; i < laserBlastShots.getListLength(); i++) {
            laserBlastShots.getNode(i).updateScreen(refresh);
        }
        if (this.getLayoutX() < (800 - this.getFitWidth() + 25)) {
            this.setLayoutX(this.getLayoutX() + this.getRate());
        } else if (0 < this.getLayoutX()) {
            this.setLayoutX(this.getLayoutX() - this.getRate());
        }
        if (blast) {
            this.laserBlastShooting();
        }
        this.setRange();
    }
}