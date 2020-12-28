import java.util.Scanner;

public class WuZiQi {
    public static void main(String[] args) {
        //第一部分：允许双人对战，可以输入每一方的名字
        //创建一个数组变量names来存储黑白方玩家姓名
        String[] names = new String[2];
        //创建一个变量scanner来接收用户输入
        Scanner scanner = new Scanner(System.in);
        System.out.println("本五子棋游戏支持双人对战。\n" + "请输入黑子方玩家姓名：");
        names[0] = scanner.nextLine();
        System.out.println("请输入白子方玩家姓名：");
        names[1] = scanner.nextLine();
        System.out.println("黑子方玩家姓名为：" + names[0] + ".白子方玩家姓名为：" + names[1]);

        //第二部分：允许输入棋盘大小，棋盘为方形。棋盘要大于5，小于100。超过100无法对齐。
        boolean cont = false;
        while(!cont) {
            System.out.println("请输入棋盘大小.注：棋盘应大于5，小于100.");
            int chessBoardScope = scanner.nextInt();
            if (chessBoardScope < 5 || chessBoardScope > 100) {
                System.out.println("输入的棋盘范围错误，请重新输入！");
            }else{
                cont = true;
                //开始画棋盘
                int[][] chessBoard = new int[chessBoardScope + 2][chessBoardScope + 2];
                for(int i = 0; i < chessBoardScope + 2; i++){
                    for(int j = 0; j < chessBoardScope + 2; j++){
                        if(i == 0 || i == chessBoardScope + 1){
                            //边框
                            if(j == 0){
                                System.out.print(" \t");
                            }else if(j == chessBoardScope + 1){
                                System.out.print(" \t\n");
                            }else{
                                chessBoard[i][j] = j;
                                System.out.print(chessBoard[i][j] + "\t");
                            }
                        }else {
                            if(j == 0){
                                System.out.print(i + "\t");
                            }else if(j == chessBoardScope + 1){
                                System.out.print(i + "\t\n");
                            }else{
                                //棋盘，但是没有存储具体i、j坐标到二维数组
                                System.out.print(' ' + "\t");
                            }
                        }
                    }
                }
            }
        }
    }
}
