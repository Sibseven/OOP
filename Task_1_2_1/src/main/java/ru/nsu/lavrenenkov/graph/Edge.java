package ru.nsu.lavrenenkov.graph;

import java.util.Objects;

/**
 * Class for edge.
 *
 * @param <T> - type of node incident to edge
 */
public record Edge<T>(Node<T> from, Node<T> to, int weight) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Edge<?> edge = (Edge<?>) o;
        if (weight != edge.weight) {
            return false;
        }
        if (!Objects.equals(from, edge.from)) {
            return false;
        }
        return Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }
}
