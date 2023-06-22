package tads.linkedlist;

import tads.graph.AdjacencyListGraph.Edge;

public interface ListADT<T> extends Iterable<T> {
  public void add(T o);

  public void remove(int i);

  public Edge get(int i);

  public boolean isEmpty();

  public int size();
}
