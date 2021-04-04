package Common;

import java.io.*;
import java.net.Socket;
import static Common.Constants.*;

// TODO DataExchange类用于数据交换。
public class DataExchange {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public DataExchange(Socket socket) throws IOException {
        this.socket = socket;
        init(socket);
    }

    private void init(Socket socket) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), DEFAULT_CHARSET));
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), DEFAULT_CHARSET));
    }

    public void send(MessagePackage messagePackage){
        writer.println(messagePackage.MessagePackageToString());
        writer.flush();
    }

    public MessagePackage receive() throws IOException {
        String line = null;
        while(true){
            line = reader.readLine();
            // TODO 对从网络读到的字符串进行一个基础的筛查。
            if(line != null && line.length() > 0){
                break;
            }
        }
        return MessagePackage.buildFrom(line);
    }

    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
