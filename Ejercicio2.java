import java.util.Scanner;
import tads.bst.AVL;
import tads.bst.Node;

public class Ejercicio2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int wordCount = sc.nextInt();
    AVL avl = new AVL<>();
    while (sc.hasNextLine()) {
      sc.nextLine();
      while (sc.hasNextLine()) {
        String word = sc.nextLine();
        avl.insert(word);
      }
    }
    Node<String> aux = avl.getNode();
    System.out.println(aux.data + " " + aux.ocurrencies);
  }
}
