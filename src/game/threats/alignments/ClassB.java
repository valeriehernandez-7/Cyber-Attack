/**
 * Files dedicated to implementing threat alignments.
 */
package game.threats.alignments;

import game.features.Generator;
import game.threats.Threat;
import game.threats.lineardatastructures.doubly.DoublyLinked;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * Class-B alignment : Similar to the Class A alignment. The boss quickly swaps with the rest of the lineup's threats. It is a doubly linked list.
 */
public class ClassB extends AlignmentManager implements Generator {

    private boolean swap;
    private int bossPosition;

    public ClassB(int level, int length, int rate, int layoutX, int layoutY) {
        super(level, length, rate, layoutX, layoutY);

        int temp = 0;
        this.setThreats(new DoublyLinked<>());
        while (temp < length) {
            Random sprite = new Random();
            int s = sprite.nextInt((5-1)+1) + 1; // assigns random value for graph variability
            String imagePath = ("/threats/t.reg-pow("+s+").png");
            Threat threat = new Threat(level, rate, 44,43, layoutX, layoutY,true, imagePath);
            this.getThreats().addNode(threat);
            temp++;
        }
        this.swap = false; // swap position pending state
        Random bossPos = new Random(); // assigns to boss position a random value
        int b = bossPos.nextInt(this.getThreats().getListLength() - 1);
        this.getThreats().getNode(b).setBossThreat();
        this.bossPosition = b;
        this.setBossState(true); // alignment-state: Has boss
        this.setAlignmentID("B"); // B = Class B threat alignment
    }

    public boolean isSwap() {
        return swap;
    }

    public void setSwap(boolean swap) {
        this.swap = swap;
    }

    public int getBossPosition() {
        return bossPosition;
    }

    public void setBossPosition(int bossPosition) {
        this.bossPosition = bossPosition;
    }

    @Override
    public void display(Graphics2D graphics) {
        for (int i = 0; i < this.getThreats().getListLength(); i++) {
            this.getThreats().getNode(i).display(graphics);
        }
    }

    @Override
    public void updateScreen(double refresh) {
        this.setBossState(false); // alignment-state: Has NO boss
        for (int i = 0; i < this.getThreats().getListLength(); i++) {
            this.getThreats().getNode(i).setLayoutY((this.getThreats().getNode(i).getLayoutY()) + this.getRate());
            this.getThreats().getNode(i).updateScreen(refresh);
            if (this.getThreats().getNode(i).isBossThreat()) {
                this.setBossState(true); // alignment-state: Has boss
            }
        }
        if (this.getHandler().sampleStatus(350)) {
            if ((1 < this.getThreats().getListLength()) && swap) {
                Random n = new Random();
                int newBossPosition = n.nextInt(this.getThreats().getListLength() - 1);
                this.getThreats().switchNode(bossPosition,newBossPosition);
                bossPosition = newBossPosition; // boss swaps
                swap = false; // swap position pending state
            }
            else {
                swap = true; // swap position pending state
            }
        }
        this.rearrangeThreatsAlignment();
    }
}