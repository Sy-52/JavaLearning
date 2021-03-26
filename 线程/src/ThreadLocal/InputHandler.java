package ThreadLocal;

public class InputHandler {
    
    public String getInput(){
        return produceString();
    }

    private String produceString(){
        PerformanceTracker.startPhase();
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < 128; i++){
            int rand = (int)(Math.random() * 1000) % 26;
            int ch = (char)(rand + 'a');
            ret.append(ch);
        }
        PerformanceTracker.endPhase("GetInputData");
        return ret.toString();
    }
}
