package edu.ecnu.teisei.algo.tree;

/**
 * Red-Black tree.
 * Created by dingcheng on 2015/3/17.
 */
public class RedBlackTree<T extends Comparable<? super T>>{
    /**
     *  A single node in our Binary tree.
     */
    public class BinaryNode {
        public T element;
        BinaryNode parent;
        BinaryNode left;
        BinaryNode right;
        String color = "";
        public BinaryNode(T elem,BinaryNode p, BinaryNode lt, BinaryNode rt) {
            element = elem;
            parent = p;
            left = lt;
            right = rt;
        }
    }
    /** Local variables */
    public BinaryNode root;                    // Pointer to root node, if present
    final BinaryNode NIL = new BinaryNode(null, null, null, null);
    public boolean removalSuccesful;    // Set to true when remove() succeeds

    /** Initializes an empty BST. */
    public RedBlackTree() {
        root = NIL;
        NIL.parent = NIL;
        NIL.left = NIL;
        NIL.right = NIL;
        NIL.color = "";
    }

    /** Return true if the BST is empty. */
    public boolean isEmpty() {
        return (root == NIL);
    }



    /**
     * search an element in the BST --- O(h)
     */
    public BinaryNode search(T elem) {
//        BinaryNode found = search(root, elem);//
        BinaryNode found = search_iterative(root, elem);
        return (found == NIL) ? NIL : found;
    }
    /** search the element giving the key */
    public BinaryNode search(BinaryNode start, T elem) {
        if (start == NIL) {
            return NIL;
        }
        int comparision = start.element.compareTo(elem);
        if (comparision == 0) {
            return start;
        } else if (comparision > 0) {
            return search(start.left, elem);
        } else {
            return search(start.right, elem);
        }
    }
    public BinaryNode search_iterative(BinaryNode x,T elem) {
        while (x != NIL && x.element.compareTo(elem) != 0) {
            if (x.element.compareTo(elem) > 0)
                x = x.left;
            else
                x = x.right;
        }
        return x;
    }


    /* get the minimun node under Node u. */
    public BinaryNode minimum(BinaryNode u) {
        while (u.left != NIL) {
            u = u.left;
        }
        return u;
    }
    /* get the maximum node under Node u */
    public BinaryNode maximum(BinaryNode u) {
        while (u.right != NIL) {
            u = u.right;
        }
        return u;
    }

    /* get the successor of Node u */
    public BinaryNode successor(BinaryNode u) {
        if (u.right != NIL) {
            return minimum(u.right);
        }
        BinaryNode x = u.parent;
        while (x != NIL && u == x.right) {
            u = x;
            x = u.parent;
        }
        //it's in the left sub-tree of the parent
        return x;
    }
    /* get the predecessor of Node u */
    public BinaryNode predecessor(BinaryNode u) {
        if (u.left != NIL) {
            return maximum(u.left);
        }
        BinaryNode x = u.parent;
        while (x != NIL && u == x.left) {
            u = x;
            x = u.parent;
        }
        //it's in the right sub-tree of the parent
        return x;
    }




