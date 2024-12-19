/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author Ming
 */
public class ByteStreamEx {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("203.162.10.109", 2207);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            dos.writeUTF("B21DCCN519;1QWOwSQz");
            System.out.println("da gui msv");
            
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println(a + ", " + b);
            
            int sum = a + b;
            int product = a * b;
            
            dos.writeInt(sum);
            dos.writeInt(product);
            socket.close();
        } catch (Exception e) {
        }
    }
}
