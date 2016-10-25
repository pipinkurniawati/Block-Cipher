/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pie;

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
        System.out.println(pie.encrypt());
    }
}
