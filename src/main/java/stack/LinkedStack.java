package stack;

import stack.Node;
import stack.StackInterface;
import stack.StackUnderflowException;

public class LinkedStack<T> implements StackInterface<T> {
    Node<T> first;
    int size;

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot pop from empty stack");
        }
        T element = first.getElement();
        first = first.getNext();
        size--;
        return element;
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException("Cannot get top of empty stack");
        }
        return first.getElement();
    }

    @Override
    public void push(T elem) throws NullPointerException {
        if (elem == null) {
            throw new NullPointerException("Cannot push null");
        }
        Node<T> newNode = new Node<>(elem);
        newNode.setNext(first);
        first = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}