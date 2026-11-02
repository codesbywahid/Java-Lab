public class StackUsingLinkedList {
    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node top = null;

    // Push: insert at the beginning
    void push(int value) {
        Node newNode = new Node(value);

        newNode.next = top;
        top = newNode;

        System.out.println(value + " pushed");
    }

    // Pop: remove the first node
    int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }

        int value = top.data;
        top = top.next;

        return value;
    }

    // Peek: view the top element
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }

        return top.data;
    }

    // Check whether the stack is empty
    boolean isEmpty() {
        return top == null;
    }

    // Display all elements from top to bottom
    void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }

        Node current = top;

        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        StackUsingLinkedList s = new StackUsingLinkedList();

        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println("Stack elements:");
        s.display();

        System.out.println("Top element: " + s.peek());
        System.out.println("Popped: " + s.pop());

        System.out.println("Stack after pop:");
        s.display();
    }
}