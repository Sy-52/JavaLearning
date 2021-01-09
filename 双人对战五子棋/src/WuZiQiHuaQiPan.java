public class WuZiQiHuaQiPan {
    public static void main(String[] args) {
        /*
            仿写臧萌老师的画棋盘程序。
            臧萌老师画棋盘程序的精妙之处在于：
            1、第一行和最后一行是相同的【字符串】，println即可
            2、第二行开始到倒数第二行都具有某种规律，捕捉到这种数学规律，然后用两层嵌套的循环println即可
         */
        //抽象出一个长度单位size，以后就不用去下面的程序挨个挨个调整数字了。
        int size = 20;

        //创建长度为3的char类型数组，用以保存【已经下了的】棋子的符号（注意该创建数组的语法）
        char[] qizi = new char[3];
        qizi[0] = '●';
        qizi[1] = '○';
        qizi[2] = ' ';

        //再创建长度为3的char类型数组，用以保存【刚移动过的】棋子的符号(注意该创建数组的语法：1、创建赋值二合一 2、长度不可变，和上面一样)
        //在本程序中下面这句没有用到，需要进一步完善功能
        char[] qiziJustMove = new char[]{'■','□',' '};

        //做一个棋盘的【头尾字符串】
        String header = "\t";
        for(int i = 0; i < size; i++){
            header += (i + 1) + "\t";
        }
        //在第一行输出【头尾字符串】
        System.out.println(header);

        //把中间的棋子，用数字表示，装在一个整形二维数组中
        int[][] qipan = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                //下面这个【数字3】可以抽象为qizi.length，这样便于修改
                qipan[i][j] = (i + j) % 3;
            }
        }

        //输出中间的【棋局实况】，也就是1~size行
        for(int i = 0; i < size; i++){
            String line = " " + (i + 1) + "\t";
            for(int j = 0; j < size; j++){
                /*
                    下面一行程序，臧萌老师的思路非常的精妙，我写的是简化版本。
                    如果录入了黑白子双方输入的行、列并保存到justMoveLine、justMoveColumn的话，
                    通过一个三元表达式就可以把刚走过的棋子、已经走过的棋子、空格分别用不同的符号打印出来。
                 */
                //下面的qizi其实可以直接写为new char[]{'●','○',' '};
                char[] arrayToUse = qizi;
                line += arrayToUse[qipan[i][j]] + "\t";
            }
            System.out.println(line + (i + 1));
        }

        //尾部输出头尾字符串
        System.out.println(header);
    }
}
