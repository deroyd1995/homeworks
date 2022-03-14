package com.t1;
import java.util.*;
public class VectorDemo {
    public static void main(String args[]){
        Vector<String> v=new Vector<>();
        v.add("Ayush");
        v.add("Zayush");
        v.add("Kayush");
        v.add("Rayush");
        v.add("Tanyush");
        v.add("Nikityush");
        v.add("Dashyush");
        System.out.println(v.size());
        v.insertElementAt("Tanec",1);
        System.out.println(v.size());
        System.out.println(v.capacity());
        for(String c:v){
            System.out.println(c);
        }
    }
}

