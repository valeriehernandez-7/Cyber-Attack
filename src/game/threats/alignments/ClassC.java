/**
 * Files dedicated to implementing threat alignments.
 */
package game.threats.alignments;

import game.features.Generator;
import game.threats.Threat;
import game.threats.lineardatastructures.singly.CircularLinked;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * Class-C alignment : Similar to the class A alignment. The boss in case of being destroyed another threat of the lineup becomes the new boss. It is a circular linked list.
 */
public class ClassC extends AlignmentManager implements Generator {

    public ClassC(int level, int length, int rate, int layoutX, int layoutY) {
        super(level, length, rate, layoutX, layoutY);

        int temp = 0;
        this.setThreats(new CircularLinked<>());
        while (temp < length) {
            Random sprite = new Random();
            int s = sprite.nextInt((5-1)+1) + 1; // assigns random value for graph variability
            String imagePath = ("/threats/t.reg-pow("+s+").png");
            Threat threat = new Threat(level, rate, 44,43, layoutX, layoutY,true, imagePath);
            this.getThreats().addNode(threat);
            temp++;
        }
        Random bossPos = new Random(); // assigns to boss position a random value
        int b = bossPos.nextInt(this.getThreats().getListLength() - 1);
        this.getThreats().getNode(b).setBossThreat();
        this.setBossState(true); // alignment-state: Has boss
        this.setAlignmentID("C"); // C = Class C threat alignment
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
        if ((this.getThreats().getListLength() != 1) && (!this.isBossState())) {
            Random n = new Random();
            int newBoss = n.nextInt(this.getThreats().getListLength() - 1);
            this.getThreats().getNode(newBoss).setBossThreat(); // turn a regular threat into boss case
        }
        else if ((this.getThreats().getListLength() == 1) && (!this.isBossState())) {
            this.getThreats().getNode(0).setBossThreat(); // boss is the only on alignment case
        }
        this.rearrangeThreatsAlignment();
    }
}