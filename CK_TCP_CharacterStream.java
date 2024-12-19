package THLTMHUNG;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class CK_TCP_CharacterStream {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "URbhxQt4";
        String message = msv + ";" + qcode;
        
        try (Socket socket = new Socket("203.162.10.109", 2208)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println("Send message success");

            String response =  reader.readLine();
            // String response = "C/+/i|6J@|ebN%/d651n]{TR/bHt*o52eNf(=Q|5BDxvfg6(166v?zbZpUpQ%~Oa}LieFq2y6@*G=Ftm{q+";
            System.out.println(response);
            StringBuilder result1 = new StringBuilder();
            StringBuilder result2 = new StringBuilder();
            for (char c: response.toCharArray()){
                if(Character.isLetterOrDigit(c)){
                    result1.append(c);
                } else {
                    result2.append(c);
                }
            }
            System.out.println("1: " + result1);
            System.out.println("2: " + result2);
            writer.write(result1.toString());
            writer.newLine();
            writer.flush();
            writer.write(result2.toString());
            writer.newLine();
            writer.flush();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

