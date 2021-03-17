package File;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreateDirAndFileMain {
    // >> TODO "."表示当前程序运行的路径。
    private static final String ROOT = "G:" + File.separator + "Test";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        File dir = createDirs();

        //File newDir = renameDir(dir);
    }

    private static File createDirs(){
        List<String> pathList = new ArrayList<>();
        while(true) {
            System.out.println("请输入文件路径，如果直接回车，结束。");
            // >> TODO nextLine可获取到按回车之前的所有字符，可带空格。
            String path = scanner.nextLine();
            if(path.isBlank()){ break; }
            pathList.add(path);
        }
        // >> TODO 如果想返回一个String[]，需要传一个String的实例去触发泛型。不传参数默认返回Object[]。
        return createDir(pathList.toArray(new String[0]));
    }

    private static File createDir(String... restPaths){
        String rest = joinRestDir(restPaths);
        System.out.println("将在" + ROOT + "下创建" + rest);
        File dir = new File(ROOT, rest);
        // >> TODO exists:是文件/文件夹，存在返回true。isDirectory:是文件夹，存在返回true。
        if(dir.exists() && dir.isDirectory()){
            System.out.println("文件夹已经存在" + dir.toString());
            return dir;
        }else{
            // >> TODO mkdir：创建一级目录。mkdirs：创建多级目录。
            boolean createSuccess = dir.mkdirs();
            if(createSuccess){
                return dir;
            }else{
                throw new IllegalArgumentException("无法在" + ROOT + "下创建" + rest);
            }
        }
    }

    private static String joinRestDir(String... restPaths){
        // >> TODO Arrays.stream(数组/Collection)会创建一个Stream类的实例
        return Arrays.stream(restPaths).map(String::trim).collect(Collectors.joining(File.separator));
    }

    private static File renameDir(File dir){
        System.out.println("请输入新的文件夹的名字：");
        String newDirName = scanner.nextLine().trim();

        File newDir = new File(dir.getParentFile(), newDirName);
        boolean renameSuccess = dir.renameTo(newDir);

        if(renameSuccess){
            System.out.println("改名为" + newDirName + "成功");
        }else {
            System.out.println("改名为" + newDirName + "失败");
            return null;
        }
        return newDir;
    }
}
