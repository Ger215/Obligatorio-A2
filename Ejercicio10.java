import java.util.*;

public class Ejercicio10 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int p = sc.nextInt();
    int[][] mat = new int[n][p];
    boolean[][] visitadosBedelia = new boolean[n][p];
    int bedX = 0;
    int bedY = 0;
    sc.nextLine();
    for (int i = 0; i < n; i++) {
      String dato = sc.nextLine();
      String[] datos = dato.split(" ");
      for (int j = 0; j < datos.length; j++) {
        if (datos[j].equals("P")) {
          mat[i][j] = 0;
          visitadosBedelia[i][j] = true;
        } else {
          mat[i][j] = 1;
          visitadosBedelia[i][j] = false;
          if (datos[j].equals("B")) {
            bedX = i;
            bedY = j;
          }
        }
      }
    }

    int CuantosAProbar = sc.nextInt();
    sc.nextLine();
    for (int i = 0; i < CuantosAProbar; i++) {
      String dato = sc.nextLine();
      String[] datos = dato.split(" ");
      int OrigenX = Integer.parseInt(datos[0]);
      int OrigenY = Integer.parseInt(datos[1]);
      int DestinoX = Integer.parseInt(datos[2]);
      int DestinoY = Integer.parseInt(datos[3]);
      if (
        EncontrarCaminoOptimo(
          OrigenY - 1,
          OrigenX - 1,
          bedX,
          bedY,
          DestinoY - 1,
          DestinoX - 1,
          mat,
          visitadosBedelia
        ) !=
        0
      ) {
        System.out.println(
          EncontrarCaminoOptimo(
            OrigenY - 1,
            OrigenX - 1,
            bedX,
            bedY,
            DestinoY - 1,
            DestinoX - 1,
            mat,
            visitadosBedelia
          ) +
          1
        );
      } else {
        System.out.println(
          EncontrarCaminoOptimo(
            OrigenY - 1,
            OrigenX - 1,
            bedX,
            bedY,
            DestinoY - 1,
            DestinoX - 1,
            mat,
            visitadosBedelia
          )
        );
      }
    }
    sc.close();
  }

  static int EncontrarCaminoOptimo(
    int OrigenX,
    int OrigenY,
    int bedX,
    int bedY,
    int DestinoX,
    int DestinoY,
    int[][] mat,
    boolean[][] visitados
  ) {
    int n = mat.length;
    int p = mat[0].length;

    int[] movimientoX = { -1, 0, 1, 0 };
    int[] movimientosY = { 0, 1, 0, -1 };

    boolean[][] aux = new boolean[n][p];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < p; j++) {
        aux[i][j] = visitados[i][j];
      }
    }

    aux[OrigenX][OrigenY] = true;

    int[][] distancia = new int[n][p];
    distancia[OrigenX][OrigenY] = 0;

    int x = OrigenX;
    int y = OrigenY;
    int OriginalX = OrigenX;
    int OriginalY = OrigenY;

    int anteriorX = OrigenX;
    int anteriorY = OrigenY;
    int cont = 0;
    int[] hecho = new int[8 + 1];

    boolean termino = false;
    int DistanciaABed = Integer.MAX_VALUE;
    while (termino != true) {
      if (LLegoABedelia(x, y, bedX, bedY) == true) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < p; j++) {
            aux[i][j] = visitados[i][j];
          }
        }
        if (distancia[bedX][bedY] < DistanciaABed) {
          DistanciaABed = distancia[bedX][bedY];
        }

        for (int k = 1; k < hecho.length; k += 2) {
          aux[hecho[k]][hecho[k + 1]] = true;
        }

        OrigenX = OriginalX;
        OrigenY = OriginalY;

        boolean Hecho = false;
        for (int i = 0; i < 4 && Hecho != true; i++) {
          int MovidoX = OriginalX + movimientoX[i];
          int MovidoY = OriginalY + movimientosY[i];

          if (
            esValido(MovidoX, MovidoY, n, p) &&
            NoEsPared(mat, MovidoX, MovidoY) &&
            FueVisitado(aux, MovidoX, MovidoY)
          ) {
            termino = false;
            Hecho = true;
            cont = 0;
            x = OriginalX;
            y = OriginalY;
            for (int k = 0; k < distancia.length; k++) {
              for (int r = 0; r < distancia[0].length; r++) {
                distancia[k][r] = 0;
              }
            }
          } else {
            termino = true;
          }
        }
      }

      int minDistancia = Integer.MAX_VALUE;
      int actualX = x;
      int actualY = y;
      int MovimientoHecho = Integer.MIN_VALUE;
      boolean hechoMovimiento = false;

      for (int i = 0; i < 4; i++) {
        int MovidoX = x + movimientoX[i];
        int MovidoY = y + movimientosY[i];

        if (
          esValido(MovidoX, MovidoY, n, p) &&
          NoEsPared(mat, MovidoX, MovidoY) &&
          FueVisitado(aux, MovidoX, MovidoY)
        ) {
          if (distancia[MovidoX][MovidoY] < minDistancia) {
            minDistancia = distancia[MovidoX][MovidoY];
            actualX = MovidoX;
            actualY = MovidoY;
            MovimientoHecho = i;
            hechoMovimiento = true;
            if (x == OrigenX && y == OrigenY) {
              anteriorX = actualX;
              anteriorY = actualY;
            }
            if (cont == 0) {
              int listo = 0;
              for (int k = 1; k < hecho.length && listo != 2; k += 2) {
                if (hecho[k] == 0) {
                  hecho[k] = actualX;
                  hecho[k + 1] = actualY;
                  listo = 2;
                }
              }
              cont++;
            }
          }
        }
      }

      if (hechoMovimiento) {
        x = actualX;
        y = actualY;
        aux[x][y] = true;
        distancia[x][y] =
          distancia[x - movimientoX[MovimientoHecho]][y -
            movimientosY[MovimientoHecho]] +
          1;
      } else {
        x = OrigenX;
        y = OrigenY;
        OrigenX = anteriorX;
        OrigenY = anteriorY;
        for (int k = 1; k < hecho.length; k += 2) {
          aux[hecho[k]][hecho[k + 1]] = false;
        }
      }
    }

    boolean[][] aux2 = new boolean[n][p];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < p; j++) {
        aux2[i][j] = visitados[i][j];
      }
    }

    aux2[bedX][bedY] = true;

    x = bedX;
    y = bedY;
    int anteriorX2 = bedX;
    int anteriorY2 = bedY;

    distancia[bedX][bedY] = DistanciaABed;

    int BedeliaX = bedX;
    int BedeliaY = bedY;
    int DistanciaADestino = Integer.MAX_VALUE;
    int cont2 = 0;
    int[] hecho2 = new int[8 + 1];
    int[][] distancia2 = new int[n][p];

    termino = false;
    while (termino != true) {
      if (LLegoADestino(x, y, DestinoX, DestinoY) == true) {
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < p; j++) {
            aux2[i][j] = visitados[i][j];
          }
        }

        if (distancia2[DestinoX][DestinoY] < DistanciaADestino) {
          DistanciaADestino = distancia2[DestinoX][DestinoY];
        }

        for (int k = 1; k < hecho.length; k += 2) {
          aux2[hecho2[k]][hecho2[k + 1]] = true;
        }

        aux2[BedeliaX][BedeliaY] = true;
        bedX = BedeliaX;
        bedY = BedeliaY;

        boolean Hecho = false;
        for (int i = 0; i < 4 && Hecho != true; i++) {
          int MovidoX = BedeliaX + movimientoX[i];
          int MovidoY = BedeliaY + movimientosY[i];

          if (
            esValido(MovidoX, MovidoY, n, p) &&
            NoEsPared(mat, MovidoX, MovidoY) &&
            FueVisitado(aux2, MovidoX, MovidoY)
          ) {
            termino = false;
            Hecho = true;
            cont2 = 0;
            x = BedeliaX;
            y = BedeliaY;
            for (int k = 0; k < distancia2.length; k++) {
              for (int r = 0; r < distancia2[0].length; r++) {
                distancia2[k][r] = 0;
              }
            }
          } else {
            termino = true;
          }
        }
      }

      int minDistancia = Integer.MAX_VALUE;
      int actualX = x;
      int actualY = y;
      int MovimientoHecho = Integer.MIN_VALUE;
      boolean hechoMovimiento = false;

      for (int i = 0; i < 4; i++) {
        int MovidoX = x + movimientoX[i];
        int MovidoY = y + movimientosY[i];

        if (
          esValido(MovidoX, MovidoY, n, p) &&
          NoEsPared(mat, MovidoX, MovidoY) &&
          FueVisitado(aux2, MovidoX, MovidoY)
        ) {
          if (distancia2[MovidoX][MovidoY] < minDistancia) {
            minDistancia = distancia2[MovidoX][MovidoY];
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
              for (int k = 1; k < hecho.length && listo != 2; k += 2) {
                if (hecho2[k] == 0) {
                  hecho2[k] = actualX;
                  hecho2[k + 1] = actualY;
                  listo = 2;
                }
              }
              cont2++;
            }
          }
        }
      }

      if (Full(aux2) && DistanciaADestino == Integer.MAX_VALUE) {
        return 0;
      }

      if (hechoMovimiento) {
        x = actualX;
        y = actualY;
        aux2[x][y] = true;
        distancia2[x][y] =
          distancia2[x - movimientoX[MovimientoHecho]][y -
            movimientosY[MovimientoHecho]] +
          1;
      } else {
        if (x == bedX && y == bedY) {
          termino = true;
        }
        x = anteriorX2;
        y = anteriorY2;
        bedX = anteriorX2;
        bedY = anteriorY2;
      }
    }

    return DistanciaABed + DistanciaADestino;
  }

  static boolean esValido(int x, int y, int m, int n) {
    return x >= 0 && x < m && y >= 0 && y < n;
  }

  static boolean NoEsPared(int[][] mat, int i, int j) {
    return mat[i][j] != 0;
  }

  static boolean FueVisitado(boolean[][] visitado, int i, int j) {
    return visitado[i][j] != true;
  }

  static boolean LLegoADestino(int x, int y, int DestinoX, int DestinoY) {
    return x == DestinoX && y == DestinoY;
  }

  static boolean LLegoABedelia(int x, int y, int bedX, int bedY) {
    return x == bedX && y == bedY;
  }

  static boolean Full(boolean[][] visitados) {
    int cont = 0;

    for (int i = 0; i < visitados.length; i++) {
      for (int j = 0; j < visitados[0].length; j++) {
        if (visitados[i][j]) {
          cont++;
        } else {
          if (estaEncerradaLaClase(visitados, i, j)) {
            cont++;
          }
        }
      }
    }

    return cont == (visitados.length * visitados[0].length);
  }

  static boolean estaEncerradaLaClase(boolean[][] mat, int i, int j) {
    int n = mat.length;
    int p = mat[0].length;

    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };

    for (int k = 0; k < 4; k++) {
      int MovidoX = i + dx[k];
      int MovidoY = j + dy[k];

      if (
        MovidoX >= 0 &&
        MovidoX < n &&
        MovidoY >= 0 &&
        MovidoY < p &&
        mat[MovidoX][MovidoY] != true
      ) {
        return false;
      }
    }

    return true;
  }
}
