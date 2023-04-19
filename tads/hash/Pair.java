package tads.hash;

public class Pair<K, V> {

  public K key;
  public V value;

  Pair() {}

  Pair(K key) {
    this.key = key;
  }

  Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    Pair<K, V> other = (Pair<K, V>) (o);
    return this.key.equals(other.key);
  }
}
