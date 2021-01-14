package com.company;

import java.util.Comparator;
import java.util.HashMap;

class ImplementComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.item - y.item;
    }
}

public class Compression {
    private final String words;
    private final HashMap<Character, String> charData;
    private final HashMap<Character, Integer> charCount;
    private HuffmanNode root;

    public Compression(String words) {
        this.words = words;
        charData = new HashMap<>();
        charCount = new HashMap<>();
    }

    private void setCharCount() {
        String temp = words;
        while (!temp.isEmpty()) {
            int size;
            char c = temp.charAt(0);
            size = temp.length() - temp.replace(c + "", "").length();
            charCount.put(c, size);
            temp = temp.replace(c + "", "");
        }
    }

}
