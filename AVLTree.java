class AVLTree {

    class Node {
        int key, h;
        Node left, right;
        Node(int k) { key = k; h = 1; }
    }

    Node root;

    int h(Node n) { return n == null ? 0 : n.h; }

    int bal(Node n) { return n == null ? 0 : h(n.left) - h(n.right); }

    void fix(Node n) { n.h = 1 + Math.max(h(n.left), h(n.right)); }

    // Right rotate
    Node rotR(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fix(y); fix(x);
        return x;
    }

    // Left rotate
    Node rotL(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fix(x); fix(y);
        return y;
    }

    // Insert
    Node insert(Node n, int k) {
        if (n == null) return new Node(k);

        if (k < n.key)      n.left  = insert(n.left, k);
        else if (k > n.key) n.right = insert(n.right, k);
        else                return n;

        fix(n);
        int b = bal(n);

        if (b > 1  && k < n.left.key)  return rotR(n);          // LL
        if (b < -1 && k > n.right.key) return rotL(n);          // RR
        if (b > 1  && k > n.left.key)  { n.left  = rotL(n.left);  return rotR(n); } // LR
        if (b < -1 && k < n.right.key) { n.right = rotR(n.right); return rotL(n); } // RL

        return n;
    }

    // Delete
    Node delete(Node n, int k) {
        if (n == null) return null;

        if (k < n.key)      n.left  = delete(n.left, k);
        else if (k > n.key) n.right = delete(n.right, k);
        else {
            if (n.left == null)  return n.right;
            if (n.right == null) return n.left;
            Node min = n.right;
            while (min.left != null) min = min.left;
            n.key = min.key;
            n.right = delete(n.right, min.key);
        }

        fix(n);
        int b = bal(n);

        if (b > 1  && bal(n.left)  >= 0) return rotR(n);          // LL
        if (b > 1  && bal(n.left)  <  0) { n.left  = rotL(n.left);  return rotR(n); } // LR
        if (b < -1 && bal(n.right) <= 0) return rotL(n);          // RR
        if (b < -1 && bal(n.right) >  0) { n.right = rotR(n.right); return rotL(n); } // RL

        return n;
    }

    
    void insert(int k) { root = insert(root, k); }
    void delete(int k) { root = delete(root, k); }

    boolean search(int k) {
        Node c = root;
        while (c != null) {
            if (k == c.key) return true;
            c = (k < c.key) ? c.left : c.right;
        }
        return false;
    }

    void inorder() { inorder(root); System.out.println(); }
    void inorder(Node n) {
        if (n != null) { inorder(n.left); System.out.print(n.key + " "); inorder(n.right); }
    }

    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        for (int k : new int[]{10, 20, 30, 40, 50, 25}) t.insert(k);
        t.inorder();                    
        System.out.println(t.search(25));
        t.delete(30);
        t.inorder();                    
    }
}