/*
 * b1: khoi tao socket voi diagramsocket = new socket;
 * b2: message string msv,... -> chuyen thanh getbyte
 * gui qua datapacket voi (byte, byte,length, serrver, port)
 * socket.send
 * 
 * b3: nhanve thi khoi tao 1 byte[1024]
 *      bien byte thanh datapacket qua (byte, byte.length) 
 *      socket.receive. xong chuyen tu datapacket sang string thi .getData() roi xu ly
 */

package THLTMHUNG;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class CK_UDP_Datatype {

    public static void main(String[] args) {
        String studentCode = "B21DCCN519";
        String qCode = "XYB9gInZ";
        String message = ";" + studentCode + ";" + qCode;

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2207);
            
            socket.send(sendPacket);
            System.out.println("Sent message to server: " + message);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);            
            //tai sao can dong nay?
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from server: " + receivedMessage);

            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            int n = Integer.parseInt(parts[1]);
            String[] receivedNumbers = parts[2].split(",");

            Set<Integer> receivedSet = new HashSet<>();
            for (String num : receivedNumbers) {
                receivedSet.add(Integer.parseInt(num));
            }

            StringBuilder missingNumbers = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (!receivedSet.contains(i)) {
                    if (missingNumbers.length() > 0) {
                        missingNumbers.append(",");
                    }
                    missingNumbers.append(i);
                }
            }

            String responseMessage = requestId + ";" + missingNumbers.toString();
            byte[] responseData = responseMessage.getBytes();
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, serverAddress, 2207);
            socket.send(responsePacket);
            System.out.println(responseMessage);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}