package dz3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class test {

    public static void main(String[] args) {

        //основная строка
        String str = "уеуеуоаао";

        StringBuilder plain = new StringBuilder(str);
        StringBuilder stra = plain.reverse();
        //строка наоборот
        String str2 = stra.toString();

        // Character array from String
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        char[] result = new char[charArray.length];
       // String[] strresult = new String[charArray.length];
        List<String> strresult = new ArrayList<>();    //

        //charArray[j]  0:length-1 (1:length-1)
        //charArray2[i] length-1:length-2 (length-1:0)
        //самерикаоптототп ->charArray2
        //...............length-1
        //птототпоакиремас ->charArray перебираю
        //j.....fPos
        boolean found = false; //если нашли элемент не на той же позиции в строке, где перебираемый
        int p = 0;

        here:
        for (int i = charArray2.length -1; i >=0 ; i--) {
            if (found == true){
                break;
            }

            //int[] series = {4,2};
           // series = ArrayUtils.add(series, 3); // series is now {4,2,3}




            List<Integer> fPos = new ArrayList<Integer>();    //найденная позиция элемента
            fPos.add(0);

            for (int j = p; j <= charArray.length-1; j++){

                if ((charArray2[i] == charArray[j])&&(charArray.length - 1 - j != i)) {
                    //int[] series = {4,2};
                    //series = ArrayUtils.add(series, 3); // series is now {4,2,3}
                    if (fPos.get(0) != 0){
                        fPos.add(j); //собираем диапазоны нахождения элементов
                    }
                    else {
                        fPos.set(0, j);
                    }

                }

                //проверка дошли ли мы до конца
                if (j == charArray.length-1)
                {
                    //если во всей строке элемент стоит только на зеркальной позиции - перебираем в дальнейшем без него
                    if (fPos.get(0) == 0){
                        p=p+1;
                    }

                    else {

                        start:
                        for (int iter=0;iter<=fPos.size()-1;iter++)
                        {
                            //если мы до этого нашли элемент где-то в строке, но не на той же зеркальной позиции
                            int p1 = charArray.length - 1 - i;
                            for (int i1 = charArray2.length - 1 - fPos.get(iter); i1 <= charArray2.length - 1; i1++) {
                                for (int j1 = p1; j1 <= fPos.get(iter); j1++) {
                                    if (charArray2[i1] == charArray[j1]) {
                                        p1 = p1 + 1;
                                        result[i1] = charArray2[i1];
                                        break;
                                    }
                                    else{
                                        Arrays.fill(result, '\u0000');
                                        continue start;
                                    }
                                }

                            }
                        }

                    }
                    strresult.add(String.valueOf(result));
                }






            }

        }





        boolean atleastone = false;
        for (int i=0;i<=strresult.size()-1;i++){
            if ((strresult.isEmpty()==false)^(String.valueOf(strresult.get(i)).replace("\u0000", "") == "")){
                System.out.println(String.valueOf(strresult.get(i)).replace("\u0000", ""));
                atleastone=true;
            }

        }
        if (atleastone == false){
            System.out.println("Палиндрома не найдено");
        }

    }
}


