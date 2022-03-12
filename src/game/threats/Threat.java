/**
 * Files dedicated to configuring and managing threats as a game element.
 */
package game.threats;

import game.features.Generator;
import game.features.Motion;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * Implement threats attributes and methods.
 */
public class Threat extends Motion implements Generator {
    private boolean bossThreat;
    private int resistance;

    public Threat(int resistance, int rate, int fitHeight, int fitWidth, int layoutX, int layoutY, boolean visible, String imageResource) {
        super(rate, fitHeight, fitWidth, layoutX, layoutY, visible, imageResource);
        this.resistance = resistance;
        this.bossThreat = false;
    }

    public boolean isBossThreat() {
        return bossThreat;
    }

    // Threat boss setup
    public void setBossThreat() {
        this.bossThreat = true;
        Random r = new Random();
        this.resistance = r.nextInt((5-2)+1) + 2;
        this.setFitHeight(44);
        this.setFitWidth(43);
        Random sprite = new Random();
        int s = sprite.nextInt((5-1)+1) + 1; // assigns random value for graph variability
        this.setImageResource("/threats/t.boss-pow("+s+").png");
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    // Check the threat status.
    public boolean attacked() {
        --resistance;
        return resistance <= 0;
    }

    @Override
    public void display(Graphics2D graphics) {
        graphics.drawImage(this.getImage(),this.getLayoutX(),this.getLayoutY(),this.getFitWidth(),this.getFitHeight(),null);
    }

    @Override
    public void updateScreen(double refresh) {
        this.setRange();
    }
}