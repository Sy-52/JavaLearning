package File;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WriteToFileMain {
    public static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        File file = createFile();
        if(file == null)return;

        writeToFile(file);
    }

    private static File createFile() throws IOException {
        System.out.println("请输入文件名:");
        String fileName = in.nextLine().trim();
        File file = new File("G:" + File.separator + "Test" + File.separator + fileName +".txt");
        if(file.isFile()){
            file.delete();
            System.out.println("目标文件存在，删除之。");
            return null;
        }else {
            file.createNewFile();
            System.out.println("文件创建成功！");
        }
        return file;
    }

    private static void writeToFile(File file){
        try(
                // >> TODO 装饰模式（"套娃"模式），osw帮我们把charactor按照字符集转成byte，pw帮我们把string转为char。
                FileOutputStream fos = new FileOutputStream(file);
                // >> TODO 计算机的处理的都是byte。如果把搜集常用charactor做一个和byte的映射，就是字符集。
                // >> TODO OutputStreamWriter是Writer这个抽象类的实现类，是连接byte和charactor的桥梁。
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(osw);
                ){
            while(true){
                System.out.println("请输入内容：（输入回车结束）");
                String input = in.nextLine().trim();
                if(input.trim().isBlank()){
                    System.out.println("输入结束");
                    break;
                }else{
                    pw.println(input);
                    // >> TODO flush用以将缓存的内容全部写入文件。（通常写入足够数量的字节后才会一起写入文件。如果输入字符少，会导致无法实时显示）
                    pw.flush();
                }
            }
        }catch(Exception ex){
            // >> TODO IO过程中很容易出现异常。如文件不存在/没有写权限/文件在写入过程中被删/硬盘被拔了...
            ex.printStackTrace();
        }
    }
}
