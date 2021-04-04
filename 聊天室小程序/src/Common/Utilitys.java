package Common;

import static Common.Constants.ADMIN_NAME;

public class Utilitys {
    public static String isValidToUserName(String userName){
        String ret = userNameCheck(userName);
        if(ret != null){
            return ret;
        }

        if(userName.trim().contains(ADMIN_NAME)){
            return "用户名中不可以包含:" + ADMIN_NAME;
        }

        return null;
    }

    public static String userNameCheck(String userName){
        if(userName.trim().length() == 0){
            return "用户名不可以为空！";
        }

        if(userName.contains(" ")){
            return "用户名中不可以含有空格！";
        }

        return null;
    }
}
