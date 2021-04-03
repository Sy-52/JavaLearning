package File;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ReadFromFileMain {
    private static final String SOURCE_FILE_NAME = "游仙人诗.txt";

    public static void main(String[] args) {
        File sourceFile = new File("G:",File.separator + "Test" + File.separator + SOURCE_FILE_NAME);

        // TODO inputStream只能读取标准输入里的byte，而Scanner可以将从控制台中读取到的不同类型的数据解析为byte。
        // TODO 上面这句是我的理解，下面这句还需研究。
        Scanner in = new Scanner(System.in);
        //classicWay(sourceFile);

        coolerWay(sourceFile);
    }

    private static void classicWay(File file){
        try(
                // >> TODO 建立从文件->本程序的input byte流
                FileInputStream fis = new FileInputStream(file);
                // >> TODO 用指定的字符集解码byte流，将读出的byte转为charactor.
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                // >> TODO BufferedReader是Reader抽象类的实现类，负责将InputStreamReader解码好的字符流读到内部缓冲区。
                // >> TODO 一次性尽量从文件中读取数据放满缓存，直到缓存数据不足才会再次从文件中读取。
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
