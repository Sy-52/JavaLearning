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
    // TODO 在服务器端，用map存储"已连接的用户 -> 其对应的DataExchange的映射"。
    private Map<String, DataExchange> userNameToDataExchange = new ConcurrentHashMap<>();
    // TODO 用Executors.newCachedThreadPool返回一个线程池配置不同的TreadPoolExecutor实例。
    private ExecutorService service = Executors.newCachedThreadPool();

    public SimpleServer(int port){this.port = port;}

    public void start() throws IOException {
        System.out.println("服务器已启动...");
        ServerSocket ss = new ServerSocket(SERVER_PORT);
        // TODO 这里由于我还没学习Java Swing，所以暂且不在外面套while循环，以便测试。
        Socket socket = ss.accept();
        // TODO 只要有客户端连过来，就把它扔给一个新的线程去执行。只要客户端不退出，线程就不结束。一个线程服务一个连接，是java网络通讯的经典模式。
        service.submit(new ClientHandler(socket));
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
            // TODO 先针对客户端的用户名进行一轮网络传输，将确定好的userName保存好。
            initUser(dataExchange);

            while(true){
                try {
                    MessagePackage messagePackage = dataExchange.receive();
                    String toName = messagePackage.getTo();
                    if(toName.equalsIgnoreCase(ADMIN_NAME)){
                        handleServerCommand(messagePackage);
                    }else{
                        handleMessagePackage(dataExchange, messagePackage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    dataExchange.close();
                    return;
                }
            }
        }

        private void initUser(DataExchange dataExchange){
            String errorMessage = null;
            while(true){
                String allUserNames = getAllUserNames();
                dataExchange.send(new MessagePackage(ADMIN_NAME, "Anonymous", (errorMessage == null?"现有用户名："
                        + allUserNames + ".请输入你的用户名：" : "")));
                try {
                    MessagePackage messagePackage = dataExchange.receive();
                    String userName = messagePackage.getMessage();
                    errorMessage = isValidToUserName(userName);
                    if(errorMessage == null && (!userNameToDataExchange.containsKey(userName))){
                        this.userName = userName;
                        userNameToDataExchange.put(userName, dataExchange);
                        dataExchange.send(new MessagePackage(ADMIN_NAME, userName, USERNAME_PASS_FLAG));
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
            DataExchange clientDataExchange = userNameToDataExchange.get(messagePackage.getFrom());
            String command = messagePackage.getMessage();

            if(command.equalsIgnoreCase(SERVER_COMMAND_LOGOFF)){
                clientDataExchange.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "bye"));
                clientDataExchange.close();
                userNameToDataExchange.remove(messagePackage.getFrom());
                System.out.println("用户\"" + messagePackage.getFrom() + "\"离开了聊天室...");
            }else if(command.equalsIgnoreCase(SERVER_COMMAND_LIST)){
                String allUserNames = getAllUserNames();
                clientDataExchange.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "当前在线用户：" + allUserNames));
            }else{
                clientDataExchange.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "服务器暂时不支持聊天，后续功能敬请期待！"));
            }
        }

        private void handleMessagePackage(DataExchange dataExchange, MessagePackage messagePackage){
            DataExchange toAnother = userNameToDataExchange.get(messagePackage.getTo());
            if(toAnother == null){
                dataExchange.send(new MessagePackage(ADMIN_NAME, messagePackage.getFrom(), "用户名\"" + messagePackage.getTo() + "不存在！"));
            }else{
                toAnother.send(messagePackage);
            }
        }
    }
}
