package tads.bst;

public class AVL<T extends Comparable<T>> implements BST<T> {

  public class Node<T> {

    T data;
    int ocurrencies;
    Node<T> left = null;
    Node<T> right = null;
    int height = 1;

    Node(T data, int ocurrencies) {
      this.data = data;
      this.ocurrencies = ocurrencies;
    }
  }

  private Node<T> root;
  private int elements;

  @Override
  public void insert(T data) {
    root = insert(root, data);
  }

  public Node<T> insert(Node<T> root, T data) {
    if (root == null) {
      elements++;
      return new Node<T>(data, 1);
    } else {
      if (data.compareTo(root.data) == 0) {
        root.ocurrencies += 1;
      } else if (data.compareTo(root.data) < 0) {
        root.left = insert(root.left, data);
      } else {
        root.right = insert(root.right, data);
      }
    }

    if (root.left == null && root.right == null) {
      root.height = 1;
    } else {
      root.height = 1 + max(height(root.left), height(root.right));
    }

    int bf = balanceFactor(root);

    if (bf > 1) {
      int bfRight = balanceFactor(root.right);
      if (bfRight > 0) {
        return LeftRotation(root);
      } else {
        return RightLeftRotation(root);
      }
    }
    if (bf < -1) {
      int bfLeft = balanceFactor(root.left);
      if (bfLeft < 0) {
        return RightRotation(root);
      } else {
        return LeftRightRotation(root);
      }
    }

    return root;
  }

  private Node<T> LeftRightRotation(Node<T> z) {
    z.left = LeftRotation(z.left);
    z = RightRotation(z);

    z.height = 1 + max(height(z.left), height(z.right));

    return z;
  }

  private Node<T> RightLeftRotation(Node<T> z) {
    z.right = RightRotation(z.right);
    z = LeftRotation(z);

    z.height = 1 + max(height(z.left), height(z.right));

    return z;
  }

  private Node<T> RightRotation(Node<T> y) {
    Node<T> x = y.left;
    Node<T> x_r = x.right;

    x.right = y;
    y.left = x_r;

    y.height = 1 + max(height(y.left), height(y.right));
    x.height = 1 + max(height(x.left), height(x.right));
    return x;
  }

  private Node<T> LeftRotation(Node<T> x) {
    Node<T> y = x.right;
    Node<T> y_l = y.left;

    y.left = x;
    x.right = y_l;

    x.height = 1 + max(height(x.left), height(x.right));
    y.height = 1 + max(height(y.left), height(y.right));
    return y;
  }

  private int balanceFactor(Node<T> root) {
    return height(root.right) - height(root.left);
  }

  private int height(Node<T> root) {
    if (root == null) {
      return 0;
    } else {
      return root.height;
    }
  }

  private int max(int a, int b) {
    if (a > b) {
      return a;
    }
    return b;
  }

  public void getNode() {
    getNodeAux(this.root);
  }

  public void getNodeAux(Node<T> root) {
    if (root == null) return;
    getNodeAux(root.right);
    System.out.println(root.data + " " + root.ocurrencies);
    getNodeAux(root.left);
  }
}
