package tads.bst;

public class AVL<T extends Comparable<T>> implements BST<T> {

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

    root.height = 1 + max(height(root.left), height(root.right));

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
    z = LeftRotation(z.right);
    z = RightRotation(z);

    z.height = 1 + max(height(z.left), height(z.right));

    return z;
  }

  private Node<T> RightLeftRotation(Node<T> z) {
    z = RightRotation(z.left);
    z = LeftRotation(z);

    z.height = 1 + max(height(z.left), height(z.right));

    return z;
  }

  private Node<T> RightRotation(Node<T> z) {
    Node<T> y = z.left;
    Node<T> y_l = y.right;

    y.right = z;
    z.left = y_l;

    z.height = 1 + max(height(z.left), height(z.right));
    y.height = 1 + max(height(y.left), height(y.right));

    return y;
  }

  private Node<T> LeftRotation(Node<T> z) {
    Node<T> y = z.right;
    Node<T> y_l = y.left;

    y.left = z;
    z.right = y_l;

    z.height = 1 + max(height(z.left), height(z.right));
    y.height = 1 + max(height(y.left), height(y.right));

    return y;
  }

  private int balanceFactor(Node<T> root) {
    return height(root.right) - height(root.left);
  }

  private int height(Node<T> root) {
    return root.height;
  }

  private int max(int a, int b) {
    if (a > b) {
      return a;
    }
    return b;
  }

  public Node<T> getNode() {
    return getNodeAux(this.root);
  }

  private Node<T> getNodeAux(Node<T> node) {
    Node<T> aux = null;
    if (node.right != null) {
      aux = getNodeAux(node.right);
    } else {
      aux = getNodeAux(node.left);
    }

    return aux;
  }
}
