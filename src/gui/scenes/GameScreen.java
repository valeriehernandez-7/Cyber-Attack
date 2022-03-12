/**
 * Files dedicated to the implementation of the game screens.
 */
package gui.scenes;

import game.agent.Agent;
import game.features.Level;
import game.features.Target;
import gui.SceneController;
import gui.SceneSetup;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Set the scene on game screen view, implements all the game features.
 */
public class GameScreen extends SceneController implements KeyListener, MouseListener {

    public static String ship = "/agent/a.ship-1.png";
    public static String pastAlignment = "!";
    public static String currentAlignment = "!";
    public static String nextAlignment = "!";
    public static String messages = "...";
    private BufferedImage backgroundImg, uiImg, resistanceImg, replayButtonImg;
    private Level level;
    private int levelID;
    private int score;
    private Target target;
    private Agent agent;

    public static Font arcade9;

    static {
        try {
            arcade9 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/resources/fonts/arcade.ttf")).deriveFont(Font.PLAIN,9);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Font arcade12;

    static {
        try {
            arcade12 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/resources/fonts/arcade.ttf")).deriveFont(Font.PLAIN,12);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Font arcade17;

    static {
        try {
            arcade17 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/resources/fonts/arcade.ttf")).deriveFont(Font.PLAIN,17);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }


    public GameScreen(SceneSetup sceneSetup) {
        super(sceneSetup);

        level = new Level(1);
        levelID = 1;
        score =  0;
        target = new Target(sceneSetup.getCanvas());
        agent = new Agent(70,54,131,330,true,ship);

        try {
            URL bgPath = this.getClass().getResource("/elements/s.game-bg.png");
            backgroundImg = ImageIO.read(bgPath);
            URL uiPath = this.getClass().getResource("/elements/s.game-ui.png");
            uiImg = ImageIO.read(uiPath);
            URL resistancePath = this.getClass().getResource("/agent/life.png");
            resistanceImg = ImageIO.read(resistancePath);
            Random random = new Random();
            int n = random.nextInt((2-1)+1) + 1;
            URL buttonPath = this.getClass().getResource("/elements/b.replay-"+n+".png");
            replayButtonImg = ImageIO.read(buttonPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Level getLevel() {
        return level;
    }

    public Agent getAgent() {
        return agent;
    }

    /**
     *
     */
    public void newGame() {
        level = new Level(1);
        levelID = 1;
        score =  0;
        agent = new Agent(70,54,131,330,true,ship);
    }

    /**
     *
     */
    public void gameOver(Graphics2D graphics) {
        graphics.drawImage(backgroundImg,0,0,800,500,null);
        this.displayObjects(graphics);
        graphics.drawImage(replayButtonImg,328,178,138,118,null);
    }

    /**
     *
     */
    public void displayObjects(Graphics2D graphics) {
        graphics.drawImage(uiImg,0,0,800,500,null);
        graphics.setFont(arcade9);
        graphics.setColor(Color.white);
        graphics.drawString(("Level "+ level.getLevel()),95,255);
        graphics.drawString(messages,635,255);
        graphics.drawString(Integer.toString(score),386,470);
        graphics.setFont(arcade12);
        graphics.drawString(pastAlignment,354,43);
        graphics.drawString(level.getNextAlignment().getAlignmentID(),435,43);
        graphics.setFont(arcade17);
        graphics.drawString(level.getCurrentAlignment().getAlignmentID(),392,37);

        for (int i = 0; i < agent.getResistance(); i++) {
            graphics.drawImage(resistanceImg,308+18*i,79,7,10,null);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressed = e.getKeyCode();
        if(pressed == KeyEvent.VK_X) {
            this.newGame();
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
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void display(Graphics2D graphics) {
        if (!this.agent.isPlaying()) {
            this.gameOver(graphics);
            return;
        }
        graphics.fillRect(0,0,800,500);
        graphics.drawImage(backgroundImg,0,0,800,500,null);
        level.display(graphics);
        agent.display(graphics);
        this.displayObjects(graphics);

    }

    @Override
    public void updateScreen(double refresh) {
        if (!this.agent.isPlaying()) {
            return;
        }
        if (level.getRate() == 4) {
            ++levelID;
            level = new Level(levelID);
        }
        for (int i = 0; i < level.getCurrentAlignment().getThreats().getListLength(); i++) {
            int shots = 0;
            if ((510 - level.getCurrentAlignment().getThreats().getNode(i).getFitHeight()) <= (level.getCurrentAlignment().getThreats().getNode(i).getLayoutY())) {
                while (agent.isPlaying()) {
                    agent.damage();
                }
                return;
            }
            while (shots < agent.getLaserBlastShots().getListLength()) {
                if (agent.getLaserBlastShots().getNode(shots).isCollisionDetected(level.getCurrentAlignment().getThreats().getNode(i))) {
                    agent.getLaserBlastShots().deleteNode(shots);
                    if (level.getCurrentAlignment().getThreats().getNode(i).attacked()) {
                        if (level.getCurrentAlignment().getThreats().getNode(i).isBossThreat()) {
                            this.score += 1000;
                        }
                        else {
                            this.score += 100;
                        }
                        level.getCurrentAlignment().getThreats().deleteNode(i);
                        if (level.getCurrentAlignment().getThreats().getListLength() == 0) {
                            level.nextLevel();
                            level.updateScreen(refresh);
                        }
                        --i;
                    }
                }
                else {
                    shots++;
                }
            }
        }
        level.getCurrentAlignment().rearrangeThreatsAlignment();
        for (int i = 0; i < agent.getLaserBlastShots().getListLength(); i++) {
            if(agent.getLaserBlastShots().getNode(i).getLayoutY() < -50) {
                agent.getLaserBlastShots().deleteNode(i);
                --i;
            }
        }
        for (int i = 0; i < level.getCurrentAlignment().getThreats().getListLength(); i++) {
            if (level.getCurrentAlignment().getThreats().getNode(i).isCollisionDetected(agent)) {
                if (level.getCurrentAlignment().getThreats().getNode(i).isBossThreat()) {
                    this.score += 500;
                }
                else {
                    this.score += 50;
                }
                level.getCurrentAlignment().getThreats().deleteNode(i);
                if (level.getCurrentAlignment().getThreats().getListLength() == 0) {
                    level.nextLevel();
                    level.updateScreen(refresh);
                }
                agent.damage();
            }
        }
        target.updateTarget();
        agent.shipController(target.getTarget());
        agent.updateScreen(refresh);
        level.updateScreen(refresh);
    }

    @Override
    public void init(Canvas canvas) {
        canvas.addMouseListener(agent);
        canvas.addMouseListener(this);
        canvas.addKeyListener(this);
    }
}