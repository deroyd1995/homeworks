package com.t1;

public class Dog {
    private String name;

    private String breed;

    private int age;


    public Dog(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void print(){
    System.out.println(" name "+ name+" breed "+breed+" age "+age);
}



}
