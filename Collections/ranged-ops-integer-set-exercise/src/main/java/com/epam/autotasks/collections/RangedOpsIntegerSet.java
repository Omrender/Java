package com.epam.autotasks.collections;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.TreeSet;

class RangedOpsIntegerSet extends AbstractSet<Integer> {
    private final TreeSet<Integer> set;

    public RangedOpsIntegerSet() {
        this.set = new TreeSet<>();
    }

    @Override
    public boolean add(final Integer integer) {
        return set.add(integer);
    }

    @Override
    public boolean remove(final Object o) {
        if (o instanceof Integer) {
            return set.remove(o);
        }
        return false;
    }

    public boolean add(int fromInclusive, int toExclusive) {
        boolean modified = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (set.add(i)) {
                modified = true;
            }
        }
        return modified;
    }

    public boolean remove(int fromInclusive, int toExclusive) {
        boolean modified = false;
        for (int i = fromInclusive; i < toExclusive; i++) {
            if (set.remove(i)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public Iterator<Integer> iterator() {
        return set.iterator();
    }

    @Override
    public int size() {
        return set.size();
    }
}
