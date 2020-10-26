package hu.unideb.inf.datastructures;

import java.util.Set;

/**
 * A generic type for representing Graphs.
 * Graph consists of nodes and edges which connect them.
 * The Graph instance will use N (the type
 * given by N parameter) as the underlying Node type
 *
 * @param <N> represents a specific node class
 */
public interface Graph<N> {

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
    Set<N> getNodesReachableFrom(N source);
}
