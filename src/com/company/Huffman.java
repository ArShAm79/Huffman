package com.company;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Huffman {
    private final String address;
    private final boolean compression;

    //Constructor
    public Huffman(String address) {
        this.address = address;
        compression = address.endsWith("txt.txt");
    }
    // Read and run Class Compression
    private void compression() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(address));
            StringBuilder data = new StringBuilder();
            while (bufferedReader.ready()) {
                data.append(bufferedReader.readLine());
                data.append("\n");
            }
            bufferedReader.close();
            Compression compress = new Compression(data.toString());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(address.substring(0, address.length() - 3) + "cmp"));
            bufferedWriter.write(compress.compress());
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Read and run Class Decompression
    private void decompression() {
        try {
            Decompression decompression = new Decompression(address);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(address.substring(0, address.length() - 3) + "txt.txt"));
            bufferedWriter.write(decompression.getData());
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Main function
    public void function() {
        if (compression)
            compression();
        else
            decompression();
    }
}
