package ru.nsu.lavrenenkov.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class for holding graph in Adjacency matrix.
 */
public class AdjMatGraph<T> implements Graph<T> {
    private final Map<Node<T>, List<Node<T>>> nodes;
    private final List<Edge<T>> edges;



    AdjMatGraph() {
        this.nodes = new HashMap<>();
        this.edges = new ArrayList<>();
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
            nodes.get(n).removeIf(e -> e == node);
        }
        edges.removeIf(e -> e.from() == node || e.to() == node);
    }

    @Override
    public List<Node<T>> getNeighbors(Node<T> node) throws IllegalArgumentException {
        if (!nodes.containsKey(node)) {
            throw new IllegalArgumentException("No such Node");
        }
        return nodes.get(node);
    }

    @Override
    public void addEdge(Edge<T> edge) throws IllegalArgumentException {
        if (!nodes.containsKey(edge.from()) || !nodes.containsKey(edge.to())) {
            throw new IllegalArgumentException("No such node/nodes");
        }
        nodes.get(edge.from()).add(edge.to());
        edges.add(edge);
    }


    @Override
    public void deleteEdge(Edge<T> edge) throws IllegalArgumentException {
        if (!nodes.containsKey(edge.from()) || !nodes.containsKey(edge.to())) {
            throw new IllegalArgumentException("No such node/nodes");
        }
        if (!edges.contains(edge)) {
            throw new IllegalArgumentException("No such edge");
        }
        nodes.get(edge.from()).remove(edge.to());
        edges.remove(edge);
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
            for (Edge<T> edge : edges) {
                if (edge.to() == node || edge.from() == node) {
                    result[i]++;
                }
            }
            i++;
        }
        return result;
    }
}
