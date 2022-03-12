/**
 * Files dedicated to simple linked linear data structures used in threat alignments
 */
package game.threats.lineardatastructures.singly;

/**
 *Simple linked node implementation class.
 */
public class Node<N> {
    private N data;
    private Node<N> next;

    public Node() {
        this.data = null;
        this.next = null;
    }

    public N getData() {
        return data;
    }

    public void setData(N data) {
        this.data = data;
    }

    public Node<N> getNext() {
        return next;
    }

    public void setNext(Node<N> next) {
        this.next = next;
    }
}
