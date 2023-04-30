package tads.bst;

public class Node<T> {

  public T data;
  public int ocurrencies;
  public Node<T> left = null;
  public Node<T> right = null;
  public int height = 1;

  Node(T data, int ocurrencies) {
    this.data = data;
    this.ocurrencies = ocurrencies;
  }
}
