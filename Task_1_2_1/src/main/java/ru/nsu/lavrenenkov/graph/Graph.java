package ru.nsu.lavrenenkov.graph;

import java.io.File;
import java.util.List;

/**
 * Interface for working with graph.
 */
public interface Graph<T> {

    /**
     * Adds node to graph.
     *
     * @param node - unique node
     *
     * @throws IllegalArgumentException if node already exists in graph.
     */
    void addNode(Node<T> node) throws IllegalArgumentException;


    /**
     * Deletes node from graph.
     *
     * @param node - unique node
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    void deleteNode(Node<T> node) throws IllegalArgumentException;

    /**
     * Returns list of neighbors.
     *
     * @param node - unique node
     *
     * @return List of node IDs that have an edge originating from the node with the given ID.
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    List<Node<T>> getNeighbors(Node<T> node) throws IllegalArgumentException;


    /**
     * Adds directed edge from node with idFrom to node with idTo.
     *
     * @param edge - edge to add
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     */
    void addEdge(Edge<T> edge) throws IllegalArgumentException;


    /**
     * Deletes directed edge from node with idFrom to node with idTo.
     *
     * @param edge - edge to delete
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     *     or if nodes exists but not an edge between them
     */
    void deleteEdge(Edge<T> edge) throws IllegalArgumentException;

    /**
     * Reads graph from file.
     * Format:
     * [number of nodes] [number of edges]
     * [idFrom1] [idTo1]
     * ...
     * [idFrom*number of edges*] [idTo*number of edges*]
     *
     * @param file - file.
     *
     * @return Objects implementing Graph Interface.
     */
//    Graph<T> readFromFile(File file);

    /**
     * Returns all ids of nodes in graph.
     *
     * @return List of ids
     */
    List<Node<T>> getNodes();

    int[] getEdgesCount();

}
