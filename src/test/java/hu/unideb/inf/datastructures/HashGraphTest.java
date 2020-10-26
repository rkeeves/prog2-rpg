package hu.unideb.inf.utils;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashGraphTest {

    @Test
    void given_graph_when_addDirectedEdgeAndSourceIsNull_Throw() {
        // given
        HashGraph<Integer> g = new HashGraph<Integer>();
        // when, then
        assertThrows(NullPointerException.class, () -> g.addDirectedEdge(null, 0));
    }

    @Test
    void given_graph_when_addDirectedEdgeAndTargetIsNull_Throw() {
        // given
        HashGraph<Integer> g = new HashGraph<Integer>();
        // when, then
        assertThrows(NullPointerException.class, () -> g.addDirectedEdge(0, null));
    }

    @Test
    void given_graph_when_addDirectedEdgeAndBothAreNull_Throw() {
        // given
        HashGraph<Integer> g = new HashGraph<Integer>();
        // when, then
        assertThrows(NullPointerException.class, () -> g.addDirectedEdge(null, null));
    }

    @Test
    void given_directedEmptyGraph_when_getNodesReachableFrom0_ReturnEmpty() {
        // given
        DirectedGraph<Integer> g = new HashGraph<Integer>();
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void given_directed0to0_when_getNodesReachableFrom0_Return0() {
        // given
        DirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addDirectedEdge(0, 0);
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        expected.add(0);
        assertEquals(expected, res);
    }

    @Test
    void given_directed0to1_when_getNodesReachableFrom0_Return1() {
        // given
        DirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addDirectedEdge(0, 1);
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        assertEquals(expected, res);
    }

    @Test
    void given_directed0to1_when_getNodesReachableFrom1_ReturnEmpty() {
        // given
        DirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addDirectedEdge(0, 1);
        // when
        var res = g.getNodesReachableFrom(1);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void given_udirectedEmptyGraph_when_getNodesReachableFrom0_ReturnEmpty() {
        // given
        UndirectedGraph<Integer> g = new HashGraph<Integer>();
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void given_undirected0to0_when_getNodesReachableFrom0_Return0() {
        // given
        UndirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addUndirectedEdge(0, 0);
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        expected.add(0);
        assertEquals(expected, res);
    }

    @Test
    void given_undirected0to1_when_getNodesReachableFrom0_Return1() {
        // given
        UndirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addUndirectedEdge(0, 1);
        // when
        var res = g.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        expected.add(1);
        assertEquals(expected, res);
    }

    @Test
    void given_undirected0to1_when_getNodesReachableFrom1_Return0() {
        // given
        UndirectedGraph<Integer> g = new HashGraph<Integer>();
        g.addUndirectedEdge(0, 1);
        // when
        var res = g.getNodesReachableFrom(1);
        // then
        Set<Integer> expected = new HashSet<>();
        expected.add(0);
        assertEquals(expected, res);
    }
}
