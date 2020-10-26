package hu.unideb.inf.utils;

import java.util.Set;

/**
 * DirectedGraph graphs is a kind of Graph.
 * Edges connect two nodes.
 * Directed means that A->B does not imply B->A.
 * @param <N>
 */
public interface DirectedGraph<N> extends Graph<N>{

    void addDirectedEdge(N node0, N node1) ;
}
