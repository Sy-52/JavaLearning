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

        //变量year用以录入用户输入的年份，变量name用以录入用户输入的科目名
        int year;
        int name;

        //创建二维数组，并在其中随机生成9年的6科成绩,并输出
        double[][] scores = new double[9][6];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 6; j++){
                scores[i][j] = 70 + Math.random() * 30;
                System.out.println("第" + (i+1) + "年的" + names[j] + "成绩为：" + scores[i][j]);
            }
        }

        //用switch语句来执行不同的功能
        switch (choise){
            case 1:
                System.out.println("请输入要求哪一年的最好成绩：");
                year = in.nextInt();
                double bestScore = 0.0;
                int bestScoreName = 0;
                for(int i = 0; i < 6; i++){
                    if(scores[year - 1][i] > bestScore){
                        bestScore = scores[year - 1][i];
                        bestScoreName = i;
                    };
                }
                System.out.println("第" + year + "年的最好成绩为:" + bestScore + ".科目为：" + names[bestScoreName]);
                break;
            case 2:
                System.out.println("请输入要求哪一年的平均成绩：");
                year = in.nextInt();
                double totalScore = 0.0;
                int averScoreName = 0;
                for(int i = 0; i < 6; i++){
                    totalScore += scores[year - 1][i];
                }
                System.out.println("第" + year + "年的平均成绩为：" + totalScore/6);
                break;
            case 3:
                double bestOfAllScore = 0.0;
                int bestOfAllScoreYear = 0;
                int bestOfAllScoreName = 0;
                for(int i = 0;i < 9; i++){
                    for(int j = 0; j < 6; j++){
                        if(scores[i][j] > bestOfAllScore){
                            bestOfAllScore = scores[i][j];
                            bestOfAllScoreYear = i;
                            bestOfAllScoreName = j;
                        }
                    }
                }
                System.out.println("所有年份中的最好成绩为,第" + (bestOfAllScoreYear+1) + "年的" + names[bestOfAllScoreName] + ".其成绩为：" + bestOfAllScore);
                break;
            case 4:
                System.out.println("请输入您要查询的科目:");
                name = in.nextInt();
                double bestProjectScore = 0.0;
                int bestProjectYear = 0;
                for(int i = 0; i < 9; i++){
                    if(scores[i][name - 1] > bestProjectScore){
                        bestProjectScore = scores[i][name - 1];
                        bestProjectYear = i;
                    }
                }
                System.out.println(names[name-1] + "的历年最好成绩为第" + (bestProjectYear+1) + "年.成绩为：" + bestProjectScore);
                break;
            default:
                System.out.println("程序结束。");
                break;
        }
    }
}
