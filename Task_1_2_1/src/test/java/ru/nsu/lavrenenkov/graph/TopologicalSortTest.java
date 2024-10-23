package ru.nsu.lavrenenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing TopologicalSort.
 */
public class TopologicalSortTest {
    private List<Node<Integer>> nodes;
    private List<Edge<Integer>> edges;

    /**
     * Method for setup nodes and edges of simple acyclic graph.
     */
    @BeforeEach
    public void setUp() {
        nodes = new ArrayList<>();
        nodes.add(new Node<>(0));
        nodes.add(new Node<>(1));
        nodes.add(new Node<>(2));
        nodes.add(new Node<>(3));
        nodes.add(new Node<>(4));
        nodes.add(new Node<>(5));

        edges = new ArrayList<>();
        edges.add(new Edge<>(nodes.get(5), nodes.get(0), 1));
        edges.add(new Edge<>(nodes.get(5), nodes.get(2), 2));
        edges.add(new Edge<>(nodes.get(2), nodes.get(3), 3));
        edges.add(new Edge<>(nodes.get(3), nodes.get(1), 4));
        edges.add(new Edge<>(nodes.get(4), nodes.get(1), 5));
        edges.add(new Edge<>(nodes.get(4), nodes.get(0), 6));

    }

    @Test
    public void checkAdjListGraph() {
        AdjListGraph<Integer> graph = new AdjListGraph<>();
        graph.addNode(nodes.get(0));
        graph.addNode(nodes.get(1));
        graph.addNode(nodes.get(2));
        graph.addNode(nodes.get(3));
        graph.addNode(nodes.get(4));
        graph.addNode(nodes.get(5));
        graph.addEdge(edges.get(0));
        graph.addEdge(edges.get(1));
        graph.addEdge(edges.get(2));
        graph.addEdge(edges.get(3));
        graph.addEdge(edges.get(4));
        graph.addEdge(edges.get(5));

        TopologicalSort<Integer> sort = new TopologicalSort<>();

        List<Node<Integer>> expected = new ArrayList<>();
        expected.add(nodes.get(5));
        expected.add(nodes.get(4));
        expected.add(nodes.get(2));
        expected.add(nodes.get(3));
        expected.add(nodes.get(1));
        expected.add(nodes.get(0));

        List<Node<Integer>> result = sort.topologicalSort(graph);
        assertEquals(expected, result);
    }

    @Test
    public void checkAdjMatGraph() {
        AdjMatGraph<Integer> graph = new AdjMatGraph<>();
        graph.addNode(nodes.get(0));
        graph.addNode(nodes.get(1));
        graph.addNode(nodes.get(2));
        graph.addNode(nodes.get(3));
        graph.addNode(nodes.get(4));
        graph.addNode(nodes.get(5));
        graph.addEdge(edges.get(0));
        graph.addEdge(edges.get(1));
        graph.addEdge(edges.get(2));
        graph.addEdge(edges.get(3));
        graph.addEdge(edges.get(4));
        graph.addEdge(edges.get(5));

        TopologicalSort<Integer> sort = new TopologicalSort<>();

        List<Node<Integer>> expected = new ArrayList<>();
        expected.add(nodes.get(5));
        expected.add(nodes.get(4));
        expected.add(nodes.get(2));
        expected.add(nodes.get(3));
        expected.add(nodes.get(1));
        expected.add(nodes.get(0));

        List<Node<Integer>> result = sort.topologicalSort(graph);
        assertEquals(expected, result);
    }

    @Test
    public void checkInMatGraph() {
        InMatGraph<Integer> graph = new InMatGraph<>();
        graph.addNode(nodes.get(0));
        graph.addNode(nodes.get(1));
        graph.addNode(nodes.get(2));
        graph.addNode(nodes.get(3));
        graph.addNode(nodes.get(4));
        graph.addNode(nodes.get(5));
        graph.addEdge(edges.get(0));
        graph.addEdge(edges.get(1));
        graph.addEdge(edges.get(2));
        graph.addEdge(edges.get(3));
        graph.addEdge(edges.get(4));
        graph.addEdge(edges.get(5));

        TopologicalSort<Integer> sort = new TopologicalSort<>();

        List<Node<Integer>> expected = new ArrayList<>();
        expected.add(nodes.get(5));
        expected.add(nodes.get(4));
        expected.add(nodes.get(2));
        expected.add(nodes.get(3));
        expected.add(nodes.get(1));
        expected.add(nodes.get(0));

        List<Node<Integer>> result = sort.topologicalSort(graph);
        assertEquals(expected, result);
    }

    @Test
    public void checkBadGraph() {
        InMatGraph<Integer> graph = new InMatGraph<>();
        graph.addNode(nodes.get(0));
        graph.addNode(nodes.get(1));
        graph.addNode(nodes.get(2));

        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        graph.addEdge(new Edge<>(nodes.get(1), nodes.get(2), 1));
        graph.addEdge(new Edge<>(nodes.get(2), nodes.get(0), 1));
        TopologicalSort<Integer> sort = new TopologicalSort<>();
        assertThrowsExactly(IllegalArgumentException.class, () -> sort.topologicalSort(graph));
    }
}
