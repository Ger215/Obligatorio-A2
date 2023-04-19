package tads.PriorityQueue;

public class MinHeap<E, P extends Comparable<P>>
  implements PriorityQueue<E, P> {

  class Pair<E, P> {

    E element;
    P priority;

    Pair(E element, P priority) {
      this.element = element;
      this.priority = priority;
    }
  }

  /**
   * Pair{E, P}[]
   */
  private Object[] arr;
  private int elements;

  public MinHeap(int n) {
    this.arr = new Object[n];
    this.elements = 0;
  }

  @Override
  public boolean isFull() {
    return this.elements == this.arr.length;
  }

  private int father(int n) {
    return (n / 2);
  }

  private void swap(int a, int b) {
    Object aux = arr[a];
    arr[a] = arr[b];
    arr[b] = aux;
  }

  @Override
  public void enqueue(E element, P priority) {
    if (isFull()) {
      throw new RuntimeException("Queue is full");
    }

    elements++;
    arr[elements] = new Pair<E, P>(element, priority);

    siftUp(elements);
  }

  private void siftUp(int pos) {
    while (pos != 1 && fatherIsGreater(pos)) {
      swap(pos, father(pos));
      pos = father(pos);
    }
  }

  private boolean fatherIsGreater(int pos) {
    Pair<E, P> f = (Pair<E, P>) arr[father(pos)];
    Pair<E, P> c = (Pair<E, P>) arr[pos];

    return f.priority.compareTo(c.priority) > 0;
  }
}
