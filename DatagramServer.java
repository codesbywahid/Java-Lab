import java.net.*;

public class DatagramServer {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket(8000);
            System.out.println("UDP Server Running...");
            byte[] buffer = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            ds.receive(dp);
            String data = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Client Data: " + data);
            String result = data.trim().toUpperCase() + " (" + data.length() + ")";
            byte[] sendBytes = result.getBytes();
            InetAddress ip = dp.getAddress();
            int port = dp.getPort();
            DatagramPacket sendDp =
                    new DatagramPacket(sendBytes, sendBytes.length, ip, port);
            ds.send(sendDp);
            System.out.println("Response Sent: " + result);
            ds.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}