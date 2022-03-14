package pizzahut;

public abstract class Pizza {
    private double price;
    private String name;

    public Pizza(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public abstract double getSquare();

    public double pricePerUnit(){
        return getPrice()/getSquare();
    }
}
