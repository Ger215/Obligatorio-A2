package tads.PriorityQueue;

import java.text.DecimalFormat;

public class MaxHeap<E, P extends Comparable<P>>
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

  public MaxHeap(int n) {
    this.arr = new Object[n + 1];
    this.elements = 0;
  }

  @Override
  public boolean isEmpty() {
    return this.elements == 0;
  }

  @Override
  public boolean isFull() {
    return this.elements == this.arr.length;
  }

  private int father(int n) {
    return (n / 2);
  }

  private int leftChild(int n) {
    return (n * 2);
  }

  private int rightChild(int n) {
    return ((n * 2) + 1);
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
    while (pos != 1 && fatherIsLesser(pos)) {
      swap(pos, father(pos));
      pos = father(pos);
    }
  }

  private void siftDown(int pos, int length) {
    while (
      leftChild(pos) <= length && rightChild(pos) <= length && sonIsBigger(pos)
    ) {
      Pair<E, P> rChild = (Pair<E, P>) arr[rightChild(pos)];
      Pair<E, P> lChild = (Pair<E, P>) arr[leftChild(pos)];
      if (lChild.priority.compareTo(rChild.priority) < 0) {
        swap(pos, rightChild(pos));
        pos = rightChild(pos);
      } else {
        swap(pos, leftChild(pos));
        pos = leftChild(pos);
      }
    }
  }

  private boolean fatherIsLesser(int pos) {
    Pair<E, P> father = (Pair<E, P>) arr[father(pos)];
    Pair<E, P> child = (Pair<E, P>) arr[pos];

    return father.priority.compareTo(child.priority) < 0;
  }

  private boolean sonIsBigger(int pos) {
    Pair<E, P> father = (Pair<E, P>) arr[pos];
    Pair<E, P> rChild = (Pair<E, P>) arr[rightChild(pos)];
    Pair<E, P> lChild = (Pair<E, P>) arr[leftChild(pos)];
    if (lChild.priority.compareTo(rChild.priority) < 0) {
      return father.priority.compareTo(rChild.priority) < 0;
    } else {
      return father.priority.compareTo(lChild.priority) < 0;
    }
  }

  @Override
  public void getMaxPriority() {
    int newLength = arr.length;
    while (newLength != 0) {
      Pair<E, P> ret = (Pair<E, P>) arr[1];
      int last = newLength - 1;
      newLength = last;
      if (newLength == 0) {} else {
        arr[1] = arr[last];
        siftDown(1, newLength);
        DecimalFormat twoDecimal = new DecimalFormat("#.00");
        Double priority = (Double) ret.priority;
        long integerPart = Math.round(priority);
        if (priority - integerPart == 0) {
          System.out.println(integerPart + " " + ret.element);
        } else {
          System.out.println(
            twoDecimal.format(ret.priority) + " " + ret.element
          );
        }
      }
    }
  }
}
