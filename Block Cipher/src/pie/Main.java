/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author TOSHIBA PC
 */
public class Main {
    public static void main(String[] args) {
        // Get input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your key: ");
        String key = input.nextLine();
        
        while (key.length() < 8) {
            System.out.println("Key must be more than 8 characters!");
            System.out.print("Enter your key: ");
            key = input.nextLine();
        }
        
        System.out.print("Enter your plaintext: ");
        String plaintext = input.nextLine();
        
        PIEBlockCipher pie = new PIEBlockCipher(plaintext,key);
        //System.out.println(pie.xor("1101", "0100"));
        //System.out.println(pie.transpose("1100101000011111", true));
        //System.out.println(pie.transpose("1100101000011111", false));
        String result = pie.encrypt();
        System.out.println(result);
        
        // Save to file
        try {
            File file = new File("cipherteks.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                    file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(result);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
