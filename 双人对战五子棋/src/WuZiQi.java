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

        //results这个整形数组，用以保存黑白方的胜出次数
        int[] results = new int[2];

        //qizi、qiziJustMove这两个字符型数组用以表示【已经下了】、【刚刚才下的】三种不同的符号
        char[] qizi = new char[]{'●', '○', ' '};
        char[] qiziJustMove = new char[]{'■', '□', ' '};

        //二位数组checkConnectedDirection用以在四个方向上进行检测，direction数组用以打印相应的结果.
        int[][] checkConnectedDirection = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        String[] direction = new String[]{"垂直", "水平", "斜向下", "斜向上"};

        //scanner变量用以接收用户输入
        Scanner scanner = new Scanner(System.in);

        //黑白双方输入自己的ID
        System.out.println("请输入黑子方玩家姓名：");
        names[0] = scanner.nextLine();
        System.out.println("请输入白子方玩家姓名：");
        names[1] = scanner.nextLine();
        System.out.println("黑子方玩家姓名为：" + names[0] + ".白子方玩家姓名为：" + names[1]);

        //2 - 输入棋盘大小并做范围检测：用户输入棋盘大小。棋盘要大于5，小于100。
        //变量currRole用以表明下子的玩家是谁
        int currRole = 0;

        boolean play = true;
        while (play) {
            //用户输入棋盘范围，并检测
            boolean hasWinner = false;
            int size = 0;
            while (true) {
                System.out.println("请输入棋盘大小.注：棋盘应大于5，小于100.");
                size = scanner.nextInt();
                if (size < 5 || size > 100) {
                    System.out.println("输入的棋盘范围错误，请重新输入！");
                } else {
                    break;
                }
            }

            //初始化棋盘,将里面的值全部设置为空
            int[][] qipan = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    qipan[i][j] = 2;
                }
            }

            //变量justMoveLine、justMoveColumn用以保存黑白方下子的行、列（共用）
            int justMoveLine = 0;
            int justMoveColumn = 0;

            while (true) {
                //轮流记录黑白双方的落子，并把每一轮下完子的棋盘实况打印出来
                while (true) {
                    //对应玩家落子
                    while (true) {
                        System.out.println("请" + roleNames[currRole] + "输入落子的行：");
                        justMoveLine = scanner.nextInt() - 1;
                        if ((justMoveLine + 1) < 1 || (justMoveLine + 1) > size) {
                            System.out.println("输入的行超过棋盘范围！请重新输入！");
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("请" + roleNames[currRole] + "输入落子的列：");
                        justMoveColumn = scanner.nextInt() - 1;
                        if ((justMoveColumn + 1) < 1 || (justMoveColumn + 1) > size) {
                            System.out.println("输入的列超过棋盘范围！请重新输入！");
                        } else {
                            break;
                        }
                    }

                    if (qipan[justMoveLine][justMoveColumn] == 2) {
                        qipan[justMoveLine][justMoveColumn] = currRole;
                        break;
                    } else {
                        System.out.println("棋盘该处已经有子，请重新输入！");
                    }
                }

            /*
                 下面这段程序为【画棋盘】。
                 我的原程序体现的思路没有臧萌老师的细腻，我直接创建一个size为20+2的二维数组，然后从上到下，从左到右画。
                 这种【直男思路】造成的问题：在i、j的嵌套for循环中，需要用if...else进行【多层的】条件判断。
                 这样导致程序的逻辑关系变得非常复杂，一旦出了问题，非常难以定位问题所在...最后只能把自己搞的很崩溃。
            */

                String header = " \t";
                for (int i = 0; i < size; i++) {
                    header += (i + 1) + "\t";
                }
                System.out.println(header + "\t");

                for (int i = 0; i < size; i++) {
                    String line = (i + 1) + "\t";
                    for (int j = 0; j < size; j++) {
                        char[] arrayToUse = (i == justMoveLine && j == justMoveColumn) ? qiziJustMove : qizi;
                        line += arrayToUse[qipan[i][j]] + "\t";
                    }
                    System.out.println(line + (i + 1));
                }
                System.out.println(header + "\t");

                //一个简简单单的break，居然引起了unreachable statement错误...
                //break;

            /*
                  下面的程序为五子棋胜负的条件检测。
                  每次以当前下子的点为起点，对【垂直、水平、左上右下、左下右上】四个方向进行检测。
                  每个方向上检测两次。（以水平为例...水平方向上需检查左侧和右侧。其他方向同理）
                  一旦检测在一个方向上找到相同值，就进行累计。一旦连起来的子达到5，判定相应的玩家获得胜利。
            */

                //变量lineToCheck、ColumnToCheck用以保存用户下子的行、列,用以进行检测操作
                int lineToCheck = justMoveLine;
                int ColumnToCheck = justMoveColumn;

                //变量totalConnected用以存储连起来的子的数量
                int totalConnected = 1;

                //变量deltaLine、deltaColumn用以存储二位数组checkConnectedDirection的行列值，以便在4个方向上进行检测
                int deltaLine = 0;
                int deltaColumn = 0;

                //开始检测
                for (int i = 0; i < 4; i++) {
                    deltaLine = checkConnectedDirection[i][0];
                    deltaColumn = checkConnectedDirection[i][1];
                    for (int j = 0; j < 2; j++) {
                        deltaLine *= -1;
                        deltaColumn *= -1;
                        while (totalConnected < 5) {
                            //考虑落子在行列边界的情况...
                            if(lineToCheck + deltaLine < 0 || ColumnToCheck + deltaColumn < 0){
                                break;
                            }
                            if (qipan[lineToCheck + deltaLine][ColumnToCheck + deltaColumn] == currRole) {
                                totalConnected++;
                                //就下面这两句...我调了多久知道吗？哭了
                                lineToCheck = lineToCheck + deltaLine;
                                ColumnToCheck = ColumnToCheck + deltaColumn;
                            } else {
                                totalConnected = 1;
                                //重置lineToCheck、ColumnToCheck
                                lineToCheck = justMoveLine;
                                ColumnToCheck = justMoveColumn;
                                break;
                            }
                        }
                    }
                    if (totalConnected == 5) {
                        results[currRole]++;
                        hasWinner = true;
                        System.out.println(names[currRole] + "胜出！棋子连接的顺序为：" + direction[i]);
                        System.out.println("目前" + names[0] + "胜出" + results[0] + "次，" + names[1] + "胜出" + results[1] + "次。");
                        //无论选择true/false，都要从检测循环中跳出
                        break;
                    }
                }
                //换对方下子
                currRole = (currRole + 1) % 2;

                if(hasWinner == true){
                    System.out.println("是否想再来一局？");
                    play = scanner.nextBoolean();
                    //重置currRole，下一把仍然黑方先行
                    currRole = 0;
                    //play作为开关，若用户选择true，则while(play)继续循环；若用户选择false，则while(play)不在循环，程序结束。
                    break;
                }
            }
        }
    }
}
