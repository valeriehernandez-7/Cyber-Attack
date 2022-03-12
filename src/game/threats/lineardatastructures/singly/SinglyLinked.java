/**
 * Files dedicated to simple linked linear data structures used in threat alignments
 */
package game.threats.lineardatastructures.singly;

import game.threats.lineardatastructures.ListManager;

/**
 *Singly linked list implementation class.
 */
public class SinglyLinked<N> implements ListManager<N> {
    private Node<N> root;
    private int length;

    public SinglyLinked () {
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
        }
        else {
            Node<N> currentNode = root;
            while(currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
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
        while((count < (index - 1)) && (currentNode.getNext() != null)) {
            currentNode = currentNode.getNext();
            count++;
        }
        if(count == (length - 2)) {
            currentNode.setNext(null);
        }
        else {
            currentNode.setNext(currentNode.getNext().getNext());
        }
        length--;
    }

    @Override
    public void switchNode (int indexA, int indexB) { }

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