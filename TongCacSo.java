import java.io.*;
import java.net.Socket;

public class TongCacSo {
    public static void main(String[] args) {
        String serverAddress = "172.188.19.218";
        int port = 1604;
        String studentCode = "B21DCCN212";
        String qCode = "F0XThnW";
        Socket socket = null;
        try {
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to server:" + socket);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
           
          
            String request = studentCode + ";" + qCode;
            outputStream.write(request.getBytes());

            System.out.println("Sent to server: " + request);

            byte[] responseBytes = new byte[1024];
            int length = inputStream.read(responseBytes);
            String response = new String(responseBytes, 0, length);
            System.out.println("Received from server: " + response);

            String[] numbers = response.split("\\|");
            int sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number);
            }
            outputStream.write(String.valueOf(sum).getBytes());
            System.out.println("Sent sum to server: " + sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
