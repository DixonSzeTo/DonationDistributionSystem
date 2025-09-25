package adt;

/**
 *
 * @author youch
 */
public class List<T> implements ListInterface<T> {

    private Node firstNode;
    private Node lastNode;
    private int numberOfEntries;

    public List() {
        clear();
    }

    @Override
    public final void clear() {
        firstNode = null;
        lastNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        if (isEmpty()) {  // List is empty
            firstNode = newNode;
            lastNode = newNode;
            lastNode.next = firstNode;  // Circular link
        } else {
            lastNode.next = newNode;
            lastNode = newNode;
            lastNode.next = firstNode;  // Circular link
        }
        numberOfEntries++;
    }

    @Override
    public T remove(int givenPosition) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            System.out.println("Invalid position. Cannot remove element.");
            return null;
        }
        T removedData = null;
        if (givenPosition == 1) {
            removedData = firstNode.data;
            if (numberOfEntries == 1) {
                firstNode = null;
                lastNode = null;
            } else {
                firstNode = firstNode.next;
                lastNode.next = firstNode;
            }
        } else {
            Node current = firstNode;
            for (int i = 1; i < givenPosition - 1; ++i) {
                current = current.next;
            }
            Node nodeToRemove = current.next;
            removedData = nodeToRemove.data;
            current.next = nodeToRemove.next;

            if (nodeToRemove == lastNode) {
                lastNode = current;
            }
        }
        numberOfEntries--;
        return removedData;
    }

    @Override
    public boolean update(int givenPosition, T newEntry) {
        if (givenPosition < 1 || givenPosition > numberOfEntries) {
            return false;
        }
        Node currentNode = firstNode;
        for (int i = 1; i < givenPosition; i++) {
            currentNode = currentNode.next;
        }
        currentNode.data = newEntry;
        return true;
    }

    @Override
    public T getDetails(int givenPosition) {
        T result = null;
        if (givenPosition >= 0 && givenPosition < numberOfEntries) {
            Node currentNode = firstNode;
            int currentIndex = 0;

            while (currentIndex < givenPosition) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            result = currentNode.data;
        }
        return result;
    }

    @Override
    public void display() {
        Node current = firstNode;
        do {
            System.out.print(" " + current.data);
            current = current.next;
            System.out.println("");
        } while (current != firstNode);

    }

    @Override
    public T filter(T criteria) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)) {
            if (anEntry.equals(currentNode.data)) {
                found = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return found;
    }

    @Override
    public String generateReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public int getCount() {
        return numberOfEntries;
    }

    
    public class Node {

        public T data;
        public Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
