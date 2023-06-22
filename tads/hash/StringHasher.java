package tads.hash;

public class StringHasher implements Hasher<String> {

  @Override
  public int hash(String data, int size) {
    int h = 0;
    int length = data.length();
    if (length >= 10) length = 10;
    for (int i = 0; i < length; i++) {
      h = (31 * h * h) + data.charAt(i);
    }
    return h % size;
  }
  /*public long hash(String data, int size) {
    long h = 0;
    int length = data.length();
    if (length >= 10) length = 10;
    for (int i = 0; i < length; i++) {
      int c = data.charAt(i);
      h = (h * 131071) ^ c;
    }
    return h % size;
  } */
}
