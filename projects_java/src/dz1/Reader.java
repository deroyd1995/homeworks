package dz1;
import java.util.Scanner;

public class Reader {
    Scanner in = new Scanner(System.in);
    private static double resultF = 0;
    public Reader(){
    }
    public Reader(double resultF){
        this.resultF = resultF;
    }

    public double getResultF() {
        return resultF;
    }

    public double readThis() {
        Scanner in = new Scanner(System.in);
        choice:
        do {
            if(in.hasNextDouble()){
                resultF=in.nextDouble();
                break choice;
            }
            else {
                System.out.println("Ошибка: Тип значения неверный! Повторите ввод:");
                in.next();
                continue choice;
            }

        }
        while(true);
        return 0;
    }


}
