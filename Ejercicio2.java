import java.util.Scanner;
import tads.bst.AVL;

public class Ejercicio2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    AVL avl = new AVL<>();
    while (sc.hasNextLine()) {
      sc.nextLine();
      while (sc.hasNextLine()) {
        String word = sc.nextLine();
        avl.insert(word);
      }
    }
    avl.getNode();

    sc.close();
  }
}
