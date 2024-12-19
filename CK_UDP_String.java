package THLTMHUNG;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class CK_UDP_String {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "QqbXgxT1";
        String message = ";" + msv + ";" + qcode;
        
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serrAddress = InetAddress.getByName("203.162.10.109");
            DatagramPacket sendData = new DatagramPacket(message.getBytes(), message.getBytes().length, serrAddress, 2208);
            socket.send(sendData);
            System.out.println(message);

            byte[] receivePacket = new byte[1024];
            DatagramPacket receiDatagramPacket = new DatagramPacket(receivePacket, receivePacket.length);
            socket.receive(receiDatagramPacket);
            String receiveString = new String(receiDatagramPacket.getData(), 0, receiDatagramPacket.getLength());
            // receiveString = "qBsGjyKA;Axt?OP|l3~?w{M0zg=+~e18)6#]E5s5hqeWidM~z1$sd5I=+eO@1dPM/o;/J3f5";
            System.out.println(receiveString);

            String[] part = receiveString.split(";");
            StringBuilder output = new StringBuilder();
            for(char c: part[1].toCharArray()){
                if(!part[2].contains(String.valueOf(c))){
                    output.append(c);
                }
            }
            System.out.println(output);
            String sendOutput = part[0] + ";" + output.toString();
            
            sendData = new DatagramPacket(sendOutput.getBytes(),sendOutput.getBytes().length, serrAddress, 2208);
            socket.send(sendData);
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
