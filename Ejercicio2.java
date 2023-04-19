import java.util.Scanner;
import tads.bst.AVL;

public class Ejercicio2 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int nroDePalabras = sc.nextInt();
    AVL avl = new AVL<>();
    while (sc.hasNextLine()) {
      sc.nextLine();
      while (sc.hasNextLine()) {
        String Palabra = sc.nextLine();
        avl.insert(Palabra);
      }
    }
    /*System.out.println(avl.getWord() + " " + avl.getOcurrencies());*/
  }
}
