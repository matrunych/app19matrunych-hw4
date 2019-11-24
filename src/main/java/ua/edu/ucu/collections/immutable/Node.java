package ua.edu.ucu.collections.immutable;

public class Node implements Cloneable {
    private final Object data;
    public Node next = null;

    public Node(Object data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

//    public setData(Object data){
//        this.data = data;
//    }

    public Object getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    @Override
    public Node clone() {
        return new Node(this.data);
    }

    public String toString() {
        return getData().toString();
    }

}
