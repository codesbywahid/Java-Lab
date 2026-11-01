public class StackUsingArray {
    int[] stack;
    int top;
    int capacity;
 
    // Constructor
    StackUsingArray(int capacity) {
        this.capacity = capacity;
        stack = new int[capacity];
        top = -1;
    }

    // Push: insert an element
    void push(int value) {
        if (top == capacity - 1) {
            System.out.println("Stack Overflow!");
            return;
        }

        top++;
        stack[top] = value;
        System.out.println(value + " pushed");
    }

    // Pop: remove the top element
    int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }

        int value = stack[top];
        top--;
        return value;
    }

    // Peek: view the top element
    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }

        return stack[top];
    }

    // Check whether the stack is empty
    boolean isEmpty() {
        return top == -1;
    }

    // Display all elements from top to bottom
    void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public static void main(String[] args) {
        StackUsingArray s = new StackUsingArray(5);

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