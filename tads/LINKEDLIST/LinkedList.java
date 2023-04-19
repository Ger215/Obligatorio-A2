package tads.LINKEDLIST;

import java.util.Iterator;

public class LinkedList<T> implements ListADT<T> {

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

  private Node<T> head;
  private Node<T> last;
  private int length;

  LinkedList() {
    head = null;
    last = null;
    length = 0;
  }

  @Override
  public T get(int pos) {
    if (pos < 0 || pos >= this.length) {
      throw new RuntimeException("pos is not within the valid range");
    }
    Node<T> n = head;
    for (int i = 0; i < pos; i++) {
      n = n.next;
    }
    return n.data;
  }

  @Override
  public void pushFront(T o) {
    if (this.head == null) {
      this.head = new Node<T>(o);
      this.last = this.head;
    } else {
      this.head = new Node<T>(o, this.head);
    }
    this.length++;
  }

  @Override
  public void pushBack(T o) {
    if (this.head == null) {
      this.head = new Node<T>(o);
      this.last = this.head;
    } else {
      this.last.next = new Node<T>(o);
      this.last = this.last.next;
    }
    this.length++;
  }

  @Override
  public void pushAt(T o, int pos) {
    if (pos < 0 || pos > this.length) {
      throw new RuntimeException("pos is not within the valid range");
    }
    if (pos == 0) {
      this.pushFront(o);
    } else if (pos == this.length) {
      this.pushBack(o);
    } else {
      Node<T> n = this.head;
      for (int i = 0; i < pos - 1; i++) {
        n = n.next;
      }
      n.next = new Node<T>(o, n.next);
      this.length++;
    }
  }

  @Override
  public void deleteAt(int pos) {
    if (pos < 0 || pos >= this.length) {
      throw new RuntimeException("pos is not within the valid range");
    }
    if (pos == 0) {
      this.head = this.head.next;
    } else {
      Node<T> n = this.head;
      for (int i = 0; i < pos - 1; i++) {
        n = n.next;
      }
      n.next = n.next.next;
    }
    this.length--;
  }

  @Override
  public void delete(T o) {
    Node<T> n = this.head;
    if (n.data.equals(o)) {
      this.head = this.head.next;
      this.length--;
    } else {
      while (n.next != null) {
        if (n.next.data.equals(o)) {
          n.next = n.next.next;
          this.length--;
        }
        n = n.next;
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return this.length == 0;
  }

  @Override
  public boolean contains(T o) {
    Node<T> n = this.head;
    while (n != null) {
      if (n.data.equals(o)) {
        return true;
      }
      n = n.next;
    }
    return false;
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
