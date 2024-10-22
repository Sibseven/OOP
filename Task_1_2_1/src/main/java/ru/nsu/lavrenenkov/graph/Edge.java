package ru.nsu.lavrenenkov.graph;

import java.util.Objects;

/**
 * Class for edge.
 *
 * @param <T> - type of node incident to edge
 */
public record Edge<T>(Node<T> from, Node<T> to, int weight) {

}
