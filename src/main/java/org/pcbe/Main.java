package org.pcbe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("127.0.0.1", 4444);
        System.out.println("Transmission started...");

        Scanner in = new Scanner(System.in);
        while (true) {
            String menu = client.getResponse();
            if (menu == null)
                break;

            System.out.println(menu);
            String option = in.nextLine();
            client.sendOption(option);

            String response = client.getResponse();
            if (response == null)
                break;
            System.out.println(response);
        }

        System.out.println("Transmission ended...");
        client.stopConnection();
    }

}
