import AI.AI;

import java.util.Scanner;

public class AIAppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("您正在使用人工不智能聊天小程序...");
        AI ai = new AI();
        while(true){
            String question = scanner.nextLine();
            if("exit".equals(question)){
                System.out.println("和您聊天非常愉快！再见！");
                break;
            }
            String answer = ai.answer(question);
            System.out.println(answer);
        }
    }
}
