import java.util.Scanner;

public class Ejercicio9 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int upperBound = sc.nextInt();
    int testCases = sc.nextInt();
    long[] combinatory = new long[testCases];
    for (int i = 0; i < testCases; i++) {
      int ni = sc.nextInt();
      int ki = sc.nextInt();
      combinatory[i] = combinatoryResult(ni, ki);
    }

    for (int i = 0; i < combinatory.length; i++) {
      System.out.println(combinatory[i]);
    }

    sc.close();
  }

  public static long combinatoryResult(int n, int k) {
    long[] comb = new long[k + 1];

    comb[0] = 1;

    for (int i = 1; i <= n; i++) {
      for (int j = min(i, k); j > 0; j--) {
        comb[j] = comb[j] + comb[j - 1];
      }
    }

    return comb[k];
  }

  public static int min(int a, int b) {
    return (a < b) ? a : b;
  }
}
