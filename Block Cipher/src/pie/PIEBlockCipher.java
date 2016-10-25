/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pie;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pipin
 */
public class PIEBlockCipher {
    String key;
    List<List<String>> plaintextBlock;
    
    public PIEBlockCipher(String plaintext, String key) {
        this.key =  convertKey(key);
        this.plaintextBlock = new ArrayList<List<String>>(parsePlaintext(plaintext));
    }
    
    public String convertKey(String key) {
        String result = new String();
        
        //proses
        
        return result;
    }
    
    public List<List<String>> parsePlaintext(String plaintext) {
        List<List<String>> result = new ArrayList<List<String>> ();
        
        //proses
        
        return result;
    }
}
