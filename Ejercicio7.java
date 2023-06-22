import java.util.Scanner;

public class Ejercicio7 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int files = sc.nextInt();
    int maxSize = sc.nextInt();
    int maxLines = sc.nextInt();
    int[] sizes = new int[files + 1];
    int[] linesCode = new int[files + 1];
    int[] scores = new int[files + 1];
    for (int i = 0; i < files; i++) {
      int fileSize = sc.nextInt();
      int fileLines = sc.nextInt();
      int score = sc.nextInt();
      sizes[i + 1] = fileSize;
      linesCode[i + 1] = fileLines;
      scores[i + 1] = score;
    }

    int pointsObtained = maximizeScore(
      files,
      maxSize,
      maxLines,
      sizes,
      linesCode,
      scores
    );
    System.out.println(pointsObtained);

    sc.close();
  }

  public static int maximizeScore(
    int files,
    int maxSize,
    int maxLines,
    int[] sizes,
    int[] linesCode,
    int[] scores
  ) {
    int[][] mat = new int[maxSize + 1][maxLines + 1];

    for (int i = 1; i <= files; i++) {
      for (int j = maxSize; j >= sizes[i]; j--) {
        for (int k = maxLines; k >= linesCode[i]; k--) {
          mat[j][k] =
            max(mat[j][k], mat[j - sizes[i]][k - linesCode[i]] + scores[i]);
        }
      }
    }

    return mat[maxSize][maxLines];
  }

  public static int max(int a, int b) {
    return (a > b) ? a : b;
  }
}
