package hu.unideb.inf.utils;

import java.util.Set;

/**
 * Graph represents nodes and their connecting edges.
 * An edge can be directed or undirected.
 * These are specified in UndirectedGraph and DirectedGraph.
 * @param <N>
 */
public interface Graph<N> {

    Set<N> getNodesReachableFrom(N source);
}
