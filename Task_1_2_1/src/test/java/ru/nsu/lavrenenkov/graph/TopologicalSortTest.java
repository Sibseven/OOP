package ru.nsu.lavrenenkov.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Class for testing TopologicalSort.
 */
public class TopologicalSortTest {

    @Test
    public void checkAdjListGraph() {
        AdjListGraph graph = new AdjListGraph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(5,0);
        graph.addEdge(5,2);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(4, 1);
        graph.addEdge(4,0);

        TopologicalSort sort = new TopologicalSort();
        List<Integer> result = sort.topologicalSort(graph);
        List<Integer> expected = new ArrayList<>(Arrays.asList(5, 4, 2, 3, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    public void checkAdjMatGraph() {
        AdjMatGraph graph = new AdjMatGraph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(5,0);
        graph.addEdge(5,2);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(4, 1);
        graph.addEdge(4,0);

        TopologicalSort sort = new TopologicalSort();
        List<Integer> result = sort.topologicalSort(graph);
        List<Integer> expected = new ArrayList<>(Arrays.asList(5, 4, 2, 3, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    public void checkInMatGraph() {
        InMatGraph graph = new InMatGraph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(5,0);
        graph.addEdge(5,2);
        graph.addEdge(2,3);
        graph.addEdge(3,1);
        graph.addEdge(4, 1);
        graph.addEdge(4,0);

        TopologicalSort sort = new TopologicalSort();
        List<Integer> result = sort.topologicalSort(graph);
        List<Integer> expected = new ArrayList<>(Arrays.asList(5, 4, 2, 3, 1, 0));
        assertEquals(expected, result);
    }

    @Test
    public void checkBadGraph(){
        InMatGraph graph = new InMatGraph();
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,0);
        TopologicalSort sort = new TopologicalSort();
        assertThrowsExactly(IllegalArgumentException.class, () -> sort.topologicalSort(graph));
    }
}
