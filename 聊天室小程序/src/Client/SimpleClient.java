package Client;

import Common.DataExchange;
import Common.MessagePackage;
import Common.Utilitys;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static Common.Constants.*;

// TODO 1、如果要发送消息给所有的在线用户，怎么改造？
// TODO 2、现在的传输是基于文本的，如果要发送文件，怎么改造？（如何改造DataExchange）
// TODO 3、在某些情况下，客户端并不是主动logoff，服务器端会一直留着<userName, dataExchange>。所以客户端每过一段时间给服务器端报一次心跳，服务器也每隔一段时间就检索客户端的心跳。
public class SimpleClient {
    private String server;
    private DataExchange dataExchange;
    private String userName;
    private String currentToName;
    private Scanner input = new Scanner(System.in);

    public SimpleClient(String server) throws IOException {
        System.out.println("客户端连接中...");
        this.server = server;
        Socket socket = new Socket(server, SERVER_PORT);
        System.out.println("客户端已成功连接到服务器：" + socket.getRemoteSocketAddress());
        dataExchange = new DataExchange(socket);
    }

    public void start() throws IOException {
        initName();

        Thread readThread = new Thread(() -> {
            while(true){
                try {
                    MessagePackage messagePackage = dataExchange.receive();
                    if(messagePackage.getFrom().equalsIgnoreCase(ADMIN_NAME) && messagePackage.getMessage().trim().equalsIgnoreCase("bye")){
                        dataExchange.close();
                        System.out.println("你已离开聊天室，程序结束。");
                        System.exit(0);
                    }
                    System.out.println("From \"" + messagePackage.getFrom() + "\"：" + messagePackage.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    dataExchange.close();
                    System.exit(-2);
                }
            }
        });
        readThread.start();

        Thread writeThread = new Thread(() -> {
            while(true){
                try {
                    dataExchange.send(createMessagePackage());
                }catch (Exception ex){
                    ex.printStackTrace();
                    dataExchange.close();
                    System.exit(-3);
                }
            }
        });
        writeThread.start();
    }

    private void initName() throws IOException {
        String typedName = null;
        while(true){
            MessagePackage messagePackage = dataExchange.receive();
            String serverMessage = messagePackage.getMessage();
            if(serverMessage.equalsIgnoreCase(USERNAME_PASS_FLAG)){
                userName = typedName;
                break;
            }else {
                System.out.println(serverMessage);
                typedName = input.nextLine();
                dataExchange.send(new MessagePackage("Anonymous", ADMIN_NAME, typedName.trim()));
            }
        }

    }

    private MessagePackage createMessagePackage(){
        while(true){
            String line = input.nextLine().trim();
            String to = null;
            String message = null;
            if(line.startsWith(CHAT_START_CHARACTOR)){
                to = line.substring(1, line.indexOf(SPACE_STRING)).trim();
                String error = Utilitys.userNameCheck(to);
                if(error == null){
                    currentToName = to;
                    message = line.substring(line.indexOf(SPACE_STRING) + 1);
                }else{
                    System.out.println("用户名\"" + to + "\"不合法！" + error);
                    continue;
                }
            }else{
                if(currentToName == null){
                    System.out.println("请使用\"@对方用户名\"进行聊天。之后如果继续和同一个人聊天，可以直接输入不用加@。");
                    continue;
                }
                message = line;
            }
            return new MessagePackage(userName, currentToName, message);
        }
    }
}
