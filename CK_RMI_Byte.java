package THLTMHUNG;

import java.rmi.registry.LocateRegistry;

import RMI.ByteService;


public class CK_RMI_Byte {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "aJflXbb6";
        try {
//             Registry registry = LocateRegistry.getRegistry("203.162.10.109");
//             ByteService byteService = (ByteService) registry.lookup("RMIByteService");
            ByteService byteService = (ByteService) LocateRegistry.getRegistry("203.162.10.109").lookup("RMIByteService");
            byte[] receivedData = byteService.requestData(msv, qcode);
            System.out.println("Received data: " + new String(receivedData));

            String hexString = bytesToHex(receivedData);
            System.out.println("Hex string: " + hexString);

            byte[] send = hexString.getBytes();

            byteService.submitData(msv, qcode, send);
        } catch (Exception e) {
        } 
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // System.out.println("..." + b);
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}

