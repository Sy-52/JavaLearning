import java.util.Scanner;

public class GuessNumber {
    /* 玩法：
        1、用户输入猜测数字的范围，程序录入起点和终点
        2、程序告知用户剩余猜测的次数、猜测的范围，并让用户输入猜测数字并录入
        3、程序判断用户猜测数字与随机生成数的大小关系，并告知用户。
           如果没有猜对，在规定猜测次数内，告知大小关系并让用户重新猜测；如果超过规定次数，告知用户游戏失败并揭秘正确数字。
           如果猜对，恭喜用户。并进入下一轮游戏。
     */
    public static void main(String[] args) {
        //录入用户指定的起始范围、终止范围
        System.out.println("猜数字游戏开始！请输入猜数字游戏范围的起始数字：");
        Scanner in = new Scanner(System.in);
        int guessNum = in.nextInt();
        int rangeStart = guessNum;
        System.out.println("请输入猜数字游戏范围的终止数字：");
        guessNum = in.nextInt(); 
        int rangeEnd = guessNum;
        
        // 随机数、总猜测次数、游戏开关、玩游戏的总轮次、总共正确的轮数
        int num = 0;
        double randNum = Math.random();
        int guessTotal = 5;
        boolean stopGame = false;
        int totalGameCount = 0;
        int totalCorrectCount = 0;

        //开始游戏
        while (!stopGame) {
             //如果用户上一轮猜测次数用完也没有猜对...那么安慰用户，并公布正确数字
            if(guessTotal == 0){
                totalGameCount++;
                System.out.println("很遗憾您没有猜中...不要灰心，请继续努力！正确的数字是:" + num);
                System.out.println("您一共玩了" + totalGameCount + "轮，猜中了" + totalCorrectCount + "次。");
            }
            
            //在起始范围与终止范围间生成一个随机数,注意随机数生成采用的数学方法
            randNum = Math.random();
            int mod = rangeEnd - rangeStart;
            num = (int) (randNum * rangeEnd * 100) % mod;
            num += rangeStart;

            //一轮游戏结束之后，先重置总猜测次数。否则如果上一轮猜测次数还有剩余的话，下一轮打印的guessTotal会出错
            guessTotal = 5;
            System.out.println("剩余猜测次数为" + guessTotal + "，请输入你要猜测的数字。猜测范围为：(" + rangeStart + "," + rangeEnd + ").输入-1结束游戏。");
            guessNum = in.nextInt();

            while (guessTotal > 0) {
                //每轮猜测之前先做判断，如果用户输入-1需结束游戏。即从该while体中break出去.
                if(guessNum == -1){
                    stopGame = true;
                    System.out.println("用户选择结束游戏！");
                    break;
                }
                if (guessNum > num) {
                    guessTotal--;
                    System.out.println("您猜测的数字比目标数字大！请重新猜测。剩余猜测次数为" + guessTotal + "次。");
                    if (guessTotal != 0)guessNum = in.nextInt();
                } else if (guessNum < num) {
                    guessTotal--;
                    System.out.println("您猜测的数字比目标数字小！请重新猜测。剩余猜测次数为" + guessTotal + "次。");
                    if (guessTotal != 0)guessNum = in.nextInt();
                } else {
                    //此处用break而不用guessTotal - 1。否则会出现在guessTotal使用完之前反复"..正确！"的情况。
                    totalGameCount++;
                    totalCorrectCount++;
                    System.out.println("您猜测的数字正确！开始新一轮游戏！");
                    System.out.println("您一共玩了" + totalGameCount + "轮，猜中了" + totalCorrectCount + "次。");
                    break;
                }
            }
        }
    }
}
