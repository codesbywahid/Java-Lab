public class CircularLL {
    class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    Node last = null;

    void inserAtBeginning(int data) {
        Node newNode = new Node(data);
        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
        }
    }

    void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (last == null) {
            last = newNode;
            last.next = last;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
    }

    void insertAtPosition(int data, int position) {
        if (position < 1) {
            System.out.println("Position should be >= 1.");
            return;
        }
        Node newNode = new Node(data);
        if (last == null) {
            if (position == 1) {
                last = newNode;
                last.next = last;
            } else {
                System.out.println("List is empty. Cannot insert at position " + position);
            }
            return;
        }
        if (position == 1) {
            newNode.next = last.next;
            last.next = newNode;
            return;
        }
        Node curr = last.next;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
            if (curr == last.next) {
                System.out.println("Position exceeds the length of the list.");
                return;
            }
        }
        newNode.next = curr.next;
        curr.next = newNode;
    }


    void deleteAtBeginning() {
        if (last == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }
        if (last.next == last) {
            last = null;
        } else {
            last.next = last.next.next;
        }
    }


    void deleteAtEnd() {
        if (last == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }
        if (last.next == last) {
            last = null;
        } else {
            Node curr = last.next;
            while (curr.next != last) {
                curr = curr.next;
            }
            curr.next = last.next;
            last = curr;
        }
    }


    void deleteAtPosition(int position) {
        if (last == null) {
            System.out.println("List is empty. Cannot delete.");
            return;
        }
        if (position < 1) {
            System.out.println("Position should be >= 1.");
            return;
        }
        if (position == 1) {
            deleteAtBeginning();
            return;
        }
        Node curr = last.next;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
            if (curr == last.next) {
                System.out.println("Position exceeds the length of the list.");
                return;
            }
        }
        if (curr.next == last.next) {
            System.out.println("Position exceeds the length of the list.");
            return;
        }
        curr.next = curr.next.next;
    }

    void display() {
        if (last == null) {
            System.out.println("List is empty.");
            return;
        }
        Node curr = last.next;
        do {
            System.out.print(curr.data + " ");
            curr = curr.next;
        } while (curr != last.next);
        System.out.println();
    }


    public static void main(String[] args) {
        CircularLL cll = new CircularLL();
        cll.insertAtEnd(10);
        cll.insertAtEnd(20);
        cll.inserAtBeginning(5);
        cll.insertAtPosition(15, 3);
        cll.display(); 
        cll.deleteAtPosition(2);
        cll.display(); 
        cll.deleteAtBeginning();
        cll.display(); 
        cll.deleteAtEnd();
        cll.display(); 
    }
}