package com.t1;

public class Execute {
   public static void main(String[] args){
     SumMachine machine = new SumMachine();
     Dog doggy = new Dog("Mike","Dolmatinec",5);
     machine.add(1);
     doggy.print();
     System.out.println(machine.getState());
     machine.add(1,2);
     System.out.println(machine.getState());
     machine.add(1,2,4);
     System.out.println(machine.getState());


     Pizzas margarita = new Pizzas(31,300,"Margarita");
     Pizzas fourCheese = new Pizzas(50,700,"FourCheese");
     Pizzas pepperoni = new Pizzas(40,600,"Pepperoni");

     margarita.printInfo();
     fourCheese.printInfo();
     pepperoni.printInfo();
   }

}
