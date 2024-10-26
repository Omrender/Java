package com.epam.rd.autocode.queue;

public final class Buyer {

    private static String nextName;

    static {
        resetNames();
    }

    public static void resetNames() {
        nextName = "A";
    }

    public static Buyer nextBuyer() {
        Buyer newBuyer = new Buyer(nextName);
        incrementName();
        return newBuyer;
    }

    // Increment the buyer name (e.g., A -> B -> ... -> Z -> AA -> AB ...)
    private static void incrementName() {
        StringBuilder sb = new StringBuilder(nextName);
        int index = sb.length() - 1;

        while (index >= 0) {
            if (sb.charAt(index) == 'Z') {
                sb.setCharAt(index, 'A');
                if (index == 0) {
                    sb.insert(0, 'A');  // Insert new letter if all are 'Z'
                }
                index--;
            } else {
                sb.setCharAt(index, (char) (sb.charAt(index) + 1));
                break;
            }
        }

        nextName = sb.toString();
    }

    private final String name;

    private Buyer(String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return name.equals(buyer.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
