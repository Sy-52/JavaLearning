package Common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// TODO Constants类用于保存一些常量。
public interface Constants {
    Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    int SERVER_PORT = 45678;

    char MESSAGE_SEP = Character.UNASSIGNED;

    char MESSAGE_BREAK = '\n';
    // TODO 在网络对话中，空格是一个有特殊意义的分隔符。
    String SPACE_MESSAGE = " ";

    String CHAT_START_CHARACTOR = "@";

    String ADMIN_NAME = "admin";

    String INTRODUCTION = "欢迎来到聊天室，你可以使用\"@admin list\"来产看所有在线用户，并且使用\"@用户民\"进行聊天。";

    String SERVER_COMMAND_LOGOFF = "logoff";

    String SERVER_COMMAND_LIST = "list";

    String BYE = "bye";
}
