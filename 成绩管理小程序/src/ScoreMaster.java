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
        //臧萌老师用了一个变量totalSubjectCount来保存总科目数量...这样科目数有变动的话修改变量即可
        //其实不光totalSubjectCount，还可创建变量totalYearCount来保存总年份（在4个功能中这两个变量可以被反复使用）

        //创建一个长度为6的数组names来保存语、数、外、物、化、生的科目名字
        String[] names = new String[6];
        names[0] = "语文";
        names[1] = "数学";
        names[2] = "英语";
        names[3] = "物理";
        names[4] = "化学";
        names[5] = "生物";

        Scanner in = new Scanner(System.in);

        //创建二维数组，并在其中随机生成9年的6科成绩,并输出
        int yearCount = 0;
        System.out.println("请输入共有多少年的成绩：");
        yearCount = in.nextInt();
        double[][] scores = new double[yearCount][6];
        for(int i = 0; i < yearCount; i++){
            for(int j = 0; j < 6; j++){
                scores[i][j] = 70 + Math.random() * 30;
                System.out.println("第" + (i+1) + "年的" + names[j] + "成绩为：" + scores[i][j]);
            }
        }

        //year用以记录用户输入的年份，name用以记录用户输入的科目名
        int year;
        int name;

        //用switch语句来执行不同的功能
        boolean cont = true;
        while(cont) {
            System.out.println("1、求某年的最好成绩\n" + "2、求某年的平均成绩\n" + "3、求所有年份中的最好成绩\n"+"4、求某门课历年最好成绩\n" + "请输入数字选择您需要的功能：(输入-1程序结束。)");
            //用户选择要使用的功能
            int oprtId = in.nextInt();

            switch (oprtId) {
                case 1:
                    System.out.println("请输入要求哪一年的最好成绩：");
                    year = in.nextInt();
                    //臧萌老师在这里加入了输入数据的非法输入的处理
                    //臧萌老师下面的操作，直接用bestOfYearScoreId在该年从0~5比一轮，将最高成绩的科目id赋给bestOfYearScoreId即可。
                    //我写的bestScore就显得多余了...我此处的渣操作做一个保留。（后面的程序全部修改。）
                    double bestOfYearScore = 0.0;
                    //程序进阶:如果有两门课成绩一样，且都是最高，怎么办？
                    int bestOfYearScoreId = 0;
                    for (int i = 0; i < 6; i++) {
                        if (scores[year - 1][i] > bestOfYearScore) {
                            bestOfYearScore = scores[year - 1][i];
                            bestOfYearScoreId = i;
                        }
                        ;
                    }
                    System.out.println("第" + year + "年的最好成绩为:" + bestOfYearScore + ".科目为：" + names[bestOfYearScoreId] + "\n");
                    break;
                case 2:
                    System.out.println("请输入要求哪一年的平均成绩：");
                    year = in.nextInt();
                    //臧萌老师在这里加入了输入数据的非法输入的处理
                    double totalCountForAvg = 0.0;
                    for (int i = 0; i < 6; i++) {
                        totalCountForAvg += scores[year - 1][i];
                    }
                    //最后的6其实可以抽出来用变量代替，写在最上面
                    System.out.println("第" + year + "年的平均成绩为：" + totalCountForAvg / 6 + "\n");
                    break;
                case 3:
                    int bestYear = 0;
                    int bestScoreId = 0;
                    for (int i = 0; i < yearCount; i++) {
                        for (int j = 0; j < 6; j++) {
                            if (scores[i][j] > scores[bestYear][bestScoreId]) {
                                bestYear = i;
                                bestScoreId = j;
                            }
                        }
                    }
                    System.out.println("所有年份中的最好成绩为,第" + (bestYear + 1) + "年的" + names[bestScoreId] + ".其成绩为：" + scores[bestYear][bestScoreId] + "\n");
                    break;
                case 4:
                    System.out.println("请输入您要查询的科目:");
                    name = in.nextInt();
                    //臧萌老师在这里加入了输入数据的非法输入的处理
                    int bestSubjectYear = 0;
                    for (int i = 0; i < yearCount; i++) {
                        if (scores[i][name - 1] > scores[bestSubjectYear][name - 1]) {
                            bestSubjectYear = i;
                        }
                    }
                    System.out.println(names[name - 1] + "的历年最好成绩为第" + (bestSubjectYear + 1) + "年.成绩为：" + scores[bestSubjectYear][name - 1] + "\n");
                    break;
                default:
                    cont = false;
                    System.out.println("程序结束。");
                    break;
            }
        }
    }
}
