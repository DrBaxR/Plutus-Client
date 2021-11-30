package org.pcbe;

import com.google.gson.Gson;
import org.pcbe.dto.ClientMessage;
import org.pcbe.util.Communication;

import java.net.*;
import java.io.*;
import java.util.Scanner;

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

    public void gatherExtraAndSendOption(Scanner input, int option) {
        String stockName = null;
        Integer quantity = null;

        if (option != 3) {
            stockName = input.nextLine();
            quantity = Integer.parseInt(input.nextLine());
        }

        ClientMessage messageToSend = new ClientMessage(option, stockName, quantity);
        String serializedMessage = new Gson().toJson(messageToSend);

        out.println(serializedMessage);
    }

    public String getResponse() {
        return Communication.readMessage(in);
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
