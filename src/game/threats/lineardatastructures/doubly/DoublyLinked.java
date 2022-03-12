/**
 * Files dedicated to double linked linear data structures used in threat alignments
 */
package game.threats.lineardatastructures.doubly;

import game.threats.lineardatastructures.ListManager;

/**
 *Double linked list implementation class.
 */
public class DoublyLinked<N> implements ListManager<N> {
    private Node<N> root;
    private int length;

    public DoublyLinked () {
        this.root = null;
        this.length = 0;
    }

    @Override
    public N getNode (int index) {
        if ((length - 1) < index)
            return null;
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
            length++;
            return;
        }
        Node<N> currentNode = root;
        while (currentNode.getNext() != null) {
            currentNode = currentNode.getNext();
        }
        newNode.setPrevious(currentNode);
        currentNode.setNext(newNode);
        length++;
    }

    @Override
    public void deleteNode (int index) {
        if ((index == 0) && (index < length)) {
            root = root.getNext();
            length--;
            return;
        }
        Node<N> currentNode = root;
        int count = 0;
        while ((count < index - 1) && (currentNode.getNext() != null)) {
            currentNode = currentNode.getNext();
            count++;
        }
        if (count == (length - 2)) {
            currentNode.setNext(null);
        }
        else {
            currentNode.setNext(currentNode.getNext().getNext());
        }
        length--;
    }

    @Override
    public void switchNode(int indexA, int indexB) {
        N nodeA = this.getNode(indexA);
        N nodeB = this.getNode(indexB);
        Node<N> currentNode = root;
        for (int i = 0; i < length; i++) {
            if(this.getNode(i) == nodeA) {
                currentNode.setData(nodeB);
            }
            else if (this.getNode(i) == nodeB) {
                currentNode.setData(nodeA);
            }
            currentNode = currentNode.getNext();
        }
    }

    @Override
    public int getListLength() {
        return length;
    }

    @Override
    public void clearList() {
        this.root = null;
        this.length = 0;
    }
}