package Utils;

public class DoublyLinkedList<E> {

    private Node<E> start;
    private Node<E> end;



    public void add(Node<E> n) {
        if (start == null) {
            start = end = n;
        } else {
            end.next = n;
            n.previous = end;
            end = end.next;

        }
    }

    public void delete(Node<E> n) {
        if (n == start && n == end) {
            start = end = null;
        } else if (start == n) {
            Node<E> next = start.next;
            next.previous = null;
            n.next = null;
            start = next;
        } else if (end == n) {
            Node<E> prev = end.previous;
            prev.next = null;
            n.previous = null;
            end = prev;
        } else {
            Node<E> prev = n.previous;
            Node<E> next = n.next;
            n.previous = null;
            n.next = null;
            prev.next = next;
            next .previous = prev;
        }
    }

    public Node<E> getStart() {
        return start;
    }

    public Node<E> getEnd() {
        return end;
    }
}






