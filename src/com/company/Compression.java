package com.company;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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
    //Constructor

    public Compression(String words) {
        this.words = words;
        charData = new HashMap<>();
        charCount = new HashMap<>();
    }

    //Count  every char
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

    //Build tree
    private void setRoot() {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(charCount.size(), new ImplementComparator());
        for (char c : charCount.keySet()) {
            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.c = c;
            huffmanNode.item = charCount.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;
            queue.add(huffmanNode);
        }
        root = null;
        while (queue.size() > 1) {
            HuffmanNode left = queue.peek();
            queue.poll();
            HuffmanNode right = queue.peek();
            queue.poll();
            HuffmanNode huffmanNode = new HuffmanNode();
            assert right != null;
            huffmanNode.item = left.item + right.item;
            huffmanNode.c = '-';
            huffmanNode.left = left;
            huffmanNode.right = right;
            root = huffmanNode;
            queue.add(huffmanNode);
        }
    }

    //Build binary code for chars
    private void setCharData(HuffmanNode root, String value) {
        if (root != null && root.left == null && root.right == null) {
            charData.put(root.c, value);
            return;
        } else if (root == null)
            return;
        setCharData(root.left, value + "0");
        setCharData(root.right, value + "1");
    }
    //Convert binary code to char
    private char stringToChar(String str) {
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            int num = 8 - i - 1;
            if (str.charAt(i) == '1')
                ans += Math.pow(2, num);
        }
        return (char) ans;
    }
    //Convert StringData to binaryString
    private String stringToLargeString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < words.length(); i++) {
            ans.append(charData.get(words.charAt(i)));
        }
        return ans.toString();
    }
}
