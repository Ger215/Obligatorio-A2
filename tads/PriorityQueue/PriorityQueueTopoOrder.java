package tads.PriorityQueue;

public class PriorityQueueTopoOrder {

  class Vertex {

    int element;
    int level;

    public Vertex(int element, int level) {
      this.element = element;
      this.level = level;
    }
  }

  private Vertex[] queue;
  private int elements;

  public PriorityQueueTopoOrder(int n) {
    this.queue = new Vertex[n + 1];
    this.elements = 0;
  }

  public boolean isEmpty() {
    return this.elements == 0;
  }

  public boolean isFull() {
    return this.elements + 1 == this.queue.length;
  }

  public int size() {
    return this.elements;
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
    Vertex aux = queue[a];
    queue[a] = queue[b];
    queue[b] = aux;
  }

  public void enqueue(int element, int level) {
    Vertex newVertex = new Vertex(element, level);
    if (isFull()) {
      throw new RuntimeException("Full queue");
    }
    elements++;
    queue[elements] = newVertex;
    int pos = elements;
    enqueueAux(pos);
  }

  public void enqueueAux(int pos) {
    if (pos != 1 && queue[pos].level == queue[father(pos)].level) {
      if (queue[pos].element < queue[father(pos)].element) {
        swap(pos, father(pos));
        enqueueAux(father(pos));
      }
    } else if (pos != 1 && queue[pos].level < queue[father(pos)].level) {
      swap(pos, father(pos));
      enqueueAux(father(pos));
    }
  }

  public int dequeue() {
    if (isEmpty()) {
      throw new RuntimeException("Empty queue");
    }
    Vertex min = queue[1];
    queue[1] = queue[elements];
    elements--;
    siftDown(1);
    return min.element;
  }

  private void siftDown(int pos) {
    int leftChild = leftChild(pos);
    int rightChild = rightChild(pos);

    if (leftChild <= elements && compareVertex(queue[leftChild], queue[pos])) {
      swap(pos, leftChild);
      siftDown(leftChild);
    }

    if (
      rightChild <= elements && compareVertex(queue[rightChild], queue[pos])
    ) {
      swap(pos, rightChild);
      siftDown(rightChild);
    }
  }

  private boolean compareVertex(Vertex Child, Vertex Father) {
    if (Child.level < Father.level) {
      return true;
    } else if (Child.level == Father.level && Child.element < Father.element) {
      return true;
    }

    return false;
  }

  public int getLevel() {
    return queue[1].level;
  }

  public int getMin() {
    return queue[1].element;
  }
}
