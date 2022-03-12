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
 * Class-D alignment : Same as Class C alignment but the threats in the lineup have different resistances. The alignment ordered from highest to lowest resistance is maintained, using a bubble sort.
 */
public class ClassD extends AlignmentManager implements Generator {

    public ClassD(int level, int length, int rate, int layoutX, int layoutY) {
        super(level, length, rate, layoutX, layoutY);

        int temp = 0;
        this.setThreats(new CircularLinked<>());
        while (temp < length) {
            Random sprite = new Random();
            int s = sprite.nextInt((5-1)+1) + 1; // assigns random value for graph variability
            String imagePath = ("/threats/t.reg-pow("+s+").png");
            Random r = new Random();
            int res = r.nextInt((3-1)+1) + 1; // assigns random value for resistance variability (1 to 3)
            Threat threat = new Threat(res, rate, 44,43, layoutX, layoutY,true, imagePath);
            this.getThreats().addNode(threat);
            temp++;
        }
        Random bossPos = new Random(); // assigns to boss position a random value
        int b = bossPos.nextInt(this.getThreats().getListLength() - 1);
        this.getThreats().getNode(b).setBossThreat();
        this.setBossState(true); // alignment-state: Has boss
        this.setAlignmentID("D"); // D = Class D threat alignment
    }

    /**
     * Sorting algorithm that repeatedly steps through the alignment, compares adjacent threats and swaps them if they are in the wrong order (high - low instead low - high).
     */
    public void bubbleSort() {
        int node = 0;
        boolean swap = false;
        while (node < (this.getThreats().getListLength() - 1)) {
            if ((this.getThreats().getNode(node).getResistance()) < (this.getThreats().getNode(node + 1).getResistance())) {
                this.getThreats().switchNode(node,node + 1);
                swap = true;
            }
            node++;
        }
        if (swap) { this.bubbleSort(); }
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
        if (1 < this.getThreats().getListLength()) {
            this.bubbleSort(); // sort the threats on alignment
        }
        this.rearrangeThreatsAlignment();
    }
}