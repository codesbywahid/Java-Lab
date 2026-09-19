import java.io.*;
import java.net.*;
public class UpperCaseServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started... Waiting for client");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String message = input.readLine();
            System.out.println("Received from client: " + message);
            String upperMessage = message.toUpperCase();
            output.println(upperMessage);
            System.out.println("Sent to client: " + upperMessage);
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}