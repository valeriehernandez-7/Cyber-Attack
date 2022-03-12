/**
 * Files dedicated to agent features and methods implementation.
 */
package game.agent;

import game.features.Generator;
import game.features.Motion;

import java.awt.Graphics2D;

/**
 * Agent's ship laser implementation.
 */
public class Laser extends Motion implements Generator {

    public Laser(int rate, int fitHeight, int fitWidth, int layoutX, int layoutY, boolean visible, String imageResource) {
        super(rate, fitHeight, fitWidth, layoutX, layoutY, visible, imageResource);
    }

    @Override
    public void display(Graphics2D graphics) {
        graphics.drawImage(this.getImage(),this.getLayoutX(),this.getLayoutY(),this.getFitWidth(),this.getFitHeight(),null);
    }

    @Override
    public void updateScreen(double refresh) {
        this.setLayoutY(this.getLayoutY() + this.getRate());
        this.setRange();
    }
}