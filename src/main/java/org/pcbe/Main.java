package org.pcbe;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        client.startConnection("127.0.0.1", 6666);
        String response = client.sendMessage("hello server");
        System.out.println(response);
    }

}
