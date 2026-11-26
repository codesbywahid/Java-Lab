import java.util.Scanner;

class StudentNode {
    int registrationNo;
    String studentName;
    StudentNode left, right;

    public StudentNode(int registrationNo, String studentName) {
        this.registrationNo = registrationNo;
        this.studentName = studentName;
        this.left = null;
        this.right = null;
    }
}

public class StudentBST {
    private StudentNode root;
    private Scanner scanner;

    public StudentBST() {
        root = null;
        scanner = new Scanner(System.in);
    }

    //  INSERT 
    public void insert(int regNo, String name) {
        if (searchNode(regNo) != null) {
            System.out.println("Error: Duplicate registration number " + regNo + " rejected.");
            return;
        }
        root = insertRec(root, regNo, name);
        System.out.println("Student inserted successfully.");
    }

    private StudentNode insertRec(StudentNode node, int regNo, String name) {
        if (node == null) {
            return new StudentNode(regNo, name);
        }
        if (regNo < node.registrationNo) {
            node.left = insertRec(node.left, regNo, name);
        } else if (regNo > node.registrationNo) {
            node.right = insertRec(node.right, regNo, name);
        }
        return node;
    }

    // SEARCH 
    public void search(int regNo) {
        if (root == null) {
            System.out.println("Tree is empty. No records to search.");
            return;
        }
        StudentNode result = searchNode(regNo);
        if (result != null) {
            System.out.println("Record Found -> RegNo: " + result.registrationNo
                    + ", Name: " + result.studentName);
        } else {
            System.out.println("Record with registration number " + regNo + " not found.");
        }
    }

    private StudentNode searchNode(int regNo) {
        StudentNode current = root;
        while (current != null) {
            if (regNo == current.registrationNo)
                return current;
            else if (regNo < current.registrationNo)
                current = current.left;
            else
                current = current.right;
        }
        return null;
    }

    // ---------- DELETE ----------
    public void delete(int regNo) {
        if (root == null) {
            System.out.println("Tree is empty. Nothing to delete.");
            return;
        }
        if (searchNode(regNo) == null) {
            System.out.println("Record with registration number " + regNo + " not found. Deletion failed.");
            return;
        }
        root = deleteRec(root, regNo);
        System.out.println("Student record with RegNo " + regNo + " deleted successfully.");
    }

    private StudentNode deleteRec(StudentNode node, int regNo) {
        if (node == null) return null;

        if (regNo < node.registrationNo) {
            node.left = deleteRec(node.left, regNo);
        } else if (regNo > node.registrationNo) {
            node.right = deleteRec(node.right, regNo);
        } else {
            // Case 1 & 2: Node with 0 or 1 child
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            // Case 3: Node with two children  use inorder successor
            StudentNode successor = findMin(node.right);
            // Transfer the complete record from successor
            node.registrationNo = successor.registrationNo;
            node.studentName = successor.studentName;
            // Delete the successor node
            node.right = deleteRec(node.right, successor.registrationNo);
        }
        return node;
    }

    // MIN / MAX 
    public void findMin() {
        if (root == null) {
            System.out.println("Tree is empty. No minimum record.");
            return;
        }
        StudentNode min = findMin(root);
        System.out.println("Minimum Record -> RegNo: " + min.registrationNo
                + ", Name: " + min.studentName);
    }

    private StudentNode findMin(StudentNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public void findMax() {
        if (root == null) {
            System.out.println("Tree is empty. No maximum record.");
            return;
        }
        StudentNode max = root;
        while (max.right != null) max = max.right;
        System.out.println("Maximum Record -> RegNo: " + max.registrationNo
                + ", Name: " + max.studentName);
    }

    // HEIGHT
    public int height() {
        return heightRec(root);
    }

    private int heightRec(StudentNode node) {
        if (node == null) return -1;
        int leftH = heightRec(node.left);
        int rightH = heightRec(node.right);
        return Math.max(leftH, rightH) + 1;
    }

    // TRAVERSALS 
    public void inorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Inorder: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(StudentNode node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print("[" + node.registrationNo + " - " + node.studentName + "] ");
            inorderRec(node.right);
        }
    }

    public void preorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Preorder: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(StudentNode node) {
        if (node != null) {
            System.out.print("[" + node.registrationNo + " - " + node.studentName + "] ");
            preorderRec(node.left);
            preorderRec(node.right);
        }
    }

    public void postorder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        System.out.print("Postorder: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(StudentNode node) {
        if (node != null) {
            postorderRec(node.left);
            postorderRec(node.right);
            System.out.print("[" + node.registrationNo + " - " + node.studentName + "] ");
        }
    }

    public void menu() {
        int choice;
        do {
            System.out.println("\n=== Student Record BST Menu ===");
            System.out.println("1. Insert Student");
            System.out.println("2. Search Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Inorder");
            System.out.println("5. Display Preorder");
            System.out.println("6. Display Postorder");
            System.out.println("7. Find Minimum Record");
            System.out.println("8. Find Maximum Record");
            System.out.println("9. Display Height of BST");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Registration No: ");
                    int reg = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    insert(reg, name);
                    break;
                case 2:
                    System.out.print("Enter Registration No to search: ");
                    search(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Registration No to delete: ");
                    delete(scanner.nextInt());
                    break;
                case 4:
                    inorder();
                    break;
                case 5:
                    preorder();
                    break;
                case 6:
                    postorder();
                    break;
                case 7:
                    findMin();
                    break;
                case 8:
                    findMax();
                    break;
                case 9:
                    if (root == null)
                        System.out.println("Height of empty tree = " + height());
                    else
                        System.out.println("Height of BST = " + height());
                    break;
                case 0:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        StudentBST bst = new StudentBST();
        bst.menu();
    }
}