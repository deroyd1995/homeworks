package dz2;

public class Parallelepiped extends d3Figure {
    private double a;
    private double b;
    private double c;

    public double getA() {
        return a;
    }
    public double getB(){
        return b;
    }
    public double getC(){
        return c;
    }

    public Parallelepiped(String name, double a, double b, double c) {
        super(name);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getS(){
        return 2.0*(getA()*getB()+getB()* getC()+getA()* getC());
    }

    @Override
    public double getV(){
        return getA()*getB()* getC();
    }

    @Override
    public String toString() {
        return "Parallelepiped{" +
                "a=" + getA() +
                ", b=" + getB() +
                ", c=" + getC() + ", name='" + getName() + '\'' +
                '}';
    }
}
