package ru.nsu.lavrenenkov.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * Incident Matrix implementation of Graph.
 */
public class InMatGraph<T> implements Graph<T> {

    private List<Node<T>> nodes;
    private List<Edge<T>> edges;



    InMatGraph() {
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public void addNode(Node<T> node) throws IllegalArgumentException {
        if (nodes.contains(node)) {
            throw new IllegalArgumentException("Node already exists");
        }
        nodes.add(node);
    }

    @Override
    public void deleteNode(Node<T> node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node does not exist");
        }
        nodes.remove(node);
        edges.removeIf(x -> x.from() == node || x.to() == node);
    }

    @Override
    public List<Node<T>> getNeighbors(Node<T> node) throws IllegalArgumentException {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("No such Node");
        }
        List<Node<T>> result = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (edge.from() == node) {
                result.add(edge.to());
            }
        }
        return result;
    }

    @Override
    public void addEdge(Edge<T> edge) throws IllegalArgumentException {
        if (edges.contains(edge)) {
            throw new IllegalArgumentException("Edge already exists");
        }
        edges.add(edge);
    }


    @Override
    public void deleteEdge(Edge<T> edge) throws IllegalArgumentException {
        if (!edges.contains(edge)) {
            throw new IllegalArgumentException("No such Edge");
        }
        edges.remove(edge);
    }



    @Override
    public List<Node<T>> getNodes() {
        return nodes;
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
        for (Node<T> node : nodes) {
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