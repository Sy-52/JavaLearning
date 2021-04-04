import Server.SimpleServer;

import java.io.IOException;

import static Common.Constants.SERVER_PORT;

public class SimpleServerMain {
    public static void main(String[] args) throws IOException {
        SimpleServer server = new SimpleServer(SERVER_PORT);
        server.start();
    }
}
