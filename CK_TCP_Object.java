package THLTMHUNG;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import TCP.Laptop;

public class CK_TCP_Object {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "Qb3DAQAA";
        String message = msv + ";" + qcode;

        try (Socket socket = new Socket("203.162.10.109", 2209)){
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println(message);

            Laptop laptop = (Laptop) ois.readObject();
            System.out.println(laptop.getName() + ";" + laptop.getQuantity());
            
            StringBuilder sb = new StringBuilder();
            String[] parts = laptop.getName().split("\\s+");
            sb.append(parts[parts.length - 1]).append(" ");
            for (int i = 1; i < parts.length - 1; i++) {
                sb.append(parts[i]).append(" ");
            }
            sb.append(parts[0]);
            System.out.println("sau khi sua:" + sb.toString() + ";" + laptop.getQuantity());
            laptop.setName(sb.toString());

            String reverveQuantity = new StringBuilder(String.valueOf(laptop.getQuantity())).reverse().toString();
            System.out.println("sau khi dao nguoc:" + laptop.getName() + ";" + reverveQuantity);

            laptop.setQuantity((Integer.parseInt(reverveQuantity)));
            oos.writeObject(laptop);
        } catch (Exception e) {
        } 
    }
}

