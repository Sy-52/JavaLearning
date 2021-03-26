package DeadLock;

public class Resources {
    private Object resourcePrinter = new Object();
    private Object resourceInput = new Object();

    public Object getResourcePrinter(){return resourcePrinter;}
    public Object getResourceInput(){return resourceInput;}
}
