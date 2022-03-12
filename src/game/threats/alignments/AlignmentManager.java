/**
 * Files dedicated to implementing threat alignments.
 */
package game.threats.alignments;

import game.features.Generator;
import game.features.Handler;
import game.threats.Threat;
import game.threats.lineardatastructures.ListManager;

/**
 * Threat alignment factory.
 */
public abstract class AlignmentManager implements Generator {
    private ListManager<Threat> threats;
    private String alignmentID;
    private boolean bossState;
    private int rate, layoutX, layoutY;
    private Handler handler;

    public AlignmentManager (int level, int length, int rate, int layoutX, int layoutY) {
        this.alignmentID = "!";
        this.bossState = false;
        this.rate = rate;
        this.layoutX = layoutX;
        this.layoutY = layoutY;
        this.handler = new Handler();
    }

    public ListManager<Threat> getThreats() {
        return threats;
    }

    public void setThreats(ListManager<Threat> threats) {
        this.threats = threats;
    }

    public String getAlignmentID() {
        return alignmentID;
    }

    public void setAlignmentID(String alignmentID) {
        this.alignmentID = alignmentID;
    }

    public boolean isBossState() {
        return bossState;
    }

    public void setBossState(boolean bossState) {
        this.bossState = bossState;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

    public Handler getHandler() {
        return handler;
    }

    /**
     * Rearrange threats alignment on screen during attack.
     */
    public void rearrangeThreatsAlignment() {
        int radio = 0;
        for (int ind = 0; ind < this.getThreats().getListLength(); ind++) {
            this.getThreats().getNode(ind).setLayoutX(radio);
            radio += (20 + this.getThreats().getNode(ind).getFitWidth());
        }
        radio = ((800 - (this.getThreats().getNode(this.getThreats().getListLength() - 1).getLayoutX())) - (this.getThreats().getNode(this.getThreats().getListLength() - 1).getFitWidth())) / 2;
        for (int ind = 0; ind < this.getThreats().getListLength(); ind++) {
            this.getThreats().getNode(ind).setLayoutX(radio + (this.getThreats().getNode(ind).getLayoutX()));
        }
    }
}