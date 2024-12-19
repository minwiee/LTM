package THLTMHUNG;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ming
 */
public class TenMienDung {
    public static void main(String[] args) {
        String serverAdress = "203.162.10.109";
        int port = 2208;
        String studenCode = "B21DCCN519";
        String qCode = "kTOWS75G";
        try (Socket socket = new Socket(serverAdress, port)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            //gui ma
            String mess = studenCode + ";" + qCode;
            writer.write(mess);
            writer.newLine();
            writer.flush();
            System.out.println("gui ma thanh cong");
            
            //nhan chuoi
            String receivedList = reader.readLine();
            System.out.println("danh sach ten mien: " + receivedList);
            
            //xu ly
            String[] domains = receivedList.split(", ");
            List<String> eduDomains = new ArrayList<>();
            for(String domain : domains){
                if(domain.endsWith(".edu")){
                    eduDomains.add(domain);
                }
            }
            //gui
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < eduDomains.size(); i++){
                result.append(eduDomains.get(i));
                if(i < eduDomains.size() - 1){
                    result.append(", ");
                }
            }
            System.out.println(result);
            writer.write(result.toString());
            writer.newLine();
            writer.flush();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
}
