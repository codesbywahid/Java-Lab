public class PostFixEvaluation {

    static class Stack {
        int[] arr;
        int top;
        int size;

        Stack(int size) {
            this.size = size;
            arr = new int[size];
            top = -1;
        }

        void push(int value) {
            if (top == size - 1) {
                System.out.println("Stack Overflow");
                return;
            }

            arr[++top] = value;
        }

        int pop() {
            if (top == -1) {
                System.out.println("Stack Underflow");
                return -1;
            }

            return arr[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' ||
               ch == '*' || ch == '/' || ch == '%';
    }

    static int evaluate(String postfix) {
        Stack stack = new Stack(postfix.length());

        for (int i = 0; i < postfix.length(); i++) {
            char ch = postfix.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if (ch >= '0' && ch <= '9') {
                stack.push(ch - '0');
            } else if (isOperator(ch)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                switch (ch) {
                    case '+':
                        stack.push(operand1 + operand2);
                        break;

                    case '-':
                        stack.push(operand1 - operand2);
                        break;

                    case '*':
                        stack.push(operand1 * operand2);
                        break;

                    case '/':
                        stack.push(operand1 / operand2);
                        break;

                    case '%':
                        stack.push(operand1 % operand2);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        String postfix = "8 2 5 * + 1 3 2 * + -";

        int result = evaluate(postfix);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Result: " + result);
    }
}
