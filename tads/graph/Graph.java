package tads.graph;

import java.util.Iterator;
import tads.graph.AdjacencyListGraph.Edge;

public interface Graph {
  public void addEdge(int from, int to);

  public void addEdge(int from, int to, int cost);

  public void removeEdge(int from, int to);

  public void removeVertex(int v);

  public void Dijkstra(int start, int end);

  public void TopologicalOrder();

  public Iterator<Edge> adjacents(int v);

  public boolean hasEdge(int from, int to);
}
