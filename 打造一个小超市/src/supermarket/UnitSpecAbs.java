package supermarket;

public abstract class UnitSpecAbs {
    private double spec;
    private String producer;

    public UnitSpecAbs(double spec, String producer){
        this.spec = spec;
        this.producer = producer;
    }

    public abstract double getNumSpec();

    public abstract String getProducer();

    public double getSpec(){return this.spec;}

    public String getProduceStr(){return this.producer;}
}
