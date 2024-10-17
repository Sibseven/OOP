package ru.nsu.lavrenenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class for testing edge.
 */
public class EdgeTest {

    @Test
    public void testEdge() {
        Edge<Integer> edge = new Edge<>(new Node<>(1), new Node<>(2), 1);
        int hash = edge.hashCode();
        assertEquals(hash, edge.hashCode());
    }
}
