public class HashDS<T> implements SequenceInterface<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    
    private class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

   
    public HashDS() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

   
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

    
    @Override
    public T first() {
        if (head == null) {
            throw new EmptySequenceException("Cannot retrieve first element: Sequence is empty.");
        }
        return head.data;
    }

    
    @Override
    public T last() {
        if (tail == null) {
            throw new EmptySequenceException("Cannot retrieve last element: Sequence is empty.");
        }
        return tail.data;
    }

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

   
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

  
    @Override
    public int size() {
        return size;
    }

    
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    
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