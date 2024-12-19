/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package THLTMHUNG;

/**
 *
 * @author Ming
 */
import java.io.*;
import java.net.*;

public class LoaiBoNguyenAm {
    public static void main(String[] args) {
        String serverIP = "172.188.19.218"; // Địa chỉ IP của server
        int serverPort = 1606; // Cổng server
        String studentCode = "B21DCCN495"; // Mã sinh viên
        String qCode = "KALfVXX"; // Mã bài tập

        try {
            // Kết nối tới server
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Đã kết nối thành công tới server: " + socket);

            // Khởi tạo BufferedReader và BufferedWriter để trao đổi dữ liệu với server
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Gửi chuỗi mã sinh viên và mã bài tập lên server
            String request = studentCode + ";" + qCode;
            writer.write(request);
            writer.newLine();  // Kết thúc dòng
            writer.flush();  // Gửi dữ liệu đi

            // Nhận chuỗi từ server
            String receivedString = reader.readLine();
            System.out.println("Chuỗi nhận từ server: " + receivedString);

            // Loại bỏ nguyên âm khỏi chuỗi
            String resultString = removeVowels(receivedString);
            System.out.println("Chuỗi sau khi loại bỏ nguyên âm: " + resultString);

            // Gửi chuỗi đã loại bỏ nguyên âm lên server
            writer.write(resultString);
            writer.newLine();
            writer.flush();

            // Đóng kết nối
            writer.close();
            reader.close();
            socket.close();
            System.out.println("Đã gửi kết quả và đóng kết nối.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm loại bỏ nguyên âm khỏi chuỗi
    public static String removeVowels(String input) {
        return input.replaceAll("[aeiou]", "");  // Loại bỏ các nguyên âm thường
    }
}
