/**
 * Files dedicated to simple linked linear data structures used in threat alignments
 */
package game.threats.lineardatastructures.singly;

import game.threats.lineardatastructures.ListManager;

/**
 *Circular linked list implementation class.
 */
public class CircularLinked<N> implements ListManager<N> {
    private Node<N> root;
    private int length;

    public CircularLinked () {
        this.root = null;
        this.length = 0;
    }

    @Override
    public N getNode (int index) {
        Node<N> currentNode = root;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public void addNode (N data) {
        Node<N> newNode = new Node<>();
        newNode.setData(data);
        if (root == null) {
            root = newNode;
            root.setNext(root);
        }
        else {
            Node<N> currentNode = root;
            while (currentNode.getNext() != root) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            newNode.setNext(root);
        }
        length++;
    }

    @Override
    public void deleteNode (int index) {
        if ((index == 0) && (index < length)) {
            root = root.getNext();
        }
        else {
            Node<N> currentNode = root;
            int counter = 0;
            while (counter < index-1) {
                currentNode = currentNode.getNext();
                counter++;
            }
            currentNode.setNext(currentNode.getNext().getNext());
        }
        length--;
    }

    @Override
    public void switchNode (int indexA, int indexB) {
        N nodeA = this.getNode(indexA);
        N nodeB = this.getNode(indexB);
        Node<N> currentNode = root;
        for (int i = 0; i < this.getListLength(); i++) {
            if (this.getNode(i) == nodeA) {
                currentNode.setData(nodeB);
            }
            else if (this.getNode(i) == nodeB) {
                currentNode.setData(nodeA);
            }
            currentNode = currentNode.getNext();
        }
    }

    @Override
    public int getListLength () {
        return length;
    }

    @Override
    public void clearList () {
        this.root = null;
        this.length = 0;
    }
}