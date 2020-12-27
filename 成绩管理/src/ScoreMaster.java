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
        //臧萌老师用了一个变量totalSubjectCount来保存总科目数量...这样科目数有变动的话修改变量即可
        //其实不光totalSubjectCount，还可创建变量totalYearCount来保存总年份（在4个功能中这两个变量可以被反复使用）
        String[] names = new String[6];
        names[0] = "语文";
        names[1] = "数学";
        names[2] = "英语";
        names[3] = "物理";
        names[4] = "化学";
        names[5] = "生物";

        //臧萌老师把这一句放在开关循环里了，除非用户主动退出，否则将一直循环。
        System.out.println("1、求某年的最好成绩\n" + "2、求某年的平均成绩\n" + "3、求所有年份中的最好成绩\n"+"4、求某门课历年最好成绩\n" + "请输入数字选择您需要的功能：");

        //录入用户输入的选择
        Scanner in = new Scanner(System.in);
        int oprtId = in.nextInt();

        //变量year用以录入用户输入的年份，变量name用以录入用户输入的科目名
        int year;
        int name;

        //创建二维数组，并在其中随机生成9年的6科成绩,并输出
        //臧萌老师这里用变量yearCount来保存用户选择的年份
        double[][] scores = new double[9][6];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 6; j++){
                scores[i][j] = 70 + Math.random() * 30;
                System.out.println("第" + (i+1) + "年的" + names[j] + "成绩为：" + scores[i][j]);
            }
        }

        //细节1：臧萌老师在switch语句外边套了一个while循环，创建布尔类型变量cont来进行开关，如果用户查询完毕可输入其他数字来结束掉循环
        //用switch语句来执行不同的功能
        switch (oprtId){
            case 1:
                System.out.println("请输入要求哪一年的最好成绩：");
                year = in.nextInt();
                //细节2:臧萌老师在这里加入了输入数据的非法数据判断
                //细节3：臧萌老师对于下面的操作，直接用bestOfYearScoreId在该年从0~5比一轮，将最高成绩的科目id赋给bestOfYearScoreId即可。
                double bestOfYearScore = 0.0;
                //bestScore是多余的
                //拓展1:如果有两门课成绩一样，且都是最高，怎么办？
                int bestOfYearScoreId = 0;
                for(int i = 0; i < 6; i++){
                    if(scores[year - 1][i] > bestOfYearScore){
                        bestOfYearScore = scores[year - 1][i];
                        bestOfYearScoreId = i;
                    };
                }
                System.out.println("第" + year + "年的最好成绩为:" + bestOfYearScore + ".科目为：" + names[bestOfYearScoreId]);
                break;
            case 2:
                System.out.println("请输入要求哪一年的平均成绩：");
                year = in.nextInt();
                //细节2:臧萌老师在这里加入了输入数据的非法数据判断
                double totalCountForAvg = 0.0;
                for(int i = 0; i < 6; i++){
                    totalCountForAvg += scores[year - 1][i];
                }
                //最后的6其实可以抽出来用变量代替，写在最上面
                System.out.println("第" + year + "年的平均成绩为：" + totalCountForAvg/6);
                break;
            case 3:
                double bestScore = 0.0;
                //bestScore是多余的...和上面的问题一样
                int bestYear = 0;
                int bestScoreId = 0;
                for(int i = 0;i < 9; i++){
                    for(int j = 0; j < 6; j++){
                        if(scores[i][j] > bestScore){
                            bestScore = scores[i][j];
                            bestYear = i;
                            bestScoreId = j;
                        }
                    }
                }
                System.out.println("所有年份中的最好成绩为,第" + (bestYear+1) + "年的" + names[bestScoreId] + ".其成绩为：" + bestScore);
                break;
            case 4:
                System.out.println("请输入您要查询的科目:");
                name = in.nextInt();
                //细节2:臧萌老师在这里加入了输入数据的非法数据判断
                double bestSubjectScore = 0.0;
                //bestProjectScore是多余的...问题同上和上。完全不用创建变量bestProjectYear，直接把上面的year拿来用即可
                int bestSubjectYear = 0;
                for(int i = 0; i < 9; i++){
                    if(scores[i][name - 1] > bestSubjectScore){
                        bestSubjectScore = scores[i][name - 1];
                        bestSubjectYear = i;
                    }
                }
                System.out.println(names[name-1] + "的历年最好成绩为第" + (bestSubjectYear+1) + "年.成绩为：" + bestSubjectScore);
                break;
            default:
                //臧萌老师写了一个开关cont。continue的缩写。
                System.out.println("程序结束。");
                break;
        }
    }
}
