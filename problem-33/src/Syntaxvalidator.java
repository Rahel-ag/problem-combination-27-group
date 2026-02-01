public class SyntaxValidator {

    // Token stores a bracket character and its index in the string
    static class Token {
        char ch;
        int index;

        Token(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }

    // Custom Stack for Tokens
    static class Stack {
        private Token[] data;
        private int top;

        Stack(int capacity) {
            data = new Token[capacity];
            top = -1;
        }

        boolean isEmpty() {
            return top == -1;
        }

        void push(Token token) {
            data[++top] = token;
        }

        Token pop() {
            return data[top--];
        }
    }

    // Validate method — checks if brackets are balanced
    public static void validate(String code) {
        Stack stack = new Stack(code.length());

        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(new Token(ch, i));
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    System.out.println("Error: Unexpected " + ch + " at index " + i + ".");
                    return;
                }

                Token top = stack.pop();
                char expected = matching(top.ch);

                if (ch != expected) {
                    System.out.println("Error: Expected " + expected + ", found " + ch + " at index " + i + ".");
                    return;
                }
            }
        }

        if (!stack.isEmpty()) {
            Token unclosed = stack.pop();
            System.out.println("Error: Unclosed " + unclosed.ch + " at index " + unclosed.index + ".");
        } else {
            System.out.println("Valid.");
        }
    }

    private static char matching(char open) {
        switch (open) {
            case '(': return ')';
            case '[': return ']';
            case '{': return '}';
            default: return '?';
        }
    }
}
