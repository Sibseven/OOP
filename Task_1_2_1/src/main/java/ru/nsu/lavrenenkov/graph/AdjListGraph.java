package ru.nsu.lavrenenkov.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Class for holding graph in Adjacency lists.
 */
public class AdjListGraph implements Graph {
    private final List<Node> nodes;

    /**
     * Builder.
     */
    AdjListGraph() {
        this.nodes = new ArrayList<>();
    }

    /**
     * Method finds index in ArrayList for given id.
     *
     * @param id - id of node
     *
     * @return index in ArrayList
     */
    private int findNodeIndexByid(int id) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds node to graph.
     *
     * @param id - unique id of node
     *
     * @throws IllegalArgumentException if node with given id already exists in graph.
     */
    @Override
    public void addNode(int id) throws IllegalArgumentException {
        if (findNodeIndexByid(id) >= 0) {
            throw new IllegalArgumentException("Node already exists");
        }
        nodes.add(new Node(id));

    }

    /**
     * Deletes node from graph.
     *
     * @param id - unique id of node
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    @Override
    public void deleteNode(int id) throws IllegalArgumentException {
        int index = findNodeIndexByid(id);
        if (index < 0) {
            throw new IllegalArgumentException("vertex are not present in the graph.");
        }
        for (Node node : nodes) {
            node.elements.removeIf(x -> x == id);
        }
        nodes.remove(index);
    }


    /**
     * Returns list of neighbors.
     *
     * @param id - unique id of node
     *
     * @return List of node IDs that have an edge originating from the node with the given ID.
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    @Override
    public List<Integer> getNeighbors(int id) throws IllegalArgumentException {
        int index = findNodeIndexByid(id);
        if (index < 0) {
            throw new IllegalArgumentException("vertex are not present in the graph.");
        }
        return nodes.get(index).elements;
    }

    /**
     * Adds directed edge from node with idFrom to node with idTo.
     *
     * @param idFrom - id
     *
     * @param idTo - id
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     */
    @Override
    public void addEdge(int idFrom, int idTo) throws IllegalArgumentException {
        int indexFrom = findNodeIndexByid(idFrom);
        int indexTo = findNodeIndexByid(idTo);
        if (indexTo < 0 || indexFrom < 0) {
            throw new IllegalArgumentException("One/both vertices are not present in the graph.");
        }
        nodes.get(indexFrom).elements.add(idTo);
    }

    /**
     * Deletes directed edge from node with idFrom to node with idTo.
     *
     * @param idFrom - id
     *
     * @param idTo - id
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     *     or if nodes exists but not an edge between them
     */
    @Override
    public void deleteEdge(int idFrom, int idTo) throws IllegalArgumentException {
        int indexFrom = findNodeIndexByid(idFrom);
        int indexTo = findNodeIndexByid(idTo);
        if (indexTo < 0 || indexFrom < 0) {
            throw new IllegalArgumentException("One/both vertices are not present in the graph.");
        }
        if (!nodes.get(indexFrom).elements.remove(Integer.valueOf(idTo))) {
            throw new IllegalArgumentException("edge is not present in the graph.");
        }
    }

    /**
     * Reads graph from file.
     *
     * @param file - file to read from.
     *
     * @return AdjListGraph
     */
    @Override
    public Graph readFromFile(File file)  {
        AdjListGraph graph = new AdjListGraph();
        try (Scanner scanner = new Scanner(file)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            for (int i = 1; i < n + 1; i++) {
                graph.addNode(i);
            }
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph.addEdge(a, b);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }

        return graph;
    }

    /**
     * Method Returns all ids of nodes in graph.
     *
     * @return List of ids
     */
    @Override
    public List<Integer> getNodeIds() {
        return nodes.stream()
                .map(node -> node.id)
                .collect(Collectors.toList());
    }

    /**
     * Method for counting number of incident edges of all nodes.
     *
     * @return array of number of edges incident to each node
     */
    public int[] getEdgesCount() {
        int[] result = new int[nodes.size()];
        for (int i = 0; i < result.length; i++) {
            final int id = nodes.get(i).id;
            result[i] = nodes.get(i).elements.size();
            for (int j = 0; j < result.length; j++) {
                result[i] += nodes.get(j).elements.stream().filter(x -> x == id).count();
            }
        }
        return result;
    }
}
