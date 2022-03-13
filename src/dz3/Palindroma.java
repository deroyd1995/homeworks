package dz3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Palindroma {
    //P.S.: я пытался ускорить алгоритм, чтобы вместо перебора всех подстрок брались только подстрочки по тем буквам, которые встречаются 2 и более раз
    //но в итоге будто бы медленнее работает (может из-за вывода всех палиндромов), или мне так кажется только

    //Функция проверки является ли данная подстрока палиндромом
    public static boolean isPalindrom(String string) {
        boolean result = true;
        int length = string.length();
        //делим строку пополам и ищем проверяем по обе стороны зеркальные элементы
        for (int i = 0; i < length / 2; i++) {
            if (string.charAt(i) != string.charAt(length - i - 1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    //Функция поиска позиций элемента в строке
    public static List<Integer> getPositions(String substring, String letter) {
            List<Integer> indexes = new ArrayList<>();
            int index = substring.indexOf(letter);
            while (index >= 0) {
                indexes.add(index);
                index = substring.indexOf(letter, index + 1);
            }
            return indexes;
    }


    //Функция получения уникальных букв в строке
    public static String getDistinctCharString(String s1) {
        Set<Character> set = new HashSet<>(s1.length());

        char[] chars1 = s1.toCharArray();

        for (char c : chars1) {
            set.add(c);
        }

        StringBuilder sb = new StringBuilder(set.size());

        for (char c : set) {
            sb.append(c);
        }

        return sb.toString();
    }


    //Функция поиска палиндромов
    public static String findPalindroms(String source) {
        List<String> allpalindroms = new ArrayList<>();                   //лист на вывод
        List<Integer> positions;       //лист с позициями буквы в строке
        int lendistinct = getDistinctCharString(source).length(); //длина строки из уникальных символов
        int lenMaxPal = 1;
        String maxP = "--- Палиндрома не найдено";

        for (int i=0; i<=lendistinct-1; i++)
        {
            char  distChar = getDistinctCharString(source).charAt(i);
            positions = getPositions(source,String.valueOf(distChar));
            //Тут сравниваем длину массива индексов, на которых встретили элемент. Если меньше или равно 1 - не проверяем на палиндром по этой букве
            if (positions.size()> 1){
                //Тут перебираем все значения между собой из листа integer
                for (int j0=0; j0<positions.size()-1; j0++){
                    for (int j1=1+j0; j1<=positions.size()-1; j1++){
                        String sub = source.substring(positions.get(j0),positions.get(j1)+1);
                        int length = sub.length();

                        if (isPalindrom(sub)){
                            allpalindroms.add(sub);
                           if (length>lenMaxPal){
                               lenMaxPal = length;
                               maxP = sub;
                           }
                        }

                    }
                }

            }
           if (i == lendistinct-1){System.out.println("--- Все палиндромы в строке: "+allpalindroms.stream()
                   .distinct()
                   .toList());}
        }
        return maxP;
    }

       //точка входа
        public static void main(String[] args) {
            String testing = "ооофывоуйуцйуыфоойуойцоараоурурурурафоаоооофывоуйуцйуыфоойуойцоараоурурурурафоаоооофывоуйуцйуыфоойуойцоараоурурурурафоао";  //тестируемая строка

            System.out.printf("--- Является ли палиндромом строка '"+"%s"+"' : %b %n",testing,isPalindrom(testing));
            System.out.println("--- Палиндром максимальной длины: "+findPalindroms(testing));

        }
    }






















