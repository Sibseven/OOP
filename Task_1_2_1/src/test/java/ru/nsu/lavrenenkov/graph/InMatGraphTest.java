package ru.nsu.lavrenenkov.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for testing InMatGraph class.
 */
public class InMatGraphTest {
    private InMatGraph<Integer> graph;
    private List<Node<Integer>> nodes;

    /**
     * Method for setting up a simple graph.
     */
    @BeforeEach
    public void setUp() {
        graph = new InMatGraph<>();
        nodes = new ArrayList<>();
        nodes.add(new Node<>(1));
        nodes.add(new Node<>(2));
        nodes.add(new Node<>(3));
        graph.addNode(nodes.get(0));
        graph.addNode(nodes.get(1));
        graph.addNode(nodes.get(2));
    }

    @Test
    public void checkAddNode() {
        List<Node<Integer>> result = graph.getNodes();
        assertEquals(nodes, result);
    }

    @Test
    public void checkAddEdge() {
        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        int[] result = graph.getEdgesCount();
        int[] expected = {1, 1, 0};
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkDeleteNode() {
        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        graph.addEdge(new Edge<>(nodes.get(1), nodes.get(2), 1));
        graph.deleteNode(nodes.get(1));
        List<Node<Integer>> expected = graph.getNodes();
        nodes.remove(1);
        assertEquals(nodes, expected);
    }

    @Test
    public void checkDeleteNodeEdges() {
        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        graph.addEdge(new Edge<>(nodes.get(1), nodes.get(2), 1));
        graph.addEdge(new Edge<>(nodes.get(2), nodes.get(0), 1));
        graph.deleteNode(nodes.get(1));
        int[] result = graph.getEdgesCount();
        int[] expected = {1, 1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkDeleteEdge() {
        Edge<Integer> edge = new Edge<>(nodes.get(1), nodes.get(2), 1);
        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        graph.addEdge(edge);
        graph.addEdge(new Edge<>(nodes.get(2), nodes.get(0), 1));
        graph.deleteEdge(edge);
        int[] result = graph.getEdgesCount();
        int[] expected = {2, 1, 1};
        assertArrayEquals(result, expected);
    }

    @Test
    public void checkDeleteGetNeighbors() {
        graph.addEdge(new Edge<>(nodes.get(0), nodes.get(1), 1));
        graph.addEdge(new Edge<>(nodes.get(1), nodes.get(2), 1));
        graph.addEdge(new Edge<>(nodes.get(2), nodes.get(0), 1));
        graph.addEdge(new Edge<>(nodes.get(1), nodes.get(0), 1));
        List<Node<Integer>> result = graph.getNeighbors(nodes.get(1));
        List<Node<Integer>> expected = new ArrayList<>();
        expected.add(nodes.get(2));
        expected.add(nodes.get(0));
        assertEquals(expected, result);
    }

    @Test
    public void checkReadFromFile() throws URISyntaxException {
        GraphReader reader = new GraphReader();
        File file = new File(getClass().getClassLoader().getResource("text.txt").toURI());
        InMatGraph<Integer> graph = new InMatGraph<>();
        reader.read(graph, file, Integer::parseInt);
        List<Node<Integer>> nodes = graph.getNodes();
        List<Integer> result = new ArrayList<>();
        for (Node<Integer> node : nodes) {
            result.add(node.getId());
        }
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(result, expected);
        int[] resultEdges = graph.getEdgesCount();
        int[] expectedArray = {2, 2, 2, 3, 3};
        assertArrayEquals(expectedArray, resultEdges);
    }

    @Test
    public void checkReadFromFile2() throws URISyntaxException {
        GraphReader reader = new GraphReader();
        File file = new File(getClass().getClassLoader().getResource("text2.txt").toURI());
        InMatGraph<Double> graph = new InMatGraph<>();
        reader.read(graph, file, Double::parseDouble);
        List<Node<Double>> nodes = graph.getNodes();
        List<Double> result = new ArrayList<>();
        for (Node<Double> node : nodes) {
            result.add(node.getId());
        }
        List<Double> expected = new ArrayList<>(Arrays.asList(1.5, 2.5, 3.6, 4.7, 5.7));
        assertEquals(result, expected);
        int[] resultEdges = graph.getEdgesCount();
        int[] expectedArray = {2, 2, 2, 3, 3};
        assertArrayEquals(expectedArray, resultEdges);
    }
}
