package org.pcbe.util;

import java.io.BufferedReader;
import java.io.IOException;

public class Communication {

    private static final String END_TOKEN = "transmission_over";

    public static void sendMessage() {
        // TODO
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
