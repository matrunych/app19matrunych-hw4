package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;

public class Node {
    private static int R = 256; // radix
    private Node root;          // root of trie
    private Integer val;
    private Node[] next = new Node[R];

    public Node get(Node x, String key, int d) {  // Return value associated with key in the subtrie rooted at x.
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d + 1);
    }

    public Node put(Node x, String key, Integer val, int d) {  // Change value associated with key if in subtrie rooted at x.
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Integer getVal() {
        return val;
    }


    public void wordWithPref(Node x, String pref, Queue q) {
        if (x == null) return;
        if (x.val != null) q.enqueue(pref);
        for (char c = 0; c < R; c++)
            wordWithPref(x.next[c], pref + c, q);
    }

}
