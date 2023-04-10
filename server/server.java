package com;
import java.io.*;
import java.net.*;

public class server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Server listening on port 8080...");
        try{
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
    
                // Create a new thread for each client
                Thread clientThread = new Thread(() -> handleClient(clientSocket));
                clientThread.start();
            }
        }catch(Exception e){
            System.out.println(e);
            serverSocket.close();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            OutputStream out = clientSocket.getOutputStream();

            // Read the request
            String request = in.readLine();
            System.out.println("Request received: " + request);

            // Send the response
            String response = "HTTP/1.1 200 OK\r\n" +
                              "Content-Type: text/plain\r\n" +
                              "Content-Length: 13\r\n" +
                              "Connection: close\r\n\r\n" +
                              "Hello, World!";
            out.write(response.getBytes());
            out.flush();

            System.out.println("Response sent.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
