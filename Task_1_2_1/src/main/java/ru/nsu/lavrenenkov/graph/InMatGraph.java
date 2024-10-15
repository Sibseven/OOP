package ru.nsu.lavrenenkov.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Incident Matrix implementation of Graph.
 */
public class InMatGraph implements Graph {

    private final List<Node> nodes;

    /**
     * Builder.
     */
    InMatGraph() {
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


    @Override
    public void addNode(int id) throws IllegalArgumentException {
        if (findNodeIndexByid(id) >= 0) {
            throw new IllegalArgumentException("Node already exists");
        }
        nodes.add(new Node(id));
    }

    @Override
    public void deleteNode(int id) throws IllegalArgumentException {
        int index = findNodeIndexByid(id);
        if (index < 0) {
            throw new IllegalArgumentException("vertex are not present in the graph.");
        }
        List<Integer> edgesToDelete = new ArrayList<>();
        for (int i = 0; i < nodes.get(index).elements.size(); i++) {
            if (nodes.get(index).elements.get(i).equals(1)
                    || nodes.get(index).elements.get(i).equals(-1)) {
                edgesToDelete.add(i);
            }
        }
        for (Node matNode : nodes) {
            for (int i = edgesToDelete.size() - 1; i >= 0; i--) {
                matNode.elements.remove(edgesToDelete.get(i).intValue());
            }
        }
        nodes.remove(index);
    }

    @Override
    public List<Integer> getNeighbors(int id) {
        List<Integer> result = new ArrayList<>();
        int index = findNodeIndexByid(id);
        if (index < 0) {
            throw new IllegalArgumentException("vertex are not present in the graph.");
        }
        for (int i = 0; i < nodes.get(index).elements.size(); i++) {
            if (nodes.get(index).elements.get(i).equals(-1)) {
                for (Node node : nodes) {
                    if (node.elements.get(i).equals(1)) {
                        result.add(node.id);
                    }
                }
            }
        }
        return result;

    }

    @Override
    public void addEdge(int idFrom, int idTo) throws IllegalArgumentException {
        int indexFrom = findNodeIndexByid(idFrom);
        int indexTo = findNodeIndexByid(idTo);
        if (indexTo < 0 || indexFrom < 0) {
            throw new IllegalArgumentException("One/both vertices are not present in the graph.");
        }
        for (Node node : nodes) {
            node.elements.add(0);
        }
        nodes.get(indexFrom).elements.set(nodes.get(indexFrom).elements.size() - 1, -1);
        nodes.get(indexTo).elements.set(nodes.get(indexTo).elements.size() - 1, 1);
    }

    @Override
    public void deleteEdge(int idFrom, int idTo) throws IllegalArgumentException {
        int indexFrom = findNodeIndexByid(idFrom);
        int indexTo = findNodeIndexByid(idTo);
        if (indexTo < 0 || indexFrom < 0) {
            throw new IllegalArgumentException("One/both vertices are not present in the graph.");

        }
        int i;
        boolean noEdge = true;
        for (i = 0; i < nodes.get(0).elements.size(); i++) {
            if (nodes.get(indexFrom).elements.get(i) == -1
                    && nodes.get(indexTo).elements.get(i) == 1) {
                noEdge = false;
                break;
            }
        }
        if (noEdge) {
            throw new IllegalArgumentException("edge is not present in the graph.");
        }
        for (Node node : nodes) {
            node.elements.remove(i);
        }
    }

    /**
     * Reads graph from file.
     *
     * @param file - file to read from.
     *
     * @return InMatGraph
     */
    @Override
    public InMatGraph readFromFile(File file) {
        InMatGraph graph = new InMatGraph();
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
        return nodes.stream()
                .mapToInt(node -> (int) node.elements.stream()
                        .filter(edge -> edge != 0)
                        .count())
                .toArray();

    }
}