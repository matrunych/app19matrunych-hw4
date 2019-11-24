package ua.edu.ucu.collections;

        import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {

    private ImmutableLinkedList linkList;

    public Queue() {
        linkList = new ImmutableLinkedList();
    }

    public Object peek() {
        return linkList.getFirst();
    }

    public void enqueue(Object e) {
        linkList = linkList.add(e);

    }

    public Object dequeue() {
        Object e = linkList.getFirst();
        linkList = linkList.removeFirst();
        return e;
    }


    public String toString() {
        return linkList.toString();
    }
    public int size() {
        return linkList.size();
    }


}