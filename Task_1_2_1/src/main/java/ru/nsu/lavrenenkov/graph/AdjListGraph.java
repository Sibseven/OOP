package ru.nsu.lavrenenkov.graph;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Class for holding graph in Adjacency lists.
 */
public class AdjListGraph<T> implements Graph<T> {
    private Map<Node<T>, List<Edge<T>>> nodes;



    AdjListGraph() {
        this.nodes = new HashMap<>();
    }

    @Override
    public void addNode(Node<T> node) throws IllegalArgumentException {
        if (nodes.containsKey(node)) {
            throw new IllegalArgumentException("Node already exists");
        }
        nodes.putIfAbsent(node, new ArrayList<>());

    }

    @Override
    public void deleteNode(Node<T> node) throws IllegalArgumentException {
        if (!nodes.containsKey(node)) {
            throw new IllegalArgumentException("No such Node");
        }
        nodes.remove(node);
        for (Node<T> n : nodes.keySet()) {
            nodes.get(n).removeIf(e -> e.to() == node);
        }
    }

    @Override
    public List<Node<T>> getNeighbors(Node<T> node) throws IllegalArgumentException {
        if (!nodes.containsKey(node)) {
            throw new IllegalArgumentException("No such Node");
        }
        List<Node<T>> result = new ArrayList<>();
        for (Edge<T> edge : nodes.get(node)) {
            result.add(edge.to());
        }
        return result;
    }

    @Override
    public void addEdge(Edge<T> edge) throws IllegalArgumentException {
        if (!nodes.containsKey(edge.from()) || !nodes.containsKey(edge.to())) {
            throw new IllegalArgumentException("No such node/nodes");
        }
        nodes.get(edge.from()).add(edge);
    }


    @Override
    public void deleteEdge(Edge<T> edge) throws IllegalArgumentException {
        if (!nodes.containsKey(edge.from()) || !nodes.containsKey(edge.to())) {
            throw new IllegalArgumentException("No such node/nodes");
        }
        if (!nodes.get(edge.from()).contains(edge)) {
            throw new IllegalArgumentException("No such edge");
        }
        nodes.get(edge.from()).remove(edge);
    }



    @Override
    public List<Node<T>> getNodes() {
        return new ArrayList<>(nodes.keySet());
    }

    /**
     * Method for counting number of incident edges of all nodes.
     *
     * @return array of number of edges incident to each node
     */
    @Override
    public int[] getEdgesCount() {
        int[] result = new int[nodes.size()];
        int i = 0;
        for (Node<T> node : nodes.keySet()) {
            for (Node<T> node1 : nodes.keySet()) {
                for (Edge<T> edge : nodes.get(node1)) {
                    if (edge.to() == node || edge.from() == node) {
                        result[i]++;
                    }
                }
            }
            i++;
        }

        return result;
    }
}
