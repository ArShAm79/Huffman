package com.company;

import java.util.HashMap;
import java.util.PriorityQueue;

public class SetRoot {
    private HuffmanNode root;
    private final HashMap<Character, Integer> charCount;
    private final HashMap<Character, String> charDataCompression;
    private final HashMap<String, Character> charDataDecompression;

    public SetRoot(HuffmanNode root, HashMap<Character, Integer> charCount) {
        this.root = root;
        this.charCount = charCount;
        charDataCompression = new HashMap<>();
        charDataDecompression = new HashMap<>();

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
        Printer.printNode(root);
    }

    private void setCharDataCompression(HuffmanNode root, String value) {
        if (root != null && root.left == null && root.right == null) {
            charDataCompression.put(root.c, value);
            return;
        } else if (root == null)
            return;
        setCharDataCompression(root.left, value + "0");
        setCharDataCompression(root.right, value + "1");
    }

    private void setCharDataDecompression(HuffmanNode root, String value) {
        if (root != null && root.left == null && root.right == null) {
            charDataDecompression.put(value, root.c);
            return;
        } else if (root == null)
            return;
        setCharDataDecompression(root.left, value + "0");
        setCharDataDecompression(root.right, value + "1");
    }

    public HashMap<Character, String> getCharDataCompression() {
        setRoot();
        setCharDataCompression(root, "");
        return charDataCompression;
    }

    public HashMap<String, Character> getCharDataDecompression() {
        setRoot();
        setCharDataDecompression(root, "");
        return charDataDecompression;
    }
}
