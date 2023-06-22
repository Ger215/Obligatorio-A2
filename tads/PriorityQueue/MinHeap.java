package tads.PriorityQueue;

public class MinHeap {

  private int[] arr;
  private int elements;
  private int size;

  public MinHeap(int size) {
    this.size = size;
    this.elements = 0;

    arr = new int[this.size + 1];
    arr[0] = Integer.MIN_VALUE;
  }

  private int parent(int pos) {
    return pos / 2;
  }

  private int leftChild(int pos) {
    return (2 * pos);
  }

  private int rightChild(int pos) {
    return (2 * pos) + 1;
  }

  private boolean isLeaf(int pos) {
    if (pos > (elements / 2)) {
      return true;
    }

    return false;
  }

  private void swap(int a, int b) {
    int tmp;
    tmp = arr[a];

    arr[a] = arr[b];
    arr[b] = tmp;
  }

  private void siftDown(int pos) {
    if (!isLeaf(pos)) {
      int swapPos = pos;
      if (rightChild(pos) <= elements) swapPos =
        arr[leftChild(pos)] < arr[rightChild(pos)]
          ? leftChild(pos)
          : rightChild(pos); else swapPos = leftChild(pos);

      if (arr[pos] > arr[leftChild(pos)] || arr[pos] > arr[rightChild(pos)]) {
        swap(pos, swapPos);
        siftDown(swapPos);
      }
    }
  }

  public void enqueue(int element) {
    if (elements >= size) {
      return;
    }

    arr[++elements] = element;
    int current = elements;

    while (arr[current] < arr[parent(current)]) {
      swap(current, parent(current));
      current = parent(current);
    }
  }

  public int dequeue() {
    int min = arr[1];
    arr[1] = arr[elements--];
    siftDown(1);

    return min;
  }

  public int top() {
    return arr[1];
  }

  public boolean isEmpty() {
    return elements == 0;
  }
}
