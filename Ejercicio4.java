import java.util.Scanner;
import tads.graph.AdjacencyListGraph;

public class Ejercicio4 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int vertices = sc.nextInt();
    int edges = sc.nextInt();
    AdjacencyListGraph graph = new AdjacencyListGraph(vertices, true);
    for (int i = 0; i < edges; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      int cost = sc.nextInt();
      int direction = sc.nextInt();
      int avaliable = sc.nextInt();

      if (avaliable == 1) {
        if (direction == 1) {
          graph.addEdge(from, to, cost);
        } else {
          graph.addEdge(from, to, cost);
          graph.addEdge(to, from, cost);
        }
      }
    }
    int unavailableCities = sc.nextInt();
    for (int i = 0; i < unavailableCities; i++) {
      int delVertex = sc.nextInt();
      graph.removeVertex(delVertex);
    }
    int unavailableStreets = sc.nextInt();
    for (int i = 0; i < unavailableStreets; i++) {
      int delFrom = sc.nextInt();
      int delTo = sc.nextInt();
      graph.removeEdge(delFrom, delTo);
    }
    int start = sc.nextInt();
    int end = sc.nextInt();

    graph.Dijkstra(start, end);

    sc.close();
  }
}
