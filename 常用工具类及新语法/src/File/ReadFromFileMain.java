package File;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadFromFileMain {
    private static final String SOURCE_FILE_NAME = "游仙人诗.txt";

    public static void main(String[] args) {
        File sourceFile = new File("G:\\Test" + File.separator + SOURCE_FILE_NAME);

        // >> TODO inputStream只能读取标准输入里的byte，而Scanner可以将这个byte读取出来转换为String，进而转换成不同的数据类型。
        Scanner in = new Scanner(System.in);

        //classicWay(sourceFile);

        coolerWay(sourceFile);
    }

    private static void classicWay(File file){
        try(
                // >> TODO 建立从文件->程序的input流
                FileInputStream fis = new FileInputStream(file);
                // >> TODO 用指定的字符集装饰一下byte流，使之将读出的byte转为字符
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                // >> TODO BufferedReader是Reader抽象类的实现类，是一个对字节流的封装类（字符流）。
                // >> TODO 一次可能读取大量的数据，用缓存将读取的数据存起来，你要用多少给多少
                BufferedReader reader = new BufferedReader(isr);
                ){
            String line = null;
            // >> TODO (a = 9)赋值完成后会返回a
            while((line = reader.readLine()) != null){
                System.out.println(line.trim());
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void coolerWay(File file){
        try(
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(isr);
        ){
                reader.lines().map(String::trim).forEach(System.out::println);
            } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
