package THLTMHUNG;

import java.io.*;
import java.net.*;

public class LuyThua {
    public static void main(String[] args) {
        String serverIP = "172.188.19.218"; // Địa chỉ IP của server
        int serverPort = 1604; // Cổng server
        String studentCode = "B21DCCN495"; // Mã sinh viên
        String qCode = "zT9uINa"; // Mã bài tập

        try {
            // Kết nối tới server
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Đã kết nối thành công tới server: " + socket);

            // Khởi tạo InputStream và OutputStream để trao đổi dữ liệu với server
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            // Gửi chuỗi mã sinh viên và mã bài tập lên server
            String request = studentCode + ";" + qCode;
            os.write(request.getBytes());
            os.flush();
            System.out.println("Đã gửi yêu cầu: " + request);

            // Nhận chuỗi từ server (chứa hai giá trị a và b phân tách bằng "|")
            byte[] buffer = new byte[1024];
            int bytesRead = is.read(buffer);
            String response = new String(buffer, 0, bytesRead);
            System.out.println("Dữ liệu nhận từ server: " + response);

            // Phân tách hai giá trị a và b
            String[] values = response.split("\\|");
            int a = Integer.parseInt(values[0]);
            int b = Integer.parseInt(values[1]);
            System.out.println("Nhận được a = " + a + ", b = " + b);

            // Tính toán giá trị a^b
            int result = (int) Math.pow(a, b);
            System.out.println("Kết quả của " + a + "^" + b + " là: " + result);

            // Gửi kết quả lên server
            String resultStr = String.valueOf(result);
            os.write(resultStr.getBytes());
            os.flush();
            System.out.println("Đã gửi kết quả lên server: " + resultStr);

            // Đóng kết nối
            os.close();
            is.close();
            socket.close();
            System.out.println("Đã gửi kết quả và đóng kết nối.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
