// Define EmptySequenceException
class EmptySequenceException extends RuntimeException {
    public EmptySequenceException(String message) {
        super(message);
    }
}

// SequenceInterface Definition
interface SequenceInterface<T> {
    void append(T item);
    void prefix(T item);
    T itemAt(int index);
    T first();
    T last();
    T deleteHead();
    T deleteTail();
    boolean isEmpty();
    int size();
    void clear();
    int getFrequencyOf(T item);
    boolean remove(T item);
}

// HashDS Implementation
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

    // Default Constructor
    public HashDS() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Copy Constructor
    public HashDS(HashDS<T> other) {
        if (other == null || other.isEmpty()) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            Node<T> current = other.head;
            while (current != null) {
                this.append(current.data);
                current = current.next;
            }
        }
    }

    // Append method
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

    // Prefix method
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

    // ItemAt method
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

    // First method
    @Override
    public T first() {
        if (head == null) {
            throw new EmptySequenceException("Cannot retrieve first element: Sequence is empty.");
        }
        return head.data;
    }

    // Last method
    @Override
    public T last() {
        if (tail == null) {
            throw new EmptySequenceException("Cannot retrieve last element: Sequence is empty.");
        }
        return tail.data;
    }

    // DeleteHead method
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

    // DeleteTail method
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

    // IsEmpty method
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Size method
    @Override
    public int size() {
        return size;
    }

    // Clear method
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // GetFrequencyOf method
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

    // Remove method
    @Override
    public boolean remove(T item) {
        if (head == null) {
            return false;
        }

        if (head.data.equals(item)) {
            deleteHead();
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(item)) {
                current.next = current.next.next;
                if (current.next == null) {
                    tail = current;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // ToString method
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            result.append(current.data);
            current = current.next;
        }
        return result.toString();
    }
}

