/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

/**
 *
 * @author Ming
 */
import java.net.*;
import java.util.Arrays;

public class UDP_DataType {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Tạo socket để gửi và nhận gói tin
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("http://203.162.10.109/"); // Thay "localhost" bằng địa chỉ IP server nếu cần

            // Chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”
            String studentCode = "B21DCCN519";
            String qCode = "XYB9gInZ";
            String messageToSend = ";" + studentCode + ";" + qCode;

            // Gửi thông điệp lên server
            byte[] sendData = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2207);
            socket.send(sendPacket);
            System.out.println("Sent message to server: " + messageToSend);

            
        
            // Nhận thông điệp từ server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from server: " + receivedMessage);

            // Phân tích thông điệp nhận được từ server
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            String[] numbersString = parts[1].split(",");
            int[] numbers = Arrays.stream(numbersString).mapToInt(Integer::parseInt).toArray();

            // Tìm giá trị lớn nhất và nhỏ nhất
            int max = Arrays.stream(numbers).max().getAsInt();
            int min = Arrays.stream(numbers).min().getAsInt();
            System.out.println("Max: " + max + ", Min: " + min);

            // Gửi kết quả lên server theo định dạng “requestId;max,min”
            String resultMessage = requestId + ";" + max + "," + min;
            byte[] resultData = resultMessage.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, 2207);
            socket.send(resultPacket);
            System.out.println("Sent result to server: " + resultMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

