package pizzahut;

public class RectangularPizza extends Pizza {

    private double a;
    private double b;

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public RectangularPizza(double price, String name, double a, double b) {
        super(price, name);
        this.a = a;
        this.b = b;
    }

    @Override
    public double getSquare(){
        return a*b;
    }

    @Override
    public String toString() {
        return "RectangularPizza{" +
                "price=" + getPrice() +
                ", name='" + getName() + '\'' +
                ", a=" + getA() +
                ", b=" + getB() +
                '}';
    }
}
