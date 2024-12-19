package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


import RMI.ByteService;
import RMI.CharacterService;
import RMI.DataService;
public class CK_RMI_Character_JSON {
    public static void main(String[] args) {
        String msv = "B21DCCN271";
        String qcode = "47MUgaCq";
        
        try {
            // Connect to RMI server
            CharacterService characterService = (CharacterService) LocateRegistry.getRegistry("203.162.10.109")
                                                                .lookup("RMICharacterService");
            
            // Request data from server
            String response = characterService.requestCharacter(msv, qcode);
            System.out.println("Received: " + response);

            String result = process(response);
            characterService.submitCharacter(msv, qcode, result);
            
            // Parse the response            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    public static String process(String data){
        data = data.substring(1, data.length()-1);
        StringBuilder oddBuilder = new StringBuilder();
        StringBuilder evenBuilder = new StringBuilder();
        System.out.println(data);
        String[] parts = data.split(",");
        for(int i = 0; i < parts.length; i++){
            String part = parts[i].trim().replace("\"","");
            if(i % 2 == 0){
                if(evenBuilder.length() > 0){
                    evenBuilder.append(", ");
                }
                evenBuilder.append(part);
            } else {
                if(oddBuilder.length() > 0){
                    oddBuilder.append(", ");
                }
                oddBuilder.append(part);
            }
        }
        return evenBuilder.toString() + "; " + oddBuilder.toString();
    }
}
// // MMMDCCCCLXXXXII
// MMMCMXCII 