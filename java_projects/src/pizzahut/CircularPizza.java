package pizzahut;

public class CircularPizza extends Pizza {
    private double diameter;

    public double getDiameter() {
        return diameter;
    }
    public double getRadius(){
        return getDiameter()/2;
    }


    public CircularPizza(double price, String name, double diameter) {
        super(price, name);
        this.diameter = diameter;
    }


    @Override
    public double getSquare(){
        return getRadius()*getRadius()*Math.PI;
    }

    @Override
    public String toString() {
        return "CircularPizza{" +
                "diameter=" + getDiameter() +
                ", price=" + getPrice() +
                ", name='" + getName() + '\'' +
                '}';
    }


}
