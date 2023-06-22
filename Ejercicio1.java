import java.util.Scanner;
import tads.hash.CloseHasherMap;
import tads.hash.Pair;
import tads.hash.StringHasher;

public class Ejercicio1 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int students = sc.nextInt();
    String[] studNames = new String[students];
    StringHasher hash = new StringHasher();
    CloseHasherMap<String, Integer> CloseTable = new CloseHasherMap<String, Integer>(
      students,
      hash
    );
    int cont = 0;
    while (sc.hasNextLine()) {
      sc.nextLine();
      while (sc.hasNextLine()) {
        String fullStudent = sc.nextLine();
        String[] data = fullStudent.split(" ");
        int grades = Integer.parseInt(data[1]);

        int sum = 0;
        int pos = 2;
        for (int i = 0; i < grades; i++) {
          sum = sum + Integer.parseInt(data[pos]);
          pos++;
        }

        int prom = sum / grades;

        studNames[cont] = data[0];

        CloseTable.insert(data[0], prom);

        cont++;
      }
    }

    for (int i = 0; i < studNames.length; i++) {
      Pair<String, Integer> p = CloseTable.getPair(studNames[i]);
      System.out.println(p.key + " " + p.value);
    }

    sc.close();
  }
}
