package hu.unideb.inf.datastructures;

/**
 * A generic type for representing directed graphs.
 * It consists of nodes and edges which connect them.
 * Directed means that A->B does NOT imply B->A.
 * The DirectedGraph instance will use N (the type
 * given by N parameter) as the underlying Node type
 *
 * @param <N> represents a specific node class
 */
public interface DirectedGraph<N> extends Graph<N>{

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
    void addDirectedEdge(N source, N target) ;
}
