
package THLTMHUNG;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TongHieuTich {

    public static void main(String[] args) {
        String serverAddress = "10.170.46.199";
        int port = 2207;
        String studentCode = "B21DCCN519";
        String questionCode = "1QWOwSQz";

        try (Socket socket = new Socket(serverAddress, port)) {
            // Khởi tạo DataInputStream và DataOutputStream
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            // Gửi chuỗi mã sinh viên và mã bài tập
            String message = studentCode + ";" + questionCode;
            outputStream.writeUTF(message);
            System.out.println("Đã gửi: " + message);

            // Nhận hai số nguyên a và b từ server
            int a = inputStream.readInt();
            int b = inputStream.readInt();
            System.out.println("Nhận được a = " + a + ", b = " + b);

            // Tính tổng, hiệu, tích
            int sum = a + b;
            int difference = a - b;
            int product = a * b;

            // Gửi kết quả lên server
            outputStream.writeInt(sum);
            outputStream.writeInt(difference);
            outputStream.writeInt(product);
            System.out.println("Đã gửi kết quả: tổng = " + sum + ", hiệu = " + difference + ", tích = " + product);

            // Đóng kết nối
            socket.close();
            System.out.println("Đã đóng kết nối.");

        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi kết nối tới server: " + e.getMessage());
        }
    }
}

