package THLTMHUNG;

import java.io.*;
import java.net.*;

public class UCLNBCNN {
    public static void main(String[] args) {
        String serverIP = "172.188.19.218"; // Địa chỉ IP của server
        int serverPort = 1605; // Cổng server
        String studentCode = "B21DCCN495"; // Mã sinh viên
        String qCode = "MzzGxGe"; // Mã bài tập

        try {
            // Kết nối tới server
            Socket socket = new Socket(serverIP, serverPort);
            System.out.println("Đã kết nối thành công tới server: " + socket);

            // Khởi tạo DataInputStream và DataOutputStream để trao đổi dữ liệu với server
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Gửi chuỗi mã sinh viên và mã bài tập lên server
            String request = studentCode + ";" + qCode;
            dos.writeUTF(request);
            dos.flush();
            System.out.println("Đã gửi yêu cầu: " + request);

            // Nhận hai số nguyên a và b từ server
            int a = dis.readInt();
            int b = dis.readInt();
            System.out.println("Nhận được hai số từ server: a = " + a + ", b = " + b);

            // Tính UCLN (Ước chung lớn nhất) và BCNN (Bội chung nhỏ nhất)
            int ucln = gcd(a, b);
            int bcnn = lcm(a, b);
            System.out.println("UCLN: " + ucln);
            System.out.println("BCNN: " + bcnn);

            // Gửi kết quả UCLN và BCNN lên server
            dos.writeInt(ucln);
            dos.writeInt(bcnn);
            dos.flush();
            System.out.println("Đã gửi UCLN và BCNN lên server");

            // Đóng kết nối
            dis.close();
            dos.close();
            socket.close();
            System.out.println("Đã đóng kết nối.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm tính UCLN (Ước chung lớn nhất)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Hàm tính BCNN (Bội chung nhỏ nhất)
    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}
