package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.*;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {

        this.trie = trie;
    }

    public int load(String... strings) {
        int counter = 0;
        for(String element: strings){
            for(String word: element.split(" ")){
                if(word.length() > 2){
                    trie.add(new Tuple(word, word.length()));
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {

        Iterable<String> words = trie.wordsWithPrefix(pref);
        ArrayList<String> result = new ArrayList<>();
        for (String word: words) {
            if(k > word.length() - pref.length()){
                result.add(word);
            }
        }return result;
    }

    public int size() {
        return trie.size();
    }
}
