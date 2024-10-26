package com.epam.rd.autocode.collection.array;

import java.util.Optional;

public class SimpleArrayListImpl implements SimpleArrayList {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_LOAD_FACTOR = 0.75;
    private static final double DECREASE_LOAD_FACTOR = 0.4;
    private static final int FACTOR_MULTIPLIER = 2;

    private Object[] elements;
    private int size;

    /**
     * Creates a list with the default capacity = 10.
     */
    public SimpleArrayListImpl() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public boolean add(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        ensureCapacity();
        elements[size++] = element;
        return true;
    }

    @Override
    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean decreaseCapacity() {
        int currentCapacity = elements.length;
        if (size <= currentCapacity * DECREASE_LOAD_FACTOR) {
            // Halve the capacity when size is less than or equal to 40% of the current capacity
            int newCapacity = Math.max(DEFAULT_CAPACITY, currentCapacity / FACTOR_MULTIPLIER);
            if (newCapacity < currentCapacity) {
                Object[] newElements = new Object[newCapacity];
                System.arraycopy(elements, 0, newElements, 0, size);
                elements = newElements;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return elements[index];
    }

    @Override
    public Optional<Object> remove(Object element) {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }

        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                Object removedElement = elements[i];
                // Shift elements left to fill the gap
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[--size] = null; // Clear the last element to avoid memory leaks
                decreaseCapacity();
                return Optional.of(removedElement);
            }
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Ensures that the internal array has enough capacity to add a new element.
     * If the size reaches or exceeds 75% of the current capacity, the capacity is increased.
     */
    private void ensureCapacity() {
        if (size >= elements.length * INCREASE_LOAD_FACTOR) {
            int newCapacity = elements.length * FACTOR_MULTIPLIER; // Simply double the capacity
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    /**
     * Checks if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    private boolean isEmpty() {
        return size == 0;
    }
}
