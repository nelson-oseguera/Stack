package jsjf.exceptions;

import java.util.Stack;

public class PostfixEvaluator {
    private String postfixExpression; // The postfix expression to be evaluated

    // Constructor to initialize the postfix expression
    public PostfixEvaluator(String postfixExpression) {
        this.postfixExpression = postfixExpression;
    }

    // Method to evaluate the postfix expression and return the result
    public int evaluate() {
        Stack<Integer> stack = new Stack<>(); // Stack to hold operands during evaluation

        // Iterate through each character of the postfix expression
        for (int i = 0; i < postfixExpression.length(); i++) {
            char token = postfixExpression.charAt(i);

            // If the character is a digit, extract the operand and push it onto the stack
            if (Character.isDigit(token)) {
                StringBuilder operand = new StringBuilder();
                operand.append(token);
                // Continue extracting digits if the next character is also a digit
                while (i + 1 < postfixExpression.length() && Character.isDigit(postfixExpression.charAt(i + 1))) {
                    operand.append(postfixExpression.charAt(++i));
                }
                stack.push(Integer.parseInt(operand.toString())); // Push the operand onto the stack
            } 
            // If the character is an operator, perform the corresponding operation
            else if (isOperator(token)) {
                int operand2 = stack.pop(); // Pop the second operand from the stack
                int operand1 = stack.pop(); // Pop the first operand from the stack
                int result = performOperation(token, operand1, operand2); // Perform the operation
                stack.push(result); // Push the result back onto the stack
            }
        }

        return stack.pop(); // Return the final result
    }

    // Method to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Method to perform the specified operation on the given operands
    private int performOperation(char op, int operand1, int operand2) {
        switch (op) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Cannot divide by zero"); // Throw exception for division by zero
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op); // Throw exception for invalid operator
        }
    }
}