package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import RMI.ByteService;
import RMI.CharacterService;
import RMI.DataService;
public class CK_RMI_Data_Sinhnhiphan {
    public static void main(String[] args) {
        String msv = "B21DCCN181";
        String qcode = "zh1Tj417";
        
        try {
            // Connect to RMI server
            DataService dataService = (DataService) LocateRegistry.getRegistry("203.162.10.109")
                                                                .lookup("RMIDataService");
            
            // Request data from server
            String response = (String) dataService.requestData(msv, qcode);
            System.out.println("Received: " + response);
            
            // Parse the response
            String[] parts = response.split(";");
            String[] numbers = parts[0].trim().replace("[", "").replace("]", "").split(",");
            int k = Integer.parseInt(parts[1].trim());
            
            // Convert string array to int array
            int[] arr = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                arr[i] = Integer.parseInt(numbers[i].trim());
            }
            
            // Generate combinations
            List<List<Integer>> combinations = generateCombinations(arr, k);
            
            // Submit result back to server
            dataService.submitData(msv, qcode, combinations);
            
            System.out.println("Combinations submitted successfully");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static List<List<Integer>> generateCombinations(int[] arr, int k) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinationsHelper(arr, k, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinationsHelper(int[] arr, int k, int start, 
                                                 List<Integer> current, 
                                                 List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            generateCombinationsHelper(arr, k, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}
