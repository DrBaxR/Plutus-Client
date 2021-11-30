package org.pcbe;

import com.google.gson.Gson;
import org.pcbe.dto.ClientMessage;
import org.pcbe.util.Communication;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

        Communication.sendMessage(out, option, stockName, quantity);
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

    public void sendRandomRequest() {
        //random number 1/2
        var rand = (int) (Math.random() * 2) + 1;
        List<String> stockName = new ArrayList<>();
        stockName.add("STK1");
        stockName.add("STK2");
        stockName.add("STK3");
        stockName.add("STK4");
        var name = stockName.get((int) (Math.random() *4) + 0);
        var quantity = (int) (Math.random() * 100) + 20;
        Communication.sendMessage(out, rand, name, quantity);
    }
}
