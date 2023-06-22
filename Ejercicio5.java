import java.util.Scanner;
import tads.graph.AdjacencyListGraph;

public class Ejercicio5 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int vertices = sc.nextInt();
    int edges = sc.nextInt();
    AdjacencyListGraph graph = new AdjacencyListGraph(vertices, true);
    for (int i = 0; i < edges; i++) {
      int from = sc.nextInt();
      int to = sc.nextInt();
      graph.addEdge(from, to);
    }

    graph.TopologicalOrder();

    sc.close();
  }
}
