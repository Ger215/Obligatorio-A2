package tads.hash;

public class CloseHasherMap<K, V> implements Map<K, V> {

  class CloseHashMapIterator<K, V> {

    private int pos;
    private Pair<K, V> p;
  }

  /**Pair{K, V} [] arr */
  private Object[] arr;
  private int elements;
  Hasher hasher;

  public CloseHasherMap(int expectedElements, Hasher h) {
    this.arr = new Object[(2 * expectedElements) - 1];
    this.elements = 0;
    this.hasher = h;
  }

  @Override
  public void insert(K key, V value) {
    int colision = 0;
    int pos = abs(this.hasher.hash(key, arr.length - 1));
    if (pos > arr.length) {
      throw new RuntimeException("Posicion no valida");
    }
    Pair<K, V> p = new Pair(key, value);
    while (arr[pos] != null) {
      colision++;
      pos += (37 * colision) ^ 2;
      pos = pos % arr.length;
    }
    arr[pos] = p;
    elements++;
  }

  @Override
  public Pair<K, V> getPair(K key) {
    int collision = 0;
    boolean finish = false;
    Pair<K, V> aux = null;
    int pos = abs(this.hasher.hash(key, arr.length));
    while (!finish) {
      aux = (Pair<K, V>) arr[pos];
      if (arr[pos] != null && aux.key.equals(key)) {
        finish = true;
      }
      collision++;
      pos += (37 * collision) ^ 2;
      pos = pos % arr.length;
    }
    return aux;
  }

  private int abs(int d) {
    if (d < 0) {
      d = d * (-1);
    }
    return d;
  }
}
