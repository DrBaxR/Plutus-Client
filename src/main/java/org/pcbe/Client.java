package org.pcbe;

import java.net.*;
import java.io.*;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendOption(String option) {
        // will probably have to change
        out.println(option);
    }

    public String getResponse() {
        // will have to change
        String response = null;
        try {
            response = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
