package Common;

import java.util.Objects;

import static Common.Constants.*;

// TODO ChatMessage类描述了一条消息:来自谁、发送给谁、消息是啥。
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

    // TODO buildFrom[静态]方法会将传入的字符串进行分割，然后打包出一个ChatMessage实例返回。
    public static MessagePackage buildFrom(String message){
        MessagePackage ret = new MessagePackage();
        int fromStringEnd = message.indexOf(MESSAGE_SEP);
        ret.from = message.substring(0,fromStringEnd);
        int toStringEnd = message.indexOf(MESSAGE_SEP,fromStringEnd + 1);
        ret.to = message.substring(fromStringEnd + 1, toStringEnd);
        ret.message = message.substring(toStringEnd + 1).trim();
        return ret;
    }

    // TODO MessagePackageToString方法会将一个ChatMessage实例的各组成部分拼接为一个完整的字符串，然后返回。
    public String MessagePackageToString(){
        StringBuilder ret = new StringBuilder();
        ret.append(from).append(MESSAGE_SEP).append(to).append(MESSAGE_SEP).append(message).append("\n");
        return ret.toString();
    }

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
