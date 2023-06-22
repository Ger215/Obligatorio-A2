import java.util.Scanner;
import tads.PriorityQueue.MaxHeap;

public class Ejercicio3 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int planeReservations = sc.nextInt();
    MaxHeap<String, Double> maxHeap = new MaxHeap<>(planeReservations);

    while (sc.hasNextLine()) {
      sc.nextLine();
      while (sc.hasNextLine()) {
        int peopleReservations = Integer.parseInt(sc.nextLine());
        double sum = 0;
        int cont = 0;
        String ret = "";
        while (cont < peopleReservations) {
          String PaT = sc.nextLine();
          String[] PaTS = PaT.split(" ");
          ret += PaTS[0] + " ";
          sum += Integer.parseInt(PaTS[1]);
          cont++;
          if (cont == peopleReservations) {
            maxHeap.enqueue(ret, sum / peopleReservations);
          }
        }
      }
    }
    maxHeap.getMaxPriority();

    sc.close();
  }
}
