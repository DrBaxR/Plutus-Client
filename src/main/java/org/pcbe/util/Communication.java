package org.pcbe.util;

import com.google.gson.Gson;
import org.pcbe.dto.ClientMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Communication {

    private static final String END_TOKEN = "transmission_over";

    public static void sendMessage(PrintWriter out, int option, String stockName, Integer quantity) {
        ClientMessage messageToSend = new ClientMessage(option, stockName, quantity);
        String serializedMessage = new Gson().toJson(messageToSend);

        out.println(serializedMessage);
    }

    public static String readMessage(BufferedReader in) {
        StringBuilder finalMessage = new StringBuilder();

        try {
            String currentLine = in.readLine();
            while(!END_TOKEN.equals(currentLine)) {
                if (currentLine == null) {
                    return null;
                }

                finalMessage.append(currentLine).append("\n");
                currentLine = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return finalMessage.toString();
    }

}
