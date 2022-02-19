package dz3;

public class Pallendrome {
    public static void main(String[] args) {
        verniString Verni = new verniString();
        //вызов модуля преобразования строки
        String a ="abcde";
        Verni.Vozvrashaem(a);

        //возврат результата
        System.out.println(Verni.getResult());
    }


}
