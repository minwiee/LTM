package THLTMHUNG;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

public class CKByStream {
    public static void main(String[] args) {
        String serverHost = "203.162.10.109"; // Đổi thành địa chỉ IP server nếu cần
        int serverPort = 2206;
        String studentCode = "B21DCCN519";
        String qCode = "FcU7Zoe4";

        try (Socket socket = new Socket(serverHost, serverPort)) {
            socket.setSoTimeout(5000); // Thiết lập thời gian chờ tối đa là 5 giây

            // Lấy các luồng dữ liệu
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Gửi thông điệp đến server
            String message = studentCode + ";" + qCode;
            outputStream.write((message + "\n").getBytes());
            outputStream.flush();
            System.out.println("Sent to server: " + message);

            // Nhận phản hồi từ server
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String receivedMessage = reader.readLine();
            // String receivedMessage = "1,3,9,19,33,20";
            System.out.println("Received from server: " + receivedMessage);

            // Phân tích chuỗi nhận được thành các số nguyên
            String[] numbers = receivedMessage.split(",");
            if (numbers.length < 2) {
                System.err.println("Invalid data from server: less than 2 numbers.");
                return;
            }

            // Tìm giá trị lớn thứ hai và vị trí của nó
            int[] intNumbers = Arrays.stream(numbers).mapToInt(Integer::parseInt).toArray();
            // System.out.println("Numbers: " + Arrays.toString(intNumbers));
            int firstMax = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
            int secondMaxIndex = -1;

            for (int i = 0; i < intNumbers.length; i++) {
                if (intNumbers[i] > firstMax) {
                    secondMax = firstMax;
                    secondMaxIndex = i - 1; // Cập nhật vị trí cũ của giá trị lớn nhất
                    firstMax = intNumbers[i];
                } else if (intNumbers[i] > secondMax && intNumbers[i] < firstMax) {
                    secondMax = intNumbers[i];
                    secondMaxIndex = i;
                }
            }

            if (secondMaxIndex == -1) {
                System.err.println("Could not find the second largest value.");
                return;
            }

            // Gửi kết quả lên server
            String response = secondMax + "," + (secondMaxIndex); // Thêm 1 vì chỉ số bắt đầu từ 0
            outputStream.write((response + "\n").getBytes());
            outputStream.flush();
            System.out.println("Sent to server: " + response);

            // Đóng kết nối
            System.out.println("Client finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
