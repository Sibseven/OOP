package ru.nsu.lavrenenkov.graph;

/**
 * Class for node.
 */
public class Node<T> {
    private final T id;

    /**
     * Builder.
     *
     * @param id - unique id of node
     */
    Node(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}