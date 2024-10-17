package ru.nsu.lavrenenkov.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for performing topological sort on graph.
 */
public class TopologicalSort<T> {
    private int[] marked;
    private Graph<T> graph;
    private List<Node<T>> nodes;
    private List<Node<T>> result;


    /**
     * Method to perform a topological sort with DFS.
     *
     * @return list of node ids sorted in topological order
     *
     * @throws IllegalArgumentException if cycle found in graph
     */
    public List<Node<T>> topologicalSort(Graph<T> graph) throws IllegalArgumentException {
        this.graph = graph;
        nodes = graph.getNodes();
        result = new ArrayList<>();
        marked = new int[nodes.size()];

        for (int i = 0; i < nodes.size(); i++) {
            if (marked[i] == 0) {
                try {
                    topoHelper(i);
                } catch (IllegalArgumentException e) {
                    System.out.println("Cycle found");
                    throw e;
                }

            }
        }

        result = result.reversed();
        return result;
    }

    /**
     * Method for DFS recursive calls.
     *
     * @param index - index of current node
     *
     * @throws IllegalArgumentException if cycle found on graph on this iteration.
     */
    private void topoHelper(int index) throws IllegalArgumentException {
        if (marked[index] == 2) {
            return;
        }
        if (marked[index] == 1) {
            throw new IllegalArgumentException();
        }
        marked[index] = 1;
        List<Node<T>> neighbors = graph.getNeighbors(nodes.get(index));
        for (Node<T> neighbor : neighbors) {
            int indexNeighbor = nodes.indexOf(neighbor);
            topoHelper(indexNeighbor);
        }
        marked[index] = 2;
        result.add(nodes.get(index));
    }
}
