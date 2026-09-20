import java.net.*;
import java.util.Scanner;
public class DatagramClient {
    public static void main(String[] args) {
        try {
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("localhost");
            Scanner input = new Scanner(System.in);
            System.out.print("Write message: ");
            String text = input.nextLine();
            byte[] sendBytes = text.getBytes();
            DatagramPacket dpSend =
                    new DatagramPacket(sendBytes, sendBytes.length, ip, 8000);
            ds.send(dpSend);
            byte[] buffer = new byte[1024];
            DatagramPacket dpReceive =
                    new DatagramPacket(buffer, buffer.length);
            ds.receive(dpReceive);
            String reply = new String(dpReceive.getData(), 0, dpReceive.getLength());
            System.out.println("Server Response: " + reply);
            ds.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}