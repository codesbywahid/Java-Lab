public class infixtoPostfix {

    static class Stack {
        char[] arr;
        int top;
        int size;

        Stack(int size) {
            this.size = size;
            arr = new char[size];
            top = -1;
        }

        void push(char value) {
            if (top == size - 1) {
                System.out.println("Stack Overflow");
                return;
            }

            arr[++top] = value;
        }

        char pop() {
            if (top == -1) {
                return '\0';
            }

            return arr[top--];
        }

        char peek() {
            if (top == -1) {
                return '\0';
            }

            return arr[top];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    static int precedence(char op) {
        if (op == '^') {
            return 3;
        }

        if (op == '*' || op == '/') {
            return 2;
        }

        if (op == '+' || op == '-') {
            return 1;
        }

        return 0;
    }

    static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' ||
               ch == '*' || ch == '/' || ch == '^';
    }

    static String convertToPostfix(String infix) {
        Stack stack = new Stack(infix.length());
        char[] postfix = new char[infix.length()];
        int k = 0;

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (ch == ' ') {
                continue;
            }

            if ((ch >= 'A' && ch <= 'Z') ||
                (ch >= 'a' && ch <= 'z') ||
                (ch >= '0' && ch <= '9')) {

                postfix[k++] = ch;
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix[k++] = stack.pop();
                }

                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() &&
                       stack.peek() != '(' &&
                       (precedence(stack.peek()) > precedence(ch) ||
                        (precedence(stack.peek()) == precedence(ch)
                         && ch != '^'))) {

                    postfix[k++] = stack.pop();
                }

                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            postfix[k++] = stack.pop();
        }

        return new String(postfix, 0, k);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C-D/E";
        String postfix = convertToPostfix(infix);

        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
    }
}
