package jsjf.exceptions;

import java.util.Stack;

public class InfixToPostfix {
    private String infixExpression; // The infix expression to be converted

    // Constructor to initialize the infix expression
    public InfixToPostfix(String infixExpression) {
        this.infixExpression = infixExpression;
    }

    // Method to convert infix expression to postfix
    public String convert() {
        StringBuilder postfix = new StringBuilder(); // String to store the postfix expression
        Stack<Character> stack = new Stack<>(); // Stack to help in conversion

        // Iterating through each character of the infix expression
        for (int i = 0; i < infixExpression.length(); i++) {
            char token = infixExpression.charAt(i);

            // If the character is a digit, append it to the postfix string
            if (Character.isDigit(token)) {
                StringBuilder operand = new StringBuilder();
                operand.append(token);
                // Continue appending digits if the next character is also a digit
                while (i + 1 < infixExpression.length() && Character.isDigit(infixExpression.charAt(i + 1))) {
                    operand.append(infixExpression.charAt(++i));
                }
                postfix.append(operand).append(" ");
            } 
            // If the character is an opening parenthesis, push it onto the stack
            else if (token == '(') {
                stack.push(token);
            } 
            // If the character is a closing parenthesis, pop from the stack until an opening parenthesis is found
            else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.pop(); // Discard '(' from stack
            } 
            // If the character is an operator, handle precedence and associativity
            else if (isOperator(token)) {
                // Pop operators from the stack and append to postfix until a lower precedence operator is encountered
                while (!stack.isEmpty() && precedence(token) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(token); // Push the current operator onto the stack
            }
        }

        // Append remaining operators from the stack to the postfix string
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim(); // Trim any extra spaces and return the postfix expression
    }

    // Method to check if a character is an operator
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // Method to determine the precedence of an operator
    private int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1; // Return -1 for any other character
    }
}