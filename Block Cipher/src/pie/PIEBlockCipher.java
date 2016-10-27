/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import vigenere.ExtendedVigenere;

/**
 *
 * @author pipin
 */
public class PIEBlockCipher {
    String key;
    String[] plaintextBlock;
    Random pseudoRandom;
    
    public PIEBlockCipher(String plaintext, String key) {
        this.key = key;
        this.plaintextBlock = parsePlaintext(plaintext);
        System.out.println(asciiToBits(plaintext));
        this.pseudoRandom = new Random(getSeed(key));
    }
    
    private int getSeed(String key) {
        int seed = 0;
        for (int i=0; i < key.length(); i++) {
            seed += (int)key.charAt(i);
        }
        return seed;
    }
    
    private String bitsToAscii(String bits) {
        //Generate ascii string based on binary sequence
        StringBuilder str = new StringBuilder(bits.length()/8);      
        for(int i=0; i<bits.length(); i++){
            int charCode = Integer.parseInt(bits.substring(i,i+8),2);
            str.append((char)charCode);
            i+=7;
        }
        
        return str.toString();
    }
    
    private String asciiToBits(String str) {
        // Generate bytes of char in string 
        int[] bytes = new int[str.length()];       
        for(int i=0; i< str.length(); i++) {
            bytes[i] = (int)str.charAt(i);
        }
        
        // Create string of binary sequence from bytes
        StringBuilder builder = new StringBuilder(str.length()*8);             
        for(int i=0; i< bytes.length; i++) {
            String bin = Integer.toBinaryString(bytes[i]);
            String format = String.format("%8s", bin).replace(' ', '0');
            builder.append(format);
        }
       
        return builder.toString();
    }
    
    public String[] parsePlaintext(String plaintext) {
        String[] result;
        StringBuilder builder = new StringBuilder(plaintext.length() * 8); 
        
        for (int i=0; i<plaintext.length(); i++) {
            int number = (int)plaintext.charAt(i);
            String bin = Integer.toBinaryString(number);
            String format = String.format("%8s", bin).replace(' ', '0');
            builder.append(format);
        }
        
        int blockSize = this.key.length() * 8;
        result = builder.toString().split("(?<=\\G.{" + blockSize + "})");
        return result;
    }
    
    // Permutate ascii string
    public String permutate(String text) {
        StringBuilder result = new StringBuilder(text.length());
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0; i<text.length(); i++){
            list.add(i);
        }
        for(int i=0; i<text.length(); i++){
           int random = pseudoRandom.nextInt(Integer.MAX_VALUE) % list.size();
           result.append(text.charAt(list.get(random)));
           list.remove(random);
        }
        
        return result.toString();
    }
    
    public String transpose(String bits, boolean right) {
        String result = "";
        int col = (int) Math.floor(Math.sqrt(bits.length()));
        if (col%4 != 0) {
            col=4;
        }
        int row = bits.length()/col;
        char[][] matrix = new char[row][col];
        int idx = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                matrix[i][j] = bits.charAt(idx);
                idx++;
            }
        }     
        if (right) {
            for (int j=col-1; j>=0; j--) {
                for (int i=0; i<row; i++) {
                    result += matrix[i][j];
                }
            }
        } else {
            for (int j=0; j<col; j++) {
                for (int i=row-1; i>=0; i--) {
                    result += matrix[i][j];
                }
            }
        }    
        return result;
    }
    
    public String xor(String text, String key) {
        String result = "";
        
        for (int i=0; i<text.length(); i++) {
            char temp = '0';
            if (text.charAt(i) == '1') {
                if (key.charAt(i) == '1') {
                    temp = '0';
                } else if (key.charAt(i) == '0'){
                    temp = '1';
                }
            } else if (text.charAt(i) == '0') {
                if (key.charAt(i) == '1') {
                    temp = '1';
                } else if (key.charAt(i) == '0'){
                    temp = '0';
                }
            }
            result += temp;
        }
        
        return result;
    }
    
    public String encrypt() {
        for (int count=0; count<5; count++) {
            String[] splittedKeys = splitString(permutate(asciiToBits(key)));
            for (int i=0; i<plaintextBlock.length; i++) {
                String temp = new String();
                String[] splittedBlocks = splitString(permutate(plaintextBlock[i]));
                splittedBlocks[1] = xor(splittedBlocks[1], splittedKeys[0]);
                if (i%2 == 0) {
                    temp += transpose(splittedBlocks[1], false);
                } else {
                    temp += transpose(splittedBlocks[1], true);
                }
                temp += xor(splittedBlocks[0], temp);
                plaintextBlock[i] = temp;
            }
        }
        
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<plaintextBlock.length; i++) {
            builder.append(bitsToAscii(plaintextBlock[i]));
        }
        return builder.toString();
    }
    
    public String[] splitString(String text) {
        String [] result = new String[2];
        result[0] = text.substring(0, text.length()/2);
        result[1] = text.substring(text.length()/2);
        return result;
    }
}
