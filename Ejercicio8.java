import java.util.Scanner;
import tads.PriorityQueue.MinHeap;

public class Ejercicio8 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int flights = sc.nextInt();
    MinHeap arrivals = new MinHeap(flights);
    MinHeap departures = new MinHeap(flights);
    for (int i = 0; i < flights; i++) {
      int arrival = sc.nextInt();
      int departure = sc.nextInt();
      arrivals.enqueue(arrival);
      departures.enqueue(departure);
    }

    System.out.println(minPlatforms(arrivals, departures));

    sc.close();
  }

  private static int minPlatforms(MinHeap arrivals, MinHeap departures) {
    int platforms = 0;
    int min = 0;

    int arrival = arrivals.top();
    int departure = departures.top();
    while (!arrivals.isEmpty() && !departures.isEmpty()) {
      if (arrival < departure) {
        platforms++;
        arrival = arrivals.dequeue();
      } else {
        platforms--;
        departure = departures.dequeue();
      }

      if (platforms > min) {
        min = platforms;
      }
    }
    return min;
  }
}
