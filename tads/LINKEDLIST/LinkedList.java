package tads.linkedlist;

import java.util.Iterator;
import tads.graph.AdjacencyListGraph.Edge;

public class LinkedList<T> implements ListADT<T> {

  class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> curr;

    public LinkedListIterator(Node<T> head) {
      this.curr = head;
    }

    @Override
    public boolean hasNext() {
      return this.curr != null;
    }

    @Override
    public T next() {
      T currData = curr.data;
      curr = curr.next;
      return currData;
    }
  }

  class Node<T> {

    T data;
    Node<T> next;

    Node(T data) {
      this.data = data;
    }

    Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  private Node<T> head;
  private int length;

  public LinkedList() {
    head = null;
    length = 0;
  }

  @Override
  public void add(T newEdge) {
    Node<T> newNode = new Node<>(newEdge);

    if (this.head == null) {
      this.head = newNode;
    } else {
      newNode.next = this.head;
      this.head = newNode;
    }
    this.length++;
  }

  public void remove(int pos) {
    if (pos == 0) {
      this.head = this.head.next;
    } else {
      Node<T> del = this.head;
      for (int i = 0; i < pos - 1; i++) {
        del = del.next;
      }
      del.next = del.next.next;
    }
    this.length--;
  }

  public Edge get(int pos) {
    Node<T> ret = null;
    if (pos == 0) {
      ret = this.head;
    } else {
      Node<T> curr = this.head;
      for (int i = 0; i < pos; i++) {
        curr = curr.next;
      }
      ret = curr;
    }
    return (Edge) ret.data;
  }

  @Override
  public boolean isEmpty() {
    return this.length == 0;
  }

  @Override
  public int size() {
    return this.length;
  }

  @Override
  public Iterator<T> iterator() {
    return new LinkedListIterator<T>(this.head);
  }
}
