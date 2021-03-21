package Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static Socket.SimpleServer.COMM_CHARSET;

public class Chat {
    private String from;
    private String greeting;
    private Socket s;

    public Chat(String from, String greeting, Socket s) {
        this.from = from;
        this.greeting = greeting;
        this.s = s;
    }

    public void chatting(){
        Scanner in = new Scanner(System.in);
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(),COMM_CHARSET));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream(),COMM_CHARSET));
        ) {
            if(greeting != null) {
                pw.println(greeting);
                pw.flush();
            }
            while(true){
                String message = br.readLine();
                if(message.equalsIgnoreCase("bye")){
                    pw.println("bye");
                    break;
                }else{
                    System.out.println(from + ":" + message);
                    String reply = in.nextLine();
                    pw.println(reply);
                    pw.flush();
                }
            }
            //System.out.println("和客户端成功建立连接：" + s.getRemoteSocketAddress());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
