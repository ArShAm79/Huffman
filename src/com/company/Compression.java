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

}
