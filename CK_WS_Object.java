package THLTMHUNG;

import RMI.ByteService;
import javax.xml.registry.RegistryService;
import java.rmi.*;


public class CK_WS_Object {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "c9hE1uXb";
        try{
            ByteService byteService = (ByteService) LocateRegistry.getRegistry
            
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
