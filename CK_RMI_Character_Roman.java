package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import RMI.ByteService;
import RMI.CharacterService;
import RMI.DataService;
public class CK_RMI_Character_Roman {
    public static void main(String[] args) {
        String msv = "B21DCCN181";
        String qcode = "PbU04awu";
        
        try {
            // Connect to RMI server
            CharacterService characterService = (CharacterService) LocateRegistry.getRegistry("203.162.10.109")
                                                                .lookup("RMICharacterService");
            
            // Request data from server
            String response = (String) characterService.requestCharacter(msv, qcode);
            System.out.println("Received: " + response);

            String result = toRoman((Integer.parseInt(response)));
            characterService.submitCharacter(msv, qcode, result);
            System.out.println("Submitted: " + result);
            
            // Parse the response            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static String toRoman(int n){
        int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < VALUES.length; i++){
            while(n >= VALUES[i]){
                n -= VALUES[i];
                sb.append(SYMBOLS[i]);
            }
        }
        return sb.toString();
    }

}
// // MMMDCCCCLXXXXII
// MMMCMXCII 