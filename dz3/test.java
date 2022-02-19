package dz3;

import java.util.Arrays;


public class test {

    public static void main(String[] args) {

        //основная строка
        String str = "eqnтптототптпоакиремас";

        StringBuilder plain = new StringBuilder(str);
        StringBuilder stra = plain.reverse();
        //строка наоборот
        String str2 = stra.toString();

        // Character array from String
        char[] charArray = str.toCharArray();
        char[] charArray2 = str2.toCharArray();
        char[] result = new char[charArray.length];
        String[] strresult = new String[charArray.length];


        //charArray[i]  0:length-1 (1:length-1)
        //charArray2[j] length-1:length-2 (length-1:0)
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
            int fPos = 0; //найденная позиция элемента


            for (int j = p; j <= charArray.length-1; j++){
                if ((charArray2[i] == charArray[j])&&(found == false)) {
                    if (charArray.length - 1 - j != i) {
                        fPos = j;
                        //если мы до этого нашли элемент где-то в строке, но не на той же зеркальной позиции
                        int p1 = charArray.length - 1 - i;
                        for (int i1 = charArray2.length - 1 - fPos; i1 <= charArray2.length - 1; i1++) {
                            for (int j1 = p1; j1 <= fPos; j1++) {
                                if (charArray2[i1] == charArray[j1]) {
                                    p1 = p1 + 1;
                                    result[i1] = charArray2[i1];
                                    break;
                                }
                                else{
                                    Arrays.fill(result, '\u0000');
                                    found = true;
                                    continue here;
                                }
                            }

                        }
                        strresult[i] = String.valueOf(result);
                        found = true;
                        continue;
                    }
                }
                else if ((j == charArray.length-1)&&(found == false))
                {
                    //если во всей строке элемент стоит только на зеркальной позиции - перебираем в дальнейшем без него
                    p=p+1;
                 }

            }
        }




        //result = result.replace("\u0000", "");
        //String strresult1 = String.valueOf(strresult[7]);
        //System.out.println(strresult1.replace("\u0000", ""));

        if (String.valueOf(result).replace("\u0000", "") == ""){
            System.out.println("Палиндрома не найдено");
        }
        else{
            System.out.println(String.valueOf(result).replace("\u0000", ""));
        }

    }
}


