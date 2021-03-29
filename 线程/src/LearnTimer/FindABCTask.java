package LearnTimer;

import java.util.TimerTask;

public class FindABCTask extends TimerTask {
    @Override
    public void run() {
        try {
            String content = getWebContent();
            if (content.contains("ABC")) {
                System.out.println("Find ABC in " + content);
            } else {
                System.out.println("ABC not found.");
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        // TODO Timer不会帮忙处理异常，如果这里把异常抛出去，程序就直接挂了。所以最好是用try...catch将其包住，出异常时输出到控制台就好。
        //throw new RuntimeException("运行时出异常啦！");
    }

    private String getWebContent() {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 4096; i++){
            int rand = (int)(Math.random() * 1000) % 26;
            char ch = (char)(rand + 'A');
            ret.append(ch);
        }
        return ret.toString();
    }
}
