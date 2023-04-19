package tads.hash;

public interface Hasher<T> {
  int hash(T key, int size);
}
