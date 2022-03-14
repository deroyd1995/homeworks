package dz2;

public class Compare {
   public static void main(String[] args){

     d3Figure[] figures = {
             new Sphere("Sphere",20.7),
             new Parallelepiped("Cube",15,15,15),
             new Parallelepiped("Rectangular parallelepiped",15,16,14)
     };

     d3Figure bestFigureV = figures[0];
     d3Figure bestFigureS = figures[0];

     for (d3Figure curfigure:figures){
       if (bestFigureV.getV() < curfigure.getV()){
         bestFigureV = curfigure;
       }
     }
     for (d3Figure curfigure:figures){
       if (bestFigureS.getS() < curfigure.getS()){
         bestFigureS = curfigure;
       }
     }

     System.out.println("====================================================");
     for (d3Figure curfigure:figures){
       System.out.printf("%s has area of surface: %.2f %n",curfigure.getName(),curfigure.getS());
     }
     System.out.println("-------------------------------------------------");
     for (d3Figure curfigure:figures){
       System.out.printf("%s has volume: %.2f %n",curfigure.getName(),curfigure.getV());
     }
     System.out.println("-------------------------------------------------");
     System.out.printf("Biggest volume has: %s %n",bestFigureV);
     System.out.printf("Biggest area of surface : %s %n",bestFigureS);
     System.out.println("====================================================");
   }

}
