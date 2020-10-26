package hu.unideb.inf.utils;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A basic implementation of a graph.
 *
 * @param <N>
 */
public class HashGraph<N> implements UndirectedGraph<N>, DirectedGraph<N> {

    /**
     * This class represents a directed edge, which connects two nodes.
     * The directed edge goes from source to target.
     */
    @Data
    private class Edge {
        private final N source;
        private final N target;
    }

    private Set<Edge> edges = new HashSet<>();

    @Override
    public void addUndirectedEdge(N node0, N node1) {
        Objects.requireNonNull(node0);
        Objects.requireNonNull(node1);
        edges.add(new Edge(node0, node1));
        edges.add(new Edge(node1, node0));
    }

    @Override
    public void addDirectedEdge(N source, N target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        edges.add(new Edge(source, target));
    }

    @Override
    public Set<N> getNodesReachableFrom(N src) {
        return edges
                .stream()
                .filter(edge -> edge.getSource().equals(src))
                .map(edge -> edge.getTarget()).collect(Collectors.toSet());
    }
}
