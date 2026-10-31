public class doubleList {
    static class Node {
        int data;
        Node previous;
        Node next;

        Node(int data) {
            this.data = data;
            this.previous = null;
            this.next = null;
        }
    }
    static Node head = null;
    static void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("Null");
    }

    //Insert at Beginning 
    static void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        if (head != null) {
            head.previous = newNode;
        }
        newNode.next = head;
        head = newNode;
    }

    // Insert at End
    static void insertAtEnd(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.previous = current;
    }

    // Insert at Specific Position
    static void insertAtSpecificPosition(int value, int position) {
        if (position < 1) {
            System.out.println("Invalid Position");
            return;
        }
        if (position == 1) {
            insertAtBeginning(value);
            return;
        }
        Node newNode = new Node(value);
        Node current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        } 
        if (current == null) {
            System.out.println("Invalid Position");
            return;
        }
        newNode.next = current.next;
        newNode.previous = current;
        if (current.next != null) {
            current.next.previous = newNode;
        }
        current.next = newNode;
    }

    // Search at Specific Position
    static void searchAtSpecificPosition(int position) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (position < 1) {
            System.out.println("Invalid Position");
            return;
        }
        Node current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Invalid Position");
            return;
        }
        System.out.println(
            "Value at position " + position + " is: " + current.data
        );
    }

    // Update at Specific Position
    static void updateAtSpecificPosition(int position, int newValue) {

        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        if (position < 1) {
            System.out.println("Invalid Position");
            return;
        }
        Node current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Invalid Position");
            return;
        }
        current.data = newValue;
        System.out.println(
            "Value at position " + position +
            " updated to: " + newValue
        );
    }

    // Delete from Beginning
    static void deleteFromBeginning() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        head = head.next;
        if (head != null) {
            head.previous = null;
        }
    }
    static void deleteFromEnd() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.next == null) {
            head = null;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.previous.next = null;
    }
    
    //Delete at specific position
    static void deleteAtSpecificPosition(int position) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (position < 1) {
            System.out.println("Invalid Position");
            return;
        }
        if (position == 1) {
            deleteFromBeginning();
            return;
        }
        Node current = head;
        for (int i = 1; i < position && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Invalid Position");
            return;
        }
        if (current.next != null) {
            current.next.previous = current.previous;
        }
        if (current.previous != null) {
            current.previous.next = current.next;
        }
    }
    public static void main(String[] args) {
        insertAtBeginning(30);
        insertAtBeginning(20);
        insertAtBeginning(10);
        insertAtEnd(40);
        insertAtEnd(50);
        System.out.println("Original List:");
        display();
        insertAtSpecificPosition(25, 3);
        System.out.println("\nAfter inserting 25 at position 3:");
        display();
        System.out.println("\nSearching position 3:");
        searchAtSpecificPosition(3);
        System.out.println("\nUpdating position 3:");
        updateAtSpecificPosition(3, 35);
        System.out.println("After updating position 3:");
        display();
        deleteFromBeginning();
        System.out.println("\nAfter deleting from beginning:");
        display();
        deleteFromEnd();
        System.out.println("\nAfter deleting from end:");
        display();
        deleteAtSpecificPosition(3);
        System.out.println("\nAfter deleting position 3:");
        display();
    }
}