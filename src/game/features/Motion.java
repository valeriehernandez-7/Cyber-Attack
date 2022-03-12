/**
 * Game object classes and dependencies implementation.
 */
package game.features;

import java.awt.Rectangle;

/**
 * Implement motion management features.
 */
public abstract class Motion extends Object {
    private int rate; // Sprite motion speed control
    private Rectangle range; // Sprite detection range
    private boolean collisionDetected; // Sprite collision status

    public Motion(int rate, int fitHeight, int fitWidth, int layoutX, int layoutY, boolean visible, String imageResource) {
        super(fitHeight, fitWidth, layoutX, layoutY, visible, imageResource);
        this.rate = rate;
        this.range = new Rectangle (layoutX, layoutY, fitWidth, fitHeight);
        this.collisionDetected = false;
    }

    public int getRate() { return rate; }

    public void setRate(int rate) { this.rate = rate; }

    public Rectangle getRange() {
        return range;
    }

    public void setRange() {
        this.range = new Rectangle(this.getLayoutX(),this.getLayoutY(),this.getFitWidth(),this.getFitHeight());
    }

    public boolean isCollisionDetected(Motion m) {
        collisionDetected = m.getRange().intersects(this.getRange());
        return collisionDetected;
    }

}