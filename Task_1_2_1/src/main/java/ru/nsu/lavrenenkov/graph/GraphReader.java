package ru.nsu.lavrenenkov.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Class for reading graph from file.
 */
public class GraphReader {

    /**
     * Method for reading and parsing file into graph.
     *
     * @param graph - graph to read into
     *
     * @param file - file to read from
     *
     * @param parser - function that parse single node
     *
     * @param <T> - type of nodes in graph
     */
    public <T> void read(Graph<T> graph, File file, Function<String, T> parser) {
        List<Node<T>> nodes = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine();
                T data = parser.apply(line);
                Node<T> node = new Node<>(data);
                nodes.add(node);
                graph.addNode(node);
            }
            for (int i = 0; i < m; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                int c = scanner.nextInt();
                Edge<T> edge = new Edge<>(nodes.get(a - 1), nodes.get(b - 1), c);
                graph.addEdge(edge);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }

    }
}
