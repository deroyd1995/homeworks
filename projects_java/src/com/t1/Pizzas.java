package com.t1;

public class Pizzas {
    private double diameter;
    private double price;
    private String name;


    public double getDiameter() {
        return diameter;
    }

    public double getPrice() {
        return price;
    }

    public String name() {
        return name;
    }

    public Pizzas(double diameter, double price, String name) {
        this.diameter = diameter;
        this.price = price;
        this.name = name;
    }

    public double getRadius(){
        double radius = diameter/2;
        return radius;
    }

    public double getSquare(){
        double square = getRadius()*getRadius()*Math.PI;
        return square;
    }



    public double pricePerUnit(){
        return price/getSquare();
    }

    public void printInfo(){
        System.out.println("Name "+name+"Price per unit "+pricePerUnit());
    }

    //@Override

    @Override
    public String toString() {
        return "Pizza{" +
                "diameter=" + diameter +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
