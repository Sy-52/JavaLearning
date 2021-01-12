import java.util.Scanner;

public class WuZiQi {
    public static void main(String[] args) {
        //1 - 游戏引导：双人对战，输入黑白方的姓名
        System.out.println("************************************");
        System.out.println("         欢迎来到双人五子棋对战         ");
        System.out.println("************************************");
        System.out.println("本五子棋游戏支持双人对战。");
        //names这个字符型数组，用以存储黑白方玩家ID
        String[] names = new String[2];

        //roleNames这个字符型数组，用以输出"黑方"、"白方"
        String[] roleNames = new String[]{"黑方", "白方"};

        //qizi、qiziJustMove这两个字符型数组用以表示【已经下了】、【刚刚才下的】三种不同的符号
        char[] qizi = new char[]{'●', '○', ' '};
        char[] qiziJustMove = new char[]{'■', '□', ' '};

        //scanner变量用以接收用户输入
        Scanner scanner = new Scanner(System.in);

        //用户输入棋盘范围，并检测
        int size = 0;
        while (true) {
            System.out.println("请输入棋盘大小.注：棋盘应大于5，小于100.");
            size = scanner.nextInt();
            if (size < 5 || size > 100) {
                System.out.println("输入的棋盘范围错误，请重新输入！");
            } else {
                //黑白双方输入自己的ID
                scanner.nextLine();
                System.out.println("请输入黑子方玩家姓名：");
                names[0] = scanner.nextLine();
                System.out.println("请输入白子方玩家姓名：");
                names[1] = scanner.nextLine();
                System.out.println("黑子方玩家姓名为：" + names[0] + ".白子方玩家姓名为：" + names[1]);
                break;
            }
        }

        //2 - 输入棋盘大小并做范围检测：用户输入棋盘大小。棋盘要大于5，小于100。
        //变量currRole用以表明下子的玩家是谁
        int currRole = 0;

        //初始化棋盘
        int[][] qipan = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                qipan[i][j] = 2;
            }
        }

        while(true){
            while (true) {
                //变量justMoveLine、justMoveColumn用以保存黑白方下子的行、列（共用）
                int justMoveLine = 0;
                int justMoveColumn = 0;

                //轮流记录黑白双方的落子，并把每一轮下完子的棋盘实况打印出来
                System.out.println("请" + roleNames[currRole] + "输入落子的行：");
                justMoveLine = scanner.nextInt() - 1;
                System.out.println("请" + roleNames[currRole] + "输入落子的列：");
                justMoveColumn = scanner.nextInt() - 1;

                /*
                       下面这段程序为【画棋盘】。
                       我的原程序体现的思路没有臧萌老师的细腻，我直接创建一个size为20+2的二维数组，然后从上到下，从左到右画。
                       我这种【直男思路】造成的问题：在i、j的嵌套for循环中，需要用if...else进行【多层的】条件判断。
                       这样导致程序的逻辑关系变得非常复杂，一旦出了问题，非常难以定位问题所在...最后只能把自己搞的很崩溃。
                */
                //初始化棋盘，将里面的值全部设置为空
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if (i == justMoveLine && j == justMoveColumn) {
                            qipan[i][j] = currRole;
                        }
                    }
                }

                String header = " \t";
                for (int i = 0; i < size; i++) {
                    header += (i + 1) + "\t";
                }
                System.out.println(header + "\t");

                for (int i = 0; i < size; i++) {
                    String line = (i + 1) + "\t";
                    for (int j = 0; j < size; j++) {
                        char[] arrayToUse = (i == justMoveLine && j == justMoveColumn)?qiziJustMove:qizi;
                        line += arrayToUse[qipan[i][j]] + "\t";
                    }
                    System.out.println(line + (i + 1));
                }
                System.out.println(header + "\t");
                //换对方下子
                currRole = (currRole + 1) % 2;
                break;
            }
        }
    }
}
