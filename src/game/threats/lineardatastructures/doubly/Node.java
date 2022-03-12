/**
 * Files dedicated to double linked linear data structures used in threat alignments
 */
package game.threats.lineardatastructures.doubly;

/**
 *Double linked node implementation class.
 */
public class Node<N> {
    private N data;
    private Node<N> next;
    private Node<N> previous;

    public Node() {
        this.data = null;
        this.next = null;
        this.previous = null;
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

    public Node<N> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<N> previous) {
        this.previous = previous;
    }
}
