package tads.PriorityQueue;

public interface PriorityQueue<E, P extends Comparable<P>> {
  public void enqueue(E element, P priority);

  public boolean isFull();
}
