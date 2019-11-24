package ua.edu.ucu.collections.immutable;

//import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {
    private final Object[] newArray;
    private int size = 0;

    public ImmutableArrayList() {
        this.newArray = new Object[0];
    }

    private ImmutableArrayList(Object[] newArray, int size) {
        this.newArray = newArray;
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

    @Override
    public ImmutableArrayList add(Object e) {
        Object[] newLst = new Object[size + 1];
        for (int i = 0; i < size(); i++) {
            newLst[i] = this.newArray[i];
        }
        newLst[size] = e;
        size++;
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        checkIndex(index);
        Object[] newLst = new Object[size() + 1];
        for (int i = 0; i < size + 1; i++) {
            if (i != index) {
                newLst[i] = newArray[i];
            } else {
                newLst[i] = e;
                int ind = index;
                while (ind < size) {
                    newLst[ind + 1] = newArray[ind];
                    ind++;
                }
                break;
            }
        }
        size++;
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] newLst = new Object[size() + c.length];
        for (int i = 0; i < size(); i++) {
            newLst[i] = newArray[i];
        }
        for (int j = 0; j < c.length; j++) {
            newLst[size()] = c[j];
            size++;
        }
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        checkIndex(index);
        Object[] newList = new Object[size() + c.length];
        int ind = index;
        for (int i = 0; i < index; i++) {
            newList[i] = newArray[i];
        }
        for (int j = 0; j < c.length; j++) {
            newList[ind] = c[j];
            ind++;
        }
        for (int k = index; k < size(); k++) {
            newList[index + c.length] = newArray[k];
            index++;
        }
        size += c.length;


        return new ImmutableArrayList(newList, size);

    }

    @Override
    public Object get(int index) {
        checkIndexLast(index);
        return newArray[index];
    }


    @Override
    public ImmutableList remove(int index) {
        checkIndexLast(index);
        Object[] newLst = new Object[size() - 1];
        for (int i = 0; i < size(); i++) {
            if (i < index) {
                newLst[i] = newArray[i];
            } else if (i > index) {
                newLst[i - 1] = newArray[i];
            }
        }
        size--;
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkIndexLast(index);
        Object[] newLst = new Object[size()];
        for (int i = 0; i < size(); i++) {
            if (i == index) {
                newLst[index] = e;
            } else {
                newLst[i] = newArray[i];
            }
        }
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < size(); i++) {
            if (e.equals(newArray[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableArrayList clear() {
        Object[] newLst = new Object[0];
        size = 0;
        return new ImmutableArrayList(newLst, size);
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] newLst = new Object[size];
        int i = 0;
        while (i != size) {
            newLst[i] = newArray[i];
            i++;
        }
        return newLst;
    }

    @Override
    public String toString() {
        String arrStr = "";
        if (size == 0) {
            return arrStr;
        } else {
            for (int i = 0; i < size(); i++) {
                arrStr += newArray[i];
                arrStr += " ";
            }
        }
        return arrStr;
    }

}