import java.util.*;

public class Ejercicio10 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = sc.nextInt();
    int[][] mat = new int[n][p];
    boolean[][] visited = new boolean[n][p];
    int bedX = 0;
    int bedY = 0;
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      String[] data = line.split(" ");
      for (int j = 0; j < data.length; j++) {
        if (data[j].equals("P")) {
          mat[i][j] = 0;
          visited[i][j] = true;
        } else {
          mat[i][j] = 1;
          visited[i][j] = false;
          if (data[j].equals("B")) {
            bedX = i;
            bedY = j;
          }
        }
      }
    }

    int testCases = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < testCases; i++) {
      String line = sc.nextLine();
      String[] data = line.split(" ");
      int originX = Integer.parseInt(data[0]);
      int originY = Integer.parseInt(data[1]);
      int endX = Integer.parseInt(data[2]);
      int endY = Integer.parseInt(data[3]);
      if (
        findOptimalPath(
          originY - 1,
          originX - 1,
          bedX,
          bedY,
          endY - 1,
          endX - 1,
          mat,
          visited
        ) !=
        0
      ) {
        System.out.println(
          findOptimalPath(
            originY - 1,
            originX - 1,
            bedX,
            bedY,
            endY - 1,
            endX - 1,
            mat,
            visited
          ) +
          1
        );
      } else {
        System.out.println(
          findOptimalPath(
            originY - 1,
            originX - 1,
            bedX,
            bedY,
            endY - 1,
            endX - 1,
            mat,
            visited
          )
        );
      }
    }
    sc.close();
  }

  static int findOptimalPath(
    int originX,
    int originY,
    int bedX,
    int bedY,
    int endX,
    int endY,
    int[][] mat,
    boolean[][] visitedPath
  ) {
    int n = mat.length;
    int p = mat[0].length;

    int[] possibleMovesX = { -1, 0, 1, 0 };
    int[] possibleMovesY = { 0, 1, 0, -1 };

    boolean[][] aux = new boolean[n][p];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < p; j++) {
        aux[i][j] = visitedPath[i][j];
      }
    }

    aux[originX][originY] = true;

    int[][] distance = new int[n][p];
    distance[originX][originY] = 0;

    int x = originX;
    int y = originY;
    int originalX = originX;
    int originalY = originY;

    int previousX = originX;
    int previousY = originY;
    int cont = 0;
    int[] done = new int[8 + 1];

    boolean finished = false;
    int distanceToEnd = Integer.MAX_VALUE;
    while (finished != true) {
      if (reachedEnd(x, y, bedX, bedY) == true) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < p; j++) {
            aux[i][j] = visitedPath[i][j];
          }
        }
        if (distance[bedX][bedY] < distanceToEnd) {
          distanceToEnd = distance[bedX][bedY];
        }

        for (int k = 1; k < done.length; k += 2) {
          aux[done[k]][done[k + 1]] = true;
        }

        originX = originalX;
        originY = originalY;

        boolean flag = false;
        for (int i = 0; i < 4 && flag != true; i++) {
          int moveX = originalX + possibleMovesX[i];
          int moveY = originalY + possibleMovesY[i];

          if (
            isValid(moveX, moveY, n, p) &&
            notWall(mat, moveX, moveY) &&
            wasVisited(aux, moveX, moveY)
          ) {
            finished = false;
            flag = true;
            cont = 0;
            x = originalX;
            y = originalY;
            for (int k = 0; k < distance.length; k++) {
              for (int r = 0; r < distance[0].length; r++) {
                distance[k][r] = 0;
              }
            }
          } else {
            finished = true;
          }
        }
      }

      int minDistance = Integer.MAX_VALUE;
      int actualX = x;
      int actualY = y;
      int moveDone = Integer.MIN_VALUE;
      boolean hasMoved = false;

      for (int i = 0; i < 4; i++) {
        int movedX = x + possibleMovesX[i];
        int movedY = y + possibleMovesY[i];

        if (
          isValid(movedX, movedY, n, p) &&
          notWall(mat, movedX, movedY) &&
          wasVisited(aux, movedX, movedY)
        ) {
          if (distance[movedX][movedY] < minDistance) {
            minDistance = distance[movedX][movedY];
            actualX = movedX;
            actualY = movedY;
            moveDone = i;
            hasMoved = true;
            if (x == originX && y == originY) {
              previousX = actualX;
              previousY = actualY;
            }
            if (cont == 0) {
              int stop = 0;
              for (int k = 1; k < done.length && stop != 2; k += 2) {
                if (done[k] == 0) {
                  done[k] = actualX;
                  done[k + 1] = actualY;
                  stop = 2;
                }
              }
              cont++;
            }
          }
        }
      }

      if (hasMoved) {
        x = actualX;
        y = actualY;
        aux[x][y] = true;
        distance[x][y] =
          distance[x - possibleMovesX[moveDone]][y - possibleMovesY[moveDone]] +
          1;
      } else {
        x = originX;
        y = originY;
        originX = previousX;
        originY = previousY;
        for (int k = 1; k < done.length; k += 2) {
          aux[done[k]][done[k + 1]] = false;
        }
      }
    }

    boolean[][] aux2 = new boolean[n][p];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < p; j++) {
        aux2[i][j] = visitedPath[i][j];
      }
    }

    aux2[bedX][bedY] = true;

    x = bedX;
    y = bedY;
    int anteriorX2 = bedX;
    int anteriorY2 = bedY;

    distance[bedX][bedY] = distanceToEnd;

    int BedeliaX = bedX;
    int BedeliaY = bedY;
    int distanceToFinish = Integer.MAX_VALUE;
    int cont2 = 0;
    int[] doneAux = new int[8 + 1];
    int[][] distanceAux = new int[n][p];

    finished = false;
    while (finished != true) {
      if (reachedDestination(x, y, endX, endY) == true) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < p; j++) {
            aux2[i][j] = visitedPath[i][j];
          }
        }

        if (distanceAux[endX][endY] < distanceToFinish) {
          distanceToFinish = distanceAux[endX][endY];
        }

        for (int k = 1; k < done.length; k += 2) {
          aux2[doneAux[k]][doneAux[k + 1]] = true;
        }

        aux2[BedeliaX][BedeliaY] = true;
        bedX = BedeliaX;
        bedY = BedeliaY;

        boolean stopped = false;
        for (int i = 0; i < 4 && stopped != true; i++) {
          int MovidoX = BedeliaX + possibleMovesX[i];
          int MovidoY = BedeliaY + possibleMovesY[i];

          if (
            isValid(MovidoX, MovidoY, n, p) &&
            notWall(mat, MovidoX, MovidoY) &&
            wasVisited(aux2, MovidoX, MovidoY)
          ) {
            finished = false;
            stopped = true;
            cont2 = 0;
            x = BedeliaX;
            y = BedeliaY;
            for (int k = 0; k < distanceAux.length; k++) {
              for (int r = 0; r < distanceAux[0].length; r++) {
                distanceAux[k][r] = 0;
              }
            }
          } else {
            finished = true;
          }
        }
      }

      int minDistancia = Integer.MAX_VALUE;
      int actualX = x;
      int actualY = y;
      int MovimientoHecho = Integer.MIN_VALUE;
      boolean hechoMovimiento = false;

      for (int i = 0; i < 4; i++) {
        int MovidoX = x + possibleMovesX[i];
        int MovidoY = y + possibleMovesY[i];

        if (
          isValid(MovidoX, MovidoY, n, p) &&
          notWall(mat, MovidoX, MovidoY) &&
          wasVisited(aux2, MovidoX, MovidoY)
        ) {
          if (distanceAux[MovidoX][MovidoY] < minDistancia) {
            minDistancia = distanceAux[MovidoX][MovidoY];
            actualX = MovidoX;
            actualY = MovidoY;
            MovimientoHecho = i;
            hechoMovimiento = true;
            if (x == bedX && y == bedY) {
              anteriorX2 = actualX;
              anteriorY2 = actualY;
            }
            if (cont2 == 0) {
              int listo = 0;
              for (int k = 1; k < done.length && listo != 2; k += 2) {
                if (doneAux[k] == 0) {
                  doneAux[k] = actualX;
                  doneAux[k + 1] = actualY;
                  listo = 2;
                }
              }
              cont2++;
            }
          }
        }
      }

      if (isFull(aux2) && distanceToFinish == Integer.MAX_VALUE) {
        return 0;
      }

      if (hechoMovimiento) {
        x = actualX;
        y = actualY;
        aux2[x][y] = true;
        distanceAux[x][y] =
          distanceAux[x - possibleMovesX[MovimientoHecho]][y -
            possibleMovesY[MovimientoHecho]] +
          1;
      } else {
        if (x == bedX && y == bedY) {
          finished = true;
        }
        x = anteriorX2;
        y = anteriorY2;
        bedX = anteriorX2;
        bedY = anteriorY2;
      }
    }

    return distanceToEnd + distanceToFinish;
  }

  static boolean isValid(int x, int y, int m, int n) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }

  static boolean notWall(int[][] mat, int i, int j) {
    return mat[i][j] != 0;
  }

  static boolean wasVisited(boolean[][] visitado, int i, int j) {
    return visitado[i][j] != true;
  }

  static boolean reachedDestination(int x, int y, int endX, int endY) {
    return x == endX && y == endY;
  }

  static boolean reachedEnd(int x, int y, int bedX, int bedY) {
    return x == bedX && y == bedY;
  }

  static boolean isFull(boolean[][] visitedPath) {
    int cont = 0;

    for (int i = 0; i < visitedPath.length; i++) {
      for (int j = 0; j < visitedPath[0].length; j++) {
        if (visitedPath[i][j]) {
          cont++;
        } else {
          if (invalidPath(visitedPath, i, j)) {
            cont++;
          }
        }
      }
    }

    return cont == (visitedPath.length * visitedPath[0].length);
  }

  static boolean invalidPath(boolean[][] mat, int i, int j) {
    int n = mat.length;
    int p = mat[0].length;

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    for (int k = 0; k < 4; k++) {
      int moveX = i + dx[k];
      int moveY = j + dy[k];

      if (
        moveX >= 0 &&
        moveX < n &&
        moveY >= 0 &&
        moveY < p &&
        mat[moveX][moveY] != true
      ) {
        return false;
      }
    }

    return true;
  }
}
