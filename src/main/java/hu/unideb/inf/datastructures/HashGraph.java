package hu.unideb.inf.datastructures;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A basic generic implementation of a graph, which uses
 * java.util.HashSet to model the underlying relationship.
 * (as a subset of Node X Node cartesian product).
 * Parametrized for a node class.
 * In more layman's terms:
 * The HashGraph instance will use N (the type
 * given by N parameter) as the underlying Node type.
 *
 * @param <N> represents a specific node class
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

    /**
     * Set to hold all edges.
     * Edges are essentially relationships,
     * aka a subset of the Cartesian product of Node X Node.
     */
    private final Set<Edge> edges = new HashSet<>();

    /**
     * Adds an undirected edge to the graph.
     * An undirected represents a relationship of A->B and B->A.
     * Undirected means that if you add an A->B relationship,
     * you will add A->B and B->A to the set.
     * <p>
     * Note: If you add A->B you WILL add B->A too.
     * </p>
     * <p>
     * Note: throws java.lang.NullPointerException if either other or some is null
     * </p>
     *
     * @param some  a Node from which 'other' is reachable
     * @param other a Node from which 'some' is reachable
     */
    @Override
    public void addUndirectedEdge(N some, N other) {
        Objects.requireNonNull(some);
        Objects.requireNonNull(other);
        edges.add(new Edge(some, other));
        edges.add(new Edge(other, some));
    }

    /**
     * Adds a directed edge to the graph.
     * A directed edge represents a relationship of A->B.
     * Directed means that if you add an A->B relationship,
     * you will only add A->B to the set.
     * <p>
     * Note: If you add A->B you will NOT add B->A.
     * </p>
     * <p>
     * Note: throws java.lang.NullPointerException if either source or target is null
     * </p>
     *
     * @param source the node from which the edge starts
     * @param target the node in which the edge ends
     */
    @Override
    public void addDirectedEdge(N source, N target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        edges.add(new Edge(source, target));
    }

    /**
     * Returns a set of all nodes reachable from source Node.
     * An edge represents a relationship between A and B Nodes.
     * This method returns all B Nodes for which
     * there exists a A->B relationship such that
     * Node A equals to the Node given by parameter source.
     * <p>
     * Note: the method doesn't throw on null but return empty set.
     * </p>
     *
     * @param source the node from which we want to calculate reachable
     * @return a set of reachable nodes from Node source
     */
    @Override
    public Set<N> getNodesReachableFrom(N source) {
        return edges
                .stream()
                .filter(edge -> edge.getSource().equals(source))
                .map(Edge::getTarget)
                .collect(Collectors.toSet());
    }
}
