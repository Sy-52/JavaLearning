import java.util.Scanner;

public class WuZiQi {
    public static void main(String[] args) {
        //1 - 游戏引导：双人对战，输入黑白方的姓名
        System.out.println("************************************");
        System.out.println("         欢迎来到双人五子棋对战         ");
        System.out.println("************************************");
        //创建一个【字符串型数组】names来存储黑白方玩家的姓名
        String[] names = new String[2];

        //创建变量roleJustMove、【字符串类型数组】roleJustMoveName来表明落子方玩家到底是谁
        int roleJustMove = 0;
        String[] roleJustMoveName = new String[]{"黑方","白方"};

        //创建【字符型数组】qizi、qiziJustMove来表示【已经下了】、【刚刚才下的】三种不同的符号
        char[] qizi = new char[]{'●','○',' '};
        char[] qiziJustMove = new char[]{'■','□',' '};

        //创建【Scanner类型变量】scanner，用以接收用户输入，并存储到names数组中
        Scanner scanner = new Scanner(System.in);
        System.out.println("本五子棋游戏支持双人对战。\n" + "请输入黑子方玩家姓名：");
        names[0] = scanner.nextLine();
        System.out.println("请输入白子方玩家姓名：");
        names[1] = scanner.nextLine();
        System.out.println("黑子方玩家姓名为：" + names[0] + ".白子方玩家姓名为：" + names[1]);

        //2 - 输入棋盘大小并做范围检测：用户输入棋盘大小。棋盘要大于5，小于100。
        //创建【布尔类型变量】play，作为开关
        boolean play = false;
        while(!play) {
            System.out.println("请输入棋盘大小.注：棋盘应大于5，小于100.");
            int chessBoardScope = scanner.nextInt();
            if (chessBoardScope < 5 || chessBoardScope > 100) {
                System.out.println("输入的棋盘范围错误，请重新输入！");
            }else{
                play = true;
                //定义两个变量qiziMoveLine、qiziMoveColumn用以保存黑白方下子的行、列（共用）
                int qiziMoveLine = 0;
                int qiziMoveColumn = 0;

                //轮流记录黑白双方的落子，并把每一轮下完子的棋盘实况打印出来
                System.out.println("请" + roleJustMoveName[roleJustMove] + "输入落子的行：");
                qiziMoveLine = scanner.nextInt();
                System.out.println("请" + roleJustMoveName[roleJustMove] + "输入落子的列：");
                qiziMoveColumn = scanner.nextInt();

                /*
                    下面这段程序为【画棋盘】。
                    我的思维就没有臧萌老师的细腻，我直接创建一个size为20+2的二维数组，然后从上到下，从左到右画。
                    我这种【直男思路】造成的问题：在i、j的嵌套for循环中，需要用if...else进行【多层的】条件判断。
                    这样导致程序的逻辑关系变得非常复杂，一旦出了问题，非常难以定位问题所在...最后只能把自己搞的很崩溃。
                 */
            }
        }
    }
}
