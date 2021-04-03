package Common;

import java.util.Objects;

import static Common.Constants.*;

// TODO ChatMessage类描述了一条消息。来自谁、发送给谁、消息是啥。
public class MessagePackage {
    private String from;
    private String to;
    private String message;

    public MessagePackage(String from, String to, String message){
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public MessagePackage(){}

    // TODO 该方法将传入的message字符串进行处理，然后return一个ChatMessage实例。
    public static MessagePackage buildFrom(String message){
        MessagePackage ret = new MessagePackage();
        int fromStringEnd = message.indexOf(MESSAGE_SEP);
        ret.from = message.substring(0,fromStringEnd);
        //System.out.println(fromEnd);
        int toStringEnd = message.indexOf(MESSAGE_SEP,fromStringEnd + 1);
        ret.to = message.substring(fromStringEnd + 1, toStringEnd);
        //System.out.println(toEnd);
        ret.message = message.substring(toStringEnd + 1).trim();
        //System.out.println(ret.message);
        return ret;
    }

    // TODO 该方法用StringBuilder,把一个ChatMessage实例的各组成部分拼接为一个完整的字符串，然后return。
    public String toMessageString(){
        StringBuilder ret = new StringBuilder();
        ret.append(from).append(MESSAGE_SEP).append(to).append(MESSAGE_SEP).append(message).append(MESSAGE_BREAK);
        return ret.toString();
    }

//    public static void main(String[] args) {
//        MessagePackage chatMessage = new MessagePackage("testFrom", "testTo","这是一条测试信息。");
//        String strMessage = chatMessage.toMessageString();
//        System.out.println(strMessage);
//        MessagePackage parsed = buildFrom(strMessage);
//        System.out.println(parsed.equals(chatMessage));
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessagePackage)) return false;
        MessagePackage that = (MessagePackage) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, message);
    }

    public String getMessage(){return this.message;}

    public String getTo(){return this.to;}

    public String getFrom(){return this.from;}
}
