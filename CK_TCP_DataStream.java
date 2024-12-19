package THLTMHUNG;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class CK_TCP_DataStream {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "qxksHKrG";
        String message = msv + ";" + qcode;
        
        try (Socket socket = new Socket("203.162.10.109", 2207)){
            DataInputStream dis = new DataInputStream((socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(message);

            int n = dis.readInt();
            int[] numberarr = new int[n];
            for (int i = 0; i < n; i++) {
                numberarr[i] = dis.readInt();
            }
            System.out.println("Da nhan duoc mang so" + Arrays.toString(numberarr));
            
            int sum = 0;
            float average = 0, othogon = 0;
            for (int i : numberarr) {
                sum += i;
            }
            average = (float) sum / n;
            System.out.println("Tong: " + sum);
            for(int string : numberarr){
                othogon += Math.pow(string - average, 2);
            }
            othogon = (float) (othogon / n);
            System.out.println(othogon);
            dos.writeInt(sum);
            dos.writeFloat(average);
            dos.writeFloat(othogon);
            socket.close();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

