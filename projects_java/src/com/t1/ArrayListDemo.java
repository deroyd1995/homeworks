package com.t1;
import java.util.*;

public class ArrayListDemo {
    public static void main(String args[]){


        ArrayList<String> list = new ArrayList<String>();
        list.add("11 Ваня");
        list.add("1 Анна");
        list.add("9 Мага");
        list.add("5 Ваня");
        list.add("3 Сана");
        list.add("2 Ваня");
        list.add("1 Йуу");
        list.remove("Ваня");

       for (String s:list){
            System.out.println(s);
        }


    }
}
