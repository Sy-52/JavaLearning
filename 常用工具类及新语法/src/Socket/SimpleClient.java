package Socket;

import java.net.Socket;

import static Socket.SimpleServer.SERVER_PORT;

public class SimpleClient {
    public static void main(String[] args) {
        commWithServer();
    }

    private static void commWithServer() {
        try(
            Socket s = new Socket("localhost",SERVER_PORT);
        ){
            Chat chat = new Chat("服务器",null, s);
            chat.chatting();
            //System.out.println("客户端成功连接到：" + s.getRemoteSocketAddress());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
