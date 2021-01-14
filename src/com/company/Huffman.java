package com.company;

public class Huffman {
    private final String address;
    private final boolean compression;

    public Huffman(String address) {
        this.address = address;
        compression = address.endsWith("txt");
    }
}
