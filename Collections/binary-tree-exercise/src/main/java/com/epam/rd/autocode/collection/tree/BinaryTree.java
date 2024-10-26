package com.epam.rd.autocode.collection.tree;

import java.util.Objects;
import java.util.Optional;

/**
 * Binary Search Tree.<br>
 * This class uses the natural ordering to compare elements.<br>
 * This implementation does not provide any balancing.
 *
 * @author D. Kolesnikov, Y. Mishcheriakov
 */
public class BinaryTree {
    private Node root;
    private int size;

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTree(Integer... elements) {
        this();
        addAll(elements);
    }

    // Nested class Node
    private static class Node {
        Integer value;
        Node left, right;

        Node(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public boolean add(Integer element) {
        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        } else {
            return addRecursive(root, element);
        }
    }

    private boolean addRecursive(Node current, Integer element) {
        if (element.equals(current.value)) {
            return false; // Duplicate value
        }
        if (element < current.value) {
            if (current.left == null) {
                current.left = new Node(element);
                size++;
                return true;
            } else {
                return addRecursive(current.left, element);
            }
        } else {
            if (current.right == null) {
                current.right = new Node(element);
                size++;
                return true;
            } else {
                return addRecursive(current.right, element);
            }
        }
    }

    public void addAll(Integer... elements) {
        for (Integer element : elements) {
            add(element);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        appendInOrder(root, sb);
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Remove the last comma and space
        }
        sb.append("]");
        return sb.toString();
    }

    private void appendInOrder(Node node, StringBuilder sb) {
        if (node != null) {
            appendInOrder(node.left, sb);
            sb.append(node.value).append(", ");
            appendInOrder(node.right, sb);
        }
    }

    public Optional<Integer> remove(Integer element) {
        Node[] nodeToDelete = new Node[1];
        Node parent = findAndTrackParent(root, element, null, nodeToDelete);
        if (nodeToDelete[0] == null) {
            return Optional.empty(); // Element not found
        }
        return Optional.of(deleteNode(nodeToDelete[0], parent));
    }

    private Node findAndTrackParent(Node current, Integer element, Node parent, Node[] nodeToDelete) {
        if (current == null) {
            return null;
        }
        if (element.equals(current.value)) {
            nodeToDelete[0] = current;
            return parent;
        }
        if (element < current.value) {
            return findAndTrackParent(current.left, element, current, nodeToDelete);
        } else {
            return findAndTrackParent(current.right, element, current, nodeToDelete);
        }
    }

    private Integer deleteNode(Node nodeToDelete, Node parent) {
        // Case 1: Node to delete has no children
        if (nodeToDelete.left == null && nodeToDelete.right == null) {
            if (parent == null) {
                root = null; // Tree is empty now
            } else if (parent.left == nodeToDelete) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } 
        // Case 2: Node to delete has one child
        else if (nodeToDelete.left == null || nodeToDelete.right == null) {
            Node child = (nodeToDelete.left != null) ? nodeToDelete.left : nodeToDelete.right;
            if (parent == null) {
                root = child; // Replacing the root
            } else if (parent.left == nodeToDelete) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        } 
        // Case 3: Node to delete has two children
        else {
            Node successorParent = nodeToDelete;
            Node successor = nodeToDelete.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            nodeToDelete.value = successor.value;
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
            nodeToDelete = successor;
        }
        size--;
        return nodeToDelete.value;
    }

    public int size() {
        return size;
    }
}