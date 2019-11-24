package ua.edu.ucu.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {
    private final Node head;
    private int size;

    public ImmutableLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Node e, int size) {
        this.head = e;
        this.size = size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    private void checkIndexLast(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public Node cloneLinkedList() {
        if (head == null) {
            return null;
        }
        Node currNode = head;
        Node clonHead = new Node(currNode.getData());
        Node currNodeClon = clonHead;
        while (currNode.next != null) {
            currNode = currNode.next;
            currNodeClon.next = currNode.clone();
            currNodeClon = currNodeClon.next;
        }
        return clonHead;
    }


    @Override
    public ImmutableLinkedList add(Object e) {
        if (size == 0) {
            Node currNode = new Node(e);
            size++;
            return new ImmutableLinkedList(currNode, size);
        }
        Node currHead = cloneLinkedList();
        Node currNode = currHead;
        while (currNode.next != null) {
            currNode = currNode.next;
        }
        currNode.next = new Node(e);
        size++;
        return new ImmutableLinkedList(currHead, size);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {
        checkIndex(index);
        Node clonedLst = cloneLinkedList();
        Node clonedList1 = clonedLst;
        Node clonedList2 = cloneLinkedList();

        if (index == 0) {
            Node node = new Node(e);
            Node newHead = node;
            newHead.next = clonedLst;
            newHead = newHead.next;

            while (clonedLst.next != null) {
                clonedLst = clonedLst.next;
                newHead.next = clonedLst;
                newHead = newHead.next;
            }
            size++;
            return new ImmutableLinkedList(node, size);
        }

        int i = 0;
        while (i < size) {
            if (i == index - 1) {
                clonedList1.next = new Node(e);
                clonedList1 = clonedList1.next;
            } else if (i < index - 1) {
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            } else {
                clonedList1.next = clonedList2.next;
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            }
            i++;
        }
        size++;
        return new ImmutableLinkedList(clonedLst, size);

    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        Node cloneLst = cloneLinkedList();
        Node cloneList1 = cloneLst;
        while (cloneList1.next != null) {
            cloneList1 = cloneList1.next;
        }
        for (int i = 0; i < c.length; i++) {
            cloneList1.next = new Node(c[i]);
            cloneList1 = cloneList1.next;
        }
        size++;
        return new ImmutableLinkedList(cloneLst, size);
    }


    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkIndex(index);
        Node clonedLst = cloneLinkedList();
        Node clonedList1 = clonedLst;
        Node clonedList2 = cloneLinkedList();
        if (index == 0) {
            Node nHead = new Node(c[0]);
            Node newHead = nHead;

            for (int i = 0; i < c.length; i++) {
                newHead.next = new Node(c[i]);
                newHead = newHead.next;
            }
            while (clonedList1.next != null) {
                newHead.next = clonedList1;
                clonedList1 = clonedList1.next;
                newHead = newHead.next;
            }
            size += c.length;
            return new ImmutableLinkedList(nHead, size);
        }
        int j = 0;
        while (j < size) {
            if (j < index - 1) {
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            } else if (j == index - 1) {
                for (int k = 0; k < c.length; k++) {
                    clonedList1.next = new Node(c[k]);
                    clonedList1 = clonedList1.next;
                }

            } else {
                clonedList1.next = clonedList2.next;
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            }
            j++;
        }
        size += c.length;
        return new ImmutableLinkedList(clonedLst, size);
    }


    @Override
    public Object get(int index) {
        checkIndexLast(index);
        Node cloneList1 = cloneLinkedList();
        int i = 0;
        while (i != index) {
            cloneList1 = cloneList1.next;
            i++;
        }
        return cloneList1.getData();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        checkIndexLast(index);
        Node clonedLst = cloneLinkedList();
        Node clonedList1 = clonedLst;
        Node clonedList2 = cloneLinkedList();
        if (index == 0) {
            Node nHead = clonedLst.next;
            size--;
            return new ImmutableLinkedList(nHead, size);
        }
        int i = 0;
        while (i < size) {
            if (i < index - 1) {
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            } else if (i == index - 1) {
                clonedList1.next = clonedList2;
                clonedList2 = clonedList2.next;
            } else {
                clonedList1.next = clonedList2.next;
                clonedList1 = clonedList1.next;
                clonedList2 = clonedList2.next;
            }
            i++;

        }
        size--;
        return new ImmutableLinkedList(clonedLst, size);
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        checkIndexLast(index);
        Node clonedLst = cloneLinkedList();
        Node clonedList1 = clonedLst;
        int i = 0;
        while (clonedList1 != null) {
            if (index == i) {
                ImmutableLinkedList lstWithoutEl = this.remove(i);
                ImmutableLinkedList lstWithEll = lstWithoutEl.add(i, e);
                Node newLst = lstWithEll.cloneLinkedList();
                return new ImmutableLinkedList(newLst, size);
            }
            clonedList1 = clonedList1.next;
            i++;
        }
        return new ImmutableLinkedList(clonedLst, size);

    }

    @Override
    public int indexOf(Object e) {
        Node clonedLst = cloneLinkedList();
        int i = 0;
        while (i != size) {
            if (clonedLst.getData().equals(e)) {
                return i;
            }
            clonedLst = clonedLst.next;
            i++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableLinkedList clear() {
        Node clonedLst = cloneLinkedList();
        int i = 0;
        while (i < size) {
            clonedLst = null;
            i++;
        }
        size = 0;
        return new ImmutableLinkedList(clonedLst, size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Node clonedLst = cloneLinkedList();
        Object[] arr = new Object[size];
        int i = 0;
        while (i != size) {
            arr[i] = clonedLst.getData();
            clonedLst = clonedLst.next;
            i++;
        }
        return arr;
    }

    @Override
    public String toString() {
        String arrStr = "";
        if (size == 0) {
            return arrStr;
        }
        Node beginning = head;
        while (beginning != null) {
            arrStr += beginning.getData().toString();
            arrStr += " ";
            beginning = beginning.next;
        }
        return arrStr;
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) add(0, e);

    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public ImmutableLinkedList removeFirst() {
        Node clonedLst = cloneLinkedList();
        Node newHead = clonedLst.next;
        size--;
        return new ImmutableLinkedList(newHead, size);

    }

    public ImmutableLinkedList removeLast() {
        Node clonedLst = cloneLinkedList();
        Node newHead = clonedLst;
        while (newHead.next.next != null) {
            newHead = newHead.next;
        }
        size--;
        newHead.next = null;
        return new ImmutableLinkedList(clonedLst, size);
    }

    public Object getFirst() {
        return this.get(0);
    }

    public Object getLast() {
        return this.get(size - 1);
    }

}
