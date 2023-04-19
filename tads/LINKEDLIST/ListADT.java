package tads.LINKEDLIST;

public interface ListADT<T> extends Iterable<T> {
  public T get(int pos);

  public void pushFront(T o);

  public void pushBack(T o);

  public void pushAt(T o, int pos);

  public void deleteAt(int pos);

  public void delete(T o);

  public boolean isEmpty();

  public boolean contains(T o);

  public int size();
}
