package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import RMI.CharacterService;
import RMI.DataService;


public class CK_RMI_Data {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "5Uhfhh5U";
        try {
            DataService dataService = (DataService) LocateRegistry.getRegistry("203.162.10.109").lookup("RMIDataService");
            int n = (int) dataService.requestData(msv, qcode);
            System.out.println(n);
            List<List<Integer>> result = findPythagoreanTriples(n);
            dataService.submitData(msv, qcode, result);
            System.out.println("submitted: " + result.toString());
            
        } catch (Exception e) {
        } 
    }
    public static List<List<Integer>> findPythagoreanTriples(int n){
        List<List<Integer>> triples = new ArrayList<>();
        for (int a = 1; a <= n; a++){
            for(int b = a + 1; b <= n; b++){
                for(int c = b + 1; c <= n; c++){
                    if(a*a + b*b == c*c){
                        List<Integer> triple = new ArrayList<>();
                        triple.add(a);
                        triple.add(b);
                        triple.add(c);
                        triples.add(triple);
                    }
                }
            }
        }
        return triples;
    }
}

