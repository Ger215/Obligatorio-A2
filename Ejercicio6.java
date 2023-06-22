import java.util.Scanner;

public class Ejercicio6 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int lengthArr = sc.nextInt();
    int[] arr = new int[lengthArr];
    for (int i = 0; i < lengthArr; i++) {
      arr[i] = sc.nextInt();
    }

    int sinP = findSingleNumber(arr, 0, lengthArr - 1);
    System.out.println(sinP);

    sc.close();
  }

  static int findSingleNumber(int[] arr, int ini, int fin) {
    if (ini <= fin) {
      int mid = (ini + fin) / 2;

      if (arr[mid] != arr[mid + 1] && arr[mid] != arr[mid - 1]) {
        return arr[mid];
      } else if (mid % 2 == 0) {
        if (arr[mid] == arr[mid - 1]) {
          return findSingleNumber(arr, ini, mid - 1);
        } else if (arr[mid] == arr[mid + 1]) {
          return findSingleNumber(arr, mid + 1, fin);
        }
      } else {
        if (arr[mid] == arr[mid + 1]) {
          return findSingleNumber(arr, ini, mid - 1);
        } else if (arr[mid] == arr[mid - 1]) {
          return findSingleNumber(arr, mid + 1, fin);
        }
      }
    }

    return -1;
  }
}
