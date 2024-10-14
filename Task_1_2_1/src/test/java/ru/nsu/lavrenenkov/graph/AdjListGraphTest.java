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
 * Class for testing AdjListGraph class.
 */
public class AdjListGraphTest {
    private AdjListGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new AdjListGraph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
    }
    @Test
    public void checkAddNode() {
        List<Integer> result = graph.getNodeIds();
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertEquals(result, expected);
    }

    @Test
    public void checkAddEdge() {
        graph.addEdge(1, 2);
        int[] result = graph.getEdgesCount();
        int[] expected = {1, 1, 0};
        assertArrayEquals(result, expected);
    }

    @Test
    public void checkDeleteNode() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.deleteNode(2);
        List<Integer> result = graph.getNodeIds();
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3));
        assertEquals(result, expected);
    }

    @Test
    public void checkDeleteNodeEdges() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.deleteNode(2);
        int[] result = graph.getEdgesCount();
        int[] expected = {1, 1};
        assertArrayEquals(expected, result);
    }

    @Test
    public void checkDeleteEdge() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.deleteEdge(2, 3);
        int[] result = graph.getEdgesCount();
        int[] expected = {2, 1 ,1};
        assertArrayEquals(result, expected);
    }

    @Test
    public void checkDeleteGetNeighbors() {
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 1);
        graph.addEdge(3, 1);
        List<Integer> result = graph.getNeighbors(2);
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(1);
        assertEquals(expected,result);
    }

    @Test
    public void checkReadFromFile() throws URISyntaxException {
        AdjListGraph graph = new AdjListGraph();
        File file = new File(getClass().getClassLoader().getResource("text.txt").toURI());
        graph = (AdjListGraph) graph.readFromFile(file);
        List<Integer> result = graph.getNodeIds();
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(result, expected);

        int[] resultEdges = graph.getEdgesCount();
        int[] expectedArray = {2, 2, 2, 3, 3};
        assertArrayEquals(expectedArray, resultEdges);
    }
}
