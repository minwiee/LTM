/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LatXau {
    public static void main(String[] args) {
        String serverAddress = "172.188.19.218";
        int port = 1605;
        String studenCode = "B21DCCN166";
        String qCode = "9C3CK1v";
        try(Socket socket= new Socket(serverAddress, port)){
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            //gui chuoi
            String mess = studenCode + ";" + qCode;
            dos.writeUTF(mess);
            
            //nhan xau
            String str = dis.readUTF();
            System.out.println("chuoi tu server: " + str);
            
            //reverse
            String reversedStr = new StringBuilder(str).reverse().toString();
            System.out.println("chuoi sau xu ly: "+ reversedStr);
            
            //gui len server
            dos.writeUTF(reversedStr);
            System.out.println("gui chuoi thanh cong");
            
            socket.close();
        }
            catch (IOException ex) {
                Logger.getLogger(LatXau.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}

