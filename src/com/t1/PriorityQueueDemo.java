package com.t1;

import java.util.PriorityQueue;

class StudentAgeComparator implements Comparable<StudentAgeComparator>{
    private int Class;
    private String Name;

    public StudentAgeComparator(){
    }
    public StudentAgeComparator(int Class, String Name){
        this.Class =Class;
        this.Name =Name;
    }


    public int getClas() {
        return Class;
    }

    public void setClas(int aClass) {
        Class = aClass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "studen: " +
                "Class=" + Class +
                ", Name='" + Name + '\'' +
                '}';
    }

    @Override
    public int compareTo(StudentAgeComparator p){
        return Name.compareTo(p.getName());
    }
}

public class PriorityQueueDemo {
    public static void main(String args[]){
        PriorityQueue<StudentAgeComparator> queue = new PriorityQueue<StudentAgeComparator>();
        queue.add(new StudentAgeComparator(1,"Anna"));
        queue.add(new StudentAgeComparator(11,"Nikita"));
        queue.add(new StudentAgeComparator(3,"Danila"));
        queue.add(new StudentAgeComparator(5,"Serega"));
        queue.add(new StudentAgeComparator(11,"Anna"));
        queue.add(new StudentAgeComparator(6,"Nikita"));
        System.out.println("head "+queue.element());
        System.out.println("head "+queue.peek());
        System.out.println("Iterating the queue elements: ...");
        for (StudentAgeComparator c:queue){
            System.out.println(c);
        }
        queue.remove();
        queue.poll();
        System.out.println("after removing two elements ");
        for (StudentAgeComparator c:queue){
            System.out.println(c);
        }
    }
}
