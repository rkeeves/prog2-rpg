package hu.unideb.inf.datastructures;

/**
 * A generic type for representing undirected graphs.
 * Undirected graphs is a kind of Graph.
 * It consists of nodes and edges which connect them.
 * Undirected means that A->B implies B->A.
 * The UndirectedGraph instance will use N (the type
 * given by N parameter) as the underlying Node type
 *
 * @param <N> represents a specific node class
 */
public interface UndirectedGraph<N> extends Graph<N> {

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
    void addUndirectedEdge(N some, N other);
}
