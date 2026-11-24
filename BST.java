import java.util.*;
class BinarySearchTree{
    static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.right=null;
            this.left=null;
        }
    }

    //Recursive Insertion
    Node root;
    Node insert(Node current,int value){
        if (current == null ) {
            return new Node(value);
        } 
        if(value<current.data){
            current.left=insert(current.left, value);
        }
        else if(value>current.data){
            current.right=insert(current.right, value);
        }
        return current;
    }
    //Recursive Searching
    boolean search(Node current,int value){
        if (current==null) {
            return false;
        }
        if (current.data == value) {
            return true;
        }
        if (value < current.data) {
            return search(current.left, value);
        }
        else{
            return search(current.right, value);
        }
    }

    //Find Min value in Right Sub-Tree
    Node findMin(Node current){
        while (current.left !=null) {
            current = current.left;
        }
        return current;
    }

    //Recursive Deletion
    Node delete(Node current,int value){
        if (current==null) {
            return null;
        }
        if (value < current.data) {
            current.left=delete(current.left, value);
        }
        else if(value > current.data){
            current.right=delete(current.right, value);
        }
        else{

            //Node with only one child or no child
            if (current.left==null) {
                return current.right;
            }
            else if(current.right==null){
                return current.left;
            }
            //Node with two children
            Node temp=findMin(current.right);
            current.data=temp.data;
            current.right=delete(current.right, temp.data);
        }
        return current;
    }
    
    //Inorder Traversal
    void inorder(Node current){
        if (current!=null) {
            inorder(current.left);
            System.out.print(current.data+" ");
            inorder(current.right);
        }
    }

    //Preorder Traversal
    void preorder(Node current){
        if (current!=null) {
            System.out.print(current.data+" ");
            preorder(current.left);
            preorder(current.right);
        }
    }

    //Postorder Traversal
    void postorder(Node current){
        if (current!=null) {
            postorder(current.left);
            postorder(current.right);
            System.out.print(current.data+" ");
        }
    }

    //Main method
    public static void main(String[] args) {
        BinarySearchTree bst=new BinarySearchTree();
        Scanner sc=new Scanner(System.in);
        int choice,value;
        do {
            System.out.println("\n1.Insert\n2.Search\n3.Delete\n4.Inorder Traversal\n5.Preorder Traversal\n6.Postorder Traversal\n7.Exit");
            System.out.print("Enter your choice: ");
            choice=sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    value=sc.nextInt();
                    bst.root=bst.insert(bst.root, value);
                    break;
                case 2:
                    System.out.print("Enter value to search: ");
                    value=sc.nextInt();
                    if (bst.search(bst.root, value)) {
                        System.out.println(value+" found in the tree.");
                    } else {
                        System.out.println(value+" not found in the tree.");
                    }
                    break;
                case 3:
                    System.out.print("Enter value to delete: ");
                    value=sc.nextInt();
                    bst.root=bst.delete(bst.root, value);
                    break;
                case 4:
                    System.out.println("Inorder Traversal:");
                    bst.inorder(bst.root);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("Preorder Traversal:");
                    bst.preorder(bst.root);
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Postorder Traversal:");
                    bst.postorder(bst.root);
                    System.out.println();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
        sc.close();
    }
}