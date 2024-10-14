package ru.nsu.lavrenenkov.graph;

import java.io.File;
import java.util.List;

/**
 * Interface for working with graph.
 */
public interface Graph {

    /**
     * Adds node to graph.
     *
     * @param id - unique id of node
     *
     * @throws IllegalArgumentException if node with given id already exists in graph.
     */
    void addNode(int id) throws IllegalArgumentException;


    /**
     * Deletes node from graph
     * @param id - unique id of node
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    void deleteNode(int id) throws IllegalArgumentException;

    /**
     * Returns list of neighbors.
     *
     * @param id - unique id of node
     *
     * @return List of node IDs that have an edge originating from the node with the given ID.
     *
     * @throws IllegalArgumentException if no such node with given id in graph
     */
    List<Integer> getNeighbors(int id) throws IllegalArgumentException;


    /**
     * Adds directed edge from node with idFrom to node with idTo
     * @param idFrom - id
     *
     * @param idTo - id
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     */
    void addEdge(int idFrom, int idTo) throws IllegalArgumentException;


    /**
     * Deletes directed edge from node with idFrom to node with idTo
     *
     * @param idFrom - id
     *
     * @param idTo - id
     *
     * @throws IllegalArgumentException - if no such node/nodes with given id/ids in graph
     * or if nodes exists but not an edge between them
     */
    void deleteEdge(int idFrom, int idTo) throws IllegalArgumentException;

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
    Graph readFromFile(File file);

    /**
     * Returns all ids of nodes in graph
     * @return List of ids
     */
    List<Integer> getNodeIds();

}
