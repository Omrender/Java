
package com.epam.rd.autocode.hashtableopen816;
public class HashtableOpen8to16Impl implements HashtableOpen8to16 {

    private int capacity;
    private int size;
    private int[] keys;
    private Object[] values;
    
    private static final int INITIAL_CAPACITY = 8;
    private static final int MAX_CAPACITY = 16;
    private static final double LOAD_FACTOR_REDUCE = 0.25;

    public HashtableOpen8to16Impl() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.keys = new int[capacity];
        this.values = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            keys[i] = Integer.MIN_VALUE;  // Sentinel for empty
        }
    }

    @Override
    public void insert(int key, Object value) {
        if (size >= capacity && capacity == MAX_CAPACITY) {
            throw new IllegalStateException("Maximum capacity reached");
        }
        
        if (size >= capacity) {
            resize(capacity * 2);
        }

        int index = findIndex(key);
        if (keys[index] == Integer.MIN_VALUE || keys[index] == key) {
            if (keys[index] == Integer.MIN_VALUE) size++;
            keys[index] = key;
            values[index] = value;
        }
    }

    @Override
    public Object search(int key) {
        int index = findIndex(key);
        if (keys[index] == key) {
            return values[index];
        }
        return null;
    }

    @Override
    public void remove(int key) {
        int index = findIndex(key);
        if (keys[index] == key) {
            keys[index] = Integer.MIN_VALUE;  // Mark as empty
            values[index] = null;
            size--;

            if (size > 0 && size <= capacity / 4 && capacity > INITIAL_CAPACITY) {
                resize(capacity / 2);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int[] keys() {
        int[] result = new int[size];
        int idx = 0;
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != Integer.MIN_VALUE) {
                result[idx++] = keys[i];
            }
        }
        return result;
    }

    private int findIndex(int key) {
        int hash = key % capacity;
        if (hash < 0) hash += capacity;

        while (keys[hash] != Integer.MIN_VALUE && keys[hash] != key) {
            hash = (hash + 1) % capacity;
        }

        return hash;
    }

    private void resize(int newCapacity) {
        int[] oldKeys = keys;
        Object[] oldValues = values;

        keys = new int[newCapacity];
        values = new Object[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            keys[i] = Integer.MIN_VALUE;
        }

        int oldCapacity = capacity;
        capacity = newCapacity;
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldKeys[i] != Integer.MIN_VALUE) {
                insert(oldKeys[i], oldValues[i]);
            }
        }
    }
}
