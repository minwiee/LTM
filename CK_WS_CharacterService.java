package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMI.ObjectService;
import RMI.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import vn.medianews.CharacterService;
import vn.medianews.CharacterService_Service;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

public class CK_WS_CharacterService {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "fwNcXOVr";
        try{
            CharacterService_Service demo = new CharacterService_Service();
            CharacterService characterService  = demo.getCharacterServicePort();
            List<String> receivedStrings = characterService.requestStringArray(msv, qcode);

            Map<Integer, List<String>> groupedByVowels = receivedStrings.stream()
                    .collect(Collectors.groupingBy(CK_WS_CharacterService::cntVowels));

            List<String> result = new ArrayList<>();
            for (Map.Entry<Integer, List<String>> entry : groupedByVowels.entrySet()) {
                List<String> sortedWords = entry.getValue().stream()
                        .sorted() // Sắp xếp từ điển
                        .collect(Collectors.toList());
                result.add(String.join(", ", sortedWords));
            }

            // c. Gửi danh sách kết quả về server
            characterService.submitCharacterStringArray(msv, qcode, result);
        } catch (Exception e) {
        }
    }
    public static int cntVowels(String word){
        String wovels = "ueoaiUEOAI";
        int cnt = 0;
        for (char c : word.toCharArray()){
            if(wovels.indexOf(c)!= -1){
                cnt++;
            }
        }
        return cnt;
    }
}
