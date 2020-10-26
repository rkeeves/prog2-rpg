package hu.unideb.inf.utils;

/**
 * Undirected graphs is a kind of Graph.
 * Edges connect two nodes.
 * Undirected means that A->B implies B->A.
 * @param <N>
 */
public interface UndirectedGraph<N> extends Graph<N>{

    void addUndirectedEdge(N node0, N node1) ;
}
