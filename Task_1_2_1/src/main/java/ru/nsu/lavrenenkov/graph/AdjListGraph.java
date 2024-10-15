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
        for (Node node : nodes) {
            node.elements.removeIf(x -> x == id);
        }
        nodes.remove(index);
    }

    @Override
    public List<Integer> getNeighbors(int id) throws IllegalArgumentException {
        int index = findNodeIndexByid(id);
        if (index < 0) {
            throw new IllegalArgumentException("vertex are not present in the graph.");
        }
        return nodes.get(index).elements;
    }

    @Override
    public void addEdge(int idFrom, int idTo) throws IllegalArgumentException {
        int indexFrom = findNodeIndexByid(idFrom);
        int indexTo = findNodeIndexByid(idTo);
        if (indexTo < 0 || indexFrom < 0) {
            throw new IllegalArgumentException("One/both vertices are not present in the graph.");
        }
        nodes.get(indexFrom).elements.add(idTo);
    }


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
