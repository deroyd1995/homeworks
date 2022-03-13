package dz2;

public class Sphere extends d3Figure {
    private double diameter;

    public double getDiameter() {
        return diameter;
    }
    public double getRadius(){
        return getDiameter()/2;
    }

    public Sphere(String name, double diameter) {
        super(name);
        this.diameter = diameter;
    }


    @Override
    public double getS(){
        return 4.0*Math.PI*Math.pow(getRadius(),2);
    }

    @Override
    public double getV(){
        return (4.0/3.0)*Math.PI*Math.pow(getRadius(),3);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "radius=" + getRadius() +
                ", name='" + getName() + '\'' +
                '}';
    }
}
