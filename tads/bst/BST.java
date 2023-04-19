package tads.bst;

public interface BST<T extends Comparable<T>> {
  public void insert(T data);

  public T getDataT();

  public int getOcurrencies();
}
