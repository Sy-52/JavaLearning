package Client;

import Common.DataExchange;
import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static Common.Constants.SERVER_PORT;

public class SimpleClient {
    private String server;
    DataExchange dataExchange;
    private String userName;
    private Scanner scanner = new Scanner(System.in);

    public SimpleClient(String server) throws IOException {
        System.out.println("客户端连接中...");
        this.server = server;
        Socket socket = new Socket(server, SERVER_PORT);
        System.out.println("客户端已成功连接到服务器：" + socket.getRemoteSocketAddress());
        dataExchange = new DataExchange(socket);
    }

    public void start(){
        initName();
    }

    private void initName(){

    }
}
