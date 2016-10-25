/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    
    public String transpose(String text) {
        String result = "";
        
        // process
        
        return result;
    }
    
    public String encrypt() {
        String result = "";
        
        // process
        
        return result;
    }
}
