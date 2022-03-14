package pizzahut;

public class Execute2 {
   public static void main(String[] args){

     Pizza[] pizzas = {
             new CircularPizza(300,"Margarita",30),
             new CircularPizza(250,"FourCheeses",50),
             new CircularPizza(350,"Pepperoni",40),
             new RectangularPizza(400,"Salami",20,30),
             new RectangularPizza(250,"Chicken",25,35)
     };

     Pizza bestPizza = pizzas[0];

     for (Pizza curPizza:pizzas){
       if (bestPizza.pricePerUnit() > curPizza.pricePerUnit()){
         bestPizza = curPizza;
       }
     }

     System.out.printf("Today we order: %s %n",bestPizza);
   }

}
