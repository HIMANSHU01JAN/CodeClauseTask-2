import java.io.*;
import java.net.*;

public class VPNServer {
    public static void main(String[] args) throws IOException {
        int serverPort = 8888;
        ServerSocket serverSocket = new ServerSocket(serverPort);

        System.out.println("VPN Server is running on port " + serverPort);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new ClientHandler(clientSocket).start();
        }
    }
}

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // VPN server logic goes here

            // Example: Echo messages back to the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Received: " + message);
                writer.println("Server: " + message);
            }

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
