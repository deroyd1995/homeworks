package dz2;

public abstract class d3Figure {
    private String name;

    public d3Figure(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract double getS();
    public abstract double getV();

}
