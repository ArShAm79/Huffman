package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Huffman huffman=new Huffman("txt.cmp");
        huffman.function();

    }
}
