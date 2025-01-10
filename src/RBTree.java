public class RBTree {
    enum Color { RED, BLACK }

    public static class Node {
        int value;
        Node left, right, parent;
        Color color;

        Node(int value) {
            this.value = value;
            this.color = Color.RED;
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public boolean insert(int value) {
        if (root == null) {
            root = new Node(value);
            root.color = Color.BLACK;
            return true;
        }

        Node newNode = insertRec(root, value);
        if (newNode == null) {
            return false;
        }
        fixViolation(newNode);
        return true;
    }

    private Node insertRec(Node root, int value) {
        if (value == root.value) {
            return null;
        }

        if (value < root.value) {
            if (root.left == null) {
                root.left = new Node(value);
                root.left.parent = root;
                return root.left;
            } else {
                return insertRec(root.left, value);
            }
        } else {
            if (root.right == null) {
                root.right = new Node(value);
                root.right.parent = root;
                return root.right;
            } else {
                return insertRec(root.right, value);
            }
        }
    }

    private void fixViolation(Node node) {
        while (node != root && node.parent.color == Color.RED) {
            Node grandparent = getGrandparent(node);
            Node uncle = getUncle(node);

            if (uncle != null && uncle.color == Color.RED) {
                node.parent.color = Color.BLACK;
                uncle.color = Color.BLACK;
                if (grandparent != null) {
                    grandparent.color = Color.RED;
                    node = grandparent;
                }
            } else {
                if (node == node.parent.right && node.parent == grandparent.left) {
                    leftRotate(node.parent);
                    node = node.left;
                } else if (node == node.parent.left && node.parent == grandparent.right) {
                    rightRotate(node.parent);
                    node = node.right;
                }

                node.parent.color = Color.BLACK;
                if (grandparent != null) {
                    grandparent.color = Color.RED;
                    if (node == node.parent.left) {
                        rightRotate(grandparent);
                    } else {
                        leftRotate(grandparent);
                    }
                }
            }
        }
        root.color = Color.BLACK;
    }

    private Node getUncle(Node node) {
        Node grandparent = getGrandparent(node);
        if (grandparent == null) return null;
        return (node.parent == grandparent.left) ? grandparent.right : grandparent.left;
    }

    private Node getGrandparent(Node node) {
        return (node.parent != null) ? node.parent.parent : null;
    }

    private void leftRotate(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rightRotate(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }
}
