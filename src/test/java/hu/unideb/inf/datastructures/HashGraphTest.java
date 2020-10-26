package hu.unideb.inf.datastructures;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashGraphTest {

    @Test
    void addDirectedEdge_NullAsFirstParamAndAnyAsSecondParam_ThrowNullPointerException() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> directedGraph.addDirectedEdge(null, 0));
    }

    @Test
    void addDirectedEdge_AnyAsFirstParamAndNullAsSecondParam_ThrowNullPointerException() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> directedGraph.addDirectedEdge(0, null));
    }

    @Test
    void addDirectedEdge_NullAsFirstParamAndNullSecondParam_ThrowNullPointerException() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> directedGraph.addDirectedEdge(null, null));
    }

    @Test
    void addDirectedEdge_NonNullFirstParamAndNonNullSecondParam_NoException() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when, then
        directedGraph.addDirectedEdge(0, 0);
    }

    @Test
    void getNodesReachableFrom_ParamIsNullAndAnyEdgesInDirectedGraph_ReturnEmptySet() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when
        var res = directedGraph.getNodesReachableFrom(null);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIsAnyAndNoEdgesInDirectedGraph_ReturnEmptySet() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        // when
        var res = directedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs0AndEdge0To0InDirectedGraph_ReturnSetWith0() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        directedGraph.addDirectedEdge(0, 0);
        // when
        var res = directedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>(Collections.singletonList(0));
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs0AndEdge0To1InDirectedGraph_ReturnSetWith1() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        directedGraph.addDirectedEdge(0, 1);
        // when
        var res = directedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>(Collections.singletonList(1));
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs1AndEdge0To1InDirectedGraph_ReturnEmptySet() {
        // given
        DirectedGraph<Integer> directedGraph = new HashGraph<>();
        directedGraph.addDirectedEdge(0, 1);
        // when
        var res = directedGraph.getNodesReachableFrom(1);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void addUndirectedEdge_NullAsFirstParamAndAnyAsSecondParam_ThrowNullPointerException() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> undirectedGraph.addUndirectedEdge(null, 0));
    }

    @Test
    void addUndirectedEdge_AnyAsFirstParamAndNullAsSecondParam_ThrowNullPointerException() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> undirectedGraph.addUndirectedEdge(0, null));
    }

    @Test
    void addUndirectedEdge_NullAsFirstParamAndNullSecondParam_ThrowNullPointerException() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when, then
        assertThrows(NullPointerException.class, () -> undirectedGraph.addUndirectedEdge(null, null));
    }

    @Test
    void addUndirectedEdge_NonNullFirstParamAndNonNullSecondParam_NoException() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when, then
        undirectedGraph.addUndirectedEdge(0, 0);
    }

    @Test
    void getNodesReachableFrom_ParamIsNullAndAnyEdgesInUndirectedGraph_ReturnEmptySet() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when
        var res = undirectedGraph.getNodesReachableFrom(null);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIsAnyAndNoEdgesInUndirectedGraph_ReturnEmptySet() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        // when
        var res = undirectedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>();
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs0AndEdge0To0InUndirectedGraph_ReturnSetWith0() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        undirectedGraph.addUndirectedEdge(0, 0);
        // when
        var res = undirectedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>(Collections.singletonList(0));
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs0AndEdge0To1InUndirectedGraph_ReturnSetWith1() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        undirectedGraph.addUndirectedEdge(0, 1);
        // when
        var res = undirectedGraph.getNodesReachableFrom(0);
        // then
        Set<Integer> expected = new HashSet<>(Collections.singletonList(1));
        assertEquals(expected, res);
    }

    @Test
    void getNodesReachableFrom_ParamIs1AndEdge0To1InUndirectedGraph_ReturnSetWith0() {
        // given
        UndirectedGraph<Integer> undirectedGraph = new HashGraph<>();
        undirectedGraph.addUndirectedEdge(0, 1);
        // when
        var res = undirectedGraph.getNodesReachableFrom(1);
        // then
        Set<Integer> expected = new HashSet<>(Collections.singletonList(0));
        assertEquals(expected, res);
    }
}
