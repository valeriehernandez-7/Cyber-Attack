/**
 * Game object classes and dependencies implementation.
 */
package game.features;

import game.threats.alignments.*;
import gui.scenes.GameScreen;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * Game level setup.
 */
public class Level implements Generator{

    private int level, rate;
    private String pastAlignment;
    private AlignmentManager currentAlignment, nextAlignment;

    public Level(int level) {
        this.level = level;
        rate = 0;
        pastAlignment = "!";
        currentAlignment = this.createAlignment();
        nextAlignment = this.createAlignment();
    }

    /**
     * Next level settings.
     */
    public void nextLevel() {
        pastAlignment = currentAlignment.getAlignmentID(); // assign alignment
        GameScreen.pastAlignment = pastAlignment; // set it in game screen
//        System.out.println("\n✖ Past threat class " + currentAlignment.getAlignmentID()); // display on terminal

        currentAlignment = nextAlignment; // assign alignment
//        GameScreen.currentAlignment = currentAlignment.getAlignmentID(); // set it in game screen
//        System.out.println("\uD83D\uDCA2 Current threat class " + currentAlignment.getAlignmentID()); // display on terminal

        nextAlignment = this.createAlignment(); // assign alignment
        GameScreen.nextAlignment = getNextAlignment().getAlignmentID(); // set it in game screen
//        System.out.println("➕ Upcoming threat class " + nextAlignment.getAlignmentID() + "\n"); // display on terminal
    }

    /**
     * Threats alignment random builder.
     */
    public AlignmentManager createAlignment() {
        int length = 5; // 5 threats defined by each alignment
        ++ rate; // increase movement speed

        Random random = new Random();
        int alignment = random.nextInt(6);
        switch (alignment) {
            case 1 -> {
                return new ClassA(this.level, length, rate, 287, -20);
            }
            case 2 -> {
                return new ClassB(this.level, length, rate, 287, -20);
            }
            case 3 -> {
                return new ClassC(this.level, length, rate, 287, -20);
            }
            case 4 -> {
                return new ClassD(this.level, length, rate, 287, -20);
            }
            case 5 -> {
                return new ClassE(this.level, length, rate, 400, -120);
            }
            default -> {
                return new Basic(this.level, length, rate, 287, -20);
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public AlignmentManager getCurrentAlignment() {
        return currentAlignment;
    }

    public AlignmentManager getNextAlignment() {
        return nextAlignment;
    }

    @Override
    public void display(Graphics2D graphics) {
        this.currentAlignment.display(graphics);
    }

    @Override
    public void updateScreen(double refresh) {
        if ((!currentAlignment.isBossState()) && (currentAlignment.getAlignmentID().equals("A") || currentAlignment.getAlignmentID().equals("B") || currentAlignment.getAlignmentID().equals("E"))) {
            // no boss, no alignment case
            this.nextLevel();
            return;
        }
        else if (currentAlignment.getThreats().getListLength() == 0) {
            // empty alignment case
            this.nextLevel();
            return;
        }
        for (int i = 0; i < this.currentAlignment.getThreats().getListLength(); i++) {
            // the threats got through the defense case
            if ( 500 < currentAlignment.getThreats().getNode(i).getLayoutY()) {
                this.nextLevel();
                return;
            }
        }
        this.currentAlignment.updateScreen(refresh);
    }
}