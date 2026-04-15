import java.util.*;

public class InfixConverter {

    // Function to check precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Infix to Postfix
    static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }

            // If '(', push
            else if (c == '(') {
                stack.push(c);
            }

            // If ')', pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            }

            // Operator
            else {
                while (!stack.isEmpty() &&
                        precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Reverse string
    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }

    // Swap brackets
    static String swapBrackets(String exp) {
        char[] chars = exp.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') chars[i] = ')';
            else if (chars[i] == ')') chars[i] = '(';
        }

        return String.valueOf(chars);
    }

    // Infix to Prefix
    static String infixToPrefix(String exp) {
        exp = reverse(exp);
        exp = swapBrackets(exp);
        String postfix = infixToPostfix(exp);
        return reverse(postfix);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Infix to Postfix");
            System.out.println("2. Infix to Prefix");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1 || choice == 2) {
                System.out.print("Enter Infix Expression: ");
                String infix = sc.nextLine();

                if (choice == 1) {
                    System.out.println("Postfix: " + infixToPostfix(infix));
                } else {
                    System.out.println("Prefix: " + infixToPrefix(infix));
                }
            }

        } while (choice != 3);

        System.out.println("Program exited.");
        sc.close();
    }
}