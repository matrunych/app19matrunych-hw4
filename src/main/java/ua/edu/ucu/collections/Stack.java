package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {

    private ImmutableLinkedList linkList;

    public Stack() {
        linkList = new ImmutableLinkedList();
    }

    public Object peek() {
        return linkList.getLast();
    }

    public void add(Object e) {
        linkList = linkList.add(e);

    }

    public Object pop() {
        Object e = linkList.getLast();
        linkList = linkList.removeLast();
        return e;
    }

    public String toString() {
        return linkList.toString();
    }

}