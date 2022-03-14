package com.t1;
import java.util.*;
public class StackDemo {
    public static void main(String args[]){
        Stack<String> stack = new Stack<>();
        stack.push("Ayush");
        stack.push("Garvit");
        stack.push("Jamalay");
        stack.push("Eshmesh");
        stack.push("Denya");
        stack.push("Gamira");
        System.out.println(stack.pop());
    }
}
