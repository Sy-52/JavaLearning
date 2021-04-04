package Common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

// TODO Constants类用于保存一些常量。
public interface Constants {
    Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    int SERVER_PORT = 45678;

    char MESSAGE_SEP = Character.UNASSIGNED;

    String SPACE_STRING = " ";

    String CHAT_START_CHARACTOR = "@";

    String ADMIN_NAME = "admin";

    String USERNAME_PASS_FLAG = "userNamePass";

    String INTRODUCTION = "欢迎来到聊天室!你可以使用\"@admin list\"来查看所有在线用户，并使用\"@用户名\"命令进行聊天。";

    String SERVER_COMMAND_LOGOFF = "logoff";

    String SERVER_COMMAND_LIST = "list";
}
