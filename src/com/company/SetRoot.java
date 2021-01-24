package com.company;

import java.util.HashMap;
import java.util.PriorityQueue;

public class SetRoot {
    private HuffmanNode root;
    private final HashMap<Character, Integer> charCount;
    private final HashMap<Character, String> charData;

    public SetRoot(HuffmanNode root, HashMap<Character, Integer> charCount) {
        this.root = root;
        this.charCount = charCount;
        charData = new HashMap<>();

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

    private void setCharData(HuffmanNode root, String value) {
        if (root != null && root.left == null && root.right == null) {
            charData.put(root.c, value);
            return;
        } else if (root == null)
            return;
        setCharData(root.left, value + "0");
        setCharData(root.right, value + "1");
    }

    public HashMap<Character, String> getCharData() {
        setRoot();
        setCharData(root, "");
        return charData;
    }

}
