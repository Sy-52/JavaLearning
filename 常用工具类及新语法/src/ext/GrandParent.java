package ext;

public class GrandParent {
    private int num;

    public GrandParent(int num){this.num = num;}

    public GrandParent(){this(0);}

    public int getNum(){return num;}
}
