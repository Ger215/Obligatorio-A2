package tads.bst;

public interface BST<T extends Comparable<T>> {
  public void insert(T data);

  public Node<T> getNode();
}
