package com.epam.rd.autocode.dllist;

import java.util.Optional;

public class DoublyLinkedListImpl implements DoublyLinkedList {

    private Node head;
    private Node tail;

    private static class Node {
        Object element;
        Node next;
        Node prev;

        Node(Object obj, Node prev, Node next) {
            this.element = obj;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public boolean addFirst(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node newNode = new Node(element, null, head);
        if (head == null) {
            head = tail = newNode;  // Empty list, head and tail are the same
        } else {
            head.prev = newNode;
            head = newNode;
        }
        return true;
    }

    @Override
    public boolean addLast(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node newNode = new Node(element, tail, null);
        if (tail == null) {
            head = tail = newNode;  // Empty list, head and tail are the same
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return true;
    }

    @Override
    public void delete(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node current = getNodeAt(index);

        if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;  // List becomes empty
            }
        } else if (current == tail) {
            tail = current.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    @Override
    public Optional<Object> remove(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        Node current = head;
        while (current != null) {
            if (current.element.equals(element)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;  // List becomes empty
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                return Optional.of(current.element);
            }
            current = current.next;
        }
        return Optional.empty();
    }

    @Override
    public boolean set(int index, Object element) throws IndexOutOfBoundsException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        Node current = getNodeAt(index);
        current.element = element;
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.element;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.element.toString());
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    private Node getNodeAt(int index) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
}
