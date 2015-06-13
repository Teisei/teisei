package edu.ecnu.teisei.algo.tree;

/**
 * A general Binary Search tree.
 * Created by dingcheng on 2015/3/16.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    /**
     *  A single node in our Binary tree.
     */
    public class BinaryNode {
        public T element;
        BinaryNode parent;
        BinaryNode left;
        BinaryNode right;
        public BinaryNode(T elem) {
            element = elem;
            left = null;
            right = null;
            parent = null;
        }
        public BinaryNode(T elem,BinaryNode p, BinaryNode lt, BinaryNode rt) {
            element = elem;
            parent = p;
            left = lt;
            right = rt;
        }
    }

    /** Local variables */
    public BinaryNode root;                    // Pointer to root node, if present
    public boolean removalSuccesful;    // Set to true when remove() succeeds

    /** Initializes an empty BST. */
    public BinarySearchTree() {
        root = null;
    }

    /** Return true if the BST is empty. */
    public boolean isEmpty() {
        return (root == null);
    }



    /**
     * search an element in the BST --- O(h)
     */
    public BinaryNode search(T elem) {
//        BinaryNode found = search(root, elem);//
        BinaryNode found = search_iterative(root, elem);
        return (found == null) ? null : found;
    }
    /** search the element giving the key */
    public BinaryNode search(BinaryNode start, T elem) {
        if (start == null) {
            return null;
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
        while (x != null && x.element.compareTo(elem) != 0) {
            if (x.element.compareTo(elem) > 0)
                x = x.left;
            else
                x = x.right;
        }
        return x;
    }


    /* get the minimun node under Node u. */
    public BinaryNode minimum(BinaryNode u) {
        while (u.left != null) {
            u = u.left;
        }
        return u;
    }
    /* get the maximum node under Node u */
    public BinaryNode maximum(BinaryNode u) {
        while (u.right != null) {
            u = u.right;
        }
        return u;
    }

    /* get the successor of Node u */
    public BinaryNode successor(BinaryNode u) {
        if (u.right != null) {
            return minimum(u.right);
        }
        BinaryNode x = u.parent;
        while (x != null && u == x.right) {
            u = x;
            x = u.parent;
        }
        //it's in the left sub-tree of the parent
        return x;
    }
    /* get the predecessor of Node u */
    public BinaryNode predecessor(BinaryNode u) {
        if (u.left != null) {
            return maximum(u.left);
        }
        BinaryNode x = u.parent;
        while (x != null && u == x.left) {
            u = x;
            x = u.parent;
        }
        //it's in the right sub-tree of the parent
        return x;
    }



    /**
     * Inserts an element into the BST, unless it is already stored.
     * O(h).
     */
    public boolean insert(T elem) {
        return insert(root, elem);
    }
    /** This method inserts an element into the BST, unless it is already stored.**/
    public boolean insert(BinaryNode start, T elem) {
        // We've reached the point of insertion
        if (start == null) {
            // Insert our element into a new node
            root = new BinaryNode(elem, null, null, null);
            return true;
        }

        int comparison = start.element.compareTo(elem);

        if (comparison > 0) {
            if (start.left == null) {
                start.left = new BinaryNode(elem, start, null,null);
                return true;
            }
            return insert(start.left, elem);
        }
        else if (comparison < 0) {
            if (start.right == null) {
                start.right = new BinaryNode(elem, start, null, null);
                return true;
            }
            return insert(start.right, elem);
        }
        else {
            // An elem with key equals this.elem is already in the tree.
            return false;
        }
    }
    public boolean insert_iterative(BinaryNode start,T z) {
        BinaryNode y = null;
        BinaryNode x = start;
        int comparison = 0;
        while (x != null) {
            y = x;
            comparison = z.compareTo(x.element);
            if (comparison < 0) {
                x = x.left;
            } else if (comparison > 0) {
                x = x.right;
            } else {
                return false;
            }
        }
        if (y == null) {
            root = new BinaryNode(z,null, null, null);
            return true;
        }
        if (comparison < 0) {
            y.left = new BinaryNode(z,y, null, null);
        } else if (comparison > 0) {
            y.right = new BinaryNode(z,y, null, null);
        } else {
            return false;
        }
        return true;
    }


    /**
     * replace u with v, and delete u.
     */
    public boolean transplant(BinaryNode u, BinaryNode v) {
        if (u.parent == null) {
            this.root = v;
        }else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
        return true;
    }

    /**
     * delete a node from the tree.
     */
    public boolean delete(T elem) {
        BinaryNode z = search(elem);
        return delete(z);
    }
    public boolean delete(BinaryNode z) {
        if (z.left == null) {
            transplant(z, z.right);
        }else if (z.right == null) {
            transplant(z, z.left);
        } else {
            BinaryNode y = minimum(z.right);
            if (y.parent != z) {
                //we need to
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
        }
        return false;
    }


    /** This method returns the tree to an empty state. */
    public void clear() {
        root = null;
    }

    /** This method determines the equality of two BSTs.**/
    @SuppressWarnings("unchecked")
    public boolean equals(Object other) {
        // Make sure a BST was passed in
        if (other instanceof BinarySearchTree) {
            // Attempt to determine the equality of the two BSTs
            try {
                BinarySearchTree<T> compare = (BinarySearchTree<T>) other;
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
        if (start1 == null && start2 == null) {
            // They are the same
            return true;
        }
        // If we've reached the end of one tree but not the other
        else if (start1 == null || start2 == null) {
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
        if (start != null) {
            if (order == -1) {
                System.out.print(start.element + " ");
                print(start.left, order);
                print(start.right, order);
            }else if (order ==1) {
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
