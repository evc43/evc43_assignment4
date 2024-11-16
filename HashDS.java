public class HashDS<T> implements SequenceInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Node class for LinkedList
    private class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    // Constructor
    public HashDS() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Method to add an item to the end of the list
    @Override
    public void append(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Method to add an item to the front of the list
    @Override
    public void prefix(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Method to get the item at a specific index
    @Override
    public T itemAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Method to get the first item
    @Override
    public T first() {
        if (head == null) {
            throw new EmptySequenceException("Cannot retrieve first element: Sequence is empty.");
        }
        return head.data;
    }

    // Method to get the last item
    @Override
    public T last() {
        if (tail == null) {
            throw new EmptySequenceException("Cannot retrieve last element: Sequence is empty.");
        }
        return tail.data;
    }

    // Method to delete the head item
    @Override
    public T deleteHead() {
        if (head == null) {
            throw new EmptySequenceException("Cannot delete head: Sequence is empty.");
        }

        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    // Method to delete the tail item
    @Override
    public T deleteTail() {
        if (tail == null) {
            throw new EmptySequenceException("Cannot delete tail: Sequence is empty.");
        }

        T data = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return data;
    }

    // Method to check if the list is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Method to get the size of the list
    @Override
    public int size() {
        return size;
    }

    // Method to clear the list
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Method to get frequency of an item
    @Override
    public int getFrequencyOf(T item) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    // Method to remove an item from the list
    @Override
    public boolean remove(T item) {
        if (head == null) {
            return false;
        }

        // If the head is the item to remove
        if (head.data.equals(item)) {
            deleteHead();
            return true;
        }

        // Traverse to find the item
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(item)) {
                current.next = current.next.next;
                if (current.next == null) { // Update tail if needed
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to convert the list to a string
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            result.append(current.data); // Concatenate without spaces
            current = current.next;
        }
        return result.toString(); // Return concatenated string
    }
}
