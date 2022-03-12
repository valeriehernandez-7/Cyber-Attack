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
 * Class-E alignment : Similar to the class C alignment. But the alignment is not horizontal, its movement is a constant rotation like the hands of the clock. The boss is always in the center. It is a double circular list.
 */
public class ClassE extends AlignmentManager implements Generator {

    private float angleRotation;

    public ClassE(int level, int length, int rate, int layoutX, int layoutY) {
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
        float middle = (float) getThreats().getListLength()/2;
        this.getThreats().getNode((int) Math.ceil(middle)).setBossThreat(); // boss assigned to middle position
        this.setBossState(true); // alignment-state: Has boss
        this.setAlignmentID("E"); // E = Class E threat alignment
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
        this.angleRotation += 0.045; // alignment's rotation rate
        this.setLayoutY(this.getLayoutY() + 1); // alignment's Y motion rate
        for (int i = 0; i < this.getThreats().getListLength(); i++) {
            int radio = this.getThreats().getNode(i).getLayoutX() - this.getLayoutX(); // distance between nodes
            int layoutX = (int) (radio * Math.cos(this.angleRotation % 90));
            this.getThreats().getNode(i).setLayoutX(this.getLayoutX() + layoutX); // threat's X motion
            int layoutY = (int) (radio * Math.sin(this.angleRotation % 90));
            this.getThreats().getNode(i).setLayoutY(this.getLayoutY() + layoutY); // threat's Y motion
            this.getThreats().getNode(i).updateScreen(refresh);
        }
    }
}