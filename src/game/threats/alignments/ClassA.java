/**
 * Files dedicated to implementing threat alignments.
 */
package game.threats.alignments;

import game.features.Generator;
import game.threats.Threat;
import game.threats.lineardatastructures.singly.SinglyLinked;

import java.awt.Graphics2D;
import java.util.Random;

/**
 * Class-A alignment : One of the threats to the lineup is the boss. The boss is chosen randomly. When the boss is destroyed, the rest of the threats disappear. To destroy the boss, it takes between 2 to 5 shots.
 */
public class ClassA extends AlignmentManager implements Generator {

    public ClassA(int level, int length, int rate, int layoutX, int layoutY) {
        super(level, length, rate, layoutX, layoutY);

        int temp = 0;
        this.setThreats(new SinglyLinked<>());
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
        this.setAlignmentID("A"); // A = Class A threat alignment
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
        this.rearrangeThreatsAlignment();
    }
}