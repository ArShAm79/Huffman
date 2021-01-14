package com.company;

import java.util.HashMap;

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
}
