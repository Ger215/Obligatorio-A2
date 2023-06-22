package tads.PriorityQueue;

public class PriorityQueueDijkstra {

  class Vertex {

    int element;
    int cost;

    public Vertex(int element, int cost) {
      this.element = element;
      this.cost = cost;
    }
  }

  private Vertex[] queue;
  private int elements;

  public PriorityQueueDijkstra(int n) {
    this.queue = new Vertex[(2 * n) + 1];
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

  public void enqueue(int element, int cost) {
    Vertex newVertex = new Vertex(element, cost);
    if (isFull()) {
      throw new RuntimeException("Full queue");
    }
    elements++;
    queue[elements] = newVertex;
    int pos = elements;
    while (pos != 1 && newVertex.cost < queue[father(pos)].cost) {
      swap(pos, father(pos));
      pos = father(pos);
    }
    queue[pos] = newVertex;
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
    int smallest = 0;
    int left = leftChild(pos);
    int right = rightChild(pos);
    if (left < elements && queue[left].cost < queue[pos].cost) {
      smallest = left;
    } else {
      smallest = pos;
    }
    if (right < elements && queue[right].cost < queue[smallest].cost) {
      smallest = right;
    }
    if (smallest != pos) {
      swap(pos, smallest);
      siftDown(smallest);
    }
  }
}
