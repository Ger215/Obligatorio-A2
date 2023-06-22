package tads.hash;

public class CloseHasherMap<K, V> implements Map<K, V> {

  /**Pair{K, V} [] arr */
  private Object[] arr;
  private int elements;
  Hasher hasher;

  public CloseHasherMap(int expectedElements, Hasher h) {
    this.arr = new Object[(nextPrime(2 * expectedElements)) - 1];
    this.elements = 0;
    this.hasher = h;
  }

  private boolean isPrime(int n) {
    if (n <= 1) return false;
    if (n <= 3) return true;

    if (n % 2 == 0 || n % 3 == 0) return false;

    for (int i = 5; i * i <= n; i = i + 6) {
      if (n % i == 0 || n % (i + 2) == 0) return false;
    }

    return true;
  }

  private int nextPrime(int N) {
    if (N <= 1) return 2;

    int prime = N;
    boolean ok = false;

    while (!ok) {
      prime++;

      if (isPrime(prime)) ok = true;
    }

    return prime;
  }

  private void resizeArray() {
    Object[] oldArr = arr;
    int newSize = nextPrime(2 * arr.length);
    arr = new Object[newSize];
    elements = 0;

    for (Object item : oldArr) {
      if (item != null) {
        Pair<K, V> pair = (Pair<K, V>) item;
        insert(pair.key, pair.value);
      }
    }
  }

  @Override
  public void insert(K key, V value) {
    int hash = abs(this.hasher.hash(key, arr.length));
    int pos = hash;
    Pair<K, V> pair = new Pair<>(key, value);

    int collision = 0;
    while (arr[pos] != null) {
      Pair<K, V> aux = (Pair<K, V>) arr[pos];
      if (aux.key.equals(key)) {
        aux.value = value;
        break;
      }

      collision++;
      pos = abs(hash + collision * collision) % arr.length;
    }

    arr[pos] = pair;
    elements++;

    if ((double) elements / arr.length >= 0.75) {
      resizeArray();
    }
  }

  @Override
  public Pair<K, V> getPair(K key) {
    int hash = abs(this.hasher.hash(key, arr.length));
    int pos = hash;

    int collision = 0;
    while (arr[pos] != null) {
      Pair<K, V> aux = (Pair<K, V>) arr[pos];
      if (aux.key.equals(key)) {
        return (Pair<K, V>) arr[pos];
      }

      collision++;
      pos = abs(hash + collision * collision) % arr.length;
    }

    return null;
  }

  private int abs(int d) {
    if (d < 0) {
      d = d * (-1);
    }
    return d;
  }
}
