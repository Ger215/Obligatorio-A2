package tads.graph;

import java.util.Iterator;
import tads.PriorityQueue.PriorityQueueDijkstra;
import tads.PriorityQueue.PriorityQueueTopoOrder;
import tads.linkedlist.LinkedList;

public class AdjacencyListGraph implements Graph {

  public class Edge {

    public int to;
    public int cost;

    public Edge(int to) {
      this.to = to;
    }

    public Edge(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }

  private LinkedList<Edge> graphLinkedList[];
  private boolean isDirected;

  public AdjacencyListGraph(int size, boolean isDirected) {
    this.isDirected = isDirected;
    graphLinkedList = new LinkedList[size + 1];
    for (int i = 0; i < graphLinkedList.length; i++) {
      graphLinkedList[i] = new LinkedList<Edge>();
    }
  }

  @Override
  public void addEdge(int from, int to) {
    addEdge(from, to, 1);
  }

  public void addEdge(int from, int to, int cost) {
    Edge newEdge = new Edge(to, cost);
    graphLinkedList[from].add(newEdge);
    if (!isDirected) {
      Edge reverseEdge = new Edge(from, cost);
      graphLinkedList[to].add(reverseEdge);
    }
  }

  @Override
  public void removeEdge(int from, int to) {
    for (int i = 0; i < graphLinkedList[from].size(); i++) {
      Edge delEdge = graphLinkedList[from].get(i);
      if (delEdge.to == to) {
        graphLinkedList[from].remove(i);
        break;
      }
    }
    if (!isDirected) {
      for (int i = 0; i < graphLinkedList[to].size(); i++) {
        Edge delEdge = graphLinkedList[to].get(i);
        if (delEdge.to == from) {
          graphLinkedList[to].remove(i);
          break;
        }
      }
    }
  }

  public void removeVertex(int v) {
    for (int i = 0; i < graphLinkedList.length; i++) {
      removeEdge(i, v);
    }

    for (int i = 0; i < graphLinkedList.length; i++) {
      removeEdge(v, i);
    }
  }

  @Override
  public Iterator<Edge> adjacents(int v) {
    LinkedList<Edge> adjacents = graphLinkedList[v];
    Iterator<Edge> it = adjacents.iterator();
    return it;
  }

  @Override
  public boolean hasEdge(int from, int to) {
    LinkedList<Edge> adjacents = graphLinkedList[from];
    Iterator<Edge> it = adjacents.iterator();
    while (it.hasNext()) {
      Edge edge = it.next();
      if (edge.to == to) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void Dijkstra(int start, int end) {
    boolean[] visited = initVisited();
    int[] costs = initCosts();
    int[] previous = initPrevious();
    PriorityQueueDijkstra queue = new PriorityQueueDijkstra(
      (2 * graphLinkedList.length) + 1
    );

    queue.enqueue(start, 0);
    costs[start] = 0;

    while (!queue.isEmpty()) {
      int v = queue.dequeue();
      visited[v] = true;
      Iterator<Edge> iter = adjacents(v);
      while (iter.hasNext()) {
        Edge w = iter.next();
        int newCost = costs[v] + w.cost;
        if (!visited[w.to] && costs[w.to] > newCost) {
          costs[w.to] = newCost;
          previous[w.to] = v;
          queue.enqueue(w.to, newCost);
        }
      }
    }

    int pos = previous[end];
    int cont = 0;
    while (pos != -1) {
      pos = previous[pos];
      cont++;
    }
    int path[] = new int[cont + 1];
    path[cont] = end;
    path[0] = start;
    for (int i = cont - 1; i > 0; i--) {
      path[i] = previous[path[i + 1]];
    }
    String ret = "[";
    for (int i = 0; i < path.length; i++) {
      if (i == path.length - 1) {
        ret += path[i] + "]";
      } else if (i == 0) {
        ret += path[0] + ", ";
      } else {
        ret += path[i] + ", ";
      }
    }
    System.out.println(costs[end]);
    System.out.print(ret);
  }

  private boolean[] initVisited() {
    boolean[] visited = new boolean[graphLinkedList.length];
    for (int i = 0; i < visited.length; i++) {
      visited[i] = false;
    }
    return visited;
  }

  private int[] initCosts() {
    int[] costs = new int[graphLinkedList.length];
    for (int i = 0; i < costs.length; i++) {
      costs[i] = Integer.MAX_VALUE;
    }
    return costs;
  }

  private int[] initPrevious() {
    int[] previous = new int[graphLinkedList.length];
    for (int i = 0; i < previous.length; i++) {
      previous[i] = -1;
    }
    return previous;
  }

  @Override
  public void TopologicalOrder() {
    int[] indegree = initIndegree();
    PriorityQueueTopoOrder queue = new PriorityQueueTopoOrder(
      graphLinkedList.length
    );
    int vertex = 0;
    for (int v = 1; v < graphLinkedList.length; v++) {
      if (indegree[v] == 0) {
        queue.enqueue(v, 0);
      }
    }

    while (!queue.isEmpty()) {
      int level = queue.getLevel() + 1;
      vertex = queue.dequeue();
      System.out.println(vertex);
      Iterator<Edge> iter = adjacents(vertex);
      while (iter.hasNext()) {
        Edge w = iter.next();
        if (indegree[w.to] - 1 == 0) {
          queue.enqueue(w.to, level);
        } else {
          indegree[w.to]--;
        }
      }
    }
  }

  private int[] initIndegree() {
    int[] indegree = new int[graphLinkedList.length + 1];
    for (int i = 1; i < graphLinkedList.length; i++) {
      Iterator<Edge> iter = adjacents(i);
      while (iter.hasNext()) {
        Edge w = iter.next();
        indegree[w.to]++;
      }
    }
    return indegree;
  }
}
