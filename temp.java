package THLTMHUNG;

import vn.medianews.DataService_Service;
import vn.medianews.DataService;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class temp {

    public static void main(String[] args) {
        String msv = "B21DCCN271";
        String qcode = "sxKS5jtL";

        try {
            DataService_Service demo = new DataService_Service();
            DataService dataService = demo.getDataServicePort();
            double n = dataService.getDataDouble(msv, qcode);
            System.out.println(n);
            int tu = (int) (n * 100);
            int mau = 100;
            int gcd = gcd(mau, tu);
            mau /= gcd;
            tu /= gcd;
            List<Integer> list = new ArrayList<>();
            list.add(tu);
            list.add(mau);
            dataService.submitDataIntArray(msv, qcode, list);
            
        } catch (Exception e) {
        }
        // Connect to RMI server

    }
    public static int gcd(int a, int b){
        while(b!=0){
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
}
// // MMMDCCCCLXXXXII
// MMMCMXCII 