    /*  */
    public boolean left_rotate(BinaryNode x) {
        BinaryNode y = x.right;
        x.right = y.left;
        if (y.left != NIL)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL)
            root = y;
        else if (x.parent.left == x)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
        return true;
    }
    /*  */
    public boolean right_rotate(BinaryNode y) {
        BinaryNode x = y.left;
        y.left = x.right;
        if (y.left != NIL)
            y.left.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL)
            root = x;
        else if (y.parent.left == y)
            y.parent.left = x;
        else
            y.parent.right = x;
        x.right = y;
        y.parent = x;
        return true;
    }

    public boolean insert(T z) {
        BinaryNode new_node = new BinaryNode(z,NIL, NIL, NIL);
        return insert_iterative(root, new_node);
    }
    public boolean insert(BinaryNode start, T z) {
        BinaryNode new_node = new BinaryNode(z, NIL, NIL, NIL);
        return insert_iterative(root, new_node);
    }
    public boolean insert_iterative(BinaryNode start, BinaryNode z) {
        BinaryNode y = NIL;
        BinaryNode x = start;
        while (x != NIL) {
            y = x;
            int comparison = z.element.compareTo(x.element);
            if (comparison < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == NIL) {
            root = z;
        }else if (z.element.compareTo(y.element) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }
        z.left = NIL;
        z.right = NIL;
        z.color = "red";
        RB_insert_fixup(z);
        return true;
    }
    public boolean RB_insert_fixup(BinaryNode z) {
        while (z.parent.color.equals("red")) {
            if (z.parent == z.parent.parent.left) {
                BinaryNode y = z.parent.parent.right;
                if (y.color.equals("red")) {
                    //cousin is red, means p is black
                    //cannot rotate from the p'p
                    //p->red, z and cousin->black
                    z.parent.color = "black";
                    y.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                } else{
                    if (z == z.parent.right) {
                        //left'right is heavy, cannot rotate
                        z = z.parent;
                        left_rotate(z);
                    }else{
                        //left'left is heavy, rotate from p'p
                        z.parent.color = "black";
                        z.parent.parent.color = "red";
                        right_rotate(z.parent.parent);
                    }
                }
            } else {
                //same as then clause with "right" and "left" exchanged
                BinaryNode y = z.parent.parent.left;
                if (y.color.equals("red")) {
                    z.parent.color = "black";
                    y.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        right_rotate(z);
                    } else {
                        z.parent.color = "black";
                        z.parent.parent.color = "red";
                        left_rotate(z.parent.parent);
                    }
                }
            }
        }
        root.color = "black";
        return true;
    }


    /**
     * replace u with v, and delete u.
     */
    public boolean transplant(BinaryNode u, BinaryNode v) {
        if (u.parent == NIL) {
            root = v;
        }else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
        return true;
    }

    public boolean delete(T elem) {
        BinaryNode z = search(elem);
        return delete(z);
    }
    public boolean delete(BinaryNode z) {
        BinaryNode y = z;
        String y_original_color = y.color;
        BinaryNode x = NIL;
        if (z.left == NIL) {
            x = z.right;
            transplant(z, z.right);
        }else if (z.right == NIL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (y_original_color.equals("black")) {
            //only: y(black),
            RB_delete_fixup(x);
        }
        return true;
    }

    public void RB_delete_fixup(BinaryNode x) {
        //color.equals("red")
        //color.equals("black")
        //color = "red"
        //color = "black"
        while (x != root && x.color.equals("black")) {
            if (x == x.parent.left) {
                BinaryNode w = x.parent.right;
                if (w.color.equals("red")) {
                    w.color = "black";
                    x.parent.color = "red";
                    left_rotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color.equals("black") && w.right.color.equals("black")) {
                    w.color = "red";
                    x = x.parent;
                } else {
                    if (w.right.color.equals("black")) {
                        w.left.color = "black";
                        w.color = "red";
                        right_rotate(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.right.color = "black";
                    left_rotate(x.parent);
                    x = root;
                }
            } else {
                // same as then clause with "right" and "left" exchanged

                BinaryNode w = x.parent.left;
                if (w.color.equals("red")) {
                    w.color = "black";
                    x.parent.color = "red";
                    right_rotate(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color.equals("black") && w.left.color.equals("black")) {
                    w.color = "red";
                    x = x.parent;
                } else {
                    if (w.left.color.equals("black")) {
                        w.right.color = "black";
                        w.color = "red";
                        left_rotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.left.color = "black";
                    right_rotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = "black";
    }

    /** This method returns the tree to an empty state. */
    public void clear() {
        root = NIL;
    }

    /** This method determines the equality of two BSTs.**/
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        // Make sure a BST was passed in
        if (other instanceof BinarySearchTree) {
            // Attempt to determine the equality of the two BSTs
            try {
                RedBlackTree<T> compare = (RedBlackTree<T>) other;
                return equals(root, compare.root);
            }
            // In case the user passes a BST filled with different kind of
            // elements
            catch (Exception e) {
                return false;
            }
        }

        return false;
    }


    /** This method determines the equality of two BSTs. */
    public boolean equals(BinaryNode start1, BinaryNode start2) {
        // If we've reached the end of each tree without any differences
        if (start1 == NIL && start2 == NIL) {
            // They are the same
            return true;
        }
        // If we've reached the end of one tree but not the other
        else if (start1 == NIL || start2 == NIL) {
            // They are different
            return false;
        }

        // Determine whether the left subtrees are equivalent
        boolean leftSame = equals(start1.left, start2.left);

        // Determine whether the current elements are equivalent
        boolean currentSame = start1.element.equals(start2.element);

        // Determine whether the right subtrees are equivalent
        boolean rightSame = equals(start1.right, start2.right);

        // Return true if everything is equivalent
        return (leftSame && currentSame && rightSame);
    }

    /** This method prints the BST. */
    public void print() {
        print(root,0);
        System.out.println();
    }
    public void print(String order) {
        if(order.equals("inorder"))
            print(root, 0);
        else if(order.equals("preorder"))
            print(root, -1);
        else
            print(root, 1);
        System.out.println();
    }

    /** This heper method prints the BST rooted at the given start node. */
    public void print(BinaryNode start, int order) {
        if (start != NIL) {
            if (order == -1) {
                System.out.print(start.element + " ");
                print(start.left, order);
                print(start.right, order);
            }else if (order == -1) {
                print(start.left, order);
                print(start.right, order);
                System.out.print(start.element + " ");
            }else{
                print(start.left, order);
                System.out.print(start.element + " ");
                print(start.right, order);
            }
        }
    }
}
