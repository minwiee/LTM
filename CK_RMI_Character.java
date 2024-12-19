package THLTMHUNG;

import java.rmi.registry.LocateRegistry;

import RMI.CharacterService;


public class CK_RMI_Character {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "1Fy0GG0L";
        try {
            CharacterService characterService = (CharacterService) LocateRegistry.getRegistry("203.162.10.109").lookup("RMICharacterService");
            String request = characterService.requestCharacter(msv, qcode);
            // String request = "fdshd;jnmonzzumirvkeghmzwn";   
            String[] parts = request.split(";");
            System.out.println("Received data: " + parts[0].toUpperCase());
            System.out.println("Received data: " + parts[1].toUpperCase());
            
            String result = encrypt(parts[0], parts[1]);
            characterService.submitCharacter(msv, qcode, result);
            
        } catch (Exception e) {
        } 
    }
    public static String encrypt(String key, String data) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        data = data.toUpperCase();

        for(int i = 0; i < data.length(); i++) {
            char input = data.charAt(i);
            char keyChar = key.charAt(i % key.length());
            char encryptedChar = (char) (((input + keyChar) - (2 * 'A')) % 26 + 'A');
            result.append(encryptedChar);
        }   
        return result.toString().toLowerCase();
    }
}

