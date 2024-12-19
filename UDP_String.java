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

public class UDP_String {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Tạo socket để gửi và nhận gói tin
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Thay "localhost" bằng địa chỉ IP server nếu cần

            // Chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”
            String studentCode = "B21DCCN519";
            String qCode = "kIhZaiKq";
            String messageToSend = ";" + studentCode + ";" + qCode;

            // Gửi thông điệp lên server
            byte[] sendData = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 2208);
            socket.send(sendPacket);
            System.out.println("Sent message to server: " + messageToSend);

            // Nhận thông điệp từ server
            byte[] receiveData = new byte[1024];
            System.out.println(receiveData.length);
            DatagramPacket receivePacket = new DatagramPacket(receiveData,0, receiveData.length);
            socket.receive(receivePacket);
            System.out.println(receivePacket);
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received message from server: " + receivedMessage);

            // Phân tích thông điệp nhận được từ server
            String[] parts = receivedMessage.split(";");
            String requestId = parts[0];
            String data = parts[1];

            // Xử lý chuẩn hóa chuỗi
            String normalizedData = capitalizeEachWord(data);
            System.out.println("Normalized data: " + normalizedData);

            // Gửi chuỗi chuẩn hóa lại lên server theo định dạng “requestId;data”
            String resultMessage = requestId + ";" + normalizedData;
            byte[] resultData = resultMessage.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, serverAddress, 2208);
            socket.send(resultPacket);
            System.out.println("Sent normalized data to server: " + resultMessage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    // Hàm chuẩn hóa chuỗi: Ký tự đầu tiên của mỗi từ là in hoa, các ký tự còn lại in thường
    public static String capitalizeEachWord(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase()).append(" ");
            }
        }

        // Xóa khoảng trắng cuối chuỗi
        return capitalized.toString().trim();
    }

}