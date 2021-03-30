import java.util.Scanner;

public class WuZiQi {
    public static void main(String[] args) {
        System.out.println("************************************");
        //臧萌老师的小细节：下面这一行不用调右边的空格
        System.out.println("         欢迎来到双人五子棋对战");
        System.out.println("************************************");
        System.out.println("本五子棋游戏支持双人对战。");

        //names数组用以存储黑白方玩家ID；字符型数组roleNames用以输出字符串"黑方"、"白方"
        String[] names = new String[2];
        String[] roleNames = new String[]{"黑方", "白方"};

        //数组results用以保存黑、白方的总胜利次数
        int[] results = new int[2];

        //数组qizi、qiziJustMove用以表示【已经落的子】、【刚落的子】的不同表示符号
        char[] qizi = new char[]{'●', '○', ' '};
        char[] qiziJustMove = new char[]{'■', '□', ' '};

        //二位数组checkConnectedDirection用以在四个方向上进行检测，direction数组用以在获胜时输出相应的方向.
        int[][] checkConnectedDirection = new int[][]{{1, 0}, {0, 1}, {1, 1}, {1, -1}};
        String[] direction = new String[]{"垂直", "水平", "斜向下", "斜向上"};

        //scanner变量用以接收用户输入
        Scanner scanner = new Scanner(System.in);

        //变量currRole用以表明当前下子的玩家是谁
        int currRole = 0;

        //变量play用以作为游戏开关
        boolean play = true;
        while (play) {
            //每一轮游戏开始前，将hasWinner重置为false
            boolean hasWinner = false;

            //1 - 游戏准备
            //黑白双方输入自己的游戏ID
            System.out.println("请输入黑子方玩家姓名：");
            names[0] = scanner.nextLine();
            System.out.println("请输入白子方玩家姓名：");
            names[1] = scanner.nextLine();
            System.out.println("欢迎黑子方玩家：" + names[0] + ".白子方玩家：" + names[1]);

            //玩家设置棋盘范围（我方做输入检测）
            int size = 0;
            while (true) {
                System.out.println("请输入棋盘大小.注：棋盘应大于5，小于100.");
                size = scanner.nextInt();
                if (size < 5 || size > 100) {
                    System.out.println("输入的棋盘范围错误，请重新输入！");
                    //臧萌老师这个continue、break用的真漂亮
                    continue;
                }
                break;
            }

            //初始化棋盘,并初始化（2代表空，其实可以把2付给一个变量empty，提到前面去）
            int[][] qipan = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    qipan[i][j] = 2;
                }
            }

            //变量justMoveLine、justMoveColumn用以保存黑、白方下子的行和列
            int justMoveLine = 0;
            int justMoveColumn = 0;

            //2 - 开始游戏
            while (true) {
                //玩家输入落子的行、列(我方做条件检测)
                while (true) {
                    while (true) {
                        System.out.println("请" + roleNames[currRole] + "输入落子的行：");
                        justMoveLine = scanner.nextInt() - 1;
                        if ((justMoveLine + 1) < 1 || (justMoveLine + 1) > size) {
                            System.out.println("输入的行超过棋盘范围！请重新输入！");
                            continue;
                        }
                        break;
                    }

                    while (true) {
                        System.out.println("请" + roleNames[currRole] + "输入落子的列：");
                        justMoveColumn = scanner.nextInt() - 1;
                        if ((justMoveColumn + 1) < 1 || (justMoveColumn + 1) > size) {
                            System.out.println("输入的列超过棋盘范围！请重新输入！");
                            continue;
                        }
                        break;
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
                 臧萌老师的【画棋盘】程序则显得很精炼。
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

                //lineToCheck、ColumnToCheck用以拷贝玩家落子的行、列,用以进行检测操作
                int lineToCheck = justMoveLine;
                int ColumnToCheck = justMoveColumn;

                //totalConnected用以存储连子的数量
                int totalConnected = 1;

            /*
                  下面程序为棋局的胜负检测。
                  以当前下子的点做为起点，在【垂直、水平、斜向下、斜向上】四个方向进行检测。
                  每个方向上往前往后共检测两次。在一个方向上若找到同一玩家落的子，则进行累计。一旦连子数量达到5，该玩家获胜。
            */
                for (int i = 0; i < 4; i++) {
                    //变量deltaLine、deltaColumn用以获取二位数组checkConnectedDirection在四个方向上的行列值，以具体的检测操作
                    int deltaLine = checkConnectedDirection[i][0];
                    int deltaColumn = checkConnectedDirection[i][1];
                    for (int j = 0; j < 2; j++) {
                        deltaLine *= -1;
                        deltaColumn *= -1;
                        while (totalConnected < 5) {
                            //考虑落子在上边界、下边界、四个角的情况，避免数组访问越界而导致程序出错。
                            if(lineToCheck + deltaLine < 0 || lineToCheck + deltaLine >= size || ColumnToCheck + deltaColumn < 0 || ColumnToCheck + deltaColumn >= size){
                                /*
                                  假设棋盘大小为10。
                                  如果你落子(3,3)，斜左上方检测到(0,0)时，如果不重置totalConnected，那么totalConnected会为3。
                                  如果你落子(1,10)，水平向右检查会直接越界
                                 */
                                totalConnected = 1;
                                break;
                            }
                            if (qipan[lineToCheck + deltaLine][ColumnToCheck + deltaColumn] == currRole) {
                                totalConnected++;
                                //下面这两句...调了N久...
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
                        //无论四个方向全部检测与否，只要有5连，就要跳出循环
                        break;
                    }
                }
                if(hasWinner){
                    break;
                }
                //换对方下子
                currRole = (currRole + 1) % 2;
            }

            System.out.println("是否想再来一局？");
            play = scanner.nextBoolean();
            //下面这一句是为了防止下一轮循环黑方输入被吞
            scanner.nextLine();
        }
    }
}
