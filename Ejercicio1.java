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
    CloseHasherMap CloseTable = new CloseHasherMap<String, Integer>(
      students,
      hash
    );
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

        String name = data[0];
        boolean finish = false;
        for (int i = 0; i < students && !finish; i++) {
          if (studNames[i] == null) {
            studNames[i] = name;
            finish = true;
          }
        }

        CloseTable.insert(data[0], prom);
      }
    }

    for (int i = 0; i < studNames.length; i++) {
      Pair<String, Integer> p = CloseTable.getPair(studNames[i]);
      System.out.println(p.key + " " + p.value);
    }

    sc.close();
  }
}
