package THLTMHUNG;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import RMI.ObjectService;
import RMI.Order;
import java.util.ArrayList;
import java.util.List;
import vn.medianews.DataService;
import vn.medianews.DataService_Service;

public class CK_WS_DataService {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "88EfBJea";
        try{
            DataService_Service dataService_service = new DataService_Service();
            DataService dataService = dataService_service.getDataServicePort();
            double res = dataService.getDataDouble(msv, qcode);
            int number = (int) res;
            System.out.println("number " + number);
            int cnt = 0;
            List<Integer> list = new ArrayList<>();
            for(int i = 1; i <= number; i++){
                if(number % i == 0){
                    cnt++;
                    list.add(i);
                }
            }
            List<Integer> result = new ArrayList<>();
            result.add(cnt);
            result.addAll(list);
            System.out.println("so luong: " + number + ":" + result.toString());
            dataService.submitDataIntArray(msv, qcode, result);
                
        } catch (Exception e) {
        }
    }
}
