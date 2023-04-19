package tads.hash;

public class StringHasher implements Hasher<String> {

  @Override
  public int hash(String data, int size) {
    int hashValue = 0;
    for (int i = 0; i < data.length(); i++) {
      hashValue = 37 * hashValue + data.charAt(i);
    }

    return hashValue % size;
  }
  /*
   * public int hash(String data, int size) {
    int hash = 0;
    for (int i = 0; i < data.length(); i++) {
      hash = 31 * hash + data.charAt(i);
    }
    return hash % size;
  }
   */
}
