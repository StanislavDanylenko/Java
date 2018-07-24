package ua.nure.danylenko.Practice6.part5;

public class OldTree<E extends Comparable<E>> {

    private static final String INDENT = "   ";

    private Node<E> root = null;

    /*public boolean remove(E element) {
        return false;
    }*/

    /*public void delete(E value) {
        delete(value, root);
    }

    private Node<E> delete(E val, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.value.compareTo(val) == 0) {
            if (node.right != null && node.left != null) {
                Node<E> min = min(node.right);
                delete(min.value);
                node.value = min.value;
            } else {
                if (node.right != null) {
                    node.father.right = null;
                    return node.right;
                } else {
                    node.father.left = null;
                    return node.left;
                }
            }
        } else {
            if (node.value.compareTo(val) < 0) {
                node.right = delete(val, node.right);
            } else {
                node.left = delete(val, node.left);
            }
        }
        return node;
    }

    private static Node min(Node node) {
        Node result = node;
        //Node last = null;
        while (result.left != null) {
            //last = result;
            result = result.left;
        }
        //last.left = null;
        result.father.left = null;
        return result;
    }*/

    // This method mainly calls deleteRec()
    void delete(E key)
    {
        root = deleteRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node deleteRec(Node<E> root, E key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;

        /* Otherwise, recur down the tree */
        if (key.compareTo((E)root.value) < 0)
            root.left = deleteRec(root.left, key);
        else if (key.compareTo((E)root.value) > 0)
            root.right = deleteRec(root.right, key);

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // node with two children: Get the inorder successor (smallest
            // in the right subtree)
            root.value = (E)minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.value);
        }

        return root;
    }

    E minValue(Node<E> root)
    {
        E minv = (E)root.value;
        while (root.left != null)
        {
            minv = (E)root.left.value;
            root = root.left;
        }
        return minv;
    }

    // region delete recurcive
/*    private Node deleteRecursive(Node<E> current, E value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) == 0) {
            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }

            // Case 2: only 1 child
            if (current.right == null) {
                return current.left;
            }

            if (current.left == null) {
                return current.right;
            }

            // Case 3: 2 children
            E smallestValue = (E)findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }
        if (value.compareTo(current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }

        current.right = deleteRecursive(current.right, value);
        //System.out.println(current.value);
        return current;
    }

    private Node<E> findSmallestValue(Node<E> root) {
        return root.left == null ? root : findSmallestValue(root.left);
    }*/
//endregion

    public void add(E[] elements) {
        for (int i = 0; i < elements.length; i++) {
            add(elements[i]);
        }
    }

    public boolean add(E e) {
        Node<E> newEl = new Node<>(e);

        if (root == null) {
            root = newEl;
            return true;
        } else {
            Node<E> focusNode = root;
            Node<E> parent;
            while (true) {
				parent = focusNode;
				if (newEl.value.compareTo(parent.value) > 0) {
				    focusNode = focusNode.left;
				    if (focusNode == null) {
				        parent.left = newEl;
				        newEl.father = parent;
				        return  true;
                    }
                } else if (newEl.value.compareTo(parent.value)  < 0){
				    focusNode = focusNode.right;
				    if (focusNode == null) {
				        parent.right = newEl;
				        newEl.father = parent;
				        return true;
                    }
                } else {
				    return false;
                }
            }
        }
    }

    public void print() {
        print(root, 0, 0);
    }

    void print(Node root, int space, int t) {
        int COUNT = 3;

        if(root == null)
            return;

        space += COUNT;

        print(root.right, space, 1);

        for(int i = COUNT; i < space; i++) {
            System.out.print(" ");
        }
        if(t == 1) {
            System.out.println(root.value);
        }
        else if (t == 2) {
            System.out.println(root.value);
        }else{
            System.out.println(root.value);
        }

        print(root.left, space, 2);
    }

    private static class Node<E> {
        E value;
        Node father;
        Node right;
        Node left;

        public Node(E obj) {
            this.value = obj;
            this.father = null;
        }
    }

    public static void main(String[] args) {
        OldTree<Integer> t = new OldTree<>();
       /* t.add(5);
        t.add(3);
        t.add(8);
        t.add(1);
        t.add(4);
        t.add(7);
        t.add(9);
        t.add(0);
        t.add(2);
        t.add(6);
        t.add(10);*/
        t.add(3);
        t.add(3);
        t.add(new Integer[]{1, 2, 5, 4, 6, 0});
        //t.delete(3);
        t.print();
        System.out.println("~~~~~~~~~~");
        t.delete(4);
        t.print();
    }
}
