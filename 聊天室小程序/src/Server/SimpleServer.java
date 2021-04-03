package Server;

import Common.MessagePackage;
import Common.DataExchange;

import static Common.Utilitys.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static Common.Constants.*;

public class SimpleServer {
    private int port;
    // TODO 为每个连接到服务器的用户，建立用户名->DataExchange的映射关系，便于服务器端转发消息。
    private Map<String, DataExchange> userNameToDataExchange = new ConcurrentHashMap<>();
    // TODO Executors.newCachedThreadPool返回的也是一个TreadPoolExecutor的实例，不过线程池配置不同。
    private ExecutorService service = Executors.newCachedThreadPool();

    public SimpleServer(int port){this.port = port;}

    public void start() throws IOException {
        System.out.println("服务器已启动...");
        ServerSocket ss = new ServerSocket(SERVER_PORT);
        while(true){
            Socket socket = ss.accept();
            // TODO 一个线程服务一个连接，是java网络通讯的经典模式。
            // TODO 只要有客户端连过来，就把它扔给一个新的线程去执行。只要客户端不退出，线程就不结束。
            service.submit(new ClientHandler(socket));
        }
    }

    class ClientHandler implements Runnable{
        private Socket socket;
        private String userName = null;

        public ClientHandler(Socket socket){this.socket = socket;}

        @Override
        public void run() {
            System.out.println("处理来自" + socket.getRemoteSocketAddress() + "的连接...");
            DataExchange dataExchange = null;
            try {
                dataExchange = new DataExchange(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            initUser(dataExchange);

            while(true){
                try {
                    MessagePackage messagePackage = dataExchange.receive();
                    String toName = messagePackage.getTo();
                    if(toName.equalsIgnoreCase(ADMIN_NAME)){
                        handleServerCommand(messagePackage);
                    }else{
                        handleChatMessage(dataExchange, messagePackage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    dataExchange.close();
                    return;
                }
            }
        }

        private void initUser(DataExchange dataExchange){
            while(true){
                String errorMessage = null;
                String allUserNames = getAllUserNames();
                dataExchange.send(new MessagePackage(ADMIN_NAME, "Anonymous", (errorMessage == null?"" : "现有用户名："
                        + allUserNames + ". 请输入你的用户名：")));

                try {
                    MessagePackage messagePackage = dataExchange.receive();
                    String userName = messagePackage.getMessage();
                    errorMessage = isValidToUserName(userName);
                    if(errorMessage == null && (!userNameToDataExchange.containsKey(userName))){
                        this.userName = userName;
                        userNameToDataExchange.put(userName, dataExchange);
                        dataExchange.send(new MessagePackage(ADMIN_NAME, userName, INTRODUCTION));
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    dataExchange.close();
                    return;
                }
            }
        }

        private String getAllUserNames(){
            String userNameListSeparator = ", ";
            if(userNameToDataExchange.isEmpty()){
                return CHAT_START_CHARACTOR + ADMIN_NAME;
            }else{
                return CHAT_START_CHARACTOR + ADMIN_NAME + userNameListSeparator + CHAT_START_CHARACTOR
                        + String.join(userNameListSeparator + CHAT_START_CHARACTOR, userNameToDataExchange.keySet());
            }
        }

        private void handleServerCommand(MessagePackage messagePackage){
            DataExchange client = userNameToDataExchange.get(messagePackage.getFrom());
            String command = messagePackage.getMessage();

            if(command.equalsIgnoreCase(SERVER_COMMAND_LOGOFF)){
                client.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), BYE));
                client.close();
                System.out.println("用户\"" + messagePackage.getFrom() + "\"离开了聊天室...");
            }else if(command.equalsIgnoreCase(SERVER_COMMAND_LIST)){
                String allUserNames = getAllUserNames();
                client.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "当前在线用户：" + allUserNames));
            }
        }

        private void handleChatMessage(DataExchange dataExchange, MessagePackage messagePackage){
            DataExchange toAnother = userNameToDataExchange.get(messagePackage.getTo());
            if(toAnother == null){
                dataExchange.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "用户名\"" + messagePackage.getTo() + "不存在！"));
            }else{
                toAnother.send(messagePackage);
            }
        }
    }
}
