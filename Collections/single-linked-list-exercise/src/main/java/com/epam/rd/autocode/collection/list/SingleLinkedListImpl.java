package com.epam.rd.autocode.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

public class SingleLinkedListImpl implements List {

    private Node head;

    private static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "[" + data + ']';
        }
    }

    public SingleLinkedListImpl() {
        head = new Node(0, null);  // The head node contains the size of the list
    }

    @Override
    public void clear() {
        head.next = null; // Removes all elements
        head.data = 0;    // Resets size to 0
    }

    @Override
    public int size() {
        return (int) head.data;  // The size is stored in head.data
    }

    @Override
    public boolean add(Object el) {
        if (el == null) {
            throw new NullPointerException("Element cannot be null");
        }
        Node newNode = new Node(el, head.next);
        head.next = newNode;
        head.data = (int) head.data + 1;  // Increment size
        return true;
    }

    @Override
    public Optional<Object> remove(Object el) {
        if (el == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node current = head;
        while (current.next != null) {
            if (Objects.equals(current.next.data, el)) {
                Object removedData = current.next.data;
                current.next = current.next.next;
                head.data = (int) head.data - 1;  // Decrement size
                return Optional.of(removedData);
            }
            current = current.next;
        }
        return Optional.empty();
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node current = head.next; // Skip the head node
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public String toString() {
        if (head.next == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node current = head.next;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<>() {
            private Node current = head.next;
            private Node previous = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object data = current.data;
                previous = current;
                current = current.next;
                return data;
            }

            @Override
            public void remove() {
                if (previous == head) {
                    throw new IllegalStateException("Cannot remove before calling next()");
                }

                Node nodeBeforePrevious = head;
                while (nodeBeforePrevious.next != previous) {
                    nodeBeforePrevious = nodeBeforePrevious.next;
                }
                nodeBeforePrevious.next = current;
                head.data = (int) head.data - 1;  // Decrement size
            }
        };
    }
}
