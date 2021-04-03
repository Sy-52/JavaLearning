package File;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CreateDirAndFileMain {
    // >> TODO "."表示当前程序运行的路径。File.separator表示当前操作系统的文件路径分隔符。
    private static final String ROOT = "G:" + File.separator + "Test";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File dir = createDirs();

        File renameDir = renameDir(dir);

        String fileName = createFile(renameDir);

        String renameFileName = renameFile(renameDir, fileName);

        deleteFile(renameDir, renameFileName);

        deleteDir(renameDir);
    }

    private static File createDirs(){
        List<String> pathList = new ArrayList<>();
        while(true) {
            System.out.println("请输入文件路径，如果直接输入回车，结束。");
            // >> TODO nextLine可获取到按回车之前的所有字符，可带空格。与next不同。
            String path = scanner.nextLine();
            if(path.isBlank()){ break; }
            pathList.add(path);
        }
        // >> TODO 如果想返回一个String[]，需要传一个String的实例去触发泛型。不传参数默认返回Object[]。
        return createDir(pathList.toArray(new String[0]));
    }

    private static File createDir(String... restPaths){
        // >> TODO Arrays.stream(数组实例/Collection实例)会创建一个Stream类的实例
        String restDir = Arrays.stream(restPaths).map(String::trim).collect(Collectors.joining(File.separator));
        System.out.println("将在" + ROOT + "下创建" + restDir);
        File dir = new File(ROOT, restDir);
        // >> TODO exists:若文件/文件夹存在，返回true。isDirectory:若文件夹存在，返回true。
        if(dir.exists() && dir.isDirectory()){
            System.out.println("文件夹已经存在：" + dir.toString());
            return dir;
        }else{
            boolean createSuccess = dir.mkdirs();
            if(createSuccess){
                return dir;
            }else{
                throw new IllegalArgumentException("无法在" + ROOT + "下创建" + restDir);
            }
        }
    }

    private static File renameDir(File oldDir){
        System.out.println("请输入新的文件夹的名字：");
        String restDir = scanner.nextLine().trim();
        // TODO 为什么功能为更改"最后"一级目录的名称，谜底就在getParentFile方法.
        File renameDir = new File(oldDir.getParentFile(), restDir);
        boolean renameSuccess = oldDir.renameTo(renameDir);
        if(renameSuccess){
            System.out.println("改名为" + restDir + "成功");
        }else {
            System.out.println("改名为" + restDir + "失败");
            return null;
        }
        return renameDir;
    }

    private static String createFile(File dir) throws IOException {
        System.out.println("请输入要创建文件的名称：");
        String fileName = scanner.nextLine().trim();
        File file = new File(dir,fileName + ".txt");
        // >> TODO createNewFile其实会返回一个布尔值
        file.createNewFile();
        System.out.println("创建文件" + file.getName());
        return fileName;
    }

    private static String renameFile(File dir, String fileName){
        System.out.println("请输入重命名的名称：");
        String newFileName = scanner.nextLine().trim();
        File oldFile = new File(dir, fileName + ".txt");
        File renameFile = new File(dir, newFileName + ".txt");
        oldFile.renameTo(renameFile);
        System.out.println("已重命名文件" + oldFile.getName() + "为" + renameFile.getName());
        return newFileName;
    }

    private static void deleteFile(File dir, String fileName){
        System.out.println("是否删除文件" + fileName + "？");
        boolean input  = scanner.nextBoolean();
        if(input){
            File file = new File(dir, fileName + ".txt");
            file.delete();
            System.out.println("删除" + fileName + "成功！");
        }
    }

    private static void deleteDir(File dir){
        System.out.println("是否删除文件夹？");
        boolean input  = scanner.nextBoolean();
        if(input){
            System.out.println("删除" + dir + "成功！");
            dir.delete();
        }
    }
}
