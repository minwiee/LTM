/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ming
 */
public class CharacterStreamEx {
    public static void main(String[] args) throws IOException {
        String serverAdd = "203.162.10.109";
        int port = 2208;
        String studentCode = "B21DCCN519";
        String qCode = "kTOWS75G";
        try (Socket socket = new Socket(serverAdd, port)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            String mess = studentCode + ";" + qCode;
            writer.write(mess);
            writer.newLine();
            writer.flush();
            System.out.println("gui ma thanh cong");
            
            String receivedList = reader.readLine();
            System.out.println("danh sach: " + receivedList);
            
            String[] domains = receivedList.split(", ");
            List<String> eduDomains = new ArrayList<>();
            for (String domain : domains){
                if (domain.endsWith(".edu")){
                    eduDomains.add(domain);
                }
            }
            
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < eduDomains.size(); i++){
                result.append(eduDomains.get(i));
                if (i < eduDomains.size() - 1) result.append(", ");
            }
            System.out.println(result);
            writer.write(result.toString());
            writer.newLine();
            writer.flush();
            socket.close();
            
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
