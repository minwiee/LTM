/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Ming
 */
public class ByteStream {

    public static void main(String[] args) {
        String serverAdd = "203.162.10.109";
        int port = 2206;
        String studentCode = "B21DCCN519";
        String qCode = "guLy2yzF";
        try (Socket socket = new Socket(serverAdd, port)){
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            
            String mess = studentCode + ";" + qCode;
            os.write(mess.getBytes());
            
            byte[] responseBytes = new byte[1024];
            int length = is.read(responseBytes);
            String response = new String(responseBytes,0, length);
            System.out.println(response);
            
            String[] numbers = response.split("\\|");
            int sum = 0;
            for (String number:numbers){
                sum += Integer.parseInt(number);
            }
            System.out.println(sum);
            os.write(String.valueOf(sum).getBytes());
            socket.close();
        } catch (Exception e) {
        }
            
            
    }
}
