import java.io.*;
import java.net.*;
public class ChatBotClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader userInput = new BufferedReader(
                new InputStreamReader(System.in)
            );

            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("You: ");
            String start = userInput.readLine();
            output.println(start);
            System.out.println("Server: " + input.readLine());
            while (true) {
                for (int i = 0; i < 6; i++) {
                    System.out.println(input.readLine());
                }
                System.out.print("Enter choice: ");
                String choice = userInput.readLine();
                output.println(choice);
                if (choice.equals("5")) {
                    System.out.println("Server: " + input.readLine());
                    break;
                }
                System.out.print("Enter text: ");
                String msg = userInput.readLine();
                output.println(msg);
                System.out.println("Server: " + input.readLine());
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}