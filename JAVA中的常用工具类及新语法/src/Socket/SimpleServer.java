package Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SimpleServer {
    public static final int SERVER_PORT = 54321;
    public static final Charset COMM_CHARSET = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {
        commWithClient();
    }

    private static void commWithClient() throws IOException {
        // >> TODO 所有在小括号()中创建Closeable的对象在try语句执行完毕后都会被close掉。
        try (
                ServerSocket ss = new ServerSocket(SERVER_PORT);
                // >> TODO 如果客户端不连接，服务器端就一直卡在这里。
                Socket s = ss.accept();
        ) {
            Chat chat = new Chat("客户端", "客户端，我们已经建立连接，可以开始聊天。", s);
            chat.chatting();
        }
    }
}
