package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Decompression {
    private String data;
    private final String address;
    private final HashMap<String, Character> charData;
    private double bytes;

    public Decompression(String address) {
        this.address = address;
        charData = new HashMap<>();
        readData();

    }

    //get data from address
    private void readData() {
        data = "";
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(address));
            double chars = Double.parseDouble(bufferedReader.readLine());
            bytes = Double.parseDouble(bufferedReader.readLine());
            for (int i = 0; i < chars; i++) {
                String tempData = bufferedReader.readLine();
                if (tempData.isEmpty())
                    tempData = "\n" + bufferedReader.readLine();
                char c = tempData.charAt(0);
                String code = tempData.substring(2);
                charData.put(code, c);
            }
            while (bufferedReader.ready()) {
                stringBuilder.append(bufferedReader.readLine()).append("\n");
            }
            data = stringBuilder.toString();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    //Convert byte to binary String
    private String charToString(char c) {
        int num = c;
        StringBuilder ans = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            if (Math.pow(2, i) <= num) {
                num -= Math.pow(2, i);
                ans.append('1');
            } else
                ans.append('0');
        }
        return ans.toString();
    }

    //Convert all bytes to binary String
    private String convertToLargeString() {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            ans.append(charToString(data.charAt(i)));
        }
        return ans.toString();
    }

    //Convert binary code to String
    public String getData() {
        String largeData = convertToLargeString();
        StringBuilder ans = new StringBuilder();
        String temp = "";
        int i = 0;
        while (!largeData.isEmpty() && bytes != 0) {
            bytes--;
            temp += largeData.charAt(i);
            i++;
            if (charData.containsKey(temp)) {
                ans.append(charData.get(temp));
                temp = "";
                largeData = largeData.substring(i);
                i = 0;
            }

        }
        return ans.toString();
    }
}
