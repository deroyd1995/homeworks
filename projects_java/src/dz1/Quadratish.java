package dz1;
import java.util.stream.IntStream;

public class Quadratish {

    public static void main(String[] args) {
        VerniKorni reshit = new VerniKorni();
        Reader read = new Reader();

        String[] Fun = {"1: ax^2+bx+c=0", "2: ax^2+bx=0", "3: ax^2=0"};

        System.out.println("Программа решает квадратное уравнение следующих видов:");
        for (int i = 0; i <= 2; i++) {
            System.out.println(Fun[i]);
        }

        do {
        System.out.println("Введите 1,2 или 3, для выбора типа уравнения, либо введите 0 для завершения.");

        //Program->Reader  (получаем выбранную функцию) = choice
        boolean contains;
            read.readThis();
            int[] a = {1, 2, 3, 0};
            //проверка, входит ли выбор пользователя в заданный интервал
            contains = IntStream.of(a).anyMatch(x -> x == (int) read.getResultF());
            if (contains){
                int choice = (int) read.getResultF();
                if (choice!=0){
                    System.out.printf("Вы выбрали тип квадратного уравнения: %s : ", Fun[choice - 1]);

                    System.out.println("Введите (завершая ввод клавишей Enter) действительные коэффициенты");
                    // 4 - choice = количество итераций для ввода коэффициентов (при функции 1 будет доступно 3 коэффициента)
                    double[] x = new double[3];
                    for (int j = 0; j < (4 - choice); j++) {
                        read.readThis();
                        x[j] = read.getResultF();
                    }
                    // x = {a,b,c}
                    System.out.printf("Ваше уравнение: %sx^2+%sx+%s %n",x[0],x[1],x[2]);
                    //double D = b * b - 4 * a * c;
                    double D =  Math.pow(x[1],2) - 4 * x[0] * x[2];
                    reshit.Reshaem(D, x[0], x[1]);
                      if (reshit.getStatus() == 0) {
                        System.out.printf("D = %.2f %n", D);
                        System.out.println("Нет действительных корней ");
                      }
                      else if (reshit.getStatus() == 2) {
                        System.out.printf("D = %.2f %n", D);
                        System.out.printf("Корни уравнения: x1 = %.2f, x2 = %.2f %n", reshit.getReshenie(), reshit.getReshenie2());
                      }
                      else {
                        System.out.printf("D = %.2f %n", D);
                        System.out.printf("Корень уравнения: x = %.2f %n", reshit.getReshenie());
                      }

                      System.out.println("Желаете ли Вы продолжить работу с программой?");
                      System.out.println("1 - если ДА, 0 - если НЕТ");
                      read.readThis();
                      int decision = (int) read.getResultF();
                        if (decision == 0) {
                          System.out.println("Вы завершили работу с программой");
                          break;
                        }
                        else if (decision == 1){
                          continue;
                        }
                        else {
                          System.out.println("Вы ввели неверное значение. Сломали программу");
                          break;
                        }
                }
                else {
                    System.out.println("Вы завершили работу с программой");
                }
                break;

            }

            else {
                System.out.println("Ошибка: Значение неверное! Повторите ввод:");
            }

        }
        while (true);
    }
}
