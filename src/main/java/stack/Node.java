package stack;

/**
 * A {@link Node} is an element of a stack that is implemented
 * using a Linked List structure to allow for unbounded size.
 */
public class Node<T>
{
    // TONOTDO: please do not edit this class
    // ...well, you can change the variable names if you like
    public T element;
    public Node<T> next;

    public Node(T elem) {
        element = elem;
        next = null;
    }

    public T getElement()
    {
        return element;
    }

    public void setElement(T elem)
    {
        element = elem;
    }

    public Node<T> getNext()
    {
        return next;
    }

    public void setNext(Node<T> nextNode)
    {
        next = nextNode;
    }
}
