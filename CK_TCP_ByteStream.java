package THLTMHUNG;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CK_TCP_ByteStream {
    public static void main(String[] args) {
        String msv = "B21DCCN519";
        String qcode = "FcU7Zoe4";
        String message = msv + ";" + qcode;
        
        try (Socket socket = new Socket("203.162.10.109", 2206)){
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            os.write(message.getBytes());

            byte[] responseBytes = new byte[1024];
            int length = is.read(responseBytes);
            String response = new String(responseBytes, 0, length);
            // String response = "117769,8412,454459,460105,369392,231211,224652,447937,34692,348481,317400,81033,298215,80081,52572,7402,464509,425,370559,381301,163855,219979,302564,143788,280202,246627,415150,230553,52996,477687,92848,230795,112189,81484,392128,335218,420046,25365";
            System.out.println(response);
            String[] parts = response.split(",");
            int maxx = Integer.MIN_VALUE;
            int second = Integer.MIN_VALUE;
            int maxpos = -1; 
            int secondpos = -1;
            for (int i = 0; i < parts.length; i++){
                int current = Integer.parseInt(parts[i]);
                if(current > maxx){
                    second = maxx;
                    secondpos = maxpos;
                    maxx = Integer.parseInt(parts[i]);
                    maxpos = i;
                }
                else if(current > second){
                    second = Integer.parseInt(parts[i]);
                    secondpos = i;
                }
                
            }
            String messSend = second + "," + secondpos;
            System.out.println(messSend);
            os.write(messSend.getBytes());
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

