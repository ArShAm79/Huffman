package com.company;

import java.util.HashMap;

public class Decompression {
    private String data;
    private final String address;
    private final HashMap<String, Character> charData;
    private double bytes;

    public Decompression(String address) {
        this.address = address;
        charData = new HashMap<>();

    }
}
