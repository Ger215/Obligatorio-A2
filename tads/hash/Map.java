package tads.hash;

public interface Map<K, V> {
  void insert(K key, V value);

  Pair<K, V> getPair(K key);
}
