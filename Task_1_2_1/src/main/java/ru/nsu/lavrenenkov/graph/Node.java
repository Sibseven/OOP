package ru.nsu.lavrenenkov.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for holding one row of Adjacent/Incident matrix or one node of Adjacent list .
 * Fields have no "private" tag on purpose,
 * Because lists of instances of this class will be private themselves.
 */
public class Node {
    int id;
    List<Integer> elements;

    /**
     * Builder.
     *
     * @param id - unique id of node
     */
    Node(int id) {
        this.id = id;
        elements = new ArrayList<>();
    }
}
