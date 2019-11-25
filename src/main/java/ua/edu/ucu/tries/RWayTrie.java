package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;

import java.util.ArrayList;

public class RWayTrie implements Trie {
    private static int R = 256;

    private Node root;
    private int size;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    public RWayTrie() {
        this.root = new Node();
    }

    private Node get(Node x, String key, int d) {  // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d + 1);
    }


    private Node put(Node x, String key, Integer val, int d) {  // Change value associated with key if in subtrie rooted at x.
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length())
            x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }


    private void wordWithPref(Node x, String pref, Queue q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pref);
        for (char c = 0; c < R; c++)
            wordWithPref(x.next[c], pref + c, q);
    }


    @Override
    public void add(Tuple t) {
        String word = t.getTerm();
        Integer wght = t.getWeight();
        put(root, word, wght, 0);
        size++;
    }

    @Override
    public boolean contains(String word) {
        if (get(root, word, 0).equals(null)) {
            return false;
        } else {
            return true;
        }


    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);
        if (root == null) {
            return false;
        }
        size--;
        return true;
    }


    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue qu = new Queue();
        ArrayList<String> result = new ArrayList<>();
        Node nod = get(root, s, 0);
        wordWithPref(nod, s, qu);
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
