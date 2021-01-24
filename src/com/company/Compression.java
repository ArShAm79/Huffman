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
    private HashMap<Character, String> charData;
    private final HashMap<Character, Integer> charCount;
    private HuffmanNode root;

    //Constructor
    public Compression(String words) {
        this.words = words;
        charData = new HashMap<>();
        charCount = new HashMap<>();
        setCharCount();
        SetRoot setRoot=new SetRoot(root,charCount);
        charData=setRoot.getCharDataCompression();
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

    //Convert binaryString to charArray
    private String wordCompression() {
        String data = stringToLargeString();
        StringBuilder ans = new StringBuilder();
        while (!data.isEmpty()) {
            String temp;
            if (data.length() >= 8)
                temp = data.substring(0, 8);
            else
                temp = data;
            char c = stringToChar(temp);
            if (data.length() >= 8)
                data = data.substring(8);
            else
                data = "";
            ans.append(c);
        }
        return ans.toString();
    }

    //Write Data for file
    public String compress() {
        StringBuilder ans = new StringBuilder();
        ans.append(charCount.size());
        ans.append("\n");
        ans.append(stringToLargeString().length());
        ans.append("\n");
        for (var data : charCount.keySet()) {
            ans.append(data.toString());
            ans.append(" ");
            ans.append(charCount.get(data));
            ans.append("\n");
        }
        ans.append(wordCompression());
        return ans.toString();
    }

}
