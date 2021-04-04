import Client.SimpleClient;

import java.io.IOException;

public class SimpleClientMain {
    public static void main(String[] args) throws IOException {
        SimpleClient client = new SimpleClient("localhost");
        client.start();
    }
}
