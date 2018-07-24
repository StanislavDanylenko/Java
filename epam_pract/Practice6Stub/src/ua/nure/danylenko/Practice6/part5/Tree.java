package ua.nure.danylenko.Practice6.part5;

// Java program to demonstrate delete operation in binary search tree
public class Tree<E extends Comparable<E>> {
    /* Class containing left and right child of current node and key value*/
    private static class Node<E> {
        E key;
        Node<E> left;
        Node<E> right;

        public Node(E item) {
            key = item;
            left = right = null;
        }
    }

    private static final String INDENT = "   ";
    private Node<E> root = null;
    private boolean isLastAdded = true;
    private boolean isLastDeleted = false;

    public boolean remove(E key) {
        isLastDeleted = false;
        root = deleteRec(root, key);
        return isLastDeleted;
    }

    /* A recursive function to insert a new key in BST */
    private Node<E> deleteRec(Node<E> root, E key) {
        /* Base Case: If the tree is empty */
        if (root == null) {
            return root;
        }
        /* Otherwise, recur down the tree */
        if (key.compareTo(root.key) < 0) {
            root.left = deleteRec(root.left, key);
        }
        else if (key.compareTo(root.key) > 0) {
            root.right = deleteRec(root.right, key);
        }

            // if key is same as root's key, then This is the node
            // to be deleted
        else {
            // node with only one child or no child
            if (root.left == null) {
                isLastDeleted = true;
                return root.right;
            }
            else if (root.right == null) {
                isLastDeleted = true;
                return root.left;
            }
            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.key = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    private E minValue(Node<E> root) {
        E minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    // This method mainly calls insertRec()
    public boolean add(E key) {
        isLastAdded = true;
        root = insertRec(root, key);
        return isLastAdded;
    }

    public void add(E[] element) {
        for (E el : element) {
            add(el);
        }
    }

    /* A recursive function to insert a new key in BST */
    private Node<E> insertRec(Node<E> root, E key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key.compareTo(root.key) == 0) {
            isLastAdded = false;
        }
        /* Otherwise, recur down the tree */
        if (key.compareTo(root.key) < 0) {
            root.left = insertRec(root.left, key);
        }
        else if (key.compareTo(root.key) > 0) {
            root.right = insertRec(root.right, key);
        }

        /* return the (unchanged) node pointer */
        return root;
    }

    public void print() {
        print(root, 0, 0);
    }

    private void print(Node<E> root, int space, int t) {
        int COUNT = 3;

        if (root == null)
            return;

        space += COUNT;

        print(root.right, space, 1);

        for (int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        if (t == 1) {
            System.out.println(root.key);
        } else if (t == 2) {
            System.out.println(root.key);
        } else {
            System.out.println(root.key);
        }
        print(root.left, space, 2);
    }

    // Driver Program to test above functions
    public static void main(String[] args) {
        Tree tree = new Tree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
        20   40  60   80 */
        System.out.println(tree.add(3));
        System.out.println(tree.add(1));
        System.out.println(tree.add(2));
        System.out.println(tree.add(5));
        System.out.println(tree.add(4));
        System.out.println(tree.add(6));
        System.out.println(tree.add(0));

        //System.out.println("Inorder traversal of the given tree");
        System.out.println("~~~~~~~~~~~~~~~~");
        tree.print();

        System.out.println("~~~~~~~~~~~~~~~~");
        int del = 3;
        System.out.println("\nDelete " + del);
        System.out.println(tree.remove(del));
        System.out.println(tree.remove(del));
//        System.out.println("Inorder traversal of the modified tree");
        tree.print();
        System.out.println("~~~~~~~~~~~~~");
        System.out.println(tree.add(5));
        tree.print();
        /*System.out.println("\nDelete 30");
        tree.deleteKey(30);
        System.out.println("Inorder traversal of the modified tree");
        tree.print();

        System.out.println("\nDelete 50");
        tree.deleteKey(50);
        System.out.println("Inorder traversal of the modified tree");
        tree.print();*/
    }
}

