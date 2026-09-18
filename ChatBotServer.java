import java.io.*;
import java.net.*;
public class ChatBotServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started... Waiting for Client");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            String startMsg = input.readLine();
            if (startMsg.equalsIgnoreCase("hi")) {
                output.println("Hello! Welcome to Smart Chat ");
            } else {
                output.println("Please start with 'hi'");
            }
            while (true) {
                output.println("\nChoose an option:");
                output.println("1. UPPERCASE");
                output.println("2. lowercase");
                output.println("3. Reverse");
                output.println("4. Word Count");
                output.println("5. Exit");
                String choice = input.readLine();
                if (choice.equals("5")) {
                    output.println("Thank you for using Smart Chat!");
                    break;
                }
                output.println("Enter your text:");
                String message = input.readLine();
                message = filterBadWords(message);
                String result = "";
                switch (choice) {
                    case "1":
                        result = message.toUpperCase();
                        break;
                    case "2":
                        result = message.toLowerCase();
                        break;
                    case "3":
                        result = new StringBuilder(message).reverse().toString();
                        break;
                    case "4":
                        int count = message.trim().split("\\s+").length;
                        result = "Word count: " + count;
                        break;
                    default:
                        result = "Invalid choice!";
                }
                output.println("Result: " + result);
            }
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String filterBadWords(String message) {
        String[] badWords = {
            "bad", "stupid", "idiot", "dumb", "fool"
        };
        for (String word : badWords) {
            message = message.replaceAll("(?i)" + word, "*****");
        }
        return message;
    }
}