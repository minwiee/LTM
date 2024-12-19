package THLTMHUNG;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import UDP.Student;
public class CK_UDP_Object {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "EcgBTRlm";
        String message = ";" + msv + ";" + qcode;
        
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serAddress = InetAddress.getByName("203.162.10.109");
            DatagramPacket sendData = new DatagramPacket(message.getBytes(), message.getBytes().length, serAddress, 2209);
            socket.send(sendData);
            System.out.println("sent: " + message);

            byte[] receive = new byte[1024];
            DatagramPacket receiveData = new DatagramPacket(receive, receive.length);
            socket.receive(receiveData);

            String reqId = new String(receiveData.getData(), 0, 8);
            System.out.println(reqId);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receive, 8, receive.length - 8);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Student student = (Student) objectInputStream.readObject();

            System.out.println("received: " + student);

            //xu ly ten
            String[] stringNames = student.getName().toLowerCase().split("\\s+");
            StringBuilder normalized = new StringBuilder();
            for (String part: stringNames){
                normalized.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1)).append(" ");
            }
            student.setName(normalized.toString().trim());
            System.out.println(student.getCode() + "," + student.getName() + "," + student.getEmail());
            //xu ly email
            StringBuilder geneEmail = new StringBuilder();
            geneEmail.append(stringNames[stringNames.length - 1]);
            for (int i = 0; i < stringNames.length - 1; i++){
                geneEmail.append(stringNames[i].charAt(0));
            }
            geneEmail.append("@ptit.edu.vn");
            student.setEmail(geneEmail.toString());
            
            System.out.println("sau xu ly: " + student);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(student);

            byte[] studentBytes = byteArrayOutputStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.allocate(8 + studentBytes.length);
            buffer.put(receiveData.getData(),0,8);
            buffer.put(studentBytes);
            byte[] sendProcessedData = buffer.array();
            System.out.println("den day roi");
            sendData = new DatagramPacket(sendProcessedData, sendProcessedData.length, serAddress, 2209);
            socket.send(sendData);
            socket.close();
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

