import java.util.Scanner;

public class ScoreMaster {
    public static void main(String[] args) {
        /*
          本程序为一个成绩管理工具，共4个功能
          1、求某年的最好成绩
          2、求某年的平均成绩
          3、求所有年份的最好成绩
          4、求某门课历年最好成绩
         */
        //创建一个长度为6的数组names来保存语、数、外、物、化、生的科目名字
        String[] names = new String[6];
        names[0] = "语文";
        names[1] = "数学";
        names[2] = "英语";
        names[3] = "物理";
        names[4] = "化学";
        names[5] = "生物";

        System.out.println("1、求某年的最好成绩\n" + "2、求某年的平均成绩\n" + "3、求所有年份中的最好成绩\n"+"4、求某门课历年最好成绩\n" + "请输入数字选择您需要的功能：");

        //录入用户输入的选择
        Scanner in = new Scanner(System.in);
        int choise = in.nextInt();

        //用switch语句来执行不同的功能
        switch (choise){
            case 1:
                System.out.println("功能1");
                break;
            case 2:
                System.out.println("功能2");
                break;
            case 3:
                System.out.println("功能3");
                break;
            case 4:
                System.out.println("功能4");
                break;
            default:
                System.out.println("程序结束。");
                break;
        }
    }
}
