package jsjf.exceptions;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Testing MyStack
        System.out.println("What is the size of the stack?");
        int size = scanner.nextInt();
        MyStack stack = new MyStack(size); // Initializing a stack with the given size

        boolean exit = false;

        // Menu-driven loop for stack operations
        while (!exit) {
            System.out.println("============================");
            System.out.println("Choose from the menu:");
            System.out.println("1. Push.");
            System.out.println("2. Pop.");
            System.out.println("3. Peak.");
            System.out.println("4. Exit.");

            int choice = scanner.nextInt(); // Reading user's choice
            switch (choice) {
                case 1:
                    System.out.println("Enter an integer to push into the stack:");
                    int value = scanner.nextInt();
                    stack.push(String.valueOf(value)); // Pushing the entered value to the stack
                    System.out.println("Elements in the stack:");
                    System.out.println(stack);
                    break;
                case 2:
                    try {
                        String popped = stack.pop(); // Popping the top element from the stack
                        System.out.println(popped + " is removed from the stack.");
                        System.out.println("Elements in the stack:");
                        System.out.println(stack);
                    } catch (EmptyCollectionException e) {
                        System.out.println("Error: " + e.getMessage()); // Handling exception if stack is empty
                    }
                    break;
                case 3:
                    try {
                        String peeked = stack.peek(); // Peeking at the top element of the stack
                        System.out.println("Top element of the stack: " + peeked);
                    } catch (EmptyCollectionException e) {
                        System.out.println("Error: " + e.getMessage()); // Handling exception if stack is empty
                    }
                    break;
                case 4:
                    exit = true; // Exiting the loop
                    break;
                default:
                    System.out.println("Invalid choice."); // Handling invalid input
            }
        }

        // After exiting the loop, performing infix to postfix conversion and evaluation
        boolean continueCalculation = true;
        while (continueCalculation) {
            System.out.println("============================");
            System.out.println("Enter an infix expression:");
            String infixExpression = scanner.next();
            InfixToPostfix infixToPostfix = new InfixToPostfix(infixExpression); // Converting infix to postfix
            String postfixExpression = infixToPostfix.convert();
            System.out.println("The postfix form of the expression is:");
            System.out.println(postfixExpression);

            PostfixEvaluator postfixEvaluator = new PostfixEvaluator(postfixExpression); // Evaluating postfix expression
            int result = postfixEvaluator.evaluate();
            System.out.println("Result = " + result);

            System.out.println("Do you want to continue? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                continueCalculation = false;
            }
        }

        scanner.close(); // Closing the scanner object
    }
}
