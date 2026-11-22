import java.util.Scanner;

class Node11 {
    int data;
    Node11 left, right;

    Node11(int value) {
        data = value;
    }
}

class BinaryTree {

    Node11 root;
    Scanner sc = new Scanner(System.in);

    void createRoot(int value) {
        if (root == null) {
            root = new Node11(value);
            System.out.println("Root created.");
        } else {
            System.out.println("Root already exists!");
        }
    }

    void insert(Node11 current, int value) {

        System.out.print("At node " + current.data +
                ". Insert LEFT or RIGHT? (L/R): ");

        char choice = sc.next().charAt(0);

        if (choice == 'L' || choice == 'l') {

            if (current.left == null) {
                current.left = new Node11(value);
            } else {
                insert(current.left, value);
            }

        } else {

            if (current.right == null) {
                current.right = new Node11(value);
            } else {
                insert(current.right, value);
            }
        }
    }

    void insert(int value) {
        if (root == null) {
            System.out.println("Create root first!");
        } else {
            insert(root, value);
        }
    }

    void inorder(Node11 node) {
        if (node == null) return;

        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    void preorder(Node11 node) {
        if (node == null) return;

        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    void postorder(Node11 node) {
        if (node == null) return;

        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }
}

public class BinaryTreeWithRecursion {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();

        System.out.print("Enter root value: ");
        tree.createRoot(sc.nextInt());

        int choice;

        do {
            System.out.println("\n1. Insert");
            System.out.println("2. Inorder");
            System.out.println("3. Preorder");
            System.out.println("4. Postorder");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value: ");
                    tree.insert(sc.nextInt());
                    break;
                case 2:
                    tree.inorder(tree.root);
                    System.out.println();
                    break;
                case 3:
                    tree.preorder(tree.root);
                    System.out.println();
                    break;
                case 4:
                    tree.postorder(tree.root);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
} 