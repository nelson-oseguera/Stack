package jsjf.exceptions;

public class MyStack {
    private String[] array; // Array to hold the elements of the stack
    private int top; // Index of the top element in the stack
    private int size; // Maximum capacity of the stack

    // Constructor to initialize the stack with a given size
    public MyStack(int size) {
        this.size = size;
        array = new String[size]; // Creating an array with the specified size
        top = -1; // Initializing top to -1 indicating an empty stack
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Method to check if the stack is full
    public boolean isFull() {
        return top == size - 1;
    }

    // Method to push an element onto the stack
    public void push(String value) {
        // If the stack is full, remove the first element to make space
        if (isFull()) {
            System.out.println("Stack is full. Removing the first element.");
            for (int i = 0; i < size - 1; i++) {
                array[i] = array[i + 1]; // Shift all elements to the left
            }
            top--; // Decrement top to reflect the removal of the first element
        }
        array[++top] = value; // Increment top and insert the new element
    }

    // Method to pop the top element from the stack
    public String pop() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty");
        }
        return array[top--]; // Return the top element and decrement top
    }

    // Method to peek at the top element of the stack without removing it
    public String peek() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Stack is empty");
        }
        return array[top]; // Return the top element
    }

    // Method to represent the stack as a string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i <= top; i++) {
            sb.append(array[i]); // Append each element to the string
            if (i < top) {
                sb.append(" "); // Add a space if it's not the last element
            }
        }
        sb.append("]");
        return sb.toString(); // Return the string representation of the stack
    }
}

// Custom exception class for indicating that the collection is empty
class EmptyCollectionException extends RuntimeException {
    public EmptyCollectionException(String message) {
        super(message);
    }
}
