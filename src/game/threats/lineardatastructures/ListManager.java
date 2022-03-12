/**
 * Files dedicated to linear data structures used in threat alignments.
 */
package game.threats.lineardatastructures;

/**
 * Method interface for linear data structures to handling threats alignments.
 */
public interface ListManager<N> {
    /**
     * Get the data of the node according to the given index.
     */
    N getNode(int index);

    /**
     * Add a new node with data to the list.
     */
    void addNode(N data);

    /**
     * Remove the node according to the given index.
     */
    void deleteNode(int index);

    /**
     * Swap two nodes based on the given index.
     */
    void switchNode(int indexA, int indexB);

    /**
     * Gets the length/size of the list.
     */
    int getListLength();

    /**
     * Remove all nodes from the list by establishing a clean list.
     */
    void clearList();
}
