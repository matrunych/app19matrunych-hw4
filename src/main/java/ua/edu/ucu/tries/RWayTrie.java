package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;


import java.util.ArrayList;
import java.util.Iterator;


public class RWayTrie implements Trie {
    private Node root;
    private int size;

    public RWayTrie() {
        this.root = new Node();
    }


    @Override
    public void add(Tuple t) {
        String word = t.getTerm();
        Integer wght = t.getWeight();
        root.put(root, word, wght, 0);
        size++;
    }

    @Override
    public boolean contains(String word) {
        if (root.get(root, word, 0).equals(null)) {
            return false;
        } else {
            return true;
        }


    }

    @Override
    public boolean delete(String word) {
        if (root.put(root, word, 0, 0).getVal().equals(0)) {
            return true;
        } else {
            return false;
        }

    }


    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue qu = new Queue();
        ArrayList<String> result = new ArrayList<>();
        Node nod = root.get(root, s, 0);
        root.wordWithPref(nod, s, qu);
        int size = qu.size();
        for (int i = 0; i < size; i++) {
            result.add((String) qu.dequeue());
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

}
